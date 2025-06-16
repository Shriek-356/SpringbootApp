package com.vdk.configs;

import com.vdk.services.UserService;
import com.vdk.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableMethodSecurity //Bat de dung PreAuthorize
@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    // Mã hóa mật khẩu (Bắt buộc nên dùng BCrypt)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Đăng ký AuthenticationProvider dùng UserService của bạn
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Cấu hình xác thực
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Value("${spring.security.oauth2.resourceserver.jwt.secret}")
    private String jwtSecret;

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(new javax.crypto.spec.SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256")).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<String> roles = jwt.getClaimAsStringList("roles");
            if (roles == null) return List.of();

            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .map(grantedAuthority -> (GrantedAuthority) grantedAuthority)
                    .toList();
        });
        return converter;
    }


    // Cấu hình bảo mật chính

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF nếu test API/ giao diện đơn giản
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").authenticated()
                        .requestMatchers("/api/hello").hasRole("Patient")
                        .anyRequest().permitAll() // Còn lại yêu cầu đăng nhập
                )
                .formLogin(form -> form
                        .loginPage("/login")       // Trang login custom (bạn tự tạo)
                        .defaultSuccessUrl("/")    // URL sau khi login thành công
                        .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())  // ✅ Chuẩn mới: dùng withDefaults()
                );

        return http.build();
    }
}
