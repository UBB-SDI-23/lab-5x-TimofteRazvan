package com.example.FirstSpring.Service;

import com.example.FirstSpring.Entity.*;
import com.example.FirstSpring.Repository.AddressRepository;
import com.example.FirstSpring.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public List<AddressDTO> findAllAddressesAndMap() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(this::mapAddressToAddressDTO)
                .collect(Collectors.toList());
    }

    private AddressDTO mapAddressToAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setLine1(address.getLine1());
        addressDTO.setLine2(address.getLine2());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setCountry(address.getCountry());
        if(address.getEmployee() != null) {
            addressDTO.setEmployee(address.getEmployee().getId());
        }
        return addressDTO;
    }

    public void saveAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setLine1(addressDTO.getLine1());
        address.setLine2(addressDTO.getLine2());
        address.setZipCode(addressDTO.getZipCode());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        if(employeeRepository.existsById(addressDTO.getEmployee())) {
            address.setEmployee(employeeRepository.getById(addressDTO.getEmployee()));
        }
        addressRepository.save(address);
    }

    public AddressDTO findAddressDTO(int id) throws Exception {
        if(!addressRepository.existsById(id)) {
            throw new Exception("getAddress(): Incorrect ID!");
        }
        return mapAddressToAddressDTO(addressRepository.findById(id).get());
    }

    public Address getAddress(int id) throws Exception {
        if(!addressRepository.existsById(id)) {
            throw new Exception("getAddress(): Incorrect ID!");
        }
        return addressRepository.findById(id).get();
    }

    public void createAddress(Address address) {
        addressRepository.save(address);
    }

    public void updateAddress(int id, Address address) throws Exception {
        if(!addressRepository.existsById(id)) {
            throw new Exception("updateAddress(): Incorrect ID!");
        }
        Address addressToUpdate = addressRepository.getOne(id);
        addressToUpdate.setLine1(address.getLine1());
        addressToUpdate.setLine2(address.getLine2());
        addressToUpdate.setZipCode(address.getZipCode());
        addressToUpdate.setCity(address.getCity());
        addressToUpdate.setState(address.getState());
        addressToUpdate.setCountry(address.getCountry());
        addressToUpdate.setEmployee(address.getEmployee());
        addressRepository.save(addressToUpdate);
    }

    public void deleteAddress(int id) throws Exception {
        if(!addressRepository.existsById(id)) {
            throw new Exception("deleteAddress(): Incorrect ID!");
        }
        addressRepository.deleteById(id);
    }

    public Page<AddressDTO> findAddressesWithPagination(int offset, int pageSize) {
        Page<Address> addresses = addressRepository.findAll(PageRequest.of(offset,pageSize));
        return addresses.map(this::mapAddressToAddressDTO);
    }

    public Long getAddressMaxPage(){
        return addressRepository.findNrAddresses();
    }
}
