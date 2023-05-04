package in.laxmi.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.laxmi.entity.CitizenPlan;
import in.laxmi.repo.CitizenPlanRepository;
import in.laxmi.request.SearchRequest;
import in.laxmi.util.EmailUtils;
import in.laxmi.util.ExcelGenerator;
import in.laxmi.util.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {
	private static final boolean CitizenPlan = false;
	@Autowired
	private CitizenPlanRepository planRepo;
	@Autowired
	private ExcelGenerator excelGenerator;
	@Autowired
	private PdfGenerator pdfGenerator;
	@Autowired
	private EmailUtils emailutils;

	public List<String> getPlanNames() {
		// TODO Auto-generated method stub
		return planRepo.getPlanNames();
	}

	public List<String> getPlanStatus() {
		// TODO Auto-generated method stub
		return planRepo.getPlanStatus();
	}

	public List<CitizenPlan> search(SearchRequest request) {
		// TODO Auto-generated method stub
		CitizenPlan entity = new CitizenPlan();
		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(date);
		}
		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
			LocalDate date = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(date);
		}
		return planRepo.findAll(Example.of(entity));

	}

	public boolean exportExcel(HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		File f = new File("plans.xls");
		List<CitizenPlan> plans = planRepo.findAll();
		excelGenerator.generate(response, plans, f);
		String subject = "Test mail subject";
		String body = "<h1>Test mail body</h1>";
		String to = "laxminara1996@gmail.com";
		emailutils.sendEmail(subject, body, to, f);

		f.delete();

		return true;

	}

	public boolean exportPdf(HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		File f = new File("plans.pdf");
		List<CitizenPlan> plans = planRepo.findAll();
		pdfGenerator.generate(response, plans, f);
		String subject = "Test mail subject";
		String body = "<h1>Test mail body</h1>";
		String to = "laxminara1996@gmail.com";
		emailutils.sendEmail(subject, body, to, f);
		f.delete();
		return true;
	}

}
