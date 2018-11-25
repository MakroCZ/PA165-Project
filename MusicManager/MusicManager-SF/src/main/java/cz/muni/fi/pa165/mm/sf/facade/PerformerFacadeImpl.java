package cz.muni.fi.pa165.mm.sf.facade;

import cz.muni.fi.pa165.mm.api.dto.PerformerCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.PerformerDTO;
import cz.muni.fi.pa165.mm.api.facade.PerformerFacade;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import cz.muni.fi.pa165.mm.sf.service.BeanMappingService;
import cz.muni.fi.pa165.mm.sf.service.PerformerService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Marek Barinka; 456295
 */
public class PerformerFacadeImpl implements PerformerFacade {

    @Inject
    private PerformerService performerService;
    
    
    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public void create(PerformerCreateDTO p) {
        Performer mappedPerformer = beanMappingService.mapTo(p, Performer.class);
        performerService.create(mappedPerformer);
    }

    @Override
    public List<PerformerDTO> findAll() {
        return beanMappingService.mapTo(performerService.findAll(), PerformerDTO.class);
    }

    @Override
    public PerformerDTO findById(Long id) {
        Performer performer = performerService.findById(id);
        if (performer == null) {
            return null;
        }
        return beanMappingService.mapTo(performer, PerformerDTO.class);
    }

    @Override
    public void update(PerformerDTO p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Long id) {
        Performer performer = new Performer();
        performer.setId(id);
        performerService.remove(performer);
    }
    
}
