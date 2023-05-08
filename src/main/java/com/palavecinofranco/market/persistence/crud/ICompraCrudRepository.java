package com.palavecinofranco.market.persistence.crud;


import com.palavecinofranco.market.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ICompraCrudRepository extends CrudRepository<Compra, Long> {
    Optional<List<Compra>> findByIdCliente(Long idCliente);
}
