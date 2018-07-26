package za.co.wethinkcode.swingy.views.guiViews;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class guiStartView {

    private ButtonGroup btnGroup = new ButtonGroup();
    private JButton btnContinue = new JButton("Continue");
    private JPanel welcome = new JPanel();
    private JLabel lblWelcome = new JLabel("Welcome to swingy");
    private JRadioButton rbtnNewHero = new JRadioButton("Create hero");
    private JRadioButton rbtnPreviousHero = new JRadioButton("Select previous hero.");
    public guiStartView() {
        lblWelcome.setBounds(180, 30 ,200,100);
        btnContinue.setBounds(200, 200 ,80,20);
        rbtnNewHero.setBounds(100, 100 ,200,40);
        rbtnPreviousHero.setBounds(300, 100 ,200,40);
        welcome.add(btnContinue);
        welcome.add(rbtnNewHero);
        welcome.add(rbtnPreviousHero);
        welcome.add(lblWelcome);
        btnGroup.add(rbtnNewHero);
        btnGroup.add(rbtnPreviousHero);
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblWelcome.setText("WElcome lerole");
            }
        });
    }
}
