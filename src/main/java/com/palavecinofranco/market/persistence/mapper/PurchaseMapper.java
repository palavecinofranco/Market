package com.palavecinofranco.market.persistence.mapper;

import com.palavecinofranco.market.domain.dto.PurchaseDTO;
import com.palavecinofranco.market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
            @Mapping(source = "idCompra", target = "id"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioDePago", target = "paymentMethod"),
            @Mapping(source = "productos", target = "items"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "comentario", target = "comment")
    })
    PurchaseDTO toPurchaseDTO(Compra compra);

    List<PurchaseDTO> toPurchasesDTO(List<Compra> compras);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "cliente", ignore = true)
    })
    Compra toCompra(PurchaseDTO purchaseDTO);
}
