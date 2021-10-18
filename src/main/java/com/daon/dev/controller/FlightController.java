package com.daon.dev.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daon.dev.domain.Flight;
import com.daon.dev.domain.Gate;
import com.daon.dev.domain.GateStatus;
import com.daon.dev.service.FlightService;
import com.daon.dev.service.GateService;
import com.daon.dev.service.GateStatusService;

@Controller
@RequestMapping("/flights")
public class FlightController {

	private static final String GATE_AVAILABLE = "Available";
	private static final Integer GATE_UNAVAILABLE_ID = 2;

	private FlightService flightService;
	private GateService gateService;
	private GateStatusService gateStatusService;

	@Autowired
	public FlightController(FlightService flightService, GateService gateService, GateStatusService gateStatusService) {
		this.flightService = flightService;
		this.gateService = gateService;
		this.gateStatusService = gateStatusService;
	}

	@ModelAttribute("gates")
	public List<Gate> populateGates() {
		return gateService.getAllActiveGates(GATE_AVAILABLE);
	}

	@GetMapping
	public String listFlights(Model model) {
		model.addAttribute("flights", flightService.getAllFlights());
		return "flight/list";
	}

	@GetMapping("/add")
	public String showFlightAdd(Model model) {
		model.addAttribute("flight", new Flight());
		return "flight/add";
	}

	@GetMapping("/edit/{id}")
	public String showFlightEdit(@PathVariable Integer id, Model model) {
		model.addAttribute("flight", flightService.findById(id));
		return "flight/edit";
	}

	@PostMapping({ "/edit/{id}", "/add" })
	public String saveFlight(@Valid Flight flight, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "flight/edit";
		}
		
		boolean isAdd = flight.getId() == null;
		
		if(isAdd) {
			
			//mode add
			
			List<Gate> gateList = flight.getGateList();
			
			if (gateList != null && !gateList.isEmpty()) {
				flight.getGateList().forEach(gate -> {
					flight.setGate(gate);
					GateStatus gateStatus = gateStatusService.findById(GATE_UNAVAILABLE_ID);
					gate.setGateStatus(gateStatus);
				});
			}
			else {
				result.rejectValue("gateList", null, "There is no available gates.");
			}
		}

		if (flight.getGateList() != null && !flight.getGateList().isEmpty()) {
			flight.getGateList().forEach(gate -> {
				flight.setGate(gate);
			});
		}

		if(flight.getGate() != null) {
			flightService.saveFlight(flight);
		}
		
		return "redirect:/flights";
	}

	@GetMapping("/search")
	public String search(@Param("keyword") String keyword, @Param("opened") String opened,
			@Param("closed") String closed, Model model) {

		List<Flight> searchResult = null;

		if (keyword != null && !keyword.isEmpty()) {
			if (opened.isEmpty() && closed.isEmpty()) {
				searchResult = flightService.searchByFlightNumber(keyword);
				model.addAttribute("keyword", keyword);
			}
		}

		else {
			if ((opened != null && !opened.isEmpty()) && (closed != null && !closed.isEmpty())) {
				searchResult = flightService.searchByDateInterval(opened, closed);
				model.addAttribute("opened", opened);
				model.addAttribute("closed", closed);
			}
		}

		if (searchResult != null && !searchResult.isEmpty()) {
			model.addAttribute("searchResult", searchResult);
		}

		return "flight/search_result";
	}

	@GetMapping("/delete/{id}")
	public String deleteFlight(@PathVariable Integer id) {
		flightService.removeFlight(id);
		return "redirect:/flights";
	}

}
