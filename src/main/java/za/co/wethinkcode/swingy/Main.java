package za.co.wethinkcode.swingy;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
       /* ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        test testing = new test(500);
        Set<ConstraintViolation<test>> constraintViolations = validator.validate(testing);
        if (constraintViolations.size() > 0 )
        {
            System.out.println("\n\n<<< Failed validations >>>\n");
            for (ConstraintViolation<test> constraints : constraintViolations)
                System.out.println("Error " + constraints.getMessage());
        }*/
        Connection conn;
        Statement statement;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3308", "root", "password");
        statement = conn.createStatement();
        String createDB = "create database if not exists budas";
        statement.executeUpdate(createDB);//statement.executeQuery();
        statement.close();
        conn.close();
    }
}