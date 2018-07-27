package za.co.wethinkcode.swingy.views.guiViews;

import lombok.Getter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class guiPlayerSelectionView
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

    public static  void initSelectionView()
    {
        frame = new JFrame("Swingy");
        btnContinue = new JButton("Proceed to Game");
        btnBack = new JButton("Back");
        panel = new JPanel();
        options = new ArrayList<>(Arrays.asList("Select a hero","budas","lerapo","lerole", "sputla", "yerr"));
        cbOptions = new JComboBox(options.toArray());
        lHeading = new JLabel("Hero Details");
        info = new JTextArea();
        jScrollPane = new JScrollPane(info);


        lHeading.setBounds(180, 10 ,150,50);
        info.setBounds(50, 70, 350,300);
        cbOptions.setBounds(100, 380, 250, 50);
        btnBack.setBounds(100, 420 ,130,20);
        btnContinue.setBounds(230, 420 ,130,20);


        panel.add(btnBack);
        panel.add(btnContinue);
        panel.add(cbOptions);
        panel.add(lHeading);
        panel.add(info);
        panel.add(jScrollPane);


        info.setEditable(false);
        btnContinue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

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
        cbOptions.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (cbOptions.getSelectedItem().equals("budas"))
                    info.setText("maar budas keng ka wena?");
                else if (cbOptions.getSelectedItem().equals("yerr"))
                    info.setText("maar budas wa bora?");
                else if (cbOptions.getSelectedItem().equals("Select a hero"))
                    info.setText("");
                else
                    info.setText("maar budas o lesepa?");
            }
        });
    }

    public static void displayStartView()
    {
        if (frame == null)
        {
            initSelectionView();
            frame.setContentPane(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(450, 500));
            frame.setMaximumSize(new Dimension(450, 500));
            frame.setLayout(null);
            frame.pack();
            frame.setVisible(true);
        }
        else
            frame.setVisible(true);
    }
}