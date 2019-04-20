package trainingportal.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
 
    private Long userId;

    @NotEmpty(message = "Please enter your first name")
    private String userName;

    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    @Size(min=3,max=100, message = "The password is too short")
    private String password;

    private int enabled;

    private String token;

    private Long roleId;
 
    public User() {
 
    }

    public User(Long userId, String userName, String email, String password, int enabled, String token, Long roleId) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.token = token;
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return this.userName + "/" + this.password;
    }
 
}