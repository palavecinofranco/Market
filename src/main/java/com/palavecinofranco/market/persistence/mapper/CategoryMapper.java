package com.palavecinofranco.market.persistence.mapper;

import com.palavecinofranco.market.domain.dto.CategoryDTO;
import com.palavecinofranco.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "id", target = "idCategory"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    CategoryDTO toCategory(Categoria categoria);

    @InheritInverseConfiguration @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(CategoryDTO category);

}
