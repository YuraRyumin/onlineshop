package com.onlineshop.controller;

import com.onlineshop.domain.Color;
import com.onlineshop.dto.ColorDTO;
import com.onlineshop.service.ColorService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ColorController {
    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/colors")
    public Iterable<ColorDTO> listOfColors(){
        return colorService.getAllColors();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createcolor")
    public void createColor(@RequestBody Color color){
        colorService.saveColor(color);
    }
}
