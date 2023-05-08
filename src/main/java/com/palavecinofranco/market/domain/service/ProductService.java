package com.palavecinofranco.market.domain.service;

import com.palavecinofranco.market.domain.dto.ProductDTO;
import com.palavecinofranco.market.domain.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    public List<ProductDTO> getAll(){
        return productRepository.getAll();
    }

    public Optional<ProductDTO> getProduct(Long productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<ProductDTO>> getByCategory(Long categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public ProductDTO save(ProductDTO productDTO){
        return productRepository.save(productDTO);
    }

    public boolean delete(Long productId){
        return getProduct(productId).map(productDTO -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }

}
