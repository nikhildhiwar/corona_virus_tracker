package com.dev.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dev.app.models.LocationStats;
import com.dev.app.services.CoronaVirusDataService;

@Controller
public class HomeController {

	@Autowired
	private CoronaVirusDataService service;

	@GetMapping(value = "/")
	public String home(Model model) {

		List<LocationStats> allStats = service.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("locationStats", allStats);
		return "home";
	}

}
