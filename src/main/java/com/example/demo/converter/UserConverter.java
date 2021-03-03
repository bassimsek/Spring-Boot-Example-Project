package com.example.demo.converter;


import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserConverter {

    public UserDTO daoToDto(UserDAO userDAO){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(userDAO, UserDTO.class);
    }

    public List<UserDTO> daoToDto(List<UserDAO> userDAO){
        return userDAO.stream().map(this::daoToDto).collect(Collectors.toList());
    }

}
