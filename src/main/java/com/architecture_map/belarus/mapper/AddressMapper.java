package com.architecture_map.belarus.mapper;

import com.architecture_map.belarus.dto.AddressDto;
import com.architecture_map.belarus.entity.Address;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface AddressMapper {

    AddressDto toAddressDto(Address address);

    Address toAddress(AddressDto addressDto);
}
