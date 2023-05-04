package in.laxmi.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.laxmi.entity.CitizenPlan;
import in.laxmi.request.SearchRequest;
import in.laxmi.service.ReportService;

@Controller
public class ReportController {
	@Autowired
	private ReportService service;
	
	@GetMapping("/pdf")
    public void pdfExport(HttpServletResponse response) throws Exception {
    	response.setContentType("application/pdf");
    	response.addHeader("content-Disposition", "attachment;filename=plans.pdf");
    	service.exportPdf(response);
    }
	
	
	@GetMapping("/excel")
    public void excelExport(HttpServletResponse response) throws Exception {
    	response.setContentType("application/octect-stream");
    	response.addHeader("content-Disposition", "attachment;filename=plans.xlsx");
    	service.exportExcel(response);
    }
	
	
	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}
	private void init(Model model) {
		//model.addAttribute("search", new SearchRequest());
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatus());
	}
	@PostMapping("/search")
	public String handleSearh(@ModelAttribute("search") SearchRequest search, Model model) {
		List<CitizenPlan> plans = service.search(search);
		model.addAttribute("plans", plans);
		init(model);
		return "index";
	}

}
