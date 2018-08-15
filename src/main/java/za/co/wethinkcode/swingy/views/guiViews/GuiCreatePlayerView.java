package za.co.wethinkcode.swingy.views.guiViews;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiCreatePlayerView
{
    private static JFrame frame;
    private static JButton btnContinue;
    private static JButton btnBack;
    private static JPanel panel;
    private static ArrayList<String> options;
    private static JRadioButton customPlayer, defaultPlayer;
    private static ButtonGroup playerGroup;
    private static JComboBox typeComboBox;
    private static JLabel headingLabel, nameLabel, typeLabel, levelLabel, expLabel, atkLabel, defLabel, hpLabel;
    private static JTextField nameText, levelText,expText, atkText, defText, hpText, errorText;

    private static  void initCreatePlayerView()
    {
        frame = new JFrame("Swingy");
        btnContinue = new JButton("Start Game");
        btnBack = new JButton("Back");
        panel = new JPanel();
        String[] options  = {"Swordsman","Gunman", "KungFuMaster"};
        typeComboBox = new JComboBox(options);
        headingLabel = new JLabel("Create a Hero");
        typeLabel = new JLabel("Hero class:");
        nameLabel = new JLabel("Hero name:"); levelLabel = new JLabel("level:");
        expLabel = new JLabel("exp:"); atkLabel = new JLabel("attack points:");
        defLabel = new JLabel("defense points:"); hpLabel = new JLabel("hit points");
        nameText = new JTextField(); levelText = new JTextField(); expText = new JTextField();
        atkText = new JTextField(); defText = new JTextField(); hpText = new JTextField();
        errorText = new JTextField("error");
        playerGroup = new ButtonGroup();
        customPlayer = new JRadioButton("custome hero"); defaultPlayer = new JRadioButton("default hero");
        playerGroup.add(customPlayer); playerGroup.add(defaultPlayer);
        defaultPlayer.setSelected(true);
        setDefaultPlayerBounds();
        setColors();
        setListeners();
        addToPanel();
    }

    private static void addToPanel()
    {
        panel.add(btnBack);
        panel.add(btnContinue);
        panel.add(customPlayer);
        panel.add(defaultPlayer);
        panel.add(headingLabel);
        panel.add(nameLabel); panel.add(nameText);
        panel.add(typeLabel); panel.add(typeComboBox);
        panel.add(levelLabel); panel.add(levelText);
        panel.add(expLabel); panel.add(expText);
        panel.add(atkLabel); panel.add(atkText);
        panel.add(defLabel); panel.add(defText);
        panel.add(hpLabel); panel.add(hpText);
        panel.add(errorText);
    }

    private static void setListeners()
    {
        btnContinue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                GuiPlayView.displayPlayView();
                frame.dispose();
            }
        });

        btnBack.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                GuiStartView.displayStartView();
                frame.setVisible(false);
            }
        });

        customPlayer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setCustomPlayerBounds();
            }
        });

        defaultPlayer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setDefaultPlayerBounds();
            }
        });
    }

    private static void setColors()
    {
        headingLabel.setForeground(Color.WHITE);
        nameText.setBackground(Color.DARK_GRAY);
        nameText.setForeground(Color.WHITE);
        nameLabel.setForeground(Color.WHITE);
        typeLabel.setForeground(Color.WHITE);
        typeComboBox.setBackground(Color.DARK_GRAY);
        typeComboBox.setForeground(Color.WHITE);
        errorText.setForeground(Color.RED);
        errorText.setBackground(Color.DARK_GRAY);
        levelText.setForeground(Color.WHITE);
        expText.setForeground(Color.WHITE);
        atkText.setForeground(Color.WHITE);
        defText.setForeground(Color.WHITE);
        hpText.setForeground(Color.WHITE);
        levelLabel.setForeground(Color.WHITE);
        expLabel.setForeground(Color.WHITE);
        atkLabel.setForeground(Color.WHITE);
        defLabel.setForeground(Color.WHITE);
        hpLabel.setForeground(Color.WHITE);
        levelText.setBackground(Color.DARK_GRAY);
        expText.setBackground(Color.DARK_GRAY);
        atkText.setBackground(Color.DARK_GRAY);
        defText.setBackground(Color.DARK_GRAY);
        hpText.setBackground(Color.DARK_GRAY);
        panel.setBackground(Color.DARK_GRAY);
        customPlayer.setBackground(Color.DARK_GRAY);
        customPlayer.setForeground(Color.WHITE);
        defaultPlayer.setBackground(Color.DARK_GRAY);
        defaultPlayer.setForeground(Color.WHITE);
    }

    private static void setDefaultPlayerBounds()
    {
        headingLabel.setBounds(140, 10 ,150,20);
        btnBack.setBounds(50, 220 ,130,20);
        btnContinue.setBounds(200, 220 ,130,20);
        nameText.setBounds(180, 60, 150, 20);
        nameLabel.setBounds(40, 60, 100, 20);
        typeLabel.setBounds(40, 100, 100, 20);
        typeComboBox.setBounds(180, 100, 150, 20);
        defaultPlayer.setBounds(40, 140, 140,20);
        customPlayer.setBounds(180, 140, 150,20);
        errorText.setBounds(20, 180, 100, 20);
        errorText.setBorder(null);
        levelText.setVisible(false); levelLabel.setVisible(false);
        expText.setVisible(false); expLabel.setVisible(false);
        atkText.setVisible(false); atkLabel.setVisible(false);
        defText.setVisible(false); defLabel.setVisible(false);
        hpText.setVisible(false); hpLabel.setVisible(false);
        frame.setPreferredSize(new Dimension(400, 480));
        frame.setResizable(false);
    }

    private static void setCustomPlayerBounds()
    {
        headingLabel.setBounds(140, 10 ,150,20);
        btnBack.setBounds(50, 420 ,130,20);
        btnContinue.setBounds(200, 420 ,130,20);
        nameText.setBounds(180, 60, 150, 20);
        nameLabel.setBounds(40, 60, 100, 20);
        typeLabel.setBounds(40, 100, 100, 20);
        typeComboBox.setBounds(180, 100, 150, 20);
        expLabel.setBounds(40, 140, 100, 20);
        expText.setBounds(180, 140, 150,20);
        levelLabel.setBounds(40, 180, 100, 20);
        levelText.setBounds(180, 180, 150,20);
        atkLabel.setBounds(40, 220, 100, 20);
        atkText.setBounds(180, 220, 150,20);
        defLabel.setBounds(40, 260, 150, 20);
        defText.setBounds(180, 260, 150,20);
        hpLabel.setBounds(40, 300, 100, 20);
        hpText.setBounds(180, 300, 150,20);
        errorText.setBounds(20, 380, 100, 20);
        errorText.setBorder(null);
        defaultPlayer.setBounds(40, 340, 140,20);
        customPlayer.setBounds(180, 340, 150,20);

        levelText.setVisible(true); levelLabel.setVisible(true);
        expText.setVisible(true); expLabel.setVisible(true);
        atkText.setVisible(true); atkLabel.setVisible(true);
        defText.setVisible(true); defLabel.setVisible(true);
        hpText.setVisible(true); hpLabel.setVisible(true);
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
            frame.setLayout(null);
            frame.pack();
            frame.setVisible(true);
        }
        else
            frame.setVisible(true);
    }
}
