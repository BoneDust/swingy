package za.co.wethinkcode.swingy.factories;

import za.co.wethinkcode.swingy.models.artefacts.Artefact;
import za.co.wethinkcode.swingy.models.playables.Player;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ArtefactFactory
{
    private static int id = 0;

    public static Artefact newArtefact(int value, String type)
    {
        id++;

        Artefact artefact = new Artefact(type +"_"+id,type,value, id);
        return (artefact);
    }

    public static Artefact oldArtefact(int id, int value, String name, String type)
    {
        Artefact artefact = new Artefact(name,type,value, id);
        return (artefact);
    }
}
