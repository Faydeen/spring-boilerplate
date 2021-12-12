package com.fayden.smartorder.order.persistance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class ClientInformationEntity {
    private String clientOrderId;
    private String orderType;
    private String carRentalName;
    private String carRentalOrderId;
    private String make;
    private String model;
    private String vehiculeType;
    private String legalCorporation;
    private String region;
    private String driverName;
}
