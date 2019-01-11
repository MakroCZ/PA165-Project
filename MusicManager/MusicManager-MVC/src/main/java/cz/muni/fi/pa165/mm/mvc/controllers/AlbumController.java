package cz.muni.fi.pa165.mm.mvc.controllers;

import cz.muni.fi.pa165.mm.api.dto.AlbumCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.AlbumDTO;
import cz.muni.fi.pa165.mm.api.dto.PerformerDTO;
import cz.muni.fi.pa165.mm.api.dto.SongDTO;
import cz.muni.fi.pa165.mm.api.facade.AlbumFacade;
import cz.muni.fi.pa165.mm.api.facade.PerformerFacade;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Václav Stehlík; 487580
 */

@Controller
@RequestMapping("/album")
public class AlbumController {
    private final static Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private AlbumFacade albumFacade;

    @Autowired
    private PerformerFacade performerFacade;

    @Autowired
    private SongFacade songFacade;

    @ModelAttribute("performers")
    public List<PerformerDTO> performers() {
        return performerFacade.findAll();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("albums", albumFacade.findAll());
        return "album/list";
    }

    @RequestMapping(value = "/list/{name}", method = RequestMethod.GET)
    public String findByName(@PathVariable String name, Model model) {
        model.addAttribute("albums", this.find(name));
        return "album/list";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        log.debug("detail({})", id);
        AlbumDTO album = albumFacade.findById(id);
        model.addAttribute("album", album);
        model.addAttribute("songs", findSongs(album));
        return "album/detail";
    }

    private List<SongDTO> findSongs(AlbumDTO album) {
        List<SongDTO> filtered = new ArrayList<>();
        List<SongDTO> all = songFacade.findAll();
        for (SongDTO a : all) {
            if (a.getAlbum().equals(album)) {
                filtered.add(a);
            }
        }
        return filtered;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAlbum(Model model) {
        log.debug("new()");
        AlbumCreateDTO albumCreateDTO = new AlbumCreateDTO();
        albumCreateDTO.setName("default");
        model.addAttribute("albumCreate", albumCreateDTO);
        return "album/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("albumCreate") AlbumCreateDTO formBean,
            RedirectAttributes redirectAttributes,
            BindingResult bindingResult,
            Model model,
            UriComponentsBuilder uriBuilder
            ) {
        log.debug("create(albumCreate={})", formBean);
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "album/new";
        }

        Long id = albumFacade.createAlbum(formBean);
        AlbumDTO album = albumFacade.findById(id);
        redirectAttributes.addFlashAttribute("alert_success", "Album \"" + album.getName() + "\" was created");
        return "redirect:" + uriBuilder.path("/album/list").toUriString();
    }

    private List<AlbumDTO> find(String name) {
        List<AlbumDTO> albums = albumFacade.findAll();
        List<AlbumDTO> filtered = new ArrayList<>();
        for (AlbumDTO album : albums) {
            if (album.getName().toLowerCase().contains(name.toLowerCase())) {
                filtered.add(album);
            }
        }
        return filtered;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        AlbumDTO album = albumFacade.findById(id);
        log.debug("delete({})", id);
        try {
            albumFacade.deleteAlbum(id);
            redirectAttributes.addFlashAttribute("alert_success", "Album \"" + album.getName() + "\" was deleted.");
        } catch (Exception ex) {
            log.error("Album "+id+" cannot be deleted.");
            log.error(NestedExceptionUtils.getMostSpecificCause(ex).getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", "Album \"" + album.getName() + "\" cannot be deleted because it is not empty.");
        }
        return "redirect:" + uriBuilder.path("/album/list").toUriString();
    }
}
