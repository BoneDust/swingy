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
        JFrame frame = new JFrame("Swingy");
        frame.setContentPane(new guiStartView().getWelcome());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 300));
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);

    }
}