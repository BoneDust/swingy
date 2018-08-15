package za.co.wethinkcode.swingy;

import za.co.wethinkcode.swingy.controllers.GameController;

public class Main
{
    public static void main (String[] args)
    {
        GameController controller = new GameController(args[0]);
        controller.renderGame();
    }
}