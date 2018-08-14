package demo01;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Car car = new Car( null, "DD-AB-123", 4 );
        Set<ConstraintViolation<Car>> constraintViolations = validator.validate( car );

        System.out.println(constraintViolations.size());

        for (ConstraintViolation<Car> v: constraintViolations) {
            System.out.println(v.getMessage());
        }
    }
}
