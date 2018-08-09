package za.co.wethinkcode.swingy.views.guiViews;

import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.views.IDisplay;

import java.util.ArrayList;

public class guiDisplay implements IDisplay
{
    public void displayStartView()
    {
        guiStartView.displayStartView();
    }

    public void displayPlayerSelectionView(ArrayList<Player> heroes)
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



    public void displayMap(final Map map){}
    public void displayFightOrRunPrompt(){}
    public void displayBattleReport(String report){}
    public void displayHeroStats(Player hero){}
    public void displayErrors(ArrayList<String> errors){}
    public void displayGameOver(boolean heroWon){}
    public void displayQuitDialogue(){}
}
