package com.website.demo.address;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(Address requestAddress){

        Address address;
        boolean addressExists = addressRepository.exists(Example.of(requestAddress));
        if (addressExists) {
            address = addressRepository.findOne(Example.of(requestAddress)).get();
        } else {
            address = requestAddress;
            addressRepository.save(address);
        }
        return address;
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
