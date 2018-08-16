package za.co.wethinkcode.swingy.views.guiViews;

import za.co.wethinkcode.swingy.controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiCreatePlayerView
{
    private GameController controller;
    private  JFrame frame;
    private  JButton btnContinue;
    private  JButton btnBack;
    private  JPanel panel;
    private  JRadioButton customPlayer, defaultPlayer;
    private  ButtonGroup playerGroup;
    private  JComboBox typeComboBox;
    private  JLabel headingLabel, nameLabel, typeLabel, levelLabel, atkLabel, defLabel, hpLabel, errorLabel;
    private  JTextField nameText, levelText, atkText, defText, hpText;

    public GuiCreatePlayerView(GameController controller)
    {
        this.controller = controller;
    }

    private   void initCreatePlayerView()
    {
        frame = new JFrame("Swingy");
        btnContinue = new JButton("Start Game");
        btnBack = new JButton("Back");
        panel = new JPanel();
        String[] options  = {"Swordsman","Gunman", "KungFuMaster"};
        typeComboBox = new JComboBox(options);
        headingLabel = new JLabel("Create a Hero");
        typeLabel = new JLabel("Hero class:"); errorLabel = new JLabel("");
        nameLabel = new JLabel("Hero name:"); levelLabel = new JLabel("level:");
        atkLabel = new JLabel("attack:");
        defLabel = new JLabel("defense:"); hpLabel = new JLabel("hp");
        nameText = new JTextField("..."); levelText = new JTextField("...");
        atkText = new JTextField("..."); defText = new JTextField("..."); hpText = new JTextField("...");
        playerGroup = new ButtonGroup();
        customPlayer = new JRadioButton("custom hero"); defaultPlayer = new JRadioButton("default hero");
        playerGroup.add(customPlayer); playerGroup.add(defaultPlayer);
        defaultPlayer.setSelected(true);
        setDefaultPlayerBounds();
        setColors();
        setListeners();
        addToPanel();
    }

    private  void addToPanel()
    {
        panel.add(btnBack);
        panel.add(btnContinue);
        panel.add(customPlayer);
        panel.add(defaultPlayer);
        panel.add(headingLabel);
        panel.add(nameLabel); panel.add(nameText);
        panel.add(typeLabel); panel.add(typeComboBox);
        panel.add(levelLabel); panel.add(levelText);
        panel.add(atkLabel); panel.add(atkText);
        panel.add(defLabel); panel.add(defText);
        panel.add(hpLabel); panel.add(hpText);
        panel.add(errorLabel);
    }

    private  boolean errors(String level, String atk, String def, String hp)
    {
        if (!(level.matches("-?\\d+") && atk.matches("-?\\d+") && def.matches("-?\\d+") &&
              hp.matches("-?\\d+") ))
        {
            errorLabel.setText("non-integer in level, attack, defense or hp");
            return (true);
        }
        return (false);
    }

    private  void setListeners()
    {
        btnContinue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (customPlayer.isSelected())
                {
                    if (!errors(levelText.getText(), atkText.getText(), defText.getText(), hpText.getText()))
                    {
                        controller.receiveUserInput(
                                typeComboBox.getSelectedItem() + ","
                                        + nameText.getText() + ","
                                        + levelText.getText() + ","
                                        + atkText.getText() + ","
                                        + defText.getText() + ","
                                        + hpText.getText());
                        if(controller.getHero() == null)
                            ((GuiDisplay)controller.getDisplay()).setErrorFrame(frame);
                        else
                            frame.dispose();
                        controller.displayStage();
                    }

                }
                else
                {
                    frame.dispose();
                    controller.receiveUserInput(typeComboBox.getSelectedItem() + "," + nameText.getText());
                    controller.displayStage();
                }

            }
        });

        btnBack.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                controller.receiveUserInput("b");
                controller.displayStage();
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

    private  void setColors()
    {
        headingLabel.setForeground(Color.WHITE);
        errorLabel.setForeground(Color.RED);
        nameText.setBackground(Color.DARK_GRAY);
        nameText.setCaretColor(Color.WHITE);
        nameText.setForeground(Color.WHITE);
        nameLabel.setForeground(Color.WHITE);
        typeLabel.setForeground(Color.WHITE);
        typeComboBox.setBackground(Color.DARK_GRAY);
        typeComboBox.setForeground(Color.WHITE);
        levelText.setForeground(Color.WHITE);
        levelText.setCaretColor(Color.WHITE);
        atkText.setForeground(Color.WHITE);
        atkText.setCaretColor(Color.WHITE);
        defText.setForeground(Color.WHITE);
        defText.setCaretColor(Color.WHITE);
        hpText.setForeground(Color.WHITE);
        hpText.setCaretColor(Color.WHITE);
        levelLabel.setForeground(Color.WHITE);
        atkLabel.setForeground(Color.WHITE);
        defLabel.setForeground(Color.WHITE);
        hpLabel.setForeground(Color.WHITE);
        levelText.setBackground(Color.DARK_GRAY);
        atkText.setBackground(Color.DARK_GRAY);
        defText.setBackground(Color.DARK_GRAY);
        hpText.setBackground(Color.DARK_GRAY);
        panel.setBackground(Color.DARK_GRAY);
        customPlayer.setBackground(Color.DARK_GRAY);
        customPlayer.setForeground(Color.WHITE);
        defaultPlayer.setBackground(Color.DARK_GRAY);
        defaultPlayer.setForeground(Color.WHITE);
    }

    private  void setDefaultPlayerBounds()
    {
        headingLabel.setBounds(140, 10 ,150,20);
        btnBack.setBounds(50, 220 ,130,20);
        btnContinue.setBounds(200, 220 ,130,20);
        errorLabel.setBounds(50, 180,300,40);
        nameText.setBounds(180, 60, 150, 20);
        nameLabel.setBounds(40, 60, 100, 20);
        typeLabel.setBounds(40, 100, 100, 20);
        typeComboBox.setBounds(180, 100, 150, 20);
        defaultPlayer.setBounds(40, 140, 140,20);
        customPlayer.setBounds(180, 140, 150,20);
        levelText.setVisible(false); levelLabel.setVisible(false);
        atkText.setVisible(false); atkLabel.setVisible(false);
        defText.setVisible(false); defLabel.setVisible(false);
        hpText.setVisible(false); hpLabel.setVisible(false);
        errorLabel.setVisible(false);
        frame.setPreferredSize(new Dimension(450, 450));
        frame.setResizable(false);
    }

    private  void setCustomPlayerBounds()
    {
        headingLabel.setBounds(140, 10 ,150,20);
        btnBack.setBounds(50, 420,130,20);
        btnContinue.setBounds(200, 420 ,130,20);
        errorLabel.setBounds(50, 340,400,60);
        nameText.setBounds(180, 60, 150, 20);
        nameLabel.setBounds(40, 60, 100, 20);
        typeLabel.setBounds(40, 100, 100, 20);
        typeComboBox.setBounds(180, 100, 150, 20);
        levelLabel.setBounds(40, 140, 100, 20);
        levelText.setBounds(180, 140, 150,20);
        atkLabel.setBounds(40, 180, 100, 20);
        atkText.setBounds(180, 180, 150,20);
        defLabel.setBounds(40, 220, 100, 20);
        defText.setBounds(180, 220, 150,20);
        hpLabel.setBounds(40, 260, 150, 20);
        hpText.setBounds(180, 260, 150,20);
        defaultPlayer.setBounds(40, 300, 140,20);
        customPlayer.setBounds(180, 300, 150,20);

        levelText.setVisible(true); levelLabel.setVisible(true);
        atkText.setVisible(true); atkLabel.setVisible(true);
        defText.setVisible(true); defLabel.setVisible(true);
        hpText.setVisible(true); hpLabel.setVisible(true);
        errorLabel.setVisible(true);
    }

    public  void displayCreatePlayerView()
    {
        initCreatePlayerView();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

    }
}
