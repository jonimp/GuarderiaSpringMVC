package prog2.guarderiaspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
@ComponentScan("prog2.guarderiaspring")
@EnableWebMvc
public class Configuracion implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/");

        registry.addResourceHandler("/imagenes/**")
                .addResourceLocations("/imagenes/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor());
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setViewClass(JstlView.class);
        vr.setPrefix("/vistas/");
        vr.setSuffix(".jsp");
        return vr;
    }

    @Bean(name = "dbName")
    public String getDBName() {
        return "guarderia";
    }

    @Bean(name = "dbURL")
    public String getDBURL() {
        return "localhost";
    }

    @Bean(name = "dbUser")
    public String getDBUser() {
        return "root";
    }

    @Bean(name = "dbPswd")
    public String getDBPswd() {
        return "";
    }
}
