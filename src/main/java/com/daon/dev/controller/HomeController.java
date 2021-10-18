package com.daon.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.daon.dev.service.GateService;

@Controller
public class HomeController {
	
	private GateService gateService;
	
	@Autowired
	public HomeController(GateService gateService){
		this.gateService = gateService;
	}
	
	@GetMapping({"/", "/index", "/home"})
	public String homePage(Model model){
		model.addAttribute("gates", gateService.getAllGates());
		System.out.println(model);
		return "index";
	}
	
}
