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
    private enum gameStage{START, SELECTION, CREATION, PLAY, GAMEOVER};
    private enum creationStage{HERO_TYPE, NAME_PROMPT, CREATION_TYPE, STATS};
    private gameStage currentStage;
    private creationStage creatingStage;
    private enum movement{UP, DOWN, LEFT, RIGHT};
    private Player hero;
    private ArrayList<Player> heroes;
    private ArrayList<Villain> villains;
    private ArrayList<String> errors;
    private Map map;
    private String battleReport;
    private IDisplay display;
    private boolean gameContinues;

    public GameController(String view)
    {
        if (view.equals("console"))
            display = new consoleDisplay(this);
        else
        {
            System.out.println(view);
            display = new guiDisplay();
        }
        currentStage = gameStage.START;
        creatingStage = creationStage.HERO_TYPE;
        gameContinues = true;
        heroes = new ArrayList<>();
        villains = new ArrayList<>();
        errors = new ArrayList<>();
        map = null;
    }

    private void createMap(int level)
    {
        map = MapFactory.newMap(level);
    }

    private void updateMap()
    {
        createMap(hero.getLevel());
        int x = hero.getCoordinates().getX(), y = hero.getCoordinates().getY();
        map.getGrid()[y][x] = 1;
        for(Villain villain : villains)
        {
            x = villain.getCoordinates().getX();
            y = villain.getCoordinates().getY();
            map.getGrid()[y][x] = 2;
        }
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
        hero = PlayerFactory.customPlayer(PlayerFactory.getId(), name, type, level, exp, atk, def, hp, coordinates);
    }

    public void createDefaultHero(String type, String name)
    {
        Coordinates coordinates = CoordinateFactory.newCoordinates(0,0, map);
        hero = PlayerFactory.defaultPlayer(name, type, coordinates);
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
            report += "\n You won!!!\n";
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

    public void receiveUserInput(String input)//todo
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
                {
                    String type = "", name="";

                    if (creatingStage == creationStage.HERO_TYPE)
                    {
                        creatingStage = creationStage.NAME_PROMPT;
                        display.displayCreatePlayerView();
                    }
                    else if (creatingStage == creationStage.NAME_PROMPT)
                    {
                        creatingStage = creationStage.CREATION_TYPE;
                        if (input.equals("1"))
                            type = "Swordsman";
                        else if (input.equals("2"))
                            type = "Gunman";
                        else
                            type = "KungFuMaster";
                        ((consoleDisplay) display).displayHeroNamePrompt(type);
                    }

                    else if (creatingStage == creationStage.CREATION_TYPE)
                    {
                        creatingStage = creationStage.STATS;
                        name = input;
                        ((consoleDisplay) display).displayDefaultOrCustomHero(type, name);
                    }

                    else
                    {
                        if (input.equals("1"))
                            ((consoleDisplay)display).displayHeroStatsPrompt(type, name);
                        else
                            this.createDefaultHero(type, name);
                        currentStage = gameStage.PLAY;
                    }
                }
                break;
            case SELECTION:
                if (input.equals("q"))
                     System.exit(1);
                else
                {
                    retrieveHeroes();
                    retrieveHero(Integer.parseInt(input));
                    currentStage = gameStage.PLAY;
                }
                break;
            case PLAY:
                creatingStage = creationStage.HERO_TYPE;
                display.displayPlayView();
                break;
            case GAMEOVER:
                display.displayGameOver();
                break;
            default:
                gameContinues = false;
                break;
        }
    }

    private void switchDisplays()
    {
        if (display instanceof consoleDisplay)
            display = new guiDisplay();
        else
            display = new consoleDisplay();
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
                display.displayPlayerSelectionView();
                break;
            case PLAY:
                display.displayPlayView();
                break;
            case GAMEOVER:
                display.displayGameOver();
                break;
            default:
                gameContinues = false;
                break;
        }
    }

    private void saveHero()
    {
        DBController.recordHero(hero);
    }

    private void retrieveHeroes()
    {
        heroes = null;
        heroes = DBController.getPlayers();
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
            hero.getArtefacts().add();
        }
    }
}
