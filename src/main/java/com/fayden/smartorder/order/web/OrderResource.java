package com.fayden.smartorder.order.web;

import com.fayden.smartorder.exception.NotFoundException;
import com.fayden.smartorder.order.persistance.OrderEntity;
import com.fayden.smartorder.order.services.OrderMapper;
import com.fayden.smartorder.order.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.factory.Mappers;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("order")
@Tag(name = "Order")
public class OrderResource {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
        this.orderMapper = Mappers.getMapper(OrderMapper.class);
    }


    @Operation(summary = "Retrieve a list of Order", description = "Retrieve a list of Order")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SimplePage<OrderDTO> findAll(@ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return SimplePage.of(orderService.findAll(pageable).map(orderMapper::toDTO));
    }


    @Operation(summary = "Retrieve an order", description = "Retrieve an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO findOneById(
            @Parameter(description = "id of order to be searched")
            @PathVariable String id
    ) {
        return orderService.findById(id).map(orderMapper::toDTO).orElseThrow(NotFoundException::new);
    }

    @Operation(summary = "Create an order with client information", description = "Create an order with client information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Required field not found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> createOne(@RequestBody @Valid ClientInformationDTO clientInformationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.toDTO(orderService.create(orderMapper.toEntity(clientInformationDto))));
    }

    @Operation(summary = "Set client information", description = "Update client information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order update", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderEntity.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @PutMapping(path = "/{id}/updateClientInformation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO updateClientInformation(
            @Parameter(description = "id of order to be updated") @PathVariable String id,
            @RequestBody ClientInformationDTO clientInformationDto
    ) {
        return orderMapper.toDTO(orderService.updateClientInformation(id, orderMapper.toEntity(clientInformationDto)));
    }

    @Operation(summary = "Set car rental information", description = "Update client information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order update", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderEntity.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)})
    @PutMapping(path = "/{id}/carRentalInformation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO updateCarRentalInformation(
            @Parameter(description = "id of order to be updated") @PathVariable String id,
            @RequestBody CarRentalInformationDTO carRentalInformationDTO
    ) {
        return orderMapper.toDTO(orderService.updateCarRentalInformation(id, orderMapper.toEntity(carRentalInformationDTO)));
    }
}
