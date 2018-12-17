package cz.muni.fi.pa165.mm.rest.controllers;

import cz.muni.fi.pa165.mm.api.dto.AlbumCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.AlbumDTO;
import cz.muni.fi.pa165.mm.api.facade.AlbumFacade;
import cz.muni.fi.pa165.mm.rest.ApiUris;
import cz.muni.fi.pa165.mm.rest.exceptions.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RequestMapping(ApiUris.ROOT_URI_ALBUMS)
@RestController
public class AlbumController {
    private final AlbumFacade albumFacade;

    @Inject
    public AlbumController(AlbumFacade albumFacade) {
        this.albumFacade = albumFacade;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final AlbumDTO getAlbum(@PathVariable("id") long id) {
        try {
            return albumFacade.findById(id);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<AlbumDTO> getAlbums() {
        return albumFacade.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final AlbumDTO createAlbum(@RequestBody AlbumCreateDTO album) {
        try {
            long id = albumFacade.createAlbum(album);
            return getAlbum(id);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity updateAlbum(@RequestBody AlbumDTO album) {
        try {
            albumFacade.updateAlbum(album);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity deleteAlbum(@PathVariable long id) {
        try {
            albumFacade.deleteAlbum(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            throw new InternalException();
        }
    }
}
