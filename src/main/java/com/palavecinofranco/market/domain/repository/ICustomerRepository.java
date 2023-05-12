package com.palavecinofranco.market.domain.repository;

import com.palavecinofranco.market.domain.dto.CustomerDTO;
import com.palavecinofranco.market.domain.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository{

    List<CustomerDTO> getAll();
    Optional<CustomerDTO> getCustomer(Long id);
    CustomerDTO save(CustomerDTO customerDTO);

    Optional<List<CustomerDTO>> getCustomerByFullName(String name, String lastname);
    void delete(Long id);

}
