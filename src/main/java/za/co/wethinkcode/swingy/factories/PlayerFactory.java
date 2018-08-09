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

@Getter
@Setter
public class PlayerFactory
{
        private static int id = 0;
    private static Player validatePlayer(Player player)
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Player>> constraintViolations = validator.validate(player);
        if (constraintViolations.size() > 0 )
        {
            for (ConstraintViolation<Player> constraints : constraintViolations)
                GameController.getErrors().add("Error :" + constraints.getMessage());
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
        else
            player = null;
        return (validatePlayer(player));
    }
}