package za.co.wethinkcode.swingy.views;

import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.map.Map;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.models.playables.Villain;

import java.util.ArrayList;

public interface IDisplay
{
    public abstract void displayStartView(GameController controller);
    public abstract void displayPlayerSelectionView(GameController controller);
    public abstract void displayCreatePlayerView(GameController controller);
    public abstract void displayPlayView(GameController controller);
    public abstract void switchViews(GameController controller);
}
