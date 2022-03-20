package se.iths.clothdatabase.view;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class MvcConfig {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/application").setViewName("application");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/login").setViewName("login");

    }
}
