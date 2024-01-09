package com.uas.hotel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uas.hotel.entity.Visitor;
import com.uas.hotel.repository.VisitorRepository;
import com.uas.hotel.service.VisitorService;


@Service
public class VisitorServiceImpl implements VisitorService {

    private VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        super();
        this.visitorRepository = visitorRepository;
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public Visitor saveVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    public Visitor getVisitorById(Long id) {
        return visitorRepository.findById(id).get();
    }

    @Override
    public Visitor updateVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    public void deleteVisitorById(Long id) {
        visitorRepository.deleteById(id);
    }
}
