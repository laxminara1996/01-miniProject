package in.laxmi.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.laxmi.entity.CitizenPlan;
import in.laxmi.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	private CitizenPlanRepository repo;

	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		repo.deleteAll();
		
		CitizenPlan p1 = new CitizenPlan();
		p1.setCitizenName("laxmi");
		p1.setGender("female");
		p1.setPlanName("cash");
		p1.setPlanStatus("approved");
		p1.setBenefitAmt(5000.00);
		p1.setPlanStartDate(LocalDate.now());
		p1.setPlanEndDate(LocalDate.now().plusMonths(6));

		CitizenPlan p2 = new CitizenPlan();
		p2.setCitizenName("raju");
		p2.setGender("male");
		p2.setPlanName("cash");
		p2.setPlanStatus("denial");
		p2.setBenefitAmt(5000.00);
		p2.setDenialReason("rental income");
		CitizenPlan p3 = new CitizenPlan();
		p3.setCitizenName("raji");
		p3.setGender("female");
		p3.setPlanName("cash");
		p3.setPlanStatus("terminated");
		p3.setBenefitAmt(5000.00);
		p3.setPlanStartDate(LocalDate.now());
		p3.setPlanEndDate(LocalDate.now().plusMonths(6));
		p3.setTerminationDate(LocalDate.now());
		p3.setTerminatedReason("employement");

		CitizenPlan p4 = new CitizenPlan();
		p4.setCitizenName("rani");
		p4.setGender("female");
		p4.setPlanName("cash");
		p4.setPlanStatus("approved");
		p4.setBenefitAmt(7000.00);
		p4.setPlanStartDate(LocalDate.now());
		p4.setPlanEndDate(LocalDate.now().plusMonths(6));
		CitizenPlan p5 = new CitizenPlan();
		p5.setCitizenName("ramu");
		p5.setGender("male");
		p5.setPlanName("food");
		p5.setPlanStatus("denial");
		p5.setBenefitAmt(7000.00);
		p5.setDenialReason("rental income");
		CitizenPlan p6 = new CitizenPlan();
		p6.setCitizenName("swetha");
		p6.setGender("female");
		p6.setPlanName("food");
		p6.setPlanStatus("terminated");
		p6.setBenefitAmt(7000.00);
		p6.setPlanStartDate(LocalDate.now());
		p6.setPlanEndDate(LocalDate.now().plusMonths(6));
		p6.setTerminationDate(LocalDate.now());
		p6.setTerminatedReason("employement");

		CitizenPlan p7 = new CitizenPlan();
		p7.setCitizenName("rama");
		p7.setGender("female");
		p7.setPlanName("medical");
		p7.setPlanStatus("approved");
		p7.setBenefitAmt(7000.00);
		p7.setPlanStartDate(LocalDate.now());
		p7.setPlanEndDate(LocalDate.now().plusMonths(6));
		CitizenPlan p8 = new CitizenPlan();
		p8.setCitizenName("jhon");
		p8.setGender("male");
		p8.setPlanName("medical");
		p8.setPlanStatus("denial");
		p8.setDenialReason("proper income");
		CitizenPlan p9 = new CitizenPlan();
		p9.setCitizenName("latha");
		p9.setGender("female");
		p9.setPlanName("medical");
		p9.setPlanStatus("terminated");
		p9.setBenefitAmt(7000.00);
		p9.setPlanStartDate(LocalDate.now());
		p9.setPlanEndDate(LocalDate.now().plusMonths(6));
		p9.setTerminationDate(LocalDate.now());
		p9.setTerminatedReason("govt job");

		CitizenPlan p10 = new CitizenPlan();
		p10.setCitizenName("lakshmi");
		p10.setGender("female");
		p10.setPlanName("employement");
		p10.setPlanStatus("approved");
		p10.setBenefitAmt(8000.00);
		p10.setPlanStartDate(LocalDate.now());
		p10.setPlanEndDate(LocalDate.now().plusMonths(6));
		CitizenPlan p11 = new CitizenPlan();
		p11.setCitizenName("josh");
		p11.setGender("male");
		p11.setPlanName("employement");
		p11.setPlanStatus("denial");
		p11.setDenialReason("proper income");
		CitizenPlan p12 = new CitizenPlan();
		p12.setCitizenName("cathy");
		p12.setGender("female");
		p12.setPlanName("employement");
		p12.setPlanStatus("terminated");
		p12.setBenefitAmt(8000.00);
		p12.setPlanStartDate(LocalDate.now());
		p12.setPlanEndDate(LocalDate.now().plusMonths(6));
		p12.setTerminationDate(LocalDate.now());
		p12.setTerminatedReason("govt job");
		List<CitizenPlan> list = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12);

		repo.saveAll(list);
	}

}
