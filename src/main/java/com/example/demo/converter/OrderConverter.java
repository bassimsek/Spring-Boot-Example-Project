package com.example.demo.converter;


import com.example.demo.dto.OrderDTO;
import com.example.demo.model.OrderDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderConverter {

    public OrderDTO daoToDto(OrderDAO orderDAO){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(orderDAO, OrderDTO.class);
    }

    public List<OrderDTO> daoToDto(List<OrderDAO> orderDAO){
        return orderDAO.stream().map(this::daoToDto).collect(Collectors.toList());
    }

}
