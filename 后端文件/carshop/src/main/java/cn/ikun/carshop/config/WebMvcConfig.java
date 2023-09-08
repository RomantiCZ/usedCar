package cn.ikun.carshop.config;

import cn.ikun.carshop.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;


@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Value("${carshop.photopath}")
    private String photoPath;
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        registry.addResourceHandler("/carshop/**","/**")
                .addResourceLocations("file:"+photoPath);

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/public/**")
                .addResourceLocations("classpath:/public/**");
    }

    /**
     * 解决跨域问题
     * @param registry
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")   //哪些目录可以跨域访问 ** 所有
//                 .allowedOrigins("*")           //放行哪些原始域
                .allowedOriginPatterns("*")
                .allowCredentials(true)        //是否发送Cookie
                .allowedHeaders("*")           //   放行哪些原始请求头部信息
                .allowedMethods("*")          //允许哪些方法可以跨域访问
                .maxAge(3600) ;

    }

    /**
     * 拓展MVC的消息转化器
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器对象

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        //设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());

        //将上面的消息转换器对象追加到mvc框架的转换器集合中
        converters.add(0,messageConverter);

    }



}
