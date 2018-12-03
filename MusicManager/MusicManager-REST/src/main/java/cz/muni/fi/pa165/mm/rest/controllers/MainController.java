package cz.muni.fi.pa165.mm.rest.controllers;

import cz.muni.fi.pa165.mm.rest.ApiUris;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("")
@RestController
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {
        Map<String, String> resourcesMap = new HashMap<>();
        resourcesMap.put("products_uri", ApiUris.ROOT_URI_PERFORMERS);
        resourcesMap.put("orders_uri", ApiUris.ROOT_URI_ALBBUMS);
        resourcesMap.put("users_uri", ApiUris.ROOT_URI_SONGS);
        resourcesMap.put("categories_uri", ApiUris.ROOT_URI_GENRES);
        return Collections.unmodifiableMap(resourcesMap);
    }
}
