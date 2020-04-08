package com.yicj.web.controller;

import com.yicj.dto.User;
import com.yicj.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(
            UserQueryCondition condition,
            @PageableDefault(page = 2,size = 17,sort = "username,asc") Pageable pageable
            /*@RequestParam(required = false,defaultValue = "tom") String username*/){
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        //System.out.println("condition : " + condition);
        List<User> users = new ArrayList<>() ;
        users.add(new User()) ;
        users.add(new User()) ;
        users.add(new User()) ;

        return users ;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getUser(@PathVariable String id){
    	
    	System.out.println("进入getUser 服务...");
        User user = new User() ;
        user.setUsername("tom");
        return user ;
        //throw new RuntimeException("user not exist !") ;
        //throw new UserNotExistException("001") ;
        
    }

    @PostMapping
    @JsonView(User.UserSimpleView.class)
	public User createUser(@Valid @RequestBody User user/* , BindingResult errors */){
//    	if(errors.hasErrors()) {
//    		errors.getAllErrors()
//    		.stream()
//    		.forEach(error -> System.out.println(error.getDefaultMessage()));
//    	}
    	System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");
        return user ;
    }
    
    @PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {
    	if(errors.hasErrors()) {
    		errors.getAllErrors()
    		.stream()
    		.forEach(error -> {
    			FieldError fieldError = (FieldError) error ;
    			System.out.println(fieldError.getField() +" : " + fieldError.getDefaultMessage()) ;
    		});
    	}
    	System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}
    
    @DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}
}
