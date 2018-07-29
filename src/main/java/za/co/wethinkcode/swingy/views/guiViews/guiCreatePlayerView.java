package za.co.wethinkcode.swingy.views.guiViews;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class guiCreatePlayerView
{
    private static JFrame frame;
    private static JButton btnContinue;
    private static JButton btnBack;
    private static JPanel panel;
    private static ArrayList<String> options;
    private static JComboBox cbOptions;
    private static JLabel lHeading;
    private static JTextArea info;
    private static JScrollPane jScrollPane;

    public static  void initCreatePlayerView()
    {
        frame = new JFrame("Swingy");
        btnContinue = new JButton("Start Game");
        btnBack = new JButton("Back");
        panel = new JPanel();
        cbOptions = new JComboBox(options.toArray());
        lHeading = new JLabel("Create a Hero");

        lHeading.setBounds(180, 10 ,150,50);
        btnBack.setBounds(100, 420 ,130,20);
        btnContinue.setBounds(230, 420 ,130,20);
        lHeading.setForeground(Color.WHITE);
        panel.add(btnBack);
        panel.add(btnContinue);
        panel.add(lHeading);
        panel.setBackground(Color.DARK_GRAY);

        btnContinue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                guiPlayView.displayPlayView();
                frame.dispose();
            }
        });

        btnBack.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                guiStartView.displayStartView();
                frame.setVisible(false);
            }
        });
    }

    public static void displayCreatePlayerView()
    {
        if (frame == null)
        {
            initCreatePlayerView();
            frame.setContentPane(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(450, 500));
            frame.setResizable(false);
            frame.setLayout(null);
            frame.pack();
            frame.setVisible(true);
        }
        else
            frame.setVisible(true);
    }
}
