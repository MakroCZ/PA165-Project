package cz.muni.fi.pa165.mm.api.facade;

import cz.muni.fi.pa165.mm.api.dto.AlbumCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.AlbumDTO;

import java.util.List;

/**
 * @author Václav Stehlík; 487580
 */
public interface AlbumFacade {
    /**
     * Adds album to the database.
     * @param albumCreateDTO Album to add.
     * @return id of created album.
     */
    Long createAlbum(AlbumCreateDTO albumCreateDTO);

    /**
     * Deletes album from the database.
     * @param id Identifier of the album to be deleted.
     */
    void deleteAlbum(Long id);

    /**
     * Finds all albums in the database.
     * @return List containing all albums from the database or null if no album is stored.
     */
    List<AlbumDTO> findAll();

    /**
     * Finds album with specified id.
     * @param id Identifier of the album to find.
     * @return Album with specified id or null if the entity does not exist.
     */
    AlbumDTO findById(Long id);
}
