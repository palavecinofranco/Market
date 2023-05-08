package com.palavecinofranco.market.persistence;

import com.palavecinofranco.market.domain.dto.PurchaseDTO;
import com.palavecinofranco.market.domain.repository.IPurchaseRepository;
import com.palavecinofranco.market.persistence.crud.ICompraCrudRepository;
import com.palavecinofranco.market.persistence.entity.Compra;
import com.palavecinofranco.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements IPurchaseRepository {
    @Autowired
    private ICompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<PurchaseDTO> getAll() {
        return mapper.toPurchasesDTO((List<Compra>)compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<PurchaseDTO>> getByClient(Long clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchasesDTO(compras));
    }

    @Override
    public PurchaseDTO save(PurchaseDTO purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchaseDTO(compraCrudRepository.save(compra));
    }
}
