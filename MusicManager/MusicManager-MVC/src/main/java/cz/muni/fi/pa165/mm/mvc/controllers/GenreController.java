package cz.muni.fi.pa165.mm.mvc.controllers;

import cz.muni.fi.pa165.mm.api.dto.GenreCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.GenreDTO;
import cz.muni.fi.pa165.mm.api.dto.SongDTO;
import cz.muni.fi.pa165.mm.api.facade.GenreFacade;
import cz.muni.fi.pa165.mm.api.facade.SongFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the GenreController
 * @author Yehor Safonov; 487596
 */

@Controller
@RequestMapping("/genre")
public class GenreController {
    final static Logger log = LoggerFactory.getLogger(GenreController.class);

     @Autowired
     private GenreFacade genreFacade;

    @Autowired
    private SongFacade songFacade;

    /**
     * RequestMapping which returns all genres
     * This class directly works with the GenreFacade layer.
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("genres", genreFacade.findAll());
        return "genre/list";
    }

    /**
     * RequestMapping which allows to create new genres.
     * This method works with GenreCreateDTO objects
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newSong(Model model) {
        log.debug("new()");
        model.addAttribute("genreCreate", new GenreCreateDTO());
        return "genre/new";
    }

    /**
     * RequestMapping which allows to find specific genre.
     * This method works with genreFacade objects
     */
    @RequestMapping(value = "/list/{name}", method = RequestMethod.GET)
    public String findByName(@PathVariable String name, Model model) {
        model.addAttribute("genres", genreFacade.findWithName(name));
        return "genre/list";
    }

    /**
     * RequestMapping which allows to find all songs which have the same genre.
     * This method works with genreFacade objects
     */
    @RequestMapping(value = "/songlist/{genre}", method = RequestMethod.GET)
    public String findAllSongsWithSameGenre(@PathVariable Long genre, Model model) {
        List<SongDTO> songs = genreFacade.findAllSongsWithSameGenre(genre);
        model.addAttribute("songs", songs);
        return "song/list";
    }

    /**
     * RequestMapping which allows to POST created Genre.
     * This class directly works with the GenreFacade layer.
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("genreCreate") GenreCreateDTO genre, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(formBean={})", genre);
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "genre/new";
        }
        Long id = genreFacade.createGenre(genre);
        redirectAttributes.addFlashAttribute("alert_success", "Genre " + id + " was created");
        return "redirect:" + uriBuilder.path("/genre/list").toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        try {
            genreFacade.deleteGenre(id);
            redirectAttributes.addFlashAttribute("alert_success", "Genre with id: \"" + id + "\" was deleted.");
        } catch (Exception ex) {
            log.error("Performer "+id+" cannot be deleted (it is included in an order)");
            log.error(NestedExceptionUtils.getMostSpecificCause(ex).getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", "Genre with id: \"" + id + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/genre/list").toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        GenreDTO genreDTO = genreFacade.findWithId(id);
        model.addAttribute("genreUpdate", genreDTO);
        return "genre/update";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable long id, @ModelAttribute("genreEdit") GenreCreateDTO genreCreateDTO, BindingResult bindingResult,
                         Model model,
                         UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", error);
            }
            for (FieldError error : bindingResult.getFieldErrors()) {
                model.addAttribute(error.getField() + "_error", true);
                log.trace("FieldError: {}", error);
            }
            model.addAttribute("genre", genreFacade.findWithId(id));
            return "genre/edit";
        }
        GenreDTO genreDTO = genreFacade.findWithId(id);
        genreDTO.setName(genreCreateDTO.getName());
        genreDTO.setDescription(genreCreateDTO.getDescription());
        genreFacade.updateGenre(genreDTO);
        return "redirect:" + uriBuilder.path("/genre/list").toUriString();
    }


}
