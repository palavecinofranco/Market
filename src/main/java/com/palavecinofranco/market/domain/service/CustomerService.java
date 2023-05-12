package com.palavecinofranco.market.domain.service;

import com.palavecinofranco.market.domain.dto.CustomerDTO;
import com.palavecinofranco.market.domain.dto.ProductDTO;
import com.palavecinofranco.market.domain.repository.ICustomerRepository;
import com.palavecinofranco.market.domain.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    public List<CustomerDTO> getAll(){
        return customerRepository.getAll();
    }

    public Optional<CustomerDTO> getCustomer(Long id){
        return customerRepository.getCustomer(id);
    }

    public Optional<List<CustomerDTO>> getByFullName(String name, String lastname){
        return customerRepository.getCustomerByFullName(name, lastname);
    }

    public CustomerDTO save(CustomerDTO customerDTO){
        return customerRepository.save(customerDTO);
    }

    public boolean delete(Long id){
        return getCustomer(id).map(customer -> {
            customerRepository.delete(id);
            return true;
        }).orElse(false);
    }

}
