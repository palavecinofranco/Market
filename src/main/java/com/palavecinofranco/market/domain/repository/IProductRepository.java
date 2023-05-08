package com.palavecinofranco.market.domain.repository;

import java.util.List;
import java.util.Optional;
import com.palavecinofranco.market.domain.dto.ProductDTO;

public interface IProductRepository {

    List <ProductDTO> getAll();
    Optional<List<ProductDTO>> getByCategory(Long categoryId);
    Optional<List<ProductDTO>> getScarseProducts(Integer quantity);
    Optional<ProductDTO> getProduct(Long id);
    ProductDTO save(ProductDTO productDTO);
    void delete(Long productId);

}
