package cz.muni.fi.pa165.mm.sf.service;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;


/**
 * Created by lsuchanek on 18.11.2018.
 */
public interface BeanMappingService {

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
