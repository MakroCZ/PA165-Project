package cz.muni.fi.pa165.mm.mvc.controllers;

import cz.muni.fi.pa165.mm.api.dto.SongCreateDTO;
import cz.muni.fi.pa165.mm.api.facade.SongFacade;
import cz.muni.fi.pa165.mm.sf.facade.SongFacadeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * Created by lsuchanek on 14.12.2018.
 */
@Controller
@RequestMapping("/song")
public class SongController {
    final static Logger log = LoggerFactory.getLogger(SongController.class);
//
//    private SongFacade songFacade = new SongFacadeImpl();
//
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
//        model.addAttribute("songs", songFacade.getAllSongs());
        return "song/list";
    }
//
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newSong(Model model) {
        log.debug("new()");
        model.addAttribute("songCreate", new SongCreateDTO());
        return "song/new";
    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String create(@Valid @ModelAttribute("songCreate") SongCreateDTO formBean, BindingResult bindingResult,
//                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
//        log.debug("create(formBean={})", formBean);
//        //in case of validation error forward back to the the form
//        if (bindingResult.hasErrors()) {
//            for (ObjectError ge : bindingResult.getGlobalErrors()) {
//                log.trace("ObjectError: {}", ge);
//            }
//            for (FieldError fe : bindingResult.getFieldErrors()) {
//                model.addAttribute(fe.getField() + "_error", true);
//                log.trace("FieldError: {}", fe);
//            }
//            return "song/new";
//        }
//        //create product
//        Long id = songFacade.createSong(formBean);
//        //report success
//        redirectAttributes.addFlashAttribute("alert_success", "Song " + id + " was created");
//        return "redirect:" + uriBuilder.path("/song/list").toUriString();
//    }
}
