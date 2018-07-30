package za.co.wethinkcode.swingy.views.guiViews;

import za.co.wethinkcode.swingy.views.IDisplay;

public class guiDisplay implements IDisplay
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
