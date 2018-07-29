package za.co.wethinkcode.swingy.views.guiViews;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class guiStartView
{
    private static JFrame frame = new JFrame("Swingy");
    private static ButtonGroup btnGroup = new ButtonGroup();
    private static JButton btnContinue = new JButton("Proceed");
    private static JPanel pWelcome = new JPanel();
    private static JLabel lblWelcome = new JLabel("Welcome to swingy");
    private static JRadioButton rbtnNewHero = new JRadioButton("Create hero");
    private static JRadioButton rbtnPreviousHero = new JRadioButton("Select previous hero.");

    public static void initStartView()
    {
        lblWelcome.setBounds(180, 10 ,200,20);
        btnContinue.setBounds(200, 120 ,100,20);
        rbtnNewHero.setBounds(100,  60,200,40);
        rbtnPreviousHero.setBounds(300, 60 ,200,40);
        pWelcome.add(btnContinue);
        pWelcome.add(rbtnNewHero);
        pWelcome.add(rbtnPreviousHero);
        pWelcome.add(lblWelcome);
        rbtnNewHero.setSelected(true);
        btnGroup.add(rbtnNewHero);
        btnGroup.add(rbtnPreviousHero);
        lblWelcome.setForeground(Color.WHITE);
        rbtnNewHero.setBackground(Color.DARK_GRAY);
        rbtnPreviousHero.setBackground(Color.DARK_GRAY);
        rbtnNewHero.setForeground(Color.WHITE);
        rbtnPreviousHero.setForeground(Color.WHITE);
        pWelcome.setBackground(Color.DARK_GRAY);
        btnContinue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (rbtnPreviousHero.isSelected())
                {
                    guiPlayerSelectionView.displaySelectionView();
                    frame.dispose();
                }
            }
        });
    }

    public static void displayStartView()
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
    }
}
