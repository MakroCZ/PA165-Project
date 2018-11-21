package cz.muni.fi.pa165.mm.api.facade;

import cz.muni.fi.pa165.mm.api.dto.GenreCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.GenreDTO;

import java.util.List;

/**
 * @author Yehor Safonov; 487596
 */

public interface GenreFacade {
    Long createGenre(GenreCreateDTO s);
    void deleteGenre(Long id);
    List<GenreDTO> getAllGenres();
    GenreDTO getWithId(Long id);
    List<GenreDTO> getWithName(String name);
}
