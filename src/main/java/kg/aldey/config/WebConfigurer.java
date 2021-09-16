package kg.aldey.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static kg.aldey.config.globalvariable.GlobalVar.*;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfigurer implements WebMvcConfigurer {
  public final String RESOURCES_DIRECTORY = System.getProperty("user.dir");

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/images/**")
            .addResourceLocations("file:" + BANNER_UPLOAD_DIRECTORY);

    registry.addResourceHandler("/css/**", "/img/**", "/js/**", "/plugins/**", "/fonts/**")
            .addResourceLocations("folder:" + RESOURCES_DIRECTORY,
                    "classpath:/static/css/",
                    "classpath:/static/img/",
                    "classpath:/static/js/",
                    "classpath:/static/plugins/",
                    "classpath:/static/fonts/");

    registry.addResourceHandler("favicon.ico")
            .addResourceLocations("classpath:/static/img/favicon.ico");
    }

    /*registry.addResourceHandler("sitemap.xml")
            .addResourceLocations("file:" + SITEMAP);*/
}
