package com.palavecinofranco.market.persistence;

import com.palavecinofranco.market.domain.dto.CustomerDTO;
import com.palavecinofranco.market.domain.repository.ICustomerRepository;
import com.palavecinofranco.market.persistence.crud.IClienteCrudRepository;
import com.palavecinofranco.market.persistence.entity.Cliente;
import com.palavecinofranco.market.persistence.entity.Producto;
import com.palavecinofranco.market.persistence.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository implements ICustomerRepository {
    @Autowired
    public IClienteCrudRepository clienteCrudRepository;
    @Autowired
    public CustomerMapper mapper;

    @Override
    public List<CustomerDTO> getAll() {
        return mapper.toCustomers((List<Cliente>)clienteCrudRepository.findAll());
    }

    @Override
    public Optional<CustomerDTO> getCustomer(Long id) {
        return clienteCrudRepository.findById(id).map(cliente -> mapper.toCustomer(cliente));
    }

    @Override
    public Optional<List<CustomerDTO>> getCustomerByFullName(String name, String lastname) {
        Optional<List<Cliente>> clientes = clienteCrudRepository.findByNombreAndApellidos(name, lastname);
        return clientes.map(cli -> mapper.toCustomers(cli));
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        return mapper.toCustomer(clienteCrudRepository.save(mapper.toCliente(customerDTO)));
    }

    @Override
    public void delete(Long id) {
        clienteCrudRepository.deleteById(id);
    }
}
