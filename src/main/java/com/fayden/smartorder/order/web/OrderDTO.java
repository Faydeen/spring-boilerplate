package com.fayden.smartorder.order.web;

import com.fayden.smartorder.order.persistance.CarRentalInformationEntity;
import com.fayden.smartorder.order.persistance.ClientInformationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "so_order")
@Getter
@Setter
public class OrderDTO {
    @Id
    private UUID id;

    @Embedded
    private ClientInformationDTO clientInformation;

    @Embedded
    private CarRentalInformationDTO carRentalInformation;
}
