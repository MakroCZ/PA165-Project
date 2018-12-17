package cz.muni.fi.pa165.mm.mvc.config;

import cz.muni.fi.pa165.sampledata.MusicManagerWithSampleDataConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.validation.Validator;

/**
 * This class represents main spring MVC configuration class.
 * @author Yehor Safonov; 487596
 */
@EnableWebMvc
@Configuration
@Import(MusicManagerWithSampleDataConfiguration.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.mm.mvc.controllers")
public class SpringMvcConfiguration implements WebMvcConfigurer {
    private final static Logger log = LoggerFactory.getLogger(SpringMvcConfiguration.class);

    private static final String TEXTS = "Texts";

    /**
     * Maping main page to a specific view.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.debug("mapping URL / to home view");
        registry.addViewController("/").setViewName("home");
    }


    /**
     * Enables default Tomcat servlet that serves static files.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        log.debug("enabling default servlet for static files");
        configurer.enable();
    }

    /**
     * Provides mapping from view names to JSP pages in WEB-INF/jsp directory.
     */
    @Bean
    public ViewResolver viewResolver() {
        log.debug("registering JSP in /WEB-INF/jsp/ as views");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


    /**
     * Mapping static CSS and JS resources
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    /**
     * Provides localized messages.
     */
    @Bean
    public MessageSource messageSource() {
        log.debug("registering ResourceBundle 'Texts' for messages");
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(TEXTS);
        return messageSource;
    }

    /**
     * JSR-303 Validator.
     */
    @Bean
    public Validator validator() {
        log.debug("registering JSR-303 validator");
        return new LocalValidatorFactoryBean();
    }
}
