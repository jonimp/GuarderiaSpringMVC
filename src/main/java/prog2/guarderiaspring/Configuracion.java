package prog2.guarderiaspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("prog2.guarderiaspring")
@EnableWebMvc
public class Configuracion implements WebMvcConfigurer {

 

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
        return "";
    }

    @Bean(name = "dbPswd")
    public String getDBPswd() {
        return "";
    }
}