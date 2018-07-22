/*https://codingexplained.com/coding/java/hibernate/unique-field-validation-using-hibernate-sprin*/
/**
 * ok, this a custom annotation for validating a field already exists in a database table
 */

package rokomari.java.recruit.restfulinpeace.lib.annotations;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import rokomari.java.recruit.restfulinpeace.lib.constraints.UniqueValidator;
import rokomari.java.recruit.restfulinpeace.lib.interfaces.FieldValueExists;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface UniqueEntry {
    String message() default "{unique.value.violation}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends FieldValueExists> service();
    String serviceQualifier() default "";
    String fieldName();
}