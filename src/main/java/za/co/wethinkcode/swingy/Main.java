package za.co.wethinkcode.swingy;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.views.guiViews.guiStartView;

import java.awt.*;
import javax.swing.*;

@Getter
@Setter
public class Main
{

    public static void main (String[] args) throws Exception
    {
      //  DBController.createDB();
       // DBController.initDB();
        guiStartView.displayStartView();
    }
}