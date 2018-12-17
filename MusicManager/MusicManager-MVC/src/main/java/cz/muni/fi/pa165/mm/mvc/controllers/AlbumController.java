package cz.muni.fi.pa165.mm.mvc.controllers;

import cz.muni.fi.pa165.mm.api.dto.AlbumCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.AlbumDTO;
import cz.muni.fi.pa165.mm.api.dto.PerformerDTO;
import cz.muni.fi.pa165.mm.api.facade.AlbumFacade;
import cz.muni.fi.pa165.mm.api.facade.PerformerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            UriComponentsBuilder uriBuilder
    ) {
        log.debug("create(formBean={})", formBean);

        Long id = albumFacade.createAlbum(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Album " + id + " was created");
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
}
