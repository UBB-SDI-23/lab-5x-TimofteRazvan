package com.example.FirstSpring.Controller;

import com.example.FirstSpring.Entity.*;
import com.example.FirstSpring.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@CrossOrigin("http://localhost:3000")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/addresses")
    public List<Integer> findAllAddresses() {
        List<Address> addressList = addressService.getAllAddresses();
        ArrayList<Integer> idList = new ArrayList<>();
        for (Address address : addressList) {
            idList.add(address.getId());
        }
        return idList;
    }

    @GetMapping("/addresses/page/{offset}/{pageSize}")
    private List<AddressDTO> getSpousesWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<AddressDTO> addressList = addressService.findAddressesWithPagination(offset, pageSize);
        return addressList.getContent();
    }

    @GetMapping("/addresses-details")
    public List<Address> findAllAddressesDetails() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/addresses/{id}")
    public AddressDTO findAddressDTO(@PathVariable int id) {
        try {
            return addressService.findAddressDTO(id);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/addresses")
    public void createAddress(@RequestBody AddressDTO addressDTO) {
        addressService.saveAddress(addressDTO);
    }

    @PutMapping("/addresses/{id}")
    public void updateAddress(@PathVariable int id, @RequestBody Address address) {
        try {
            addressService.updateAddress(id, address);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @DeleteMapping("/addresses/{id}")
    public void deleteAddress(@PathVariable int id) {
        try {
            addressService.deleteAddress(id);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
