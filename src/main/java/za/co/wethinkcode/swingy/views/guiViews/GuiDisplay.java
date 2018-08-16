package za.co.wethinkcode.swingy.views.guiViews;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.views.IDisplay;

import java.util.ArrayList;

@Getter
@Setter
public class GuiDisplay implements IDisplay
{
    private GameController controller;

    private GuiPlayView playView;

    public GuiDisplay(GameController controller)
    {
        this.controller = controller;
        playView = new GuiPlayView(controller);
    }

    public void displayStartView()
    {
        new GuiStartView(controller).displayStartView();
    }

    public void displayPlayerSelectionView(ArrayList<Player> heroes)
    {
       new GuiPlayerSelectionView(controller).displaySelectionView(heroes);
    }

    public void displayCreatePlayerView()
    {
        new GuiCreatePlayerView(controller).displayCreatePlayerView();
    }

    public void displayPlayView()
    {
        playView.displayPlayView();
    }

    public void displayMap(final Map map)
    {
        playView.drawMap(controller.getMap());
    }

    public void setRedrawMap(boolean value)
    {
        playView.setReDrawMap(value);
    }

    public void displayFightOrRunPrompt(){}//todo
















    public void displayBattleReport(String report)
    {
        playView.displayBattle(report);
    }

    public void displayErrors(ArrayList<String> errors){}//todo

    public void displayGameOver(boolean heroWon){}//todo

    public void displayQuitDialogue(){}//todo

    public void displayForcedFightNotice(){}//todo

    public void displayRenderGame()
    {
            controller.displayStage();
    }
}
