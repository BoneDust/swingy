package za.co.wethinkcode.swingy;

import za.co.wethinkcode.swingy.views.consoleViews.consoleDisplay;
import za.co.wethinkcode.swingy.views.guiViews.guiDisplay;

public class Main
{
    public static void main (String[] args)
    {
       guiDisplay gui = new guiDisplay();
       consoleDisplay console = new consoleDisplay();
       //gui.displayStartView();
        console.displayStartView();
    }
}