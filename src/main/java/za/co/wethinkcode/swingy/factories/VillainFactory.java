package za.co.wethinkcode.swingy.factories;

import za.co.wethinkcode.swingy.models.map.Coordinates;
import za.co.wethinkcode.swingy.models.playables.Villain;

import java.util.Random;

public class VillainFactory
{
    public static Villain newVillain(String name, String type, Coordinates coordinates)
    {
        int level, exp, atk, def, hp;
        Random rand = new Random();
        level = rand.nextInt(10) + 1;
        exp = 0;
        atk = rand.nextInt(10 - (level / 2) + 1) * (level / 2);//todo needs work.
        def = rand.nextInt(10 - (level / 2) + 1) * (level / 2);
        hp = rand.nextInt(10 - (level / 2) + 1) * (level / 2);
        Villain villain = new Villain(name, type, level, exp, atk, def, hp, coordinates);
        villain.setCatchPhrase("I'm an evil villain. huahahahahah!");
        return (villain);
    }

    public static Villain oldVillain(String name, String type, int lvl, int exp, int atk, int def, int hp,
                                     Coordinates coordinates)
    {
        Villain villain = new Villain(name, type, lvl, exp, atk, def, hp, coordinates);
        villain.setCatchPhrase("I'm an evil villain. huahahahahah!");
    }
}
