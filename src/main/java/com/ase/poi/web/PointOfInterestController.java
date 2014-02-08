package com.ase.poi.web;
import com.ase.poi.domain.PointOfInterest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

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

       try{

           PointOfInterest pointOfInterest = PointOfInterest.fromJsonToPointOfInterest(json);

            if (pointOfInterest.merge() == null) {
               return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
           }

           return new ResponseEntity<String>(headers, HttpStatus.OK);


       }catch (Exception e)
       {
           return new ResponseEntity<String>(e.toString(),headers, HttpStatus.BAD_REQUEST);
       }

    }


    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {

        HttpHeaders headers = new HttpHeaders();

        try{

           for (PointOfInterest pointOfInterest: PointOfInterest.fromJsonArrayToPointOfInterests(json)) {
               pointOfInterest.persist();
           }

           headers.add("Content-Type", "application/json");

       }catch (Exception e)
       {
           return new ResponseEntity<String>(e.toString(),headers, HttpStatus.BAD_REQUEST);
       }


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


    @RequestMapping(value = "/", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson(@RequestParam(value="name",required = false)String name,
                                           @RequestParam(value="description",required = false)String description,
                                           @RequestParam(value="category",required = false)String category,
                                           @RequestParam(value="creator",required = false)String creator,
                                           @RequestParam(value="latitude",required = false)String latitude,
                                           @RequestParam(value="longitude",required = false)String longitude ) {

        HttpHeaders headers = new HttpHeaders();
        List<PointOfInterest> result;

        try{

            headers.add("Content-Type", "application/json; charset=utf-8");
            List<PointOfInterest> queryResult = PointOfInterest.findAllPointOfInterests();

            result = new ArrayList<PointOfInterest>(queryResult.size());

            for (PointOfInterest poi : queryResult) {
                result.add(new PointOfInterest(poi));
            }

            Iterator<PointOfInterest> iter = result.iterator();
            PointOfInterest poi;


            while (iter.hasNext()){
                /*
                System.out.println(111);
                if ( ( name != null | name.isEmpty()  )
                        &( description != null | description.isEmpty()  )
                        &( category != null | category.isEmpty()  )
                        &( creator != null | creator.isEmpty()  )
                        &( latitude != null | latitude.isEmpty()  )
                        &( longitude != null | longitude.isEmpty() ) ){
                    System.out.println(222);
                    break;
                }
                */

                poi = iter.next();

                if ( ( name != "" && !poi.getName().equalsIgnoreCase(name) )
                        ||( description != "" && !poi.getDescription().equalsIgnoreCase(description) )
                        ||( category != "" && !poi.getCategory().equalsIgnoreCase(category) )
                        ||( creator != "" && !poi.getCreator().equalsIgnoreCase(creator) )
                        ||( latitude != "" && !poi.getLatitude().toString().equalsIgnoreCase(latitude) )
                        ||( longitude != "" && !poi.getLongitude().toString().equalsIgnoreCase(longitude) ) ){
                    iter.remove();
                }

            }


        }catch (Exception e)
        {
            Logger.getLogger(PointOfInterest.class.getName()).severe(e.toString());
            return new ResponseEntity<String>(e.toString(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

            return new ResponseEntity<String>(PointOfInterest.toJsonArray(result), headers, HttpStatus.OK);

    }



    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {

        HttpHeaders headers = new HttpHeaders();
        List<PointOfInterest> queryResult;

        try{

            headers.add("Content-Type", "application/json; charset=utf-8");
            queryResult = PointOfInterest.findAllPointOfInterests();

        }catch (Exception e)
        {
            Logger.getLogger(PointOfInterest.class.getName()).severe(e.toString());
            return new ResponseEntity<String>(e.toString(),headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(PointOfInterest.toJsonArray(queryResult), headers, HttpStatus.OK);

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
