package cz.muni.fi.pa165.mm.sf.service.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import cz.muni.fi.pa165.mm.daolayer.DAOLayerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import(DAOLayerApplicationContext.class)
@ComponentScan(basePackages={"cz.muni.fi.pa165.mm.sf.facade", "cz.muni.fi.pa165.mm.sf.service"})
public class ServiceConfiguration {


    @Bean
    public Mapper dozer(){
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        return mapper;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

