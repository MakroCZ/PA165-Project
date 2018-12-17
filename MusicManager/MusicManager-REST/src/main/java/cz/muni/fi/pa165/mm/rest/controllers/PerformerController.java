package cz.muni.fi.pa165.mm.rest.controllers;

import cz.muni.fi.pa165.mm.api.dto.PerformerCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.PerformerDTO;
import cz.muni.fi.pa165.mm.api.facade.PerformerFacade;
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
@RequestMapping(ApiUris.ROOT_URI_PERFORMERS)
@RestController
public class PerformerController {
    private final PerformerFacade performerFacade;

    @Inject
    public PerformerController(PerformerFacade performerFacade) {
        this.performerFacade = performerFacade;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final PerformerDTO getPerformer(@PathVariable("id") long id) {
        try {
            return performerFacade.findById(id);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PerformerDTO> getPerformers() {
        return performerFacade.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final PerformerDTO createPerformer(@RequestBody PerformerCreateDTO performer) {
        try {
            long id = performerFacade.create(performer);
            return getPerformer(id);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity updatePerformer(@RequestBody PerformerDTO performer) {
        try {
            performerFacade.update(performer);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity deletePerformer(@PathVariable long id) {
        try {
            performerFacade.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }
}
