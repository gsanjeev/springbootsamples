package com.laxtech.example.repository;

import com.laxtech.example.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
