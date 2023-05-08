package com.palavecinofranco.market.persistence.crud;

import com.palavecinofranco.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IProductoCrudRepository extends CrudRepository<Producto, Long> {
    //Query method
    List<Producto> findByIdCategoriaOrderByNombreAsc(Long id);
    Optional<List<Producto>> findByCantidadDeStockLessThanAndEstado(Integer cantidadDeStock, Boolean estado );
}
