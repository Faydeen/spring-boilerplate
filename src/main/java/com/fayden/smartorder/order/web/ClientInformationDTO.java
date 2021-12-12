package com.fayden.smartorder.order.web;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class ClientInformationDTO {
    @NotNull
    private String clientOrderId;
    private String orderType;
    private String carRentalName;
    private String carRentalOrderId;
    @NotNull
    private String make;
    @NotNull
    private String model;
    @NotNull
    private String vehiculeType;
    @NotNull
    private String legalCorporation;
    @NotNull
    private String region;
    private String driverName;
}
