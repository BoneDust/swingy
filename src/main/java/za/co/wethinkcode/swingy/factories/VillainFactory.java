package za.co.wethinkcode.swingy.factories;

import za.co.wethinkcode.swingy.models.map.Coordinates;
import za.co.wethinkcode.swingy.models.playables.Player;
import za.co.wethinkcode.swingy.models.playables.Villain;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Random;
import java.util.Set;

public class VillainFactory
{
    private static Villain validateViilain(Villain villain)
    {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Villain>> constraintViolations = validator.validate(villain);
        if (constraintViolations.size() > 0 )
        {
            System.out.println(ANSI_RED + "\n\n<<< Failed villain validations >>>\n");
            for (ConstraintViolation<Villain> constraints : constraintViolations)
                System.out.println("Error :" + constraints.getMessage());
            System.out.println(ANSI_RESET);
            return (null);
        }
        return (villain);
    }

    public static Villain newVillain(int id, String name, String type, Coordinates coordinates)
    {
        int level, exp, atk, def, hp;
        Random rand = new Random();
        level = rand.nextInt(10) + 1;
        exp = 0;
        atk = rand.nextInt(10 - level + 1) * 10;
        def = rand.nextInt(10 - level  + 1) * 10;
        hp = rand.nextInt(10 - level + 1) * 30;
        Villain villain = new Villain(id, name, type, level, exp, atk, def, hp, coordinates);
        villain.setCatchPhrase("I'm an evil villain. huahahahahah!");
        return (validateViilain(villain));
    }

    public static Villain oldVillain(int id, String name, String type, int lvl, int exp, int atk, int def, int hp,
                                     Coordinates coordinates)
    {
        Villain villain = new Villain(id, name, type, lvl, exp, atk, def, hp, coordinates);
        villain.setCatchPhrase("I'm an evil villain. huahahahahah!");
        return (validateViilain(villain));
    }
}