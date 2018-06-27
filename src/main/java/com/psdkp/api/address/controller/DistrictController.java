package com.psdkp.api.address.controller;

import com.psdkp.api.address.domain.District;
import com.psdkp.api.address.domain.Input;
import com.psdkp.api.address.service.address.impl.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address/district")
public class DistrictController {

    @Autowired
    private DistrictServiceImpl districtService;

    @GetMapping
    public Object getFindByName(
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) Integer id,
            @PageableDefault(sort = { "id" }, value = 5000) Pageable pageable
    ) {
        if (id != null) {
            return districtService.findById(id);
        } else {
            return districtService.findAll(name, pageable);
        }
    }

    @PostMapping
    public Object saveDistrict(@RequestBody District district) {
        return districtService.save(district);
    }

    @PutMapping
    public Object editDistrict(@RequestBody District district) {
        return districtService.edit(district);
    }

    @DeleteMapping(value = "/del")
    public Object removeDistrict(@RequestBody Input in) {
        return districtService.del(in.getId());
    }
}
