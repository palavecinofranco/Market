package com.palavecinofranco.market.persistence;

import com.palavecinofranco.market.domain.dto.ProductDTO;
import com.palavecinofranco.market.domain.repository.IProductRepository;
import com.palavecinofranco.market.persistence.crud.IProductoCrudRepository;
import com.palavecinofranco.market.persistence.entity.Producto;
import com.palavecinofranco.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements IProductRepository {
    @Autowired
    private IProductoCrudRepository crudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<ProductDTO> getAll(){
        List<Producto> productos = (List<Producto>)crudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<ProductDTO>> getByCategory(Long categoryId) {
        List<Producto> productos = crudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<ProductDTO>> getScarseProducts(Integer quantity) {
        Optional<List<Producto>> productos = crudRepository.findByCantidadDeStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<ProductDTO> getProduct(Long id) {
        return crudRepository.findById(id).map(producto -> mapper.toProductDTO(producto));
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Producto producto = mapper.toProducto(productDTO);
        return mapper.toProductDTO(crudRepository.save(producto));
    }

    @Override
    public void delete(Long productId) {
        crudRepository.deleteById(productId);
    }

}
