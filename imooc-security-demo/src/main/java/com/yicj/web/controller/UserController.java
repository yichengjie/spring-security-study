package com.yicj.web.controller;

import com.yicj.dto.User;
import com.yicj.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

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
        User user = new User() ;
        user.setUsername("tom");
        return user ;
    }

    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public User createUser(@RequestBody User user){
        System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");
        return user ;
    }
}
