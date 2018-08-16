package za.co.wethinkcode.swingy.views.guiViews;

import lombok.Getter;
import za.co.wethinkcode.swingy.controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class GuiStartView
{
    private GameController controller;

    private  JFrame frame = new JFrame("Swingy");
    private  ButtonGroup btnGroup = new ButtonGroup();
    private  JButton btnContinue = new JButton("Proceed");
    private  JPanel pWelcome = new JPanel();
    private  JLabel lblWelcome = new JLabel("Welcome to swingy");
    private  JRadioButton rbtnNewHero = new JRadioButton("Create hero");
    private  JRadioButton rbtnPreviousHero = new JRadioButton("Select previous hero.");

    public GuiStartView(GameController controller)
    {
        this.controller = controller;
    }

    private  void initStartView()
    {
        setColors();
        setBounds();
        setListeners();
        addToPanel();
    }

    private  void addToPanel()
    {
        pWelcome.add(btnContinue);
        pWelcome.add(rbtnNewHero);
        pWelcome.add(rbtnPreviousHero);
        pWelcome.add(lblWelcome);
        rbtnNewHero.setSelected(true);
        btnGroup.add(rbtnNewHero);
        btnGroup.add(rbtnPreviousHero);
    }

    private  void setListeners()
    {
        btnContinue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                if (rbtnPreviousHero.isSelected())
                    controller.receiveUserInput("2");
                else
                    controller.receiveUserInput("1");
                controller.displayStage();
            }
        });
    }

    private  void setBounds()
    {
        lblWelcome.setBounds(180, 10 ,200,20);
        btnContinue.setBounds(200, 120 ,100,20);
        rbtnNewHero.setBounds(100,  60,200,40);
        rbtnPreviousHero.setBounds(300, 60 ,200,40);
    }

    private  void setColors()
    {
        lblWelcome.setForeground(Color.WHITE);
        rbtnNewHero.setBackground(Color.DARK_GRAY);
        rbtnPreviousHero.setBackground(Color.DARK_GRAY);
        rbtnNewHero.setForeground(Color.WHITE);
        rbtnPreviousHero.setForeground(Color.WHITE);
        pWelcome.setBackground(Color.DARK_GRAY);
    }

    public  void displayStartView()
    {
        initStartView();

        frame.setContentPane(pWelcome);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 200));
        frame.setResizable(false);
        frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }
}
