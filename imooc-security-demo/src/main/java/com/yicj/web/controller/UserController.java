package com.yicj.web.controller;

import com.yicj.dto.User;
import com.yicj.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
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

    @GetMapping("/user/{id:\\d+}")
    public User getInfo(@PathVariable String id){
        User user = new User() ;
        user.setUsername("tom");
        return user ;
    }
}
