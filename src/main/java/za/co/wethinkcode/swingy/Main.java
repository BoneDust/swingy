package za.co.wethinkcode.swingy;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.controllers.DBController;
import za.co.wethinkcode.swingy.views.viewGui;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.*;
import javax.validation.ValidatorFactory;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter
@Setter
public class Main
{

    public static void main (String[] args) throws Exception
    {
      //  DBController.createDB();
       // DBController.initDB();
        JFrame frame = new JFrame("Swingy");
        frame.setContentPane(new viewGui().getWelcome());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 300));
        //frame.setLayout(null);
        frame.pack();
        frame.setVisible(true);

    }
}