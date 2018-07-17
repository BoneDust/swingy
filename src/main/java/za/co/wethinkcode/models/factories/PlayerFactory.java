package za.co.wethinkcode.models.factories;

import za.co.wethinkcode.models.map.Coordinates;
import za.co.wethinkcode.models.playables.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class PlayerFactory
{
    public static Player newPlayer(String name, String type, int level, int exp, int atk, int def, int hp,
                                   Coordinates coordinate)
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Player player;

        if (type.equals("Gunman"))
            player = new Gunman(name, type, level, exp, atk, def, hp, coordinate);
        else if (type.equals("KungFuMaster"))
            player = new KungFuMaster(name, type, level, exp, atk, def, hp, coordinate);
        else if (type.equals("Swordsman"))
            player = new Swordsman(name, type, level, exp, atk, def, hp, coordinate);
        else if (type.equals("Villain"))
        {
            player = new Swordsman(name, type, level, exp, atk, def, hp, coordinate);
            ((Villain)player).setCatchPhrase("I'm an evil villain. huahahahahah!");
        }
        else
            player = null;

        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);
        if (constraintViolations.size() > 0 )
        {
            System.out.println("\n\n<<< Failed validations >>>\n");
            for (ConstraintViolation<Player> constraints : constraintViolations)
                System.out.println("Error " + constraints.getMessage());
            return (null);
        }
        return (player);
    }
}
