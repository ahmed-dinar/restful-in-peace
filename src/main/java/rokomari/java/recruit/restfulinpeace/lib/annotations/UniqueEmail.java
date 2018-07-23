package rokomari.java.recruit.restfulinpeace.lib.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

import rokomari.java.recruit.restfulinpeace.lib.constraints.UniqueEmailValidator;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueEmail {

	public String message() default "There is already user with this email!";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default{};

}