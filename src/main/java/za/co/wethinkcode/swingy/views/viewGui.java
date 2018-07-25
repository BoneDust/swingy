package za.co.wethinkcode.swingy.views;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class viewGui {
    private JButton btnContinue = new JButton();
    private JPanel welcome = new JPanel();
    private JLabel lblWelcome = new JLabel();
    private JRadioButton rbtnNewHero = new JRadioButton("Create hero");
    private JRadioButton rbtnPreviousHero = new JRadioButton("SElect previous hero.");
    public viewGui() {
        lblWelcome.setBounds(50, 50 ,20,20);
        welcome.add(btnContinue);
        welcome.add(rbtnNewHero);
        welcome.add(rbtnPreviousHero);
        welcome.add(lblWelcome);
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblWelcome.setText("WElcome lerole");
            }
        });
    }
}
