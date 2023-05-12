package com.palavecinofranco.market.web.controller;

import com.palavecinofranco.market.domain.dto.ProductDTO;
import com.palavecinofranco.market.domain.dto.ProductDTOSchema;
import com.palavecinofranco.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/products")
@Tag(name = "Products", description = "Controller for products operations")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    @Operation(description = "Get all supermarket products")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(description = "Search a product with an ID")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Product found"),
            @ApiResponse (responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductDTO> getProduct(
            @Parameter(description = "The id of the product", required = true, example = "7")
            @PathVariable("id") Long id){
        return productService.getProduct(id)
                .map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{id}")
    @Operation(description = "Search a product with a category")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Product found"),
            @ApiResponse (responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<List<ProductDTO>> getByCategory(
            @Parameter(description = "The id of the category", required = true, example = "1")
            @PathVariable("id") Long idCategory){

        List<ProductDTO> products = productService.getByCategory(idCategory).orElse(null);
        return products != null && !products.isEmpty() ?
                new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<List<ProductDTO>>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    @Operation(description = "Create a product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ProductDTOSchema.class))))
    @ApiResponse(responseCode = "201", description = "CREATED",
                content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class))})
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.save(productDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete a product by id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Product deleted"),
            @ApiResponse (responseCode = "404", description = "Product not found")
    })
    public ResponseEntity delete(@PathVariable("id") Long id){
        return new ResponseEntity(productService.delete(id) ? HttpStatus.OK
                : HttpStatus.NOT_FOUND);
    }

}
