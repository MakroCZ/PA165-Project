package cz.muni.fi.pa165.mm.mvc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * This class represents main initializer.
 * @author Yehor Safonov; 487596
 */

public class Initializer  extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * Provides main root configuration classes
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringMvcConfiguration.class};
    }

    /**
     * Provides getter for ServletMappings
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Provides ServletFilter
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("utf-8");
        return new Filter[]{encodingFilter};
    }

    /**
     * Provides servlet configuration classes
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
}
