package com.daon.dev.controller;

import java.text.ParseException;
import java.util.Date;
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

import com.daon.dev.domain.Gate;
import com.daon.dev.domain.GateStatus;
import com.daon.dev.service.GateService;
import com.daon.dev.service.GateStatusService;

@Controller
@RequestMapping("/gates")
public class GateController {

	private static final String GATE_AVAILABLE = "Available";

	private GateService gateService;
	private GateStatusService gateStatusService;

	@Autowired
	public GateController(GateService gateService, GateStatusService gateStatusService) {
		this.gateService = gateService;
		this.gateStatusService = gateStatusService;
	}

	@ModelAttribute("gatestatuses")
	public List<GateStatus> populateGateStatus() {
		return gateStatusService.getAllGateStatuses();
	}

	@GetMapping
	public String listGates(Model model) throws ParseException {

		List<Gate> gateList = gateService.getAllGates();
		model.addAttribute("gates", gateList);
		return "gate/list";
	}

	@GetMapping("/add")
	public String showGateAdd(Model model) {
		Gate gate = new Gate();
		gate.setGateOpened(new Date());
		gate.setGateClosed(new Date());
		model.addAttribute("gate", gate);
		return "gate/add";
	}

	@GetMapping("/edit/{id}")
	public String showGateEdit(@PathVariable Integer id, Model model) {
		Gate gate = gateService.findById(id);
		model.addAttribute("gate", gate);
		return "gate/edit";
	}

	@PostMapping({ "/edit/{id}", "/add" })
	public String saveGate(@Valid Gate gate, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "gate/edit";
		}

		gate.getGateStatusList().forEach(gatestatus -> {
			gate.setGateStatus(gatestatus);
		});

		gateService.saveGate(gate);
		return "redirect:/gates";

	}

	@GetMapping("/search")
	public String search(@Param("opened") String opened, @Param("closed") String closed, Model model) {

		List<Gate> searchResult = gateService.search(opened, closed, GATE_AVAILABLE);
		model.addAttribute("searchResult", searchResult);

		return "gate/search_result";
	}

	@GetMapping("/delete/{id}")
	public String deleteGate(@PathVariable Integer id) {
		gateService.removeGate(id);
		return "redirect:/gates";
	}

}
