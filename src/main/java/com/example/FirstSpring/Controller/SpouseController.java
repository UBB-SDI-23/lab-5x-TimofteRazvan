package com.example.FirstSpring.Controller;

import com.example.FirstSpring.Entity.*;
import com.example.FirstSpring.Entity.Spouse;
import com.example.FirstSpring.Service.SpouseService;
import com.example.FirstSpring.Validator.SpouseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebInputException;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@CrossOrigin("http://localhost:3000")
public class SpouseController {
    @Autowired
    SpouseService spouseService;
    private final SpouseValidator spouseValidator = new SpouseValidator();

    @GetMapping("/spouses")
    public List<Integer> findAllSpouses() {
        List<Spouse> spouseList = spouseService.getAllSpouses();
        ArrayList<Integer> idList = new ArrayList<>();
        for (Spouse spouse : spouseList) {
            idList.add(spouse.getId());
        }
        return idList;
    }

    @GetMapping("/spouses/page/{offset}/{pageSize}")
    private List<Spouse> getSpousesWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Spouse> spouseList = spouseService.findSpousesWithPagination(offset, pageSize);
        return spouseList.getContent();
    }

    @GetMapping("/spouses-details")
    public List<Spouse> findAllSpousesDetails() {
        return spouseService.getAllSpouses();
    }

    @GetMapping("/spouses/{id}")
    public Spouse findSpouse(@PathVariable int id) {
        try {
            return spouseService.getSpouse(id);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/spouses")
    public Object createSpouse(@RequestBody Spouse spouse) {
        Errors errors = new BeanPropertyBindingResult(spouse,"spouse");
        try {
            spouseValidator.validate(spouse, errors);
            return spouseService.createSpouse(spouse);
        }
        catch (ServerWebInputException e) {
            return e.getReason();
        }
    }

    @PutMapping("/spouses/{id}")
    public void updateSpouse(@PathVariable int id, @RequestBody Spouse spouse) {
        try {
            spouseService.updateSpouse(id, spouse);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @DeleteMapping("/spouses/{id}")
    public void deleteSpouse(@PathVariable int id) {
        try {
            spouseService.deleteSpouse(id);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/spouses/filter/age/{age}")
    public List<Spouse> findSpouseByAge(@PathVariable int age) {
        return spouseService.findSpouseByAge(age);
    }

    @GetMapping("/spouses/compare/age")
    public List<SpouseDTO> getComparison() {
        return spouseService.getComparison();
    }

    @GetMapping("/spouses/maxPage")
    public Long getMaxPage(){
        return spouseService.getSpouseMaxPage();
    }
}
