package trainingportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import trainingportal.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

        // For ADMIN only.
        http.authorizeRequests()
                .antMatchers("/admin",
                        "/trainers/*", "/trainer*",
                        "/manager*", "/managers/*",
                        "/addsubordinates/*", "/addSelectedSubordinates/*", "/releaseSubordinate*",
                        "/group_create/*", "/group*", "/edit-group*",
                        "/course*", "/edit-course*",
                        "/manager_search/*")
                .access("hasRole('ROLE_ADMIN')");

        // For Managers.
        http.authorizeRequests()
                .antMatchers("/subordinates/*/{id}")
                .access("hasRole('ROLE_ADMIN') or (hasRole('ROLE_MANAGER') and " +
                        "@userSecurity.hasUserId(authentication,#id))");

        // For Trainers
        http.authorizeRequests()
                .antMatchers("/course_create/*")
                .access("hasRole('ROLE_ADMIN') or (hasRole('ROLE_TRAINER'))");
        http.authorizeRequests()
                .antMatchers("/course_lessons/*/{id}", "/edit-lesson-*-{id}", "/lesson-delete-by-*-{id}")
                .access("hasRole('ROLE_ADMIN') or (hasRole('ROLE_TRAINER') and " +
                        "@userSecurity.isConnectedWithTrainer(authentication,#id))");

        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/");

        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/")//
                .failureUrl("/login?error")//
                .usernameParameter("email")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout");
    }
}