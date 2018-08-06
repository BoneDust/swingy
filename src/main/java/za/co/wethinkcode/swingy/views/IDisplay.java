package za.co.wethinkcode.swingy.views;

import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.Player;
import java.util.ArrayList;

public interface IDisplay
{
    public abstract void displayStartView();
    public abstract void displayPlayerSelectionView(ArrayList<Player> heroes);
    public abstract void displayCreatePlayerView();
    public abstract void displayPlayView();
    public abstract void displayMap(final Map map);
    public abstract void displayBattleReport(String report);
    public abstract void displayHeroStats(Player hero);
    public abstract void displayErrors(ArrayList<String> errors);
    public abstract void displayGameOver(boolean heroWon);
    public abstract boolean displayQuitDialogue();
}
