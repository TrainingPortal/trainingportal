package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trainingportal.dao.RoleDAOImpl;
import trainingportal.dao.UserDAOImpl;
import trainingportal.model.LoggedInUser;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserDAOImpl userRepository;
 
    @Autowired
    private RoleDAOImpl roleRepository;
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        trainingportal.model.User user = this.userRepository.findUserAccount(email);
 
        if (user == null) {
            System.out.println("Email was not found! " + email);
            throw new UsernameNotFoundException(email + " was not found in the database");
        }

        if(user.getEnabled() == 0){
            System.out.println(email + " is not enabled");
            throw new UsernameNotFoundException(email + " is not enabled");
        }
 
        System.out.println("Found User: " + user);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.roleRepository.getRoleNames(user.getUserId());
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new LoggedInUser(user.getUserName(),
                user.getPassword(), grantList, user.getUserId());
 
        return userDetails;
    }
}