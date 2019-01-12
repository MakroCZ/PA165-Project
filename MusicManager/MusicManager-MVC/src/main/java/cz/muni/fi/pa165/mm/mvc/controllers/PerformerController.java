package cz.muni.fi.pa165.mm.mvc.controllers;

import cz.muni.fi.pa165.mm.api.dto.AlbumDTO;
import cz.muni.fi.pa165.mm.api.dto.PerformerCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.PerformerDTO;
import cz.muni.fi.pa165.mm.api.facade.AlbumFacade;
import cz.muni.fi.pa165.mm.api.facade.PerformerFacade;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
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
        model.addAttribute("performers", performerFacade.findByName(name));
        return "performer/list";
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
    public String create(@Valid @ModelAttribute("performerCreate") PerformerCreateDTO formBean,
            BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
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
        return "redirect:" + uriBuilder.path("/performer/list").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPerformer(@PathVariable Long id, Model model) {
        log.debug("editPerformer({id})");
        model.addAttribute("performerEdit", performerFacade.findById(id));
        return "performer/edit";
    }
    
    @RequestMapping(value= "/save", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("performerEdit") PerformerDTO formBean,
            BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("edit(performerEdit={}", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "performer/edit";
        }
        //update performer
        performerFacade.update(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success",
                "Modifications to performer: " + formBean.getName() + " was succesfully saved.");
        return "redirect:" + uriBuilder.path("/performer/list").encode().toUriString();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        PerformerDTO performer = performerFacade.findById(id);
        log.debug("delete({})", id);
        try {
            performerFacade.remove(id);
            redirectAttributes.addFlashAttribute("alert_success", "Performer \"" + performer.getName() + "\" was deleted.");
        } catch (Exception ex) {
            log.error("Performer "+id+" cannot be deleted (performer has some albums)");
            log.error(NestedExceptionUtils.getMostSpecificCause(ex).getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", "Performer \"" + performer.getName() + "\" cannot be deleted. "
                    + "You must first delete all performer's albums.");
        }
        return "redirect:" + uriBuilder.path("/performer/list").toUriString();
    }
}
