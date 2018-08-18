package za.co.wethinkcode.swingy.views.guiViews;

import lombok.Getter;
import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.playables.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Getter
public class GuiPlayerSelectionView
{
    private GameController controller;
    private  JFrame frame;
    private  JButton btnContinue;
    private  JButton btnBack;
    private  JPanel panel;
    private  ArrayList<String> options;
    private  JComboBox cbOptions;
    private  JLabel lHeading;
    private  JTextArea info;
    private  JScrollPane jScrollPane;
    private String selectedHero = "-1";

    public GuiPlayerSelectionView(GameController controller)
    {
        this.controller = controller;
    }

    private ArrayList<String> getShortHeroDetails(ArrayList<Player> heroes)
    {
        ArrayList<String> details = new ArrayList<>();
        int index = 0;
        for(Player player : heroes)
        {
            index++;
            String detail = Integer.toString(index) + ". " + player.getType() + " " + player.getName();
            details.add(detail);
        }
        return (details);
    }

    private ArrayList<String> getAllHeroDetails(ArrayList<Player> heroes)
    {
        ArrayList<String> details = new ArrayList<>();
        for(Player player : heroes)
        {
            String detail = "\n\tName: "+ player.getName() +"\n\n";
            detail += "\tClass: "+ player.getType() +"\n\n";
            detail += "\tLevel: "+ player.getLevel() +"\n\n";
            detail += "\tExp: "+ player.getExp() +"\n\n";
            detail += "\tAtk: "+ player.getAtk() +"\n\n";
            detail += "\tDef: "+ player.getDef() +"\n\n";
            detail += "\tHp: "+ player.getHp() +"\n\n";
            details.add(detail);
        }
        return (details);
    }

    private   void initSelectionView(ArrayList<Player> heroes)
    {
        frame = new JFrame("Swingy");
        btnContinue = new JButton("Start Game");
        if (heroes.size() == 0)
            btnContinue.setEnabled(false);
        else
            btnContinue.setEnabled(true);
        btnBack = new JButton("Back");
        panel = new JPanel();
        options = getShortHeroDetails(heroes);
        cbOptions = new JComboBox(options.toArray());
        lHeading = new JLabel("Hero Details");
        info = new JTextArea();
        jScrollPane = new JScrollPane(info);
        info.setEditable(false);
        setColors();
        setBounds();
        setListeners(heroes);
        addToPanel();
    }

    private  void addToPanel()
    {
        panel.add(btnBack);
        panel.add(btnContinue);
        panel.add(cbOptions);
        panel.add(lHeading);
        panel.add(jScrollPane);
    }

    private  void setBounds()
    {
        lHeading.setBounds(180, 10 ,150,20);
        jScrollPane.setBounds(50, 70, 350,300);
        cbOptions.setBounds(50, 380, 350, 20);
        btnBack.setBounds(100, 420 ,130,20);
        btnContinue.setBounds(230, 420 ,130,20);
    }

    private  void setColors()
    {
        info.setBackground(Color.DARK_GRAY);
        info.setForeground(Color.WHITE);
        lHeading.setForeground(Color.WHITE);
        panel.setBackground(Color.DARK_GRAY);
    }

    private  void setListeners(final ArrayList<Player> heroes)
    {
        btnContinue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.receiveUserInput(selectedHero);
                if(controller.getHero() == null)
                    ((GuiDisplay)controller.getDisplay()).setErrorFrame(frame);
                else
                    frame.dispose();
                controller.displayStage();
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

        cbOptions.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<String> details = getAllHeroDetails(heroes);
                info.setText(details.get(cbOptions.getSelectedIndex()));
                selectedHero = Integer.toString(cbOptions.getSelectedIndex() + 1);
            }
        });
    }

    public  void displaySelectionView(ArrayList<Player> heroes)
    {
        initSelectionView(heroes);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(450, 500));
        frame.setResizable(false);
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }
}