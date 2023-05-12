package com.palavecinofranco.market.persistence.mapper;
import com.palavecinofranco.market.domain.dto.CustomerDTO;
import com.palavecinofranco.market.persistence.entity.Cliente;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface CustomerMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellidos", target = "lastname"),
            @Mapping(source = "celular", target = "phone"),
            @Mapping(source = "direccion", target = "addres"),
            @Mapping(source = "correoElectronico", target = "email"),
    })
    CustomerDTO toCustomer(Cliente cliente);
    List<CustomerDTO> toCustomers(List<Cliente> clientes);

    @InheritInverseConfiguration @Mapping(target = "compras", ignore = true)
    Cliente toCliente(CustomerDTO customerDTO);

}
