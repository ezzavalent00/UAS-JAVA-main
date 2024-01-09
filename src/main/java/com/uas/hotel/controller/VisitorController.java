package com.uas.hotel.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.uas.hotel.HotelManagementSystemApplication;
import com.uas.hotel.entity.Visitor;
import com.uas.hotel.service.VisitorService;

@Controller
public class VisitorController {

    private VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        super();
        this.visitorService = visitorService;
    }

    // handler method to handle list visitors and return model and view
    @GetMapping("/visitors")
    public String listVisitors(Model model) {
        Authentication authentication = HotelManagementSystemApplication.authenticatedUser;

        // Check if the user is authenticated
        if (authentication != null) {
            model.addAttribute("adminRole", authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
            model.addAttribute("userRole", authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));

            model.addAttribute("visitors", visitorService.getAllVisitors());
            return "visitors";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/visitors/new")
    public String createVisitorForm(Model model) {

        Authentication authentication = HotelManagementSystemApplication.authenticatedUser;

        // Check if the user is authenticated
        if (authentication != null) {
            model.addAttribute("adminRole", authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
            // create visitor object to hold visitor form data
            if (model.getAttribute("adminRole") == null) {
                return "redirect:/home";
            }
            Visitor visitor = new Visitor();
            model.addAttribute("visitor", visitor);
            return "create_visitor";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/visitors")
    public String saveVisitor(@ModelAttribute("visitor") Visitor visitor) {
        visitorService.saveVisitor(visitor);
        return "redirect:/visitors";
    }

    @GetMapping("/visitors/edit/{id}")
    public String editVisitorForm(@PathVariable Long id, Model model) {
        Authentication authentication = HotelManagementSystemApplication.authenticatedUser;
        if (authentication != null) {
            // create visitor object to hold visitor form data
            model.addAttribute("visitor", visitorService.getVisitorById(id));
            return "edit_visitor";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/visitors/{id}")
    public String updateVisitor(@PathVariable Long id,
                                @ModelAttribute("visitor") Visitor visitor,
                                Model model) {

        // get visitor from database by id
        Visitor existingVisitor = visitorService.getVisitorById(id);
        existingVisitor.setId(id);
        existingVisitor.setFirstName(visitor.getFirstName());
        existingVisitor.setLastName(visitor.getLastName());
        existingVisitor.setEmail(visitor.getEmail());

        // save updated visitor object
        visitorService.updateVisitor(existingVisitor);
        return "redirect:/visitors";
    }

    // handler method to handle delete visitor request
    @GetMapping("/visitors/{id}")
    public String deleteVisitor(@PathVariable Long id) {
        Authentication authentication = HotelManagementSystemApplication.authenticatedUser;
        if (authentication != null) {
            visitorService.deleteVisitorById(id);
            return "redirect:/visitors";
        } else {
            return "redirect:/login";
        }
    }
}