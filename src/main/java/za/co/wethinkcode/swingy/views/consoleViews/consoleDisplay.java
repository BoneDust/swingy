package za.co.wethinkcode.swingy.views.consoleViews;

import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.views.IDisplay;
import java.util.ArrayList;
import java.util.Scanner;

public class consoleDisplay implements IDisplay
{
    private void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayStartView() {
        Scanner stdin = new Scanner(System.in);
        String input = "";
        while (stdin.hasNextLine())
        {
            clearScreen();
            System.out.print(
                    "\n***************************************************************************\n" +
                            "*                                                                         *\n" +
                            "*                           Welcome To Swingy                             *\n" +
                            "*                                                                         *\n" +
                            "* Do you want to create a new player or play with a saved player?         *\n" +
                            "*    1. New player                                                        *\n" +
                            "*    2. Saved player                                                      *\n" +
                            "*    3. Quit                                                              *\n" +
                            "*                                                                         *\n" +
                            "***************************************************************************\n" +
                            "Choice: "
            );
            input = stdin.nextLine();
            if (input.equals("1") || input.equals("2") || input.equals("3"))
                break;
            else
            {
                System.out.println("Invalid choice. Enter either 1, 2, or 3.");
                try {Thread.sleep(3000);} catch (InterruptedException ex){ex.printStackTrace();}
            }
        }
        if (input.equals("1"))
            displayCreatePlayerView();
        else if (input.equals("2"))
            displayPlayerSelectionView(GameController.getHeroes());
        else
            System.exit(1);
    }


    public void displayPlayerSelectionView(ArrayList<Player> heroes) {
        Scanner stdin = new Scanner(System.in);
        String input = "";
        while (stdin.hasNextLine())
        {
            clearScreen();
            System.out.print(
                    "\n********************************************************************************************\n" +
                    "                                                                                           *\n" +
                    "*                                   Select a saved hero                                    *\n" +
                    "*                                                                                          *\n"
            );
            int index = 0;
            for(Player player : heroes)
            {
                index++;
                String line = String.format("*\tId: %d, Name: %s , Class: %s , Level: %d, ATK: %d, DEF: %d, HP: %d*\n",
                        index, player.getName(), player.getType(),player.getAtk(),player.getDef(),player.getHp()
                        );
                System.out.print(line);
            }
            System.out.print(
                    "\n*                                                                                          *\n"+
                    "********************************************************************************************\n" +
                    "Select by Id: ");
            input = stdin.nextLine();
            if (input.matches("\\d+$"))
            {
                int option = Integer.parseInt(input);
                if (option <= 0 && option > heroes.size())
                {
                    System.out.println("Invalid choice. Enter a number representing a hero");
                    try { Thread.sleep(3000);} catch (InterruptedException ex) {ex.printStackTrace();}
                }
                else
                    break;
            }
            else if (input.equals("q"))
                break;
            else
            {
                System.out.println("Invalid choice. Enter a number representing a hero");
                try{Thread.sleep(3000);} catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (input.equals("q"))
            System.exit(1);
        else
        {
            GameController.getPlayer(Integer.parseInt(input));
            displayPlayView();
        }
    }


    public void displayCreatePlayerView()
    {
        displayHeroType();
    }

    private void displayHeroType()
    {
        Scanner stdin = new Scanner(System.in);
        String input = "";

        while (stdin.hasNextLine())
        {
            clearScreen();
            System.out.print(
                    "\n************************\n" +
                    "*                      *\n" +
                    "*   Select Hero class  *\n" +
                    "*                      *\n" +
                    "* 1. Swordsman         *\n" +
                    "* 2. Gunman            *\n" +
                    "* 3. KungFuMaster      *\n" +
                    "*                      *\n" +
                    "************************\n" +
                    "Choice: "
            );
            input = stdin.nextLine();
            if (input.equals("1") || input.equals("2") || input.equals("3"))
                break;
            else
            {
                System.out.println("Invalid choice. Enter either 1, 2 or 3.");
                try { Thread.sleep(3000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        if (input.equals("1"))
            displayHeroNamePrompt("Swordsman");
        else if (input.equals("1"))
            displayHeroNamePrompt("Gunman");
        else
            displayHeroNamePrompt("KungFuMaster");
    }

    private void displayHeroNamePrompt(String type)
    {
        Scanner stdin = new Scanner(System.in);
        String input = "";
        System.out.print(
                "\n************************\n" +
                "*                      *\n" +
                "*    Enter Hero Name   *\n" +
                "*                      *\n" +
                "************************\n" +
                "Name: "
                );
        if (stdin.hasNextLine())
            input = stdin.nextLine();
        displayDefaultOrCustomHero(type, input);
    }

    private void displayDefaultOrCustomHero(String type, String name)
    {
        Scanner stdin = new Scanner(System.in);
        String input = "";

        while (stdin.hasNextLine())
        {
            //clearScreen();
            System.out.print(
                    "\n*************************************\n" +
                            "*                                   *\n" +
                            "*   Select custom or default stats  *\n" +
                            "*                                   *\n" +
                            "* 1. Custom                         *\n" +
                            "* 2. Default                        *\n" +
                            "*                                   *\n" +
                            "*************************************\n" +
                            "Choice: "
            );
            input = stdin.nextLine();
            if (input.equals("1") || input.equals("2"))
                break;
            else
            {
                System.out.println("Invalid choice. Enter either 1 or 2.");
                try { Thread.sleep(3000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        if (input.equals("1"))
            displayHeroStatsPrompt(type, name);
        else
        {
            GameController.createDefaultHero(type, name);
            displayPlayView();
        }
    }

    private String retrieveStat(String stat, Scanner stdin)
    {
        String value = "";
        if (stdin.hasNextLine())
        {
            System.out.print("\n" + stat +": ");
            value = stdin.nextLine();
            while (!value.matches("\\d+$")) //todo need to see if this regex works also note thats its in the displaySelectPlayer view
            {
                System.out.println("\nInvalid" + stat +". Please enter a number.");
            }
        }
        return (value);
    }

    private void displayHeroStatsPrompt(String type, String name)
    {
        //todo remember to remove exp in model constuctors or at least deal with it in controller
        Scanner stdin = new Scanner(System.in);
        String level, atk, def, hp;
        do
        {
            clearScreen();
            System.out.print(
                    "\n*************************************\n" +
                            "*                                   *\n" +
                            "*        Entering hero stats        *\n" +
                            "*                                   *\n" +
                            "*************************************\n"
            );
            level = retrieveStat("Level", stdin);
            atk = retrieveStat("Atk", stdin);
            def = retrieveStat("Def", stdin);
            hp = retrieveStat("Hp", stdin);
            GameController.createCustomHero(type, name,level, atk, def, hp);
        }
        while (GameController.getHero() == null);
        displayPlayView();
    }

    public void displayMap(final Map map)
    {
    }
    public void displayBattleReport(String report){}//todo
    public void displayHeroStats(Player hero){}//todo
    public void displayErrors(ArrayList<String> errors){}//todo work with the factories

    private void displayOptions()
    {
        System.out.println(
                "\n*******************************************\n" +
                        "*                                         *\n" +
                        "* Direction         Game Options          *\n" +
                        "*                                         *\n" +
                        "* w - NORTH         q - quit game         *\n" +
                        "* a - WEST          h - view hero stats   *\n" +
                        "* s - SOUTH         x - Switch to gui     *\n" +
                        "* d - EAST                                *\n" +
                        "*******************************************\n" +
                        "Choice: "
        );
    }



    public void displayGameOver(boolean heroWon)
    {
        String status = heroWon ? "won!" : "lost!";
        String input="";
        Scanner stdin = new Scanner(System.in);
        while (stdin.hasNextLine())
        {
            System.out.print(
                "\n*************************\n" +
                        "*                       *\n" +
                        "* Game over you " + status + "   *\n" +
                        "*                       *\n" +
                        "* 1. Back to Main Menu  *\n" +
                        "* 2. Quit               *\n" +
                        "*                       *\n" +
                        "*************************\n"
            );
            input =  stdin.nextLine();
            if (input.equals("1") || input.equals("2"))
                break;
            else
            {
                System.out.println("Invalid choice.");
                try { Thread.sleep(3000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        GameController.saveHero();
        if (input.equals("1"))
            displayStartView();
    }

    public boolean displayQuitDialogue()
    {
        String input="";
        Scanner stdin = new Scanner(System.in);
        while (stdin.hasNextLine())
        {
            clearScreen();
            System.out.print(
                    "\n****************************************\n" +
                            "*                                      *\n" +
                            "* Are you sure you want to quit Game?  *\n" +
                            "*                                      *\n" +
                            "* 1. Yes                               *\n" +
                            "* 2. No                                *\n" +
                            "*                                      *\n" +
                            "****************************************\n" +
                            "Choice: ";
             );
            input =  stdin.nextLine();
            if (input.equals("1") || input.equals("2"))
                break;
            else
            {
                System.out.println("Invalid choice. Enter 1 or 2.");
                try { Thread.sleep(3000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        return (input.equals("1"));
    }

    public void displayPlayView()
    { //todo not done remember that controller will have array of errors gotten from the factories, remember to create a view
        //todo need to display hero stats.
        Scanner stdin = new Scanner(System.in);
        String input = "";

        while (stdin.hasNextLine())
        {
            clearScreen();
            displayMap(GameController.getMap());
            displayOptions();
            input = stdin.nextLine();
            if (input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d") || input.equals("q"))
                GameController.updateGame(input);
            else if (input.equals("x"))
                break;
            else
            {
                System.out.println("Invalid choice.");
                try { Thread.sleep(3000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        if (input.equals("x"))
        {
            clearScreen();
            stdin.close();
            GameController.switchViews();
        }
    }
}