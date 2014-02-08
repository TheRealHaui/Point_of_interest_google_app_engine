package com.ase.poi.web;
import com.ase.poi.domain.PointOfInterest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

import java.util.List;

@RequestMapping("/pointofinterests")
@Controller
@RooWebScaffold(path = "pointofinterests", formBackingObject = PointOfInterest.class)
@RooWebJson(jsonObject = PointOfInterest.class)
public class PointOfInterestController {


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        PointOfInterest pointOfInterest = PointOfInterest.findPointOfInterest(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (pointOfInterest == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        pointOfInterest.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        PointOfInterest pointOfInterest = PointOfInterest.fromJsonToPointOfInterest(json);
        if (pointOfInterest.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (PointOfInterest pointOfInterest: PointOfInterest.fromJsonArrayToPointOfInterests(json)) {
            pointOfInterest.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {

        HttpHeaders headers = new HttpHeaders();

        try{

        PointOfInterest pointOfInterest = PointOfInterest.fromJsonToPointOfInterest(json);
        pointOfInterest.persist();

        headers.add("Content-Type", "application/json");

        }catch (Exception e)
        {
            return new ResponseEntity<String>(e.toString(),headers, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }


    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<PointOfInterest> result = PointOfInterest.findAllPointOfInterests();
        return new ResponseEntity<String>(PointOfInterest.toJsonArray(result), headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        PointOfInterest pointOfInterest = PointOfInterest.findPointOfInterest(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (pointOfInterest == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(pointOfInterest.toJson(), headers, HttpStatus.OK);
    }

}
