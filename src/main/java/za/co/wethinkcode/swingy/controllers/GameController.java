package za.co.wethinkcode.swingy.controllers;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.factories.*;
import za.co.wethinkcode.swingy.models.artefacts.Artefact;
import za.co.wethinkcode.swingy.models.map.Coordinates;
import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.models.playables.Villain;
import za.co.wethinkcode.swingy.views.IDisplay;
import za.co.wethinkcode.swingy.views.consoleViews.consoleDisplay;
import za.co.wethinkcode.swingy.views.guiViews.guiDisplay;

import java.util.ArrayList;
import java.util.Random;

@Getter
@Setter

public class GameController
{
    private enum gameStage{START, SELECTION, CREATION, ERRORS, PLAY, RUN_FIGHT, GAMEOVER, QUIT};
    private enum creationStage{HERO_TYPE, NAME_PROMPT, CREATION_TYPE, STATS};
    private gameStage currentStage;
    private DBController dbController;
    private creationStage creatingStage;
    private Coordinates posBeforeBattle;
    private Player hero;
    private ArrayList<Player> heroes;
    private ArrayList<Villain> villains;
    private ArrayList<String> errors;
    private Map map;
    private String battleReport;
    private IDisplay display;
    private boolean gameContinues;
    private boolean heroWon;
    static String type = "", name="";

    public GameController(String view)
    {
        if (view.equals("console"))
            display = new consoleDisplay(this);
        else
            display = new guiDisplay();
        currentStage = gameStage.START;
        creatingStage = creationStage.HERO_TYPE;
        gameContinues = true;
        heroWon = false;
        heroes = new ArrayList<>();
        villains = new ArrayList<>();
        errors = new ArrayList<>();
        map = null;
        dbController = new DBController(this);
        dbController.createDB();
        dbController.initDB();
    }

    public boolean isGameContinues()
    {
        return (gameContinues);
    }

    private void createMap(int level)
    {
        map = MapFactory.newMap(level);
    }

    private void updateMap()
    {
        createMap(hero.getLevel());
        int x, y;
        for(Villain villain : villains)
        {
            x = villain.getCoordinates().getX();
            y = villain.getCoordinates().getY();
            map.getGrid()[y][x] = 2;
        }
        x = hero.getCoordinates().getX(); y = hero.getCoordinates().getY();
        map.getGrid()[y][x] = 1;
    }

    private void createVillains()
    {
        Random rand = new Random();
        int numVillainsRange =  map.getSize();
        int numVillains = rand.nextInt(numVillainsRange) + 1;
        Coordinates coordinates;
        for (int i = 0; i < numVillains; i++)
        {
            do
            {
                int x = rand.nextInt(map.getSize()), y = rand.nextInt(map.getSize());
                coordinates = CoordinateFactory.newCoordinates(x, y, map);
            }
            while(coordinates == null);
            String names[] = {"Grodd","Skeevy", "Emilps"}, phrases[] = {"I am Grodd", "A Snitch is what i am", "Brute"};
            int nameIndex = rand.nextInt(3);
            String name = names[nameIndex] + i;
            villains.add(VillainFactory.newVillain(name, phrases[nameIndex], coordinates));
        }
    }

    public void createCustomHero(String type, String name, int level, int atk, int def, int hp)
    {
        int exp = (int)(level*1000 + (Math.pow(level - 1, 2) *450));
        PlayerFactory.setId(PlayerFactory.getId()+ 1);
        createMap(level);
        Coordinates coordinates = CoordinateFactory.newCoordinates(map.getSize() / 2,map.getSize() / 2, map);
        hero = PlayerFactory.customPlayer(PlayerFactory.getId(), name, type, level, exp, atk, def, hp, coordinates, this);
    }

    public void consoleCustomHero(String input)
    {
        while (currentStage == gameStage.CREATION)
        {
            if (creatingStage == creationStage.HERO_TYPE)
            {
                if (input.equals("1"))
                    type = "Swordsman";
                else if (input.equals("2"))
                    type = "Gunman";
                else
                    type = "KungFuMaster";
                creatingStage = creationStage.NAME_PROMPT;
            }
            else if (creatingStage == creationStage.NAME_PROMPT)
            {
                creatingStage = creationStage.CREATION_TYPE;
                ((consoleDisplay) display).displayHeroNamePrompt();
            }

            else if (creatingStage == creationStage.CREATION_TYPE)
            {
                creatingStage = creationStage.STATS;
                name = input;
                ((consoleDisplay) display).displayDefaultOrCustomHero();
            }

            else
            {
                if (input.equals("1"))
                    ((consoleDisplay)display).displayHeroStatsPrompt(name, type);
                else
                    this.createDefaultHero(type, name);
                if (errors.size() !=0)
                    currentStage = gameStage.ERRORS;
                else
                {
                    createVillains();
                    updateMap();
                    currentStage = gameStage.PLAY;
                    creatingStage = creationStage.HERO_TYPE;
                }
            }
        }
    }

    public void createDefaultHero(String type, String name)
    {
        Coordinates coordinates = CoordinateFactory.newCoordinates(0,0, map);
        hero = PlayerFactory.defaultPlayer(name, type, coordinates, this);
        createMap(hero.getLevel());
        hero.setCoordinates(CoordinateFactory.newCoordinates(map.getSize() / 2,map.getSize() / 2, map));
    }

    private String roundResults(Player one, Player two, boolean bothAttack)
    {
        String result;
        if (bothAttack)
        {
            result = String.format("\n%s %s attacked %s %s with %d damage points.\n", one.getType(), one.getName(),
                                    two.getType(), two.getName(), one.getAtk());
            result += String.format("%s %s attacked %s %s with %d damage points.\n", two.getType(), two.getName(),
                                    one.getType(), one.getName(), two.getAtk());
            result += String.format("%s %s received %d damage points.\n", one.getType(), one.getName(),
                                    two.getAtk());
            result += String.format("%s %s received %d damage points.\n", two.getType(), two.getName(),
                                    one.getAtk());
        }
        else
        {
            result = String.format("\n%s %s attacked %s %s with %d damage points.\n", one.getType(), one.getName(),
                    two.getType(), two.getName(), one.getAtk());
            result += String.format("%s %s defended %s %s with %d defense points.\n", two.getType(), two.getName(),
                    one.getType(), one.getName(), two.getAtk());
            result += String.format("%s %s received %d damage points.\n", two.getType(), two.getName(),
                    one.getAtk() - two.getDef());
        }
        return (result);
    }

    private boolean enemyEncountered()
    {
        for (Villain villain : villains)
        {
            if (villain.getCoordinates().getX() == hero.getCoordinates().getX() &&
                villain.getCoordinates().getY() == hero.getCoordinates().getY()
            )
                return (true);
        }
        return (false);
    }

    private String simulateFight(Villain villain)
    {
        Random rand = new Random();
        String report = hero.getName() + "(" + hero.getType() + ")  vs " + villain.getName() + "(" +
                        villain.getCatchPhrase() +")\n\n";
        while (hero.getHp() != 0 && villain.getHp() != 0)
        {
            int heroAction = rand.nextInt(2), villainAction = rand.nextInt(2);
            if (heroAction == 1 && villainAction == 1)
            {
                report += roundResults(hero, villain, true);
                hero.setHp(hero.getHp() - villain.getAtk());
                villain.setHp(villain.getHp() - hero.getAtk());
            }
            else if (heroAction == 1 && villainAction == 0)
            {
                report += roundResults(hero, villain, false);
                villain.setHp(villain.getHp() - hero.getAtk());
            }
            else if (heroAction == 0 && villainAction == 1)
            {
                report += roundResults(villain, hero,false);
                hero.setHp(hero.getHp() - villain.getAtk());
            }
        }
        if (hero.getHp() != 0)
        {
            report += "\n You won the fight!!!\n";
            hero.setExp(hero.getExp() + (10 * villain.getLevel()));
            levelUp();
            pickUpArtefact();
            villains.remove(villain);
        }
        else
        {
            report += "\n You lost!!!\n";
            currentStage = gameStage.GAMEOVER;
        }
        return (report);
    }

    private void checkPlayerWon()
    {
        if (hero.getLevel() >=10)
            heroWon = true;
        if (hero.getCoordinates().getX() == 0 || hero.getCoordinates().getY() == 0)
            heroWon = true;
        else if (hero.getCoordinates().getX() == map.getSize() - 1 || hero.getCoordinates().getY() == map.getSize() - 1)
            heroWon = true;
        else
            heroWon = false;
    }

    private boolean isMoveInput(String input)
    {
        return (input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d"));
    }

    private void movePlayer(String direction)
    {
        posBeforeBattle = hero.getCoordinates();
        if (direction.equals("w"))
            hero.getCoordinates().setY(hero.getCoordinates().getY() - 1);
        else if (direction.equals("a"))
            hero.getCoordinates().setX(hero.getCoordinates().getX() - 1);
        else if (direction.equals("s"))
            hero.getCoordinates().setY(hero.getCoordinates().getY() + 1);
        else
            hero.getCoordinates().setX(hero.getCoordinates().getX() + 1);
    }

    private void processOption(String input)
    {
        if (input.equals("q"))
            currentStage = gameStage.QUIT;
        else
            switchDisplays();
    }

    private void levelUp()
    {
        int incr = 0, tmpExp = 0;
        do
        {
            tmpExp = (int) ((hero.getLevel() + incr) * 1000 + (Math.pow((hero.getLevel() + incr) - 1, 2) * 450));
            incr++;
        }
        while (tmpExp < hero.getExp());
        hero.setLevel(hero.getLevel() + incr - 1);
    }

    private boolean forcedFight()
    {
        Random rand = new Random();
        int roll = rand.nextInt(2);
        return (roll == 1);
    }

    public void receiveUserInput(String input)
    {
        switch (currentStage)
        {
            case START:
                if (input.equals("1"))
                    currentStage = gameStage.CREATION;
                else if (input.equals("2"))
                    currentStage = gameStage.SELECTION;
                else
                    System.exit(1);
                break;
            case CREATION:
                if (display instanceof consoleDisplay)
                    consoleCustomHero(input);//todo needs an else for guiDisplay. also don't forget the error stuff
                break;
            case SELECTION:
                if (input.equals("q"))
                     System.exit(1);
                else
                {
                    retrieveHeroes();
                    retrieveHero(Integer.parseInt(input));
                    //todo before we create the villains check errors;
                    createVillains();
                    updateMap();
                    currentStage = gameStage.PLAY;
                }
                break;
            case ERRORS:
                //todo
                break;
            case PLAY:
                if (isMoveInput(input))
                {
                    movePlayer(input);
                    updateMap();
                    if (enemyEncountered())
                        currentStage = gameStage.RUN_FIGHT;
                    checkPlayerWon();
                    if (heroWon)
                        currentStage = gameStage.GAMEOVER;
                }
                else
                    processOption(input);
                break;
            case RUN_FIGHT:
                //todo
                break;
            case GAMEOVER:
                //todo
                break;
            case QUIT:
                //todo
                break;
            default:
                gameContinues = false;
                break;
        }
    }

    private void switchDisplays()
    {
        if (display instanceof consoleDisplay)
        {
            ((consoleDisplay)display).closeInputStream();
            display = new guiDisplay();
        }
        else
            display = new consoleDisplay(this);
    }

    public void playGame()
    {
        switch (currentStage)
        {
            case START:
                display.displayStartView();
                break;
            case CREATION:
                display.displayCreatePlayerView();
                break;
            case SELECTION:
                display.displayPlayerSelectionView(heroes);
                break;
            case ERRORS:
                display.displayErrors(errors);
                break;
            case PLAY:
                display.displayPlayView();
                break;
            case RUN_FIGHT:
                display.displayFightOrRunPrompt();
                break;
            case GAMEOVER:
                display.displayGameOver(heroWon);
                break;
            case  QUIT:
                display.displayQuitDialogue();
            default:
                gameContinues = false;
                break;
        }
    }

    private void saveHero()
    {
        dbController.recordHero(hero);
    }

    private void retrieveHeroes()
    {
        heroes = null;
        heroes = dbController.getPlayers();
    }

    private void retrieveHero(int id)
    {
        for (Player player : heroes)
        {
            if (player.getId() == id)
            {
                hero = player;
                break;
            }
        }
        if (hero == null)
            errors.add("Error : Invalid id supplied.");
    }

    private void pickUpArtefact()
    {
        Random rand = new Random();
        int chances = rand.nextInt(10);
        if (chances >= 7)
        {
            String types[] = {"Weapon", "Armor", "Helm"};
            int index = rand.nextInt(3);
            int value = rand.nextInt(100 - 50 + 1) + 50;
            Artefact  artefact = ArtefactFactory.newArtefact(value, types[index]);
            if (index == 0)
                hero.setAtk(hero.getAtk() + value);
            else if (index == 1)
                hero.setDef(hero.getAtk() + value);
            else
                hero.setHp(hero.getHp() + value);
            hero.getArtefacts().add(artefact);
        }
    }
}
