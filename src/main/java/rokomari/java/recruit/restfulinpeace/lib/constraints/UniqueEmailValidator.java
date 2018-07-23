package rokomari.java.recruit.restfulinpeace.lib.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rokomari.java.recruit.restfulinpeace.lib.annotations.UniqueEmail;
import rokomari.java.recruit.restfulinpeace.service.UserService;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("isvalid value ================= ");
		System.out.println(value);
		
		System.out.print("userService == ");
		System.out.println();
		
		if( userService == null ) {
			System.out.println("why null");
			return false;
		}
		
		
		boolean b = userService.fieldValueExists(value, "email");
		
		System.out.print("userServicedone === ");
		System.out.println(b);
		
		return value != null && !b;
	}
	
}