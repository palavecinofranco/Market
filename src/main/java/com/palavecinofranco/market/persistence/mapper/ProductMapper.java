package com.palavecinofranco.market.persistence.mapper;

import com.palavecinofranco.market.domain.dto.ProductDTO;
import com.palavecinofranco.market.persistence.entity.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "id", target = "idProduct"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "idCategory"),
            @Mapping(source = "precioDeVenta", target = "price"),
            @Mapping(source = "cantidadDeStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category")
    })
    ProductDTO toProductDTO(Producto producto);
    List<ProductDTO> toProducts(List<Producto> productos);

    @InheritInverseConfiguration @Mapping(target = "codigoDeBarras", ignore = true)
    Producto toProducto(ProductDTO productDTO);

}
