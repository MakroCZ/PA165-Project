package cz.muni.fi.pa165.mm.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Marek
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    private UserDetailsService userDetailsService;
 
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http
            .authorizeRequests()
                .antMatchers("/performer/new").hasRole("ADMIN")
                .antMatchers("/performer/create").hasRole("ADMIN")
                .antMatchers("/performer/delete").hasRole("ADMIN")
                .antMatchers("/performer/edit/*").hasRole("ADMIN")
                .antMatchers("/performer/save").hasRole("ADMIN")
                .antMatchers("/song/new").hasRole("ADMIN")
                .antMatchers("/song/create").hasRole("ADMIN")
                .antMatchers("/song/delete").hasRole("ADMIN")
                .antMatchers("/album/new").hasRole("ADMIN")
                .antMatchers("/album/create").hasRole("ADMIN")
                .antMatchers("/album/delete").hasRole("ADMIN")
                .antMatchers("/album/edit/*").hasRole("ADMIN")
                .antMatchers("/album/edit").hasRole("ADMIN")
                .antMatchers("/genre/new").hasRole("ADMIN")
                .antMatchers("/genre/create").hasRole("ADMIN")
                .antMatchers("/genre/delete").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
}
