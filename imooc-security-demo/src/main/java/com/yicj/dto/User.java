package com.yicj.dto;

import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.validator.constraints.NotBlank;

import com.yicj.validator.MyConstraint;

import java.util.Date;

import javax.validation.constraints.Past;

public class User {

    public interface UserSimpleView {}
    public interface UserDetailView extends UserSimpleView {}

    private String id ;
    @MyConstraint(message = "这是一个测试")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Past(message = "生日必须是过去的时间")//过去的时间
    private Date birthday ;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
