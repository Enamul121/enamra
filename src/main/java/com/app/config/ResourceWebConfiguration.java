package com.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/media/**")
                .addResourceLocations("classpath:/media/");// home/enamul/IdeaProjects/new/enamra/src/main/
    }
}
