package com.example.demo.repository;


import com.example.demo.model.AssetDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<AssetDAO, Integer> {

    List<AssetDAO> findAll();
    List<AssetDAO> findAllByOwnerId(int ownerId);
}
