package com.gty.dsr.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gty.dsr.domain.Discrepancy;
import com.gty.dsr.service.DiscrepancyService;
import com.gty.dsr.utility.JSONUtility;

@Controller
@RestController
public class DiscrepancyController {
	@RequestMapping(value = "/discrepancies")
	public ModelAndView showDiscrepancies() {
		ModelAndView modelAndView = new ModelAndView("discrepancy");

		List<Discrepancy> discrepancies = DiscrepancyService.getAllDiscrepancies();
		modelAndView.addObject("discrepancies", discrepancies);

		return modelAndView;
	}

	@RequestMapping(value = "/addNewDiscrepancy", method = RequestMethod.POST)
	public @ResponseBody String addNewDiscrepancy(@ModelAttribute("discrepancy") Discrepancy discrepancy) throws IOException {
		return DiscrepancyService.addDiscrepancy(discrepancy);
	}

	@RequestMapping(value = "/getDiscrepancyByDiscrepancyType", method = RequestMethod.POST)
	public @ResponseBody String getDiscrepancyByDiscrepancyType(@RequestParam("type") String type) throws JsonProcessingException {
		Discrepancy discrepancy = DiscrepancyService.getDiscrepancyByDiscrepancyType(type);
		return JSONUtility.parseJSON(discrepancy);
	}

	@RequestMapping(value = "/updateDiscrepancy", method = RequestMethod.POST)
	public @ResponseBody String updateDiscrepancy(@ModelAttribute("discrepancy") Discrepancy discrepancy) throws IOException {
		return DiscrepancyService.updateDiscrepancy(discrepancy);
	}
}