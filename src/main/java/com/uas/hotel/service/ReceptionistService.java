package com.uas.hotel.service;

import java.util.List;

import com.uas.hotel.entity.Visitor;
import com.uas.hotel.entity.Receptionist;


public interface ReceptionistService {
    List<Receptionist> getAllReceptionists();
    
    Receptionist saveReceptionist(Receptionist receptionist);

    Receptionist getReceptionistById(Long id);

    Receptionist updateReceptionist(Receptionist receptionist);
    
    void deleteReceptionistById(Long id);
}
