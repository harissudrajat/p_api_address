package com.psdkp.api.address.controller;

import com.psdkp.api.address.domain.City;
import com.psdkp.api.address.domain.Input;
import com.psdkp.api.address.service.address.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/address/city")
public class CityController{

    @Autowired
    private CityServiceImpl cityService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            @PageableDefault(sort = { "id" }, value = 3000) Pageable pageable
    ){
        if (id!=null){
            return cityService.findById(id);
        } else {
            return cityService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object save(@RequestBody City city){
        return cityService.save(city);
    }

    @PutMapping
    public Object edit(@RequestBody City city){
        return cityService.edit(city);
    }

    @DeleteMapping(value = "/del")
    public Object del(@RequestBody Input in){
        return cityService.del(in.getId());
    }


}