package cz.muni.fi.pa165.mm.mvc.controllers;

import cz.muni.fi.pa165.mm.api.dto.AlbumDTO;
import cz.muni.fi.pa165.mm.api.dto.GenreDTO;
import cz.muni.fi.pa165.mm.api.dto.SongCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.SongDTO;
import cz.muni.fi.pa165.mm.api.facade.AlbumFacade;
import cz.muni.fi.pa165.mm.api.facade.GenreFacade;
import cz.muni.fi.pa165.mm.api.facade.SongFacade;
import cz.muni.fi.pa165.mm.sf.facade.SongFacadeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lsuchanek on 14.12.2018.
 */
@Controller
@RequestMapping("/song")
public class SongController {
    final static Logger log = LoggerFactory.getLogger(SongController.class);
    @Autowired
    private SongFacade songFacade;

    @Autowired
    private AlbumFacade albumFacade;

    @Autowired
    private GenreFacade genreFacade;
    //
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("songs", songFacade.getAllSongs());
        return "song/list";
    }

    @RequestMapping(value = "/list/{name}", method = RequestMethod.GET)
    public String findByName(@PathVariable String name,Model model) {
        model.addAttribute("songs", this.find(name));
        return "song/list";
    }
    //
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newSong(Model model) {
        log.debug("new()");
        SongCreateDTO songCreateDTO = new SongCreateDTO();
        songCreateDTO.setName("default");
        songCreateDTO.setDate(LocalDate.now());
        songCreateDTO.setLength(LocalTime.of(0,3,33));
        songCreateDTO.setAlbumId(1L);
        songCreateDTO.setGenreId(1L);
        model.addAttribute("songCreate", songCreateDTO);
        return "song/new";
    }

    @ModelAttribute("albums")
    public List<AlbumDTO> albums(){
        return albumFacade.findAll();
    }

    @ModelAttribute("genres")
    public List<GenreDTO> genres(){
        return genreFacade.getAllGenres();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("songCreate") SongCreateDTO formBean,
                         BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(formBean={})", formBean);

        formBean.setDate(LocalDate.now());

        Long id = songFacade.createSong(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Song " + id + " was created");
        return "redirect:" + uriBuilder.path("/song/list").toUriString();
    }

    private List<SongDTO> getSongs(){
        AlbumDTO album = new AlbumDTO();
        album.setName("album");
        GenreDTO genre = new GenreDTO();
        genre.setName("genre");
        SongDTO song = new SongDTO();
        song.setName("song1");
        song.setDate(LocalDate.now());
        song.setLength(LocalTime.of(0,3,0));
        song.setAlbum(album);
        song.setGenre(genre);

        SongDTO song2 = new SongDTO();
        song2.setName("song2");
        song2.setDate(LocalDate.now());
        song2.setLength(LocalTime.of(0,3,0));
        song2.setAlbum(album);
        song2.setGenre(genre);

        List<SongDTO> songs = new ArrayList<>();
        songs.add(song);
        songs.add(song2);
        return songs;
    }

    private List<SongDTO> find(String name){
        List<SongDTO> allSongs = songFacade.getAllSongs();
        List<SongDTO> filtered = new ArrayList<>();
        for(SongDTO song:allSongs){
            if(song.getName().toLowerCase().contains(name.toLowerCase())){
                filtered.add(song);
            }
        }
        return filtered;
    }
}
