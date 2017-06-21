package at.qe.sepm.asn_app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


/**
 * Spring configuration for web security.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@Configuration
@EnableWebSecurity()
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.headers().frameOptions().disable(); // needed for H2 console

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(false)
                .logoutSuccessUrl("/login.xhtml");

        http.authorizeRequests()
                //Permit access to the H2 console
                .antMatchers("/h2-console/**").permitAll()
                //Permit access for all to error pages
                .antMatchers("/error/**")
                .permitAll()
                // Only access with admin role
                .antMatchers("/admin/**")
                .hasAnyAuthority("ADMIN")
                //Permit access only for some roles
                .antMatchers("/employee/**")
                .hasAnyAuthority("ADMIN", "EMPLOYEE")
                .antMatchers("/parent/**")
                .hasAnyAuthority("PARENT")
                .antMatchers("/nursery/**")
                .hasAnyAuthority("EMPLOYEE")
                .antMatchers("/general/**")
                .hasAnyAuthority("ADMIN", "EMPLOYEE", "PARENT")
                //If user doesn't have permission, forward him to login page
                .and()
                .formLogin()
                .loginPage("/login.xhtml")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/secured/welcome.xhtml").successHandler(successHandler())
                .failureForwardUrl("/error/login_failure.xhtml");

        http.exceptionHandling().accessDeniedPage("/error/access_denied.xhtml");

        http.sessionManagement().invalidSessionUrl("/login.xhtml");

    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new LoginHandler();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //Configure roles and passwords via datasource
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, true from user_data where username=?")
                .authoritiesByUsernameQuery("select username, user_role from user_data where username=?")
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}