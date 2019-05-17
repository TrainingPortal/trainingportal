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
                        "/trainers/*", "/trainer*", "/saveTrainer*",
                        "/manager*", "/managers/*", "/saveManager*",
                        "/addsubordinates/*", "/addSelectedSubordinates/*", "/releaseSubordinate*",
                        "/manager_search/*",
                        "/website_settings*", "/manage_main_slider*", "/manage_main_card*", "/card-delete-by-*",
                        "/slider-delete-by-*", "/card-save*", "/slider-save*")
                .access("hasRole('ROLE_ADMIN')");

        // Forum page
        http.authorizeRequests()
                .antMatchers("/chat")
                .access("hasAnyRole('ROLE ADMIN', 'ROLE_TRAINER', 'ROLE_EMPLOYEE', 'ROLE_MANAGER')");

        // For Managers and Admin
        http.authorizeRequests()
                .antMatchers("/subordinates/*/{id}")
                .access("hasRole('ROLE_ADMIN') or (hasRole('ROLE_MANAGER') and " +
                        "@userSecurity.hasUserId(#id))");

        // Courses page
        http.authorizeRequests()
                .antMatchers("/course_create/*")
                .access("hasRole('ROLE_ADMIN') or (hasRole('ROLE_TRAINER')) " +
                        "or (hasRole('ROLE_EMPLOYEE')) or (hasRole('ROLE_MANAGER'))");

        http.authorizeRequests()
                .antMatchers("/course*", "/edit-course*")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')");

        //Groups page
        http.authorizeRequests()
                .antMatchers("/group_create/*/{id}")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE', 'ROLE_MANAGER') " +
                        "or (hasRole('ROLE_TRAINER') and " +
                        "@userSecurity.isConnectedWithTrainer(#id))");

        http.authorizeRequests()
                .antMatchers("/group*", "/edit-group*")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')");

        //Lessons pages
        http.authorizeRequests()
                .antMatchers("/lesson-add-*")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')");
        http.authorizeRequests()
                .antMatchers("/edit-lesson-*-{id}", "/lesson-delete-by-*-{id}")
                .access("hasRole('ROLE_ADMIN') or (hasRole('ROLE_TRAINER') and " +
                        "@userSecurity.isConnectedWithTrainer(#id))");
        http.authorizeRequests()
                .antMatchers("/course_lessons/*/{id}")
                .access("hasRole('ROLE_ADMIN') or (hasRole('ROLE_TRAINER') and " +
                        "@userSecurity.isConnectedWithTrainer(#id))" +
                        "or (hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER') " +
                        "and @userSecurity.isConnectedWithLessonByCourseId(#id))");

        // Profile page settings
        http.authorizeRequests()
                .antMatchers("/profile_page/{id}")
                .access("hasRole('ROLE_ADMIN') " +
                        "or (hasRole('ROLE_EMPLOYEE') and @userSecurity.hasUserId(#id)) " +
                        "or (hasRole('ROLE_TRAINER') and @userSecurity.hasUserId(#id)) " +
                        "or (hasRole('ROLE_MANAGER') and (@userSecurity.isSubordinate(#id) " +
                        "or @userSecurity.hasUserId(#id)))");

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