package com.grid.salary.verify.config;

import com.grid.salary.verify.security.UserDetailsServiceImpl;
import com.grid.salary.verify.security.filter.JwtAuthenticationTokenFilter;
import com.grid.salary.verify.security.handle.AuthenticationEntryPointImpl;
import com.grid.salary.verify.security.handle.AuthenticationLoginFailureHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @description: Security配置类
 * @author: zhangyaofang
 * @date: 2020/03/09 08:51
 * @version: 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    @Autowired
    private AuthenticationLoginFailureHandler preAuthenticationLoginFailureHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CorsFilter corsFilter;

    /**
     * 解决 无法直接注入 AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 配置策略
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.cors().configurationSource(this.CorsConfigurationSource());
//        httpSecurity.addFilter(corsFilter);


        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 认证失败处理类
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 过滤请求
                .authorizeRequests()
                //放过 option 请求
                //.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 对于登录login 图标 要允许匿名访问
                .antMatchers("/login/**", "/mobile/login/**", "/favicon.ico","/socialSignUp","/bind","/register/**").anonymous()
                .antMatchers(HttpMethod.GET,"/*.html","/**/*.html","/**/*.css","/**/*.js")
                .permitAll()
                .antMatchers("/captcha.jpg").anonymous()
                .antMatchers("/sendCode/**").anonymous()
                .antMatchers("/tenant/list").anonymous()
                .antMatchers("/tenant/setting/**")
                .permitAll()
                // 访问/user 需要拥有admin权限
                //  .antMatchers("/user").hasAuthority("ROLE_ADMIN")
                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();
        // 添加JWT filter 用户名登录
        httpSecurity
                // 添加图形证码校验过滤器
//                .addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 添加JWT验证过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 设置UserDetailsService
                .userDetailsService(userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 装载BCrypt密码编码器 密码加密
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //配置跨域访问资源
    private CorsConfigurationSource CorsConfigurationSource() {
        CorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");	//同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");	//允许的请求方法，PSOT、GET等
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
        return source;
    }
}
