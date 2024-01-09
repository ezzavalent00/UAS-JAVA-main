package com.uas.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uas.hotel.HotelManagementSystemApplication;
import com.uas.hotel.entity.Receptionist;
import com.uas.hotel.service.ReceptionistService;

@Controller
public class ReceptionistController {

	private ReceptionistService receptionistService;

	public ReceptionistController(ReceptionistService receptionistService) {
		super();
		this.receptionistService = receptionistService;
	}
	
	// handler method to handle list receptionists and return mode and view
	@GetMapping("/receptionists")
	public String listreceptionists(Model model) {
		Authentication authentication = HotelManagementSystemApplication.authenticatedUser;

		// Check if the user is authenticated

		if (authentication!= null){

			model.addAttribute("adminRole", authentication.getAuthorities().stream()
					.anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
			model.addAttribute("userRole", authentication.getAuthorities().stream()
					.anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));

			model.addAttribute("receptionists", receptionistService.getAllReceptionists());
			return "receptionists";
		}else{
			return "redirect:/login";
		}

	}
	
	@GetMapping("/receptionists/new")
	public String createreceptionistForm(Model model) {

		Authentication authentication = HotelManagementSystemApplication.authenticatedUser;

		// Check if the user is authenticated

		if (authentication!= null ){
			model.addAttribute("adminRole", authentication.getAuthorities().stream()
					.anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
			// create receptionist object to hold receptionist form data
			if( model.getAttribute("adminRole") == null) {
				return "redirect:/home";
			}
			Receptionist receptionist = new Receptionist();
			model.addAttribute("receptionist", receptionist);
			return "create_receptionist";
		}else{
			return "redirect:/login";
		}

		
	}
	
	@PostMapping("/receptionists")
	public String saveReceptionist(@ModelAttribute("receptionist") Receptionist receptionist) {
		receptionistService.saveReceptionist(receptionist);
		return "redirect:/receptionists";
	}
	
	@GetMapping("/receptionists/edit/{id}")
	public String editReceptionistForm(@PathVariable Long id, Model model) {
		Authentication authentication = HotelManagementSystemApplication.authenticatedUser;
		if (authentication!= null){
			// create receptionist object to hold receptionist form data
			model.addAttribute("receptionist", receptionistService.getReceptionistById(id));
			return "edit_receptionist";
		}else{
			return "redirect:/login";
		}

	}

	@PostMapping("/receptionists/{id}")
	public String updateReceptionist(@PathVariable Long id,
			@ModelAttribute("receptionist") Receptionist receptionist,
			Model model) {
		
		// get receptionist from database by id
		Receptionist existingReceptionist = receptionistService.getReceptionistById(id);
		existingReceptionist.setId(id);
		existingReceptionist.setName(receptionist.getName());
		existingReceptionist.setPhone(receptionist.getPhone());
		existingReceptionist.setEmail(receptionist.getEmail());
		
		// save updated receptionist object
		receptionistService.updateReceptionist(existingReceptionist);
		return "redirect:/receptionists";
	}
	
	// handler method to handle delete receptionist request
	
	@GetMapping("/receptionists/{id}")
	public String deleteReceptionist(@PathVariable Long id) {
		Authentication authentication = HotelManagementSystemApplication.authenticatedUser;
		if (authentication!= null){
			receptionistService.deleteReceptionistById(id);
			return "redirect:/receptionists";
		}else{
			return "redirect:/login";
		}

	}
	
}

// @Controller
// @RequestMapping("/receptionists")
// public class ReceptionistController {

// 	private static final Logger logger = Logger.getLogger(ReceptionistController.class.getName());

// 	@Autowired
// 	private ReceptionistService receptionistService;

// 	@GetMapping("/receptionists")
// 	public String listReceptionists(Model model) {
// 		model.addAttribute("receptionists", receptionistService.getAllReceptionists());
// 		return "receptionists";
// 	}

// 	@GetMapping("/receptionists/new")
// 	public String createReceptionistForm(Model model) {
// 		Receptionist receptionist = new Receptionist();
// 		model.addAttribute("receptionist", receptionist);
// 		return "create_receptionist";
// 	}

// 	@PostMapping("/receptionists")
// 	public String saveReceptionist(@ModelAttribute("receptionist") Receptionist receptionist) {
// 		receptionistService.saveReceptionist(receptionist);
// 		return "redirect:/receptionists";
// 	}

// 	@GetMapping("/receptionists/edit/{id}")
// 	public String editReceptionistForm(@PathVariable Long id, Model model) {
// 		model.addAttribute("receptionist", receptionistService.getReceptionistById(id));
// 		return "edit_receptionist";
// 	}

// 	@PostMapping("/receptionists/{id}")
// 	public String updateReceptionist(@PathVariable Long id,
// 									 @ModelAttribute("receptionist") Receptionist receptionist,
// 									 Model model) {
// 		Receptionist existingReceptionist = receptionistService.getReceptionistById(id);
// 		existingReceptionist.setId(id);
// 		existingReceptionist.setName(receptionist.getName());
// 		existingReceptionist.setPhone(receptionist.getPhone());
// 		existingReceptionist.setEmail(receptionist.getEmail());

// 		receptionistService.updateReceptionist(existingReceptionist);
// 		return "redirect:/receptionists";
// 	}

// 	@GetMapping("/receptionists/{id}")
// 	public String deleteReceptionist(@PathVariable Long id) {
// 		receptionistService.deleteReceptionistById(id);
// 		return "redirect:/receptionists";
// 	}
// }
