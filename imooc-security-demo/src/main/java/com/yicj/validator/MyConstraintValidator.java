package com.yicj.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.yicj.service.HelloService;

//注意这里不需要使用Component注解，spring会自动扫描进入容器中
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	//这里可以注入spring容器中的bean
	@Autowired
	private HelloService helloService ;
	
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		helloService.greeting("tom") ;
		System.out.println("value : " + value);
		return false;
	}

}
