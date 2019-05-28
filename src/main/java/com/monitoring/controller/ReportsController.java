package com.monitoring.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.monitoring.dto.Report;
import com.monitoring.dto.ReportDTO;
import com.monitoring.reports.ReportingUtils;
import com.monitoring.repository.DeploymentRepository;
import com.monitoring.repository.EnvironmentRepository;
import com.monitoring.repository.HorizontalPodAutoscalerRepository;

@Controller
@RequestMapping("/reports")
public class ReportsController {
	
	@Autowired
	EnvironmentRepository envRepository;
	
	@Autowired
	DeploymentRepository deploymentRepository;
	
	@Autowired
	HorizontalPodAutoscalerRepository horizontalPodAutoscalerRepository;
	
	@Autowired
	ReportingUtils reportUtil;
	
	@GetMapping("/index")
    public String getMainPage(Model model) throws Exception {
		
		List<Object> environments = reportUtil.getAllEnvName();
		model.addAttribute("environments", environments);
        return "reports/index";
    }
	
	@PostMapping("/submit")
    public String submitForm(@RequestParam Map<String, String> map , Model model) throws Exception {
		String envName = map.get("envName");
		String namespace= map.get("namespace");
		String deployment =map.get("deployment");
		String startDate = map.get("startDate");
		String endDate = map.get("endDate");
		Report report = new Report(envName,namespace,deployment,startDate,endDate);
		List<String> namespaces = new ArrayList<String>();
		List<String> deployments= new ArrayList<String>();
		List<ReportDTO> reportData = new ArrayList<ReportDTO>();
		List<Object> environments = reportUtil.getAllEnvName();
		if(null!=envName)
			namespaces = reportUtil.getNamespaces(envName);
		if(null!=namespace && null!= envName)
			deployments = reportUtil.getDeployments(envName,namespace);
		if(null!=envName && null!=namespace && null!=deployment && null!=startDate && null != endDate )
			reportData = reportUtil.getReportData(envName,namespace,deployment,startDate,endDate);
	    List unmodifiableList = Collections.unmodifiableList(reportData);

	    List newList = new ArrayList(unmodifiableList);

	    Collections.sort(newList);
		model.addAttribute("envName",envName);
		model.addAttribute("namespace",namespace);
		model.addAttribute("namespaces", namespaces);
		model.addAttribute("environments", environments);
		model.addAttribute("deployments", deployments);
		model.addAttribute("deployment",deployment);
		model.addAttribute("report", report);
		model.addAttribute("reportData", newList);
        return "reports/index";
    }
	
}
