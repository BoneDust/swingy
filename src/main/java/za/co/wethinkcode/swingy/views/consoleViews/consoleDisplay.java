package za.co.wethinkcode.swingy.views.consoleViews;

import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.views.IDisplay;

import java.util.ArrayList;
import java.util.Scanner;

public class consoleDisplay implements IDisplay
{
    public void displayStartView(GameController controller) {
        Scanner stdin = new Scanner(System.in);
        clearScreen();
        String input = "";
        while (stdin.hasNext()) {
            System.out.print(
                    "\n***************************************************************************\n" +
                            "*                                                                         *\n" +
                            "*                           Welcome To Swingy                             *\n" +
                            "*                                                                         *\n" +
                            "* Do you want to create a new player or play with a saved player?         *\n" +
                            "*    1. New player                                                        *\n" +
                            "*    2. Saved player                                                      *\n" +
                            "*    3. Switch to gui                                                     *\n" +
                            "*    4. Quit                                                              *\n" +
                            "*                                                                         *\n" +
                            "***************************************************************************\n" +
                            "Choice: "
            );
            input = stdin.nextLine();

            if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4"))
                break;
            else
                System.out.println("Invalid choice. Enter either 1, 2, 3 or 4.");
        }
        if (input.equals("1"))
            displayCreatePlayerView();
        if (input.equals("2"))
            displayPlayerSelectionView();
        if (input.equals("3"))
            switchViews(controller);
    }


    public void displayPlayerSelectionView(ArrayList<Player> heroes) {
        Scanner stdin = new Scanner(System.in);
        clearScreen();
        String input = "";

        while (stdin.hasNext()) {
            System.out.print(
                    "\n***************************************************************************\n" +
                            "*                                                                         *\n" +
                            "*                           Welcome To Swingy                             *\n" +
                            "*                                                                         *\n" +
                            "* Do you want to create a new player or play with a saved player?         *\n" +
                            "*    1. New player                                                        *\n" +
                            "*    2. Saved player                                                      *\n" +
                            "*    3. Switch to gui                                                     *\n" +
                            "*    4. Quit                                                              *\n" +
                            "*                                                                         *\n" +
                            "***************************************************************************\n" +
                            "Choice: "
            );
            input = stdin.nextLine();

            if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4"))
                break;
            else
                System.out.println("Invalid choice. Enter either 1, 2, 3 or 4.");
        }
        if (input.equals("1"))
            displayCreatePlayerView();
        if (input.equals("2"))
            displayPlayerSelectionView();
        if (input.equals("3"))
            switchViews(controller);
    }


    private void displayHeroType()
    {
        clearScreen();
        Scanner stdin = new Scanner(System.in);
        String input = "";

        while (stdin.hasNext()) {
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
                System.out.println("Invalid choice. Enter either 1, 2 or 3.");
        }
        displayDefaultOrCustomHero(input);
    }

    private void displayDefaultOrCustomHero(String type)
    {
    }

    private void printMap(final Map map)
    {
    }

    private void displayOptions() {
        System.out.println(
                "\n*******************************************\n" +
                        "*                                         *\n" +
                        "* Direction         Game Options          *\n" +
                        "*                                         *\n" +
                        "* W - NORTH         C - Save and continue *\n" +
                        "* A - WEST          Z - Save and exit     *\n" +
                        "* S - SOUTH         X - Switch to gui     *\n" +
                        "* D - EAST                                *\n" +
                        "*******************************************\n");
    }


    private void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void displayGameOver(boolean heroWon)
    {
        String status = heroWon ? "won!" : "lost!";
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
    }


    private void displayQuitDialogue()
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
                        "****************************************\n"
        );
    }

}






















































    public void displayStartView()
    {

    }


    public void displayCreatePlayerView()
    {
        
    }


    public void displayPlayView()
    {

    }
}
