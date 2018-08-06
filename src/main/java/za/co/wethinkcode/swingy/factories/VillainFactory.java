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
        return (villain);
    }
}