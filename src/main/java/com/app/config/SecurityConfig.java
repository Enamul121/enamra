package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DataSource dataSource;
    private final String USERS_QUERY = "select email, password, active from user where email=?";
    private final String ROLES_QUERY = "select u.email, r.role from user u inner join user_role ur on (u.id = ur.user_id) inner join role r on (ur.role_id=r.role_id) where u.email=?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(USERS_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/files/**", "/academic/**","/professional/**","/general/**",
                                       "**/img/**","**/img/logo.png","**/img/favicon.ico","/v/**",
                                       "/error","/user/logout",
                                       "/g/topic/**","/g/**", "/user/signup","/user/login",
                                       "/","/entry","/blog/**",
                                       "/media/files/blog/img/**",
                                       "**/webjars/**","/webjars/**","/resources/static/**").permitAll()


                .antMatchers("/resources/**","/resources/static/upload_files",
                                       "/resources/static/upload_files/**","/resources/static/img/**",
                                       "/resources/static/user_files/**", "/img/**", "/fonts/**",
                                       "/css/**","/js/**").permitAll()

               .antMatchers("/home").fullyAuthenticated()

                .antMatchers("/admin_Dashboard","/admin","/admin/**").hasAnyAuthority("ADMIN")
             //   .anyRequest().authenticated()
                .antMatchers("/hr/**").hasAnyAuthority("ADMIN","HR")// "/hr_Dashboard"
                .antMatchers("/manager/**","/manager_dashboard").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers("/comment/create","/comment/**","/blog/file_upload").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
                .antMatchers("/your_Dashboard","/user","/user/**").hasAuthority("USER")
                //.anyRequest().authenticated()


                .and()
                .csrf().disable()
                .formLogin().loginPage("/user/login").failureUrl("/user/login?error=true")
                .defaultSuccessUrl("/home", true)
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout().logoutUrl("/user/logout").logoutSuccessUrl("/");
    }

    @Bean
    public static BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
  }
