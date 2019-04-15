package trainingportal.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
 
    private Long userId;
    @Size(min=3,max=30)
    @NotNull
    private String userName;
    @NotNull
    @Email
    @Size(min=5,max=30)
    private String email;
    @NotNull
    @Size(min=3,max=100)
    private String encryptedPassword;
    private int enabled;
 
    public User() {
 
    }
 
    public User(Long userId, String userName, String email, String encryptedPassword, int enabled) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.enabled = enabled;
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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return this.userName + "/" + this.encryptedPassword;
    }
 
}