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

    public void displayStartView()
    {
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
        GameController.receiveUserInput(input);
        /*if (input.equals("1"))
            displayCreatePlayerView();
        else if (input.equals("2"))
            displayPlayerSelectionView(GameController.getHeroes());
        else
            System.exit(1);*/
    }


    public void displayPlayerSelectionView(ArrayList<Player> heroes)
    {
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
            if (input.matches("\\d+$") || input.equals("q"))
                break;
            else
            {
                System.out.println("Invalid choice. Enter a number representing a hero");
                try{Thread.sleep(3000);} catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        GameController.receiveUserInput(input);
        /*if (input.equals("q"))
            System.exit(1);
        else
        {
            GameController.getPlayer(Integer.parseInt(input));
            displayPlayView();
        }*/
    }


    public void displayCreatePlayerView()
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
        GameController.receiveUserInput(input);
        /*if (input.equals("1"))
            displayHeroNamePrompt("Swordsman");
        else if (input.equals("1"))
            displayHeroNamePrompt("Gunman");
        else
            displayHeroNamePrompt("KungFuMaster");*/
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
        GameController.receiveUserInput(input);
        //displayDefaultOrCustomHero(type, input);
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
        GameController.receiveUserInput();
        /*if (input.equals("1"))
            displayHeroStatsPrompt(type, name);
        else
        {
            GameController.createDefaultHero(type, name);
            displayPlayView();
        }*/
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
            GameController.createCustomHero(type, name,level, atk, def, hp);//todo remember increment factory id and exp calculations
        }
        while (GameController.getHero() == null);
        GameController.receiveInput("");
    }

    public void displayErrors(ArrayList<String> errors)
    {
        Scanner stdin = new Scanner(System.in);
        String input = "";
        while (stdin.hasNextLine())
        {
            clearScreen();
            System.out.print(
                         "\n********************************************************************\n" +
                            "*                                                                 *\n" +
                            "*                   Invalid hero parameters detected!             *\n" +
                            "*                                                                 *\n" +
                            "*******************************************************************\n"
            );
            for (String error : errors)
                System.out.println("\t" + error);
            System.out.print("b - Back\n\nChoice: ");
            input = stdin.nextLine();
            if (input.equals("b"))
                break;
            else {
                System.out.println("Invalid choice. Enter b to go back.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        GameController.receiveUserInput(input);
        // todo possibly displayHeroType();
    }

    public void displayBattleReport(String report)
    {
        Scanner stdin = new Scanner(System.in);
        String input = "";
        while (stdin.hasNextLine())
        {
            clearScreen();
            System.out.print(
                          "\n*******************************************************************\n" +
                            "*                                                                 *\n" +
                            "*                            Battle Report!                       *\n" +
                            "*                                                                 *\n" +
                            "*******************************************************************\n"
            );
            System.out.println(report);
            System.out.print("\nc - continue game\n\nChoice: ");
            input = stdin.nextLine();
            if (input.equals("c"))
                break;
            else
            {
                System.out.println("Invalid choice. Enter c to go back.");
                try { Thread.sleep(3000); } catch (InterruptedException ex) { ex.printStackTrace();}
            }
        }
        GameController.receiveUserInput(input);
        //todo possibly displayPlayView();
    }

    public void displayHeroStats(Player hero)
    {
        Scanner stdin = new Scanner(System.in);
        String input = "";
        while (stdin.hasNextLine())
        {
            clearScreen();
            System.out.print(
                  "\n*******************************************************************\n" +
                    "*                                                                 *\n" +
                    "*                              Hero Stats                         *\n" +
                    "*                                                                 *\n" +
                    "*******************************************************************\n"
            );
            System.out.println("Name: " + hero.getName());
            System.out.println("Type: " + hero.getType());
            System.out.println("Level: " + hero.getLevel());
            System.out.println("Experience: " + hero.getExp());
            System.out.println("Attack points: " + hero.getAtk());
            System.out.println("Defense points: " + hero.getDef());
            System.out.println("Hp Points: " + hero.getHp());
            System.out.print("r - Resume game.\n\nChoice: ");
            if (input.equals("r"))
                break;
            else
            {
                System.out.println("Invalid choice. Enter r to resume.");
                try { Thread.sleep(3000); } catch (InterruptedException ex) { ex.printStackTrace();}
            }
        }
        GameController.receiveUserInput(input);
       //todo displayPlayView();
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
        GameController.receiveUserInput(input);
    }

    public void displayQuitDialogue()
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
                            "Choice: "
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
        GameController.receiveUserInput(input);
    }

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

    public void displayMap(final Map map)
    {
        clearScreen();
        System.out.print(
                "\n************************\n" +
                        "*                      *\n" +
                        "*         Arena        *\n" +
                        "*                      *\n" +
                        "************************\n");

        for (int i = 0; i < map.getSize(); i++)
        {
            for (int j = 0; j < map.getSize(); j++)
            {
                if (map.getGrid()[i][j] == 1)
                    System.out.print("[ H ] ");
                else if(map.getGrid()[i][j] == 2)
                    System.out.print("[ E ] ");
                else
                    System.out.print("[   ] ");
            }
            System.out.println("");
        }
    }

    public void displayFightOrRunPrompt()
    {
        Scanner stdin = new Scanner(System.in);
        String input = "";

        while (stdin.hasNextLine())
        {
            System.out.print(
                    "\n***************************\n" +
                            "*                         *\n" +
                            "*    Villain encountered  *\n" +
                            "*                         *\n" +
                            "* 1. Fight                *\n" +
                            "* 2. Run                  *\n" +
                            "*                         *\n" +
                            "***************************\n" +
                            "Choice: "
            );
            input = stdin.nextLine();
            if (input.equals("1") || input.equals("2"))
                break;
            else {
                System.out.println("Invalid input. Enter either 1 or 2.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        GameController.receiveUserInput(input);
    }

    public void displayPlayView()
    {
        Scanner stdin = new Scanner(System.in);
        String input = "";

        while (stdin.hasNextLine())
        {
            clearScreen();
            displayMap(GameController.getMap());
            displayOptions();
            input = stdin.nextLine();
            if (input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d") || input.equals("q") ||
                input.equals("x") || input.equals("h"))
                break;
            else
            {
                System.out.println("Invalid choice.");
                try { Thread.sleep(3000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        GameController.receiveUserInput(input);
    }
}