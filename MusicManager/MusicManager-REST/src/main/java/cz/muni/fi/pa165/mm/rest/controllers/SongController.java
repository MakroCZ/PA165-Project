package cz.muni.fi.pa165.mm.rest.controllers;

import cz.muni.fi.pa165.mm.api.dto.SongCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.SongDTO;
import cz.muni.fi.pa165.mm.api.facade.SongFacade;
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
@RequestMapping(ApiUris.ROOT_URI_SONGS)
@RestController
public class SongController {
    private final SongFacade songFacade;

    @Inject
    public SongController(SongFacade songFacade) {
        this.songFacade = songFacade;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO getSong(@PathVariable("id") long id) {
        try {
            return songFacade.getSongWithID(id);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getSongs() {
        return songFacade.getAllSongs();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO createSong(@RequestBody SongCreateDTO song) {
        try {
            long id = songFacade.createSong(song);
            return getSong(id);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity updateSong(@RequestBody SongDTO song) {
        try {
            songFacade.updateSong(song);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity deleteSong(@PathVariable long id) {
        try {
            songFacade.deleteSong(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }
}
