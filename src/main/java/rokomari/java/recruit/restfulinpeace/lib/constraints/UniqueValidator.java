/*https://codingexplained.com/coding/java/hibernate/unique-field-validation-using-hibernate-sprin*/

package rokomari.java.recruit.restfulinpeace.lib.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import rokomari.java.recruit.restfulinpeace.lib.annotations.UniqueEntry;
import rokomari.java.recruit.restfulinpeace.lib.interfaces.FieldValueExists;

public class UniqueValidator implements ConstraintValidator<UniqueEntry, Object> {
	
    @Autowired
    private ApplicationContext applicationContext;

    private FieldValueExists service;
    private String fieldName;

    @Override
    public void initialize(UniqueEntry unique) {
    	
    	
    	System.out.println("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    	System.out.println(unique);
    	
        Class<? extends FieldValueExists> clazz = unique.service();
        this.fieldName = unique.fieldName();
        String serviceQualifier = unique.serviceQualifier();
        
        System.out.print("..................................... serviceQualifier ====== ");
        System.out.println(serviceQualifier);

        if (!serviceQualifier.equals("")) {
            this.service = this.applicationContext.getBean(serviceQualifier, clazz);
        } else {
        	System.out.println("clazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        	System.out.println(clazz);
            this.service = this.applicationContext.getBean(clazz);
        }
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    	System.out.println("isvalida??????????????????????????????????????????????????????????????");
        return !this.service.fieldValueExists(o, this.fieldName);
    }
}