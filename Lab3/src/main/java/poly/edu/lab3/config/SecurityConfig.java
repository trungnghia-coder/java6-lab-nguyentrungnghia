package poly.edu.lab3.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder pe) {
        String password = pe.encode("123");
        UserDetails user1 = User.withUsername("user@gmail.com").password(password).roles("USER").build();
        UserDetails user2 =  User.withUsername("admin@gmail.com").password(password).roles("ADMIN").build();
        UserDetails user3 = User.withUsername("both@gmail.com").password(password).roles("USER","ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2Login(login -> {
            login.loginPage("/login/form");
            login.successHandler(((request, response, authentication) -> {
                DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
                String username = user.getEmail();

                UserDetails newUser = User.withUsername(username).password("{noop}").roles("USER","ADMIN").build();
                Authentication newauth = new
                        UsernamePasswordAuthenticationToken(newUser, null, newUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newauth);

                HttpSession session = request.getSession();
                String attr = "SPRING_SECURITY_SAVED_REQUEST";
                DefaultSavedRequest req = (DefaultSavedRequest) session.getAttribute(attr);
                String redirectUrl = (req == null) ? "/" : req.getRedirectUrl();
                response.sendRedirect(redirectUrl);
            }));
        });

        http.formLogin(config -> {
            config.loginPage("/login/form");
            config.loginProcessingUrl("/login/check");
            config.defaultSuccessUrl("/login/success");
            config.failureUrl("/login/failure");
            config.permitAll();
            config.usernameParameter("username");
            config.passwordParameter("password");
        });

        http.csrf(config -> config.disable()).cors(config -> config.disable());
        http.authorizeHttpRequests(config -> {
            config
                    .anyRequest().permitAll();
        });

        http.rememberMe(config -> {
            config.tokenValiditySeconds(3*24*60*60);
            config.rememberMeCookieName("remember-me");
            config.rememberMeParameter("remember-me");
        });
        http.logout(config -> {
            config.logoutUrl("/logout");
            config.logoutSuccessUrl("/login/exit");
            config.clearAuthentication(true);
            config.invalidateHttpSession(true);
            config.deleteCookies("remember-me");
        });
        http.logout(Customizer.withDefaults());
        return http.build();
    }
}
