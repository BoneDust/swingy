package za.co.wethinkcode.swingy.views.guiViews;

import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.views.IDisplay;

import java.util.ArrayList;

public class GuiDisplay implements IDisplay
{
    private GameController controller;

    public GuiDisplay(GameController controller)
    {
        this.controller = controller;
    }

    public void displayStartView()
    {
        GuiStartView.displayStartView();
    }

    public void displayPlayerSelectionView(ArrayList<Player> heroes)
    {
        GuiPlayerSelectionView.displaySelectionView();
    }

    public void displayCreatePlayerView()
    {
        GuiCreatePlayerView.displayCreatePlayerView();
    }

    public void displayPlayView()
    {
        GuiPlayView.displayPlayView();
    }

    public void displayMap(final Map map){}

    public void displayFightOrRunPrompt(){}

    public void displayBattleReport(String report){}

    public void displayErrors(ArrayList<String> errors){}

    public void displayGameOver(boolean heroWon){}

    public void displayQuitDialogue(){}

    public void displayForcedFightNotice(){}

    public void displayRenderGame()
    {
        controller.displayStage();
    }
}
