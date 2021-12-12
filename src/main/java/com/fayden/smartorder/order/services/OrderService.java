package com.fayden.smartorder.order.services;

import com.fayden.smartorder.exception.NotFoundException;
import com.fayden.smartorder.order.persistance.CarRentalInformationEntity;
import com.fayden.smartorder.order.persistance.ClientInformationEntity;
import com.fayden.smartorder.order.persistance.OrderEntity;
import com.fayden.smartorder.order.persistance.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderEntity create(ClientInformationEntity clientInformationEntity) {
        // TODO Later control if authenticated user has right to create this order
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(UUID.randomUUID());
        orderEntity.setClientInformation(clientInformationEntity);
        return (orderRepository.save(orderEntity));
    }


    public Page<OrderEntity> findAll(Pageable paging) {
        return orderRepository.findAll(paging);
    }

    @Transactional
    public Optional<OrderEntity> findById(String id) {
        return orderRepository.findById(mapIdOrElseThrowNotFound(id));
    }

    @Transactional
    public OrderEntity updateClientInformation(String id, ClientInformationEntity clientInformationEntity) {
        final OrderEntity orderEntity = orderRepository.findById(mapIdOrElseThrowNotFound(id)).orElseThrow(NotFoundException::new);
        orderEntity.setClientInformation(clientInformationEntity);
        return (orderRepository.save(orderEntity));
    }

    @Transactional
    public OrderEntity updateCarRentalInformation(String id, CarRentalInformationEntity carRentalInformationEntity) {
        final OrderEntity orderEntity = orderRepository.findById(mapIdOrElseThrowNotFound(id)).orElseThrow(NotFoundException::new);
        orderEntity.setCarRentalInformation(carRentalInformationEntity);
        return (orderRepository.save(orderEntity));
    }

    private UUID mapIdOrElseThrowNotFound(String s) {
        try {
            return UUID.fromString(s);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException();
        }
    }
}
