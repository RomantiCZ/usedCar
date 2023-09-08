package cn.ikun.carshop.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
            http.//关闭csrf
                    csrf().disable()
                    //不通过Session获取SecurityContext
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeHttpRequests()

                    // 对于登录接口 允许匿名访问
                    .antMatchers("/user/login").permitAll()

                    // 除上面外的所有请求全部不需要认证即可访问
                    .anyRequest().permitAll();


    }

}
