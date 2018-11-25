package cz.muni.fi.pa165.mm.api.facade;

import cz.muni.fi.pa165.mm.api.dto.AlbumCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.AlbumDTO;

import java.util.List;

/**
 * @author Václav Stehlík; 487580
 */
public interface AlbumFacade {
    Long createAlbum(AlbumCreateDTO albumCreateDTO);
    void deleteAlbum(Long id);
    List<AlbumDTO> getAllAlbums();
    AlbumDTO getAlbumWithID(Long id);
}
