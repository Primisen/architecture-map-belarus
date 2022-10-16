package by.architecture.map.mapper;

import by.architecture.map.dto.AddressDto;
import by.architecture.map.entity.Address;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface AddressMapper {

    AddressDto toAddressDto(Address address);

    Address toAddress(AddressDto addressDto);
}
