//package compare.site.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests()
////                .antMatchers("/").permitAll()
////                .antMatchers("/admin/**").hasRole("admin")
////                .antMatchers("/user/**").hasRole("user")
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .successForwardUrl("/ok")
////                .failureUrl("/login-error");
////    }
//
////    @Override
////    public void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth
////                .inMemoryAuthentication()
////                .withUser("admin").password("{noop}admin").roles("admin");
////    }
//}
