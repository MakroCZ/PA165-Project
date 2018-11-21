package cz.muni.fi.pa165.mm.sf.facade;

import cz.muni.fi.pa165.mm.api.dto.GenreCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.GenreDTO;
import cz.muni.fi.pa165.mm.api.facade.GenreFacade;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.sf.service.BeanMappingService;
import cz.muni.fi.pa165.mm.sf.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Yehor Safonov; 487596
 */

@Service
@Transactional
public class GenreFacadeImpl implements GenreFacade {

    final static Logger log = LoggerFactory.getLogger(GenreFacadeImpl.class);

    @Inject
    private GenreService genreService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createGenre(GenreCreateDTO g) {
        Genre mappedGenre = beanMappingService.mapTo(g, Genre.class);
        Genre newGenre = genreService.create(mappedGenre);
        return newGenre.getId();
    }

    @Override
    public void deleteGenre(Long id) {
        Genre genre = new Genre();
        genre.setId(id);
        genreService.delete(genre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return beanMappingService.mapTo(genreService.getAllGenres(), GenreDTO.class);
    }

    @Override
    public GenreDTO getWithId(Long id) {
        Genre genre = genreService.findById(id);
        return (genre==null) ? null : beanMappingService.mapTo(genre, GenreDTO.class);
    }

    @Override
    public List<GenreDTO> getWithName(String name) {
        return beanMappingService.mapTo(genreService.findByName(name), GenreDTO.class);
    }
}
