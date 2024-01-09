package com.uas.hotel.service.impl;

import org.springframework.stereotype.Service;

import com.uas.hotel.entity.Visitor;
import com.uas.hotel.entity.Receptionist;
import com.uas.hotel.repository.VisitorRepository;
import com.uas.hotel.repository.ReceptionistRepository;
import com.uas.hotel.service.ReceptionistService;

import java.util.List;


@Service
public class ReceptionistServiceImpl implements ReceptionistService {

	private ReceptionistRepository receptionistRepository;

	public ReceptionistServiceImpl(ReceptionistRepository receptionistRepository) {
		super();
		this.receptionistRepository = receptionistRepository;
	}

	@Override
	public List<Receptionist> getAllReceptionists() {
		return receptionistRepository.findAll();
	}

	@Override
	public Receptionist saveReceptionist(Receptionist receptionist) {
		return receptionistRepository.save(receptionist);
	}

	@Override
	public Receptionist getReceptionistById(Long id) {
		return receptionistRepository.findById(id).get();
	}

	@Override
	public Receptionist updateReceptionist(Receptionist receptionist) {
		return receptionistRepository.save(receptionist);
	}

	@Override
	public void deleteReceptionistById(Long id) {
		receptionistRepository.deleteById(id);
	}

}
