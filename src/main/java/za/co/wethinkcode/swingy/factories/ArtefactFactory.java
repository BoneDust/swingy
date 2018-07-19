package za.co.wethinkcode.swingy.factories;

import za.co.wethinkcode.swingy.models.artefacts.Artefact;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ArtefactFactory
{
    private static int id = 0;
    public static Artefact newArtefact(int value, String name, String type)
    {
        id++;
        Artefact artefact = new Artefact(name,type,value, id);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Artefact>> constraintViolations = validator.validate(artefact);
        if (constraintViolations.size() > 0 )
        {
            System.out.println("\n\n<<< Failed validations >>>\n");
            for (ConstraintViolation<Artefact> constraints : constraintViolations)
                System.out.println("Error " + constraints.getMessage());
            return (null);
        }
        return (artefact);
    }
}
