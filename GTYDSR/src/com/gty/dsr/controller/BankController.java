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
import com.gty.dsr.domain.Bank;
import com.gty.dsr.service.BankService;
import com.gty.dsr.utility.JSONUtility;

@Controller
@RestController
public class BankController {
	@RequestMapping(value = "/banks")
	public ModelAndView showBanks() {
		ModelAndView modelAndView = new ModelAndView("bank");

		List<Bank> banks = BankService.getAllBanks();
		modelAndView.addObject("banks", banks);

		return modelAndView;
	}

	@RequestMapping(value = "/addNewBank", method = RequestMethod.POST)
	public @ResponseBody String addNewBank(@ModelAttribute("bank") Bank bank) throws IOException {
		return BankService.addBank(bank);
	}

	@RequestMapping(value = "/getBankByBankName", method = RequestMethod.POST)
	public @ResponseBody String getBankByBankName(@RequestParam("name") String name) throws JsonProcessingException {
		Bank bank = BankService.getBankByBankName(name);
		return JSONUtility.parseJSON(bank);
	}

	@RequestMapping(value = "/updateBank", method = RequestMethod.POST)
	public @ResponseBody String updateBank(@ModelAttribute("bank") Bank bank) throws IOException {
		return BankService.updateBank(bank);
	}
}