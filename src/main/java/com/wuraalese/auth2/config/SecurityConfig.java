package com.wuraalese.auth2.config;


import com.wuraalese.auth2.repo.UserRepo;
import com.wuraalese.auth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true) //this will allow our '@PreAuthorize' in controllers to work because by default it's false
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepo.class)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("**/secured/**").authenticated() // any request with path secure should be authenticated
                .anyRequest().permitAll() // any other request without path 'secured' should be allowed without authentication
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error") // to add a custom log in use ~~> .loginPage("/<url path>") <~~
                .usernameParameter("name").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf();

        // if you want to secure the whole app but grant access to just the landing / reg page then you can swap authenticated() with permitALl() i..e
        // .antMatchers("/css/**", "/js/**", "/registration").permitAll()
        // .anyRequest().authenticated()
    }

    private PasswordEncoder getPasswordEncoder() { // used to add encrypting
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }
}
