package com.example.demo.converter;


import com.example.demo.dto.LogDTO;
import com.example.demo.model.LogDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class LogConverter {

    public LogDTO daoToDto(LogDAO logDAO){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(logDAO, LogDTO.class);
    }

    public List<LogDTO> daoToDto(List<LogDAO> logDAO){
        return logDAO.stream().map(this::daoToDto).collect(Collectors.toList());
    }

}
