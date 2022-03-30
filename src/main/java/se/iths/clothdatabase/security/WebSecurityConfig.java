package se.iths.clothdatabase.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
       return http
               .csrf().disable()
               .httpBasic()
               .and()
               .authorizeRequests()
               .antMatchers("/users/signup").permitAll()
               .antMatchers("/home", "/", "/application",
                       "/users/update/",
                       "userDetails/update/",
                       "userDetails/find/",
                       "product/all",
                       "product/find/",
                       "payment/update/",
                       "category/find/",
                       "category/findAll/",
                       "address/update/").hasRole("USER")
               .antMatchers("/product/","address/", "/users/","/userDetails/","/payment/","/category/","/","home","/application", "/admin").hasRole("ADMIN")
               .anyRequest().authenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .permitAll()
               .and()
               .logout()
               .invalidateHttpSession(true)
               .clearAuthentication(true)
               .permitAll().and().build();
    }

}
