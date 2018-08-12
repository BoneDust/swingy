package za.co.wethinkcode.swingy.factories;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.controllers.GameController;
import za.co.wethinkcode.swingy.models.map.Coordinates;
import za.co.wethinkcode.swingy.models.playables.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Setter
@Getter
public class PlayerFactory
{
    public static int id = 0;

    public static int getId()
    {
        return (id);
    }

    public static void setId(int Id)
    {
        id = Id;
    }

    private static Player validatePlayer(Player player, GameController controller)
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);
        if (constraintViolations.size() > 0 )
        {
            for (ConstraintViolation<Player> constraints : constraintViolations)
                controller.getErrors().add("Error :" + constraints.getMessage());
            return (null);
        }
        return (player);
    }

    public static Player customPlayer(int id, String name, String type, int lvl, int exp, int atk, int def, int hp,
                                   Coordinates coordinates, GameController controller)
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
        return (validatePlayer(player, controller));
    }

    public static Player defaultPlayer(String name, String type, Coordinates coordinates, GameController controller)
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
        else
            player = null;
        return (validatePlayer(player, controller));
    }
}