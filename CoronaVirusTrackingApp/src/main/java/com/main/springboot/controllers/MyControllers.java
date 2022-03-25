package com.main.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.main.springboot.services.CoronaVirusDataService;

@Controller
public class MyControllers {
	
	@Autowired
	private CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
      model.addAttribute("totalNumberofcasesToday",coronaVirusDataService.getTotalNumberOfCasesToday());
      model.addAttribute("totalNumberOfIncresesCase",coronaVirusDataService.getIncreaseCasesToday());
	  model.addAttribute("localStats", coronaVirusDataService.getAllStats());
		return "home";
	}

}
