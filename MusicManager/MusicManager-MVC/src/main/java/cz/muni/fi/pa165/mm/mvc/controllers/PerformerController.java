package cz.muni.fi.pa165.mm.mvc.controllers;

import cz.muni.fi.pa165.mm.api.dto.AlbumDTO;
import cz.muni.fi.pa165.mm.api.dto.PerformerCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.PerformerDTO;
import cz.muni.fi.pa165.mm.api.facade.AlbumFacade;
import cz.muni.fi.pa165.mm.api.facade.PerformerFacade;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 *
 * @author Marek
 */
@Controller
@RequestMapping("/performer")
public class PerformerController {
    
    private final static Logger log = LoggerFactory.getLogger(PerformerController.class);
    
    @Autowired
    private PerformerFacade performerFacade;
    
    @Autowired
    private AlbumFacade albumFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("list()");
        model.addAttribute("performers", performerFacade.findAll());
        return "performer/list";
    }
    
    @RequestMapping(value = "/list/{name}", method = RequestMethod.GET)
    public String find(@PathVariable String name, Model model) {
        model.addAttribute("performers", findByName(name));
        return "performer/list";
    }

    private List<PerformerDTO> findByName(String name){
        List<PerformerDTO> allPerformers = performerFacade.findAll();
        List<PerformerDTO> filtered = new ArrayList<>();
        for(PerformerDTO performer: allPerformers){
            if(performer.getName().toLowerCase().contains(name.toLowerCase())){
                filtered.add(performer);
            }
        }
        return filtered;
    }
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        log.debug("detail({})", id);
        PerformerDTO p = performerFacade.findById(id);
        model.addAttribute("performer", p);
        model.addAttribute("albums", findAlbums(p));
        return "performer/detail";
    }
    
    private List<AlbumDTO> findAlbums(PerformerDTO p) {
        List<AlbumDTO> filtered = new ArrayList<>();
        List<AlbumDTO> all = albumFacade.findAll();
        for (AlbumDTO a : all) {
            if (a.getPerformer().equals(p)) {
                filtered.add(a);
            }
        }
        return filtered;
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPerformer(Model model) {
        log.debug("newPerformer()");
        model.addAttribute("performerCreate", new PerformerCreateDTO());
        return "performer/new";
    }
    
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("performerCreate") PerformerCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(performerCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "performer/new";
        }
        //create performer
        Long id = performerFacade.create(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Performer " + id + " was created");
        return "redirect:" + uriBuilder.path("/performer/detail/{id}").buildAndExpand(id).encode().toUriString();
    }
}
