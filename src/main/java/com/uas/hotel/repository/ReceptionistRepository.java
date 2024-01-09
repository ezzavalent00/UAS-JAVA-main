package com.uas.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uas.hotel.entity.Visitor;
import com.uas.hotel.entity.Receptionist;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Long>{

}
