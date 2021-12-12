package com.fayden.smartorder.order.web;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CarRentalInformationDTO {
    private String carRentalId;
    private String energy;

    private String deliveryPlace;
    private LocalDate orderDate;
    private LocalDate initialDeliveryDate;
    private LocalDate estimatedDeliveryDate;
    private LocalDate realDeliveryDate;

    private String status;
}
