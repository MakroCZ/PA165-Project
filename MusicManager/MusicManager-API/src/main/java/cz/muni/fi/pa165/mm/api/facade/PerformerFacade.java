package cz.muni.fi.pa165.mm.api.facade;

import cz.muni.fi.pa165.mm.api.dto.PerformerCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.PerformerDTO;
import java.util.List;

/**
 *
 * @author Marek Barinka; 456295
 */
public interface PerformerFacade {
    
    /**
     * Add performer to database
     * @param p Performer to add
     * @return id of created performer
     */
    Long create(PerformerCreateDTO p);
    
    /**
     * Finds all stored performers
     * @return List containing all stored performers,
     * empty list if no performers are stored
     */
    List<PerformerDTO> findAll();
    
    /**
     * Finds one performer with specified id
     * @param id Id of perfomer to find
     * @return Performer with specified id,
     * null if there is no performer with specified id
     */
    PerformerDTO findById(Long id);
    
    /**
     * Updates performer
     * @param p Performer to update
     */
    void update(PerformerDTO p);
    
    /**
     * Removes performer
     * @param id Id of performer to remove
     */
    void remove(Long id);
    
    /**
     * Adds album to performer
     * @param performerId Id of performer to update
     * @param AlbumId Id of album to update
     */
    void addAlbum(Long performerId, Long AlbumId);
}
