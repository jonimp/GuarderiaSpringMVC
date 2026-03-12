package guarderiaSpring.configuracion;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = "guarderiaSpring")
@EnableWebMvc
public class Configuracion implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/");

        registry.addResourceHandler("/imagenes/**")
                .addResourceLocations("/imagenes/");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setViewClass(JstlView.class);
        vr.setPrefix("/vistas/");
        vr.setSuffix(".jsp");
        return vr;
    }

    @Bean
    public DataSource dataSource() {

        HikariDataSource ds = new HikariDataSource();

        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/guarderia");

        ds.setUsername("root");
        ds.setPassword("");

        ds.setMaximumPoolSize(10);
        ds.setMinimumIdle(2);

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    
    
    
    @Bean(name="dbName")
public String dbName(){
    return "guarderia";
}

@Bean(name="dbURL")
public String dbURL(){
    return "localhost:3306";
}

@Bean(name="dbUser")
public String dbUser(){
    return "root";
}

@Bean(name="dbPswd")
public String dbPswd(){
    return "";
}

}
