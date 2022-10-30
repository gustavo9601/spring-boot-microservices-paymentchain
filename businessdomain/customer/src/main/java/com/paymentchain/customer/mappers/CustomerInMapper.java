package com.paymentchain.customer.mappers;

import com.paymentchain.customer.dtos.CustomerInDto;
import com.paymentchain.customer.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerInMapper {

    /*
     * Los mappings permiten definir campos de nmbres que vienen diferente entre Objectos
     * */
    @Mappings({
            @Mapping(source = "lastname", target = "surname")
    })
    Customer customerInDtoToCustomer(CustomerInDto customerInDto);


    @Mappings({
            @Mapping(source = "surname", target = "lastname")
    })
    CustomerInDto customerToCustomerInDto(Customer customer);

    // No es necesario el mapper para listas, ya que ya se definio arriba y el mapper lo tomara
    List<Customer> customerInDtosToCustomerList(List<CustomerInDto> customerInDtos);
}
