package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import java.util.List;

/**
 *
 * @author Marek Barinka; 456295
 */
public interface PerformerService {
    
    /**
     * Add performer to database
     * @param p Performer to add
     */
    void create(Performer p);
    
    /**
     * Finds all stored performers
     * @return List containing all stored performers,
     * empty list if no performers are stored
     */
    List<Performer> findAll();
    
    /**
     * Finds one performer with specified id
     * @param id Id of perfomer to find
     * @return Performer with specified id,
     * null if there is no performer with specified id
     */
    Performer findById(Long id);
    
    /**
     * Updates performer
     * @param p Performer to update
     */
    void update(Performer p);
    
    /**
     * Removes performer
     * @param p Performer to remove
     */
    void remove(Performer p);
    
}
