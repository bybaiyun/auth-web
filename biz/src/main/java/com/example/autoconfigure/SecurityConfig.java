package com.example.autoconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.common.domain.auth.Role.ADMIN;
import static com.example.common.domain.auth.Role.USER;

/**
 * @Author: suragi
 * @Date: 2023/8/14 00:56
 * @Description:
 */
@Configuration
@EnableWebSecurity(debug = true)
//@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> {
                //permitAll 允许所有人访问，即使未登录
                //authenticated 允许通过认证的用户访问
                request.requestMatchers("/api/user/**").hasAnyRole(ADMIN.name(), USER.name())
                        .requestMatchers("/api/admin/**").hasRole(ADMIN.name())
                        /*.requestMatchers("/api/admin/**").hasAuthority(ADMIN_READ.name())
                        .requestMatchers("/api/user/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())*/
                        .anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                //记住我
                .rememberMe(config -> {})
                //认证方式，下面是 basic 方式，还有 formLogin、oAuthLogin 等等
                /*.formLogin(conf -> {
                    conf.defaultSuccessUrl("/api/user/get");
                    conf.permitAll();
                })*/
                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/"))
                .oauth2Client(Customizer.withDefaults())
            .build();
    }

    /**
     * 配置 UserDetails
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails alex = User.withDefaultPasswordEncoder()
                .username("Alex")
                .password("123456")
                .roles(USER.name())
                .authorities(USER.getAuthorities())
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("Admin")
                .password("admin")
                .authorities(ADMIN.getAuthorities())
                .roles(ADMIN.name())
                .build();
        return new InMemoryUserDetailsManager(alex, admin);
    }

}

