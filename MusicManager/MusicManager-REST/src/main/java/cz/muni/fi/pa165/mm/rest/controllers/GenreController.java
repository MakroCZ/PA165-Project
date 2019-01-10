package cz.muni.fi.pa165.mm.rest.controllers;

import cz.muni.fi.pa165.mm.api.dto.GenreCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.GenreDTO;
import cz.muni.fi.pa165.mm.api.facade.GenreFacade;
import cz.muni.fi.pa165.mm.rest.ApiUris;
import cz.muni.fi.pa165.mm.rest.exceptions.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Václav Stehlík; 487580
 */
@RequestMapping(ApiUris.ROOT_URI_GENRES)
@RestController
public class GenreController {
    private final GenreFacade genreFacade;

    @Inject
    public GenreController(GenreFacade genreFacade) {
        this.genreFacade = genreFacade;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final GenreDTO getGenre(@PathVariable("id") long id) {
        try {
            return genreFacade.findWithId(id);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<GenreDTO> getGenres() {
        return genreFacade.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final GenreDTO createGenre(@RequestBody GenreCreateDTO genre) {
        try {
            long id = genreFacade.createGenre(genre);
            return getGenre(id);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity updateGenre(@RequestBody GenreDTO genre) {
        try {
            genreFacade.updateGenre(genre);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity deleteGenre(@PathVariable long id) {
        try {
            genreFacade.deleteGenre(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }
}
