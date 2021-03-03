package com.example.demo.converter;


import com.example.demo.dto.AssetDTO;
import com.example.demo.model.AssetDAO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AssetConverter {

    public AssetDTO daoToDto(AssetDAO assetDAO){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(assetDAO, AssetDTO.class);
    }

    public List<AssetDTO> daoToDto(List<AssetDAO> assetDAO){
        return assetDAO.stream().map(this::daoToDto).collect(Collectors.toList());
    }

}
