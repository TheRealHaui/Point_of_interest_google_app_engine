package com.ase.poi.web;
import com.ase.poi.domain.PointOfInterest;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/pointofinterests")
@Controller
@RooWebScaffold(path = "pointofinterests", formBackingObject = PointOfInterest.class)
@RooWebJson(jsonObject = PointOfInterest.class)
public class PointOfInterestController {
}
