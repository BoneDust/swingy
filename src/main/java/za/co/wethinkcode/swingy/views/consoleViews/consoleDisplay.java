package za.co.wethinkcode.swingy.views.consoleViews;

import za.co.wethinkcode.swingy.views.IDisplay;
import za.co.wethinkcode.swingy.views.guiViews.guiCreatePlayerView;
import za.co.wethinkcode.swingy.views.guiViews.guiPlayView;
import za.co.wethinkcode.swingy.views.guiViews.guiPlayerSelectionView;
import za.co.wethinkcode.swingy.views.guiViews.guiStartView;

public class consoleDisplay implements IDisplay
{
    public void displayStartView()
    {
        guiStartView.displayStartView();
    }

    public void displayPlayerSelectionView()
    {
        guiPlayerSelectionView.displaySelectionView();
    }

    public void displayCreatePlayerView()
    {
        guiCreatePlayerView.displayCreatePlayerView();
    }

    public void displayPlayView()
    {
        guiPlayView.displayPlayView();
    }
}
