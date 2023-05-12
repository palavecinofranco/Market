package com.palavecinofranco.market.persistence.crud;

import com.palavecinofranco.market.persistence.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IClienteCrudRepository extends CrudRepository<Cliente, Long> {

    public Optional<List<Cliente>> findByNombreAndApellidos(String nombre, String apellidos);
}
