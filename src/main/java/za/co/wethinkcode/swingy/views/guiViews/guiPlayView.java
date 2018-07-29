package za.co.wethinkcode.swingy.views.guiViews;

import lombok.Getter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class guiPlayView
{
    private static JFrame frame;
    private static JButton btnContinue, btnExit, btnNorth, btnSouth, btnWest, btnEast, btnSwitch;
    private static JPanel  mapPanel, arenaPanel, movementPanel, statsPanel, reportPanel;
    private static JLabel reportLabel, statsLabel, arenaLabel;
    private static JTextArea reportText, statsText;
    private static JScrollPane reportScroll, statsScroll;

    public static  void initPlayView()
    {
        frame = new JFrame("Swingy");
        btnContinue = new JButton("save and continue");
        btnExit = new JButton("save and exit");
        btnSwitch = new JButton("switch to console");
        btnWest = new JButton("WEST");
        btnEast = new JButton("EAST");
        btnSouth = new JButton("SOUTH");
        btnNorth = new JButton("NORTH");
        mapPanel = new JPanel();
        arenaPanel = new JPanel();
        movementPanel = new JPanel();
        statsPanel = new JPanel();
        reportPanel = new JPanel();
        statsLabel = new JLabel("Hero Stats");
        reportLabel = new JLabel("Battle report");
        arenaLabel = new JLabel("Arena");
        statsText = new JTextArea();
        reportText = new JTextArea();
        statsScroll = new JScrollPane(statsText);
        reportScroll = new JScrollPane(reportText);

        statsLabel.setBounds(120, 10, 100, 20);
        statsLabel.setForeground(Color.WHITE);
        statsScroll.setBounds(20, 40, 270, 460);
        statsText.setBackground(Color.DARK_GRAY);
        statsText.setForeground(Color.WHITE);
        statsText.setText("Lerole\neeee\neeeeeee\neeeeeeeee\neeeeeeeee\neeeeee\neeeeee\neeeeeeee\neeeeeee\neeeeee\neeeeeeeee\neeeeeeee\neeeeeee\neeeeeeeee\neee\neeeeeeeeeeeeee\neeeeeeeeeeeeee\neeeeeeeeeeee\neeeeee\n\n\n\nkakr\n\n\n\n\n\n\n\n\nminathoko yaga budasdsfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff\nfffffffffffffffffffffffffffff\nfffffffffffff\nffffff\n salvador");
        statsText.setEditable(false);
        statsPanel.add(statsLabel);
        statsPanel.add(statsScroll);
        statsPanel.setLayout(null);
        statsPanel.setBackground(Color.DARK_GRAY);
        statsPanel.setPreferredSize( new Dimension( 295, 495 ) );

        reportLabel.setBounds(120, 10, 100, 20);
        reportLabel.setForeground(Color.white);
        reportScroll.setBounds(0, 40, 270, 460);
        reportText.setEditable(false);
        reportText.setBackground(Color.DARK_GRAY);
        reportText.setForeground(Color.WHITE);
        reportText.setText("Lerole\neeee\neeeeeee\neeeeeeeee\neeeeeeeee\neeeeee\neeeeee\neeeeeeee\neeeeeee\neeeeee\neeeeeeeee\neeeeeeee\neeeeeee\neeeeeeeee\neee\neeeeeeeeeeeeee\neeeeeeeeeeeeee\neeeeeeeeeeee\neeeeee\n\n\n\nkakr\n\n\n\n\n\n\n\n\nminathoko yaga budasdsfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff\nfffffffffffffffffffffffffffff\nfffffffffffff\nffffff\n salvador");

        reportPanel.add(reportLabel);
        reportPanel.add(reportScroll);
        reportPanel.setLayout(null);
        reportPanel.setPreferredSize( new Dimension( 295, 495 ) );
        reportPanel.setBackground(Color.DARK_GRAY);


        btnNorth.setBounds(560, 20, 120, 20);
        btnSouth.setBounds(560, 100, 120, 20);
        btnEast.setBounds(700, 60, 120, 20);
        btnWest.setBounds(420, 60, 120, 20);
        btnContinue.setBounds(380, 140, 160, 20);
        btnExit.setBounds(700, 140, 160, 20);
        btnSwitch.setBounds(540, 170, 160, 20);
        movementPanel.add(btnNorth);
        movementPanel.add(btnSouth);
        movementPanel.add(btnEast);
        movementPanel.add(btnWest);
        movementPanel.add(btnContinue);
        movementPanel.add(btnExit);
        movementPanel.add(btnSwitch);
        movementPanel.setBackground(Color.DARK_GRAY);
        movementPanel.setPreferredSize( new Dimension( 1200, 200));
        movementPanel.setLayout(null);


        arenaLabel.setBounds(280, 10, 100, 20);
        arenaLabel.setForeground(Color.WHITE);
        mapPanel.setLayout(new GridLayout(5,5));
        mapPanel.setBounds(0,40,600,495);
        arenaPanel.add(arenaLabel);
        arenaPanel.add(mapPanel);
        arenaPanel.setBackground(Color.DARK_GRAY);
        arenaPanel.setLayout(null);
        arenaPanel.setPreferredSize( new Dimension( 600, 495 ) );
    }

    public static void displayPlayView()
    {
        initPlayView();
        drawGrid(5);
        frame.add(statsPanel,BorderLayout.WEST);
        frame.add(arenaPanel, BorderLayout.CENTER);
        frame.add(reportPanel,BorderLayout.EAST);
        frame.add(movementPanel, BorderLayout.SOUTH);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 700));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private static void drawGrid(int size)
    {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                JButton button = new JButton("boom");
                button.setEnabled(false);
                mapPanel.add(button);
            }
        }
    }
}
