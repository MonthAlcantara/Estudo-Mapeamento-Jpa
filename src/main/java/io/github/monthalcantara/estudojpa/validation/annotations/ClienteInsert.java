package io.github.monthalcantara.estudojpa.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import io.github.monthalcantara.estudojpa.validation.validator.ClienteInsertValidator;

@Documented
@Constraint(validatedBy = ClienteInsertValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteInsert {
	
	 String message() default "{io.github.monthalcantara.nossobancodigital.validation.annotations.MaiorIdade}";

	    Class<?>[] groups() default {};

	    Class<? extends Payload>[] payload() default {};

}
