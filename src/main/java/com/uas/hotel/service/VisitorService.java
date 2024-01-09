package com.uas.hotel.service;

import java.util.List;

import com.uas.hotel.entity.Visitor;


public interface VisitorService {
    List<Visitor> getAllVisitors();
    
    Visitor saveVisitor(Visitor visitor);
    
    Visitor getVisitorById(Long id);
    
    Visitor updateVisitor(Visitor visitor);
    
    void deleteVisitorById(Long id);
}
