package com.gty.dsr.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gty.dsr.domain.Bank;
import com.gty.dsr.domain.Branch;
import com.gty.dsr.domain.Discrepancy;
import com.gty.dsr.domain.Record;
import com.gty.dsr.service.BankService;
import com.gty.dsr.service.BranchService;
import com.gty.dsr.service.DiscrepancyService;
import com.gty.dsr.service.RecordService;
import com.gty.dsr.utility.BankComparator;
import com.gty.dsr.utility.BranchComparator;
import com.gty.dsr.utility.DiscrepancyComparator;
import com.gty.dsr.utility.JSONUtility;

@Controller
public class RecordController {
	@RequestMapping("/")
	public void showIndex(HttpServletResponse response) throws IOException {
		response.sendRedirect("records");
	}
	@RequestMapping("/records")
	public ModelAndView showRecords() {
		ModelAndView modelAndView = new ModelAndView("record");

		List<Record> records = RecordService.getAllRecords();
		modelAndView.addObject("records", records);

		List<Branch> branches = BranchService.getAllBranches();
		Collections.sort(branches, new BranchComparator());
		modelAndView.addObject("branches", branches);

		List<Bank> banks = BankService.getAllBanks();
		Collections.sort(banks, new BankComparator());
		modelAndView.addObject("banks", banks);

		List<Discrepancy> discrepancies = DiscrepancyService.getAllDiscrepancies();
		Collections.sort(discrepancies, new DiscrepancyComparator());
		modelAndView.addObject("discrepancies", discrepancies);

		return modelAndView;
	}

	@RequestMapping(value = "/getPreviousRecordAcoh", method = RequestMethod.POST)
	public @ResponseBody String getPreviousRecordAcoh(@ModelAttribute("record") Record record, BindingResult bindingResult) throws JsonProcessingException {
		return RecordService.getPreviousRecordAcoh(record).toString();
	}

	@RequestMapping(value = "/addNewRecord", method = RequestMethod.POST)
	public @ResponseBody String addNewRecord(@ModelAttribute("record") Record record, BindingResult bindingResult) throws IOException {
		return RecordService.addRecord(record);
	}

	@RequestMapping(value = "/getRecordByBranchAndDate", method = RequestMethod.POST)
	public @ResponseBody String getRecordByBranchAndDate(@RequestParam("branch") String branch, @RequestParam("date") Date date) throws JsonProcessingException {
		Record record = RecordService.getRecordByBranchAndDate(branch, date);
		return JSONUtility.parseJSON(record);
	}

	@RequestMapping(value = "/updateRecord", method = RequestMethod.POST)
	public @ResponseBody String updateRecord(@ModelAttribute("record") Record record, BindingResult bindingResult) throws IOException {
		return RecordService.updateRecord(record);
	}
}
