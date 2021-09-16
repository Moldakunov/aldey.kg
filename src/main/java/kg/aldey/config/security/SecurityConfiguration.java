package kg.aldey.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers( "/adminPage")
                    .authenticated()
                .anyRequest()
                    .permitAll()
                .and()
                .formLogin()
                    .loginPage("/admin/fishkAdmin")
                    .failureUrl("/admin/fishkAdmin-error")
                    .successForwardUrl("/admin/adminPage")
                    .permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}aldey") // Spring Security 5 requires specifying the password storage format
                .roles("USER");
    }
}
