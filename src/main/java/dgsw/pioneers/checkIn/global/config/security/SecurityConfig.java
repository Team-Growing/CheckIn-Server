package dgsw.pioneers.checkIn.global.config.security;

import dgsw.pioneers.checkIn.global.properties.AdminSignInProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .formLogin().and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**").hasRole("ADMIN");
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(final PasswordEncoder passwordEncoder, final AdminSignInProperties adminProperties) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode(adminProperties.getPw()))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
