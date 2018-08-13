package za.co.wethinkcode.swingy;

import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.views.consoleViews.consoleDisplay;
import za.co.wethinkcode.swingy.views.guiViews.guiDisplay;

public class Main
{
    public static void main (String[] args)
    {
        GameController controller = new GameController(args[0]);
        boolean play = controller.isGameContinues();
        while (play)
        {
            controller.playGame();
            play = controller.isGameContinues();
        }
    }
}