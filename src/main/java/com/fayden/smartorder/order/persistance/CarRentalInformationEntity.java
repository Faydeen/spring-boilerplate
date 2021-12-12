package com.fayden.smartorder.order.persistance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class CarRentalInformationEntity {
    private String carRentalId;
    private String energy;

    private String deliveryPlace;
    private LocalDate orderDate;
    private LocalDate initialDeliveryDate;
    private LocalDate estimatedDeliveryDate;
    private LocalDate realDeliveryDate;

    private String status;
}
