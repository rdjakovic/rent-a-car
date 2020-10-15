package com.ranko.rent_a_car.config;

import com.ranko.rent_a_car.web.converter.StringToUserRoleConverter;
import com.ranko.rent_a_car.web.converter.StringToVehicleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.ranko.rent_a_car.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    StringToVehicleConverter stringToVehicleConverter;

    @Autowired
    StringToUserRoleConverter stringToUserRoleConverter;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login?error").setViewName("403");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/admin").setViewName("admin");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    // Asking DispatcherServlet to forward requests for static resources to the servlet container’s default servlet and not to try to handle them itself
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Configure TilesConfigurer.
     */
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tiles = new TilesConfigurer();
        //if we have more definitions
        //new String[] {"/WEB-INF/tiles/tiles.xml", "/another_tiles.xml"});
        tiles.setDefinitions("/WEB-INF/tiles/tiles.xml");
        tiles.setCheckRefresh(true);
        return tiles;
    }

    /**
     * Configure ViewResolvers to deliver preferred views.
     */
    @Bean
    public ViewResolver viewResolver() {
        return new TilesViewResolver();
    }

    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

    /**
     * Register formatters or converters
     * (in this case converter from String vehicle.id returned from view addEditRentals
     * to object Vehicle, so Hibernate can persist rental.vehicle to db)
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Add formatters and/or converters
		registry.addConverter(stringToVehicleConverter);
        registry.addConverter(stringToUserRoleConverter);
    }

    /**
     * Configure MessageSource to lookup any validation/error message in internationalized property files
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("validation");
        return messageSource;
    }

    //There is no need for this ViewResolver because now TilesViewResolver is used
    /*@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }*/
}
