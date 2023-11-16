package com.prod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prod.entities.Produto;

public interface ProdRepository extends JpaRepository <Produto, Long> {

}
