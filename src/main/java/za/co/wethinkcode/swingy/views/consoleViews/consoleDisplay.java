package za.co.wethinkcode.swingy.views.consoleViews;

import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.views.IDisplay;
import java.util.ArrayList;
import java.util.Scanner;

public class consoleDisplay implements IDisplay
{
    private GameController controller;
    private Scanner stdin;
    public consoleDisplay(GameController controller)
    {
        this.controller = controller;
        stdin = new Scanner(System.in);
    }

    public void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayStartView()
    {
        String input = "";
        while (!(input.equals("1") || input.equals("2") || input.equals("3"))) {
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
                            "***************************************************************************\n");
            System.out.print("Choice: ");
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2") || input.equals("3")))
            {
                System.out.println("\nInvalid choice. Enter either 1, 2, or 3.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }


    public void displayPlayerSelectionView(ArrayList<Player> heroes)
    {
        String input = "";
        while (!(input.matches("\\d+$") || input.equals("q") || input.equals("b")))
        {
            clearScreen();
            System.out.print(
                    "\n********************************************************************************************\n" +
                    "*                                                                                          *\n" +
                    "*                                   Select a saved hero                                    *\n" +
                    "*                                                                                          *\n"
            );
            int index = 0;
            for(Player player : heroes)
            {
                index++;
                String line = String.format("\tId: %d, Name: %s , Class: %s , Level: %d, ATK: %d, DEF: %d, HP: %d\n",
                        index, player.getName(), player.getType(),player.getAtk(),player.getDef(),player.getHp()
                        );
                System.out.print(line);
            }
            System.out.print(
                    "*  q - Quit                                                                                 *\n" +
                    "*  b - Back                                                                                 *\n" +
                    "*                                                                                           *\n" +
                    "********************************************************************************************\n" +
                    "Select by Id: ");
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.matches("\\d+$") || input.equals("q") || input.equals("b")))
            {
                System.out.println("Invalid choice. Enter a number representing a hero");
                try{Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }


    public void displayCreatePlayerView()
    {
        String input = "";

        while (!(input.equals("1") || input.equals("2") || input.equals("3")))
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
            if(stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2") || input.equals("3")))
            {
                System.out.println("Invalid choice. Enter either 1, 2 or 3.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }

    public void displayHeroNamePrompt()
    {
        String input = "";
        System.out.print(
                "\n************************\n" +
                "*                      *\n" +
                "*    Enter Hero Name   *\n" +
                "*                      *\n" +
                "************************\n" +
                "Name: "
                );
        if (stdin.hasNext())
            input = stdin.next();
        else
            System.exit(0);
        controller.receiveUserInput(input);
    }

    public void displayDefaultOrCustomHero()
    {
        String input = "";

        while (!(input.equals("1") || input.equals("2")))
        {
            clearScreen();
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
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2")))
            {
                System.out.println("Invalid choice. Enter either 1 or 2.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }

    private int retrieveStat(String stat)
    {
        String value = "";
        while (!value.matches("-?\\d+$"))
        {
            clearScreen();
            System.out.print(
                    "\n*************************************\n" +
                            "*                                   *\n" +
                            "*        Entering hero stats        *\n" +
                            "*                                   *\n" +
                            "*************************************\n"
            );
            System.out.print("\n" + stat +": ");
            if (stdin.hasNext())
                value = stdin.next();
            else
                System.exit(0);
            if(!value.matches("-?\\d+$"))
            {
                System.out.println("\nInvalid " + stat +". Please enter a number.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        return (Integer.parseInt(value));
    }

    public void displayHeroStatsPrompt(String name, String type)
    {
        int level, atk, def, hp;

        level = retrieveStat("Level");
        atk = retrieveStat("Atk");
        def = retrieveStat("Def");
        hp = retrieveStat("Hp");
        controller.createCustomHero(type, name,level, atk, def, hp);
    }

    public void displayErrors(ArrayList<String> errors)
    {
        String input = "";
        while (!input.equals("b"))
        {
            clearScreen();
            System.out.println(
                         "\n********************************************************************\n" +
                            "*                                                                 *\n" +
                            "*                        Invalid hero detected!                   *\n" +
                            "*                                                                 *\n" +
                            "*******************************************************************\n"
            );
            for (String error : errors)
                System.out.println("\t" + error);
            System.out.print("\nb - Back\n\nChoice: ");
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!input.equals("b"))
            {
                System.out.println("Invalid choice. Enter b to go back.");
                try {Thread.sleep(1000); } catch (InterruptedException ex) {ex.printStackTrace(); }
            }
        }
        controller.receiveUserInput(input);
    }

    public void displayBattleReport(String report)
    {
        System.out.println(
            "\n*******************************************************************\n" +
            "*                                                                 *\n" +
            "*                            Battle Report!                       *\n" +
            "*                                                                 *\n" +
            "*******************************************************************\n");
        System.out.println(report);
    }

    public void displayGameOver(boolean heroWon)
    {
        String status = heroWon ? "won!" : "lost!";
        String input="";
        while (!(input.equals("1") || input.equals("2")))
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
            if (stdin.hasNext())
                input =  stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2")))
            {
                System.out.println("Invalid choice.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }

    public void displayQuitDialogue()
    {
        String input="";
        while (!(input.equals("1") || input.equals("2")))
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
            if (stdin.hasNext())
                input =  stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2")))
            {
                System.out.println("Invalid choice. Enter 1 or 2.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }

    private void displayOptions()
    {
        System.out.print(
                "\n*******************************************\n" +
                        "*                                         *\n" +
                        "* Direction         Game Options          *\n" +
                        "*                                         *\n" +
                        "* w - NORTH         q - quit game         *\n" +
                        "* a - WEST                                *\n" +
                        "* s - SOUTH         x - Switch to gui     *\n" +
                        "* d - EAST                                *\n" +
                        "*******************************************\n" +
                        "Choice: "
        );
    }

    public void displayMap(final Map map)
    {
        Player hero = controller.getHero();
        System.out.println(
                      "\n******************************************************************************************\n" +
                        "*                                        Hero Stats                                      *\n" +
                        "******************************************************************************************\n");
        String stats =  String.format("Name: %s , Class: %s , Level: %d, Exp: %d, ATK: %d, DEF: %d, HP: %d\n\n",
             hero.getName(), hero.getType(), hero.getLevel(), hero.getExp(), hero.getAtk(), hero.getDef(), hero.getHp());
        System.out.print(stats);
        for (int i = 0; i < map.getSize(); i++)
        {
            for (int j = 0; j < map.getSize(); j++)
            {
                if (map.getGrid()[i][j] == 1)
                    System.out.print("[H]  ");
                else if(map.getGrid()[i][j] == 2)
                    System.out.print("[E]  ");
                else
                    System.out.print("[ ]  ");
            }
            System.out.println("");
        }
    }

    public void displayFightOrRunPrompt()
    {
        String input = "";

        while (!(input.equals("1") || input.equals("2")))
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
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.equals("1") || input.equals("2")))
            {
                System.out.println("Invalid input. Enter either 1 or 2.");
                try {Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace(); }
            }
        }
        controller.receiveUserInput(input);
    }

    public void displayForcedFightNotice()
    {
        String input = "";

        while (!input.equals("c"))
        {
            System.out.print(
                    "\n*******************************************************************\n" +
                            "*                                                                 *\n" +
                            "*                      You are forced to battle!                  *\n" +
                            "*                                                                 *\n" +
                            "*    c - continue to do battle                                    *\n" +
                            "*                                                                 *\n" +
                            "*******************************************************************\n");

            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!input.equals("c"))
            {
                System.out.println("Invalid input. Enter c to continue.");
                try {Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }

    public void displayPlayView()
    {
        String input = "";

        while (!(input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d") || input.equals("q") ||
                input.equals("x") || input.equals("h")))
        {
            //clearScreen();
            displayMap(controller.getMap());
            displayOptions();
            if (stdin.hasNext())
                input = stdin.next();
            else
                System.exit(0);
            if (!(input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d") || input.equals("q") ||
                    input.equals("x") || input.equals("h")))
            {
                System.out.println("Invalid choice.");
                try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
            }
        }
        controller.receiveUserInput(input);
    }
}