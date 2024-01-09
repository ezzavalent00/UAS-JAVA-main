package com.uas.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uas.hotel.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long>{

}
