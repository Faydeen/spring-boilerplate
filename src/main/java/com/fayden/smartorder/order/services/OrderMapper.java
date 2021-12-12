package com.fayden.smartorder.order.services;

import com.fayden.smartorder.order.persistance.CarRentalInformationEntity;
import com.fayden.smartorder.order.persistance.ClientInformationEntity;
import com.fayden.smartorder.order.persistance.OrderEntity;
import com.fayden.smartorder.order.web.CarRentalInformationDTO;
import com.fayden.smartorder.order.web.ClientInformationDTO;
import com.fayden.smartorder.order.web.OrderDTO;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    CarRentalInformationEntity toEntity(CarRentalInformationDTO carRentalInformationDTO);

    ClientInformationEntity toEntity(ClientInformationDTO clientInformationDTO);

    OrderDTO toDTO(OrderEntity orderEntity);
}
