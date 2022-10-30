package com.paymentchain.customer.mappers;

import com.paymentchain.customer.dtos.CustomerOutDto;
import com.paymentchain.customer.entities.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerOutMapper {

    @Mappings({
            @org.mapstruct.Mapping(source = "lastname", target = "surname")
    })
    Customer customerOutDtoToCustomer(CustomerOutDto customerOutDto);

    @Mappings({
            @org.mapstruct.Mapping(source = "surname", target = "lastname")
    })
    CustomerOutDto customerToCustomerOutDto(Customer customer);


    List<CustomerOutDto> customerListToCustomerOutDtoList(List<Customer> customers);

}
