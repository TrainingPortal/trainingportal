package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trainingportal.dao.RoleDAOImpl;
import trainingportal.dao.UserDAOImpl;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserDAOImpl userDAOImpl;
 
    @Autowired
    private RoleDAOImpl roleDAOImpl;
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        trainingportal.model.User appUser = this.userDAOImpl.findUserAccount(email);
 
        if (appUser == null) {
            System.out.println("Email was not found! " + email);
            throw new UsernameNotFoundException(email + " was not found in the database");
        }
 
        System.out.println("Found User: " + appUser);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.roleDAOImpl.getRoleNames(appUser.getUserId());
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(),
                appUser.getEncryptedPassword(), grantList);
 
        return userDetails;
    }
 
}