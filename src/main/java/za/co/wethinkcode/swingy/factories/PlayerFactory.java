package za.co.wethinkcode.swingy.factories;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.models.map.Coordinates;
import za.co.wethinkcode.swingy.models.playables.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Getter
@Setter
public class PlayerFactory
{
    //todo the errors need refactoring. need to put them into an array in Controller

    private static int id = 0;
    private static Player validatePlayer(Player player)
    {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);
        if (constraintViolations.size() > 0 )
        {
            System.out.println(ANSI_RED + "\n\n<<< Failed player validations >>>\n");
            for (ConstraintViolation<Player> constraints : constraintViolations)
                System.out.println("Error :" + constraints.getMessage());
            System.out.println(ANSI_RESET);
            return (null);
        }
        return (player);
    }

    public static Player customPlayer(int id, String name, String type, int lvl, int exp, int atk, int def, int hp,
                                   Coordinates coordinates)
    {
        Player player;

        if (type.equals("Gunman"))
            player = new Gunman(id, name, type, lvl, exp, atk, def, hp, coordinates);
        else if (type.equals("KungFuMaster"))
            player = new KungFuMaster(id, name, type, lvl, exp, atk, def, hp, coordinates);
        else if (type.equals("Swordsman"))
            player = new Swordsman(id, name, type, lvl, exp, atk, def, hp, coordinates);
        else
            player = null;
        return (validatePlayer(player));
    }

    public static Player defaultPlayer(String name, String type, Coordinates coordinates)
    {

        Player player;
        if (type.equals("Gunman"))
        {
            id++;
            player = new Gunman(id, name, coordinates);
        }
        else if (type.equals("KungFuMaster"))
        {
            id++;
            player = new KungFuMaster(id, name, coordinates);

        }
        else if (type.equals("Swordsman"))
        {
            id++;
            player = new Swordsman(id, name, coordinates);
        }
        else if (type.equals("Villain"))
        {
            id++;
            player = VillainFactory.newVillain(id, name, type, coordinates);
        }
        else
            player = null;
        return (validatePlayer(player));
    }
}