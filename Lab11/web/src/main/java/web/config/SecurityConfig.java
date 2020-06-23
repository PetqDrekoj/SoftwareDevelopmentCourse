package web.config;


import core.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import web.security.CatalogUserDetailsService;
import web.security.MySavedRequestAwareAuthenticationSuccessHandler;
import web.security.RestAuthenticationEntryPoint;

/**
 * Created by radu.
 *
 * http://www.baeldung.com/securing-a-restful-web-service-with-spring-security
 *
 * curl -i -X POST -d username=student -d password=student -c /home/radu/cookies.txt http://localhost:8080/login
 *
 * curl -i --header "Accept:application/json" -X GET -b /home/radu/cookies.txt http://localhost:8080/api/students
 *
 */

@Configuration
@EnableWebSecurity
@ComponentScan("web.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler
            mySavedRequestAwareAuthenticationSuccessHandler;

    @Autowired
    private CatalogUserDetailsService catalogUserDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.authenticationProvider(authProvider());
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("1").roles("USER", "ADMIN");
    }


    @Override
    protected UserDetailsService userDetailsService() {
        return catalogUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/clients").hasRole("TEACHER")
                .antMatchers(HttpMethod.POST,"/api/clients").hasRole("TEACHER")
                .antMatchers(HttpMethod.GET,"/api/movies").permitAll()
                .antMatchers(HttpMethod.POST,"/api/movies").permitAll()
                .antMatchers(HttpMethod.GET,"/api/rents").permitAll()
                .antMatchers(HttpMethod.POST,"/api/rents").permitAll()
                .antMatchers(HttpMethod.POST,"/api/login").permitAll()
                .antMatchers("/error").permitAll()
                .and()
                .csrf().disable()
                .formLogin()
                .failureHandler(myFailureHandler());
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/api/clients").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/movies").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .successHandler(mySavedRequestAwareAuthenticationSuccessHandler)
//                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
//                .and()
//                .logout();
    }

    @Bean
    public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler() {
        return mySavedRequestAwareAuthenticationSuccessHandler;
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }
}

