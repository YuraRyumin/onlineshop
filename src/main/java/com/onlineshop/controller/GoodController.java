package com.onlineshop.controller;

import com.onlineshop.domain.Good;
import com.onlineshop.dto.GoodDTO;
import com.onlineshop.service.GoodService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GoodController {
    private final GoodService goodService;

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping("/goodslist")
    public Iterable<GoodDTO> listOfGoods(){
        return goodService.getAllGoods();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/creategood")
    public void createCatalog(@RequestBody Good good){
        goodService.saveGood(good);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/savegood")
    public void saveCatalog(@RequestBody Good good){
        goodService.saveGood(good);
    }
}
