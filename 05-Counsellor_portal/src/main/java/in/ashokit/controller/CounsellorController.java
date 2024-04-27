package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.service.CounsellorService;
import in.ashokit.service.EnquiryService;
import in.ashokit.utils.AppConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	@Autowired
	private CounsellorService cservice;
	
	@Autowired
	private EnquiryService eservice;
	
	@GetMapping("/logout")
	public String logout(Model model,HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		model.addAttribute("cobj", new Counsellor());
		return AppConstants.LOGIN_VIEW;
	}
	@GetMapping("/")
	public String login(Model model,Counsellor counsellor) {
		model.addAttribute("cobj", new Counsellor());
		return AppConstants.LOGIN_VIEW;
	}
	
	@PostMapping("/login")
	public String handleLogin(Model model,Counsellor c,HttpServletRequest req) {
		Counsellor counsellor = cservice.getCounsellor(c);
		if( counsellor==null) {
			model.addAttribute("cobj", new Counsellor());
			model.addAttribute("emsg", "Invalid Credentials");
			return AppConstants.LOGIN_VIEW;
		}else {
			HttpSession session = req.getSession(true);
			session.setAttribute("cid", counsellor.getCid());
			Dashboard dashboardInfo = eservice.getDashboardInfo(counsellor.getCid());
			model.addAttribute("db",dashboardInfo );
			return "dashboard";
			
		}
	}
	
	@GetMapping("/dashboard")
	public String dashBoard(Model model,Counsellor c,HttpServletRequest req) {
	//set cid to a session
		HttpSession session = req.getSession(false);
		Integer id =(Integer) session.getAttribute("cid");
		Dashboard dashboardInfo = eservice.getDashboardInfo(id);
	model.addAttribute("db",dashboardInfo );
	return "dashboard";
	}
	

	
	@GetMapping("/register")
	public String register(Model model,Counsellor counsellor) {
		model.addAttribute("obj", new Counsellor());
		return "rview";
	}
	
	@PostMapping("/register")
	public String handleRegister(Model model,Counsellor counsellor) {
		model.addAttribute("obj", new Counsellor());
		 cservice.saveCounsellor(counsellor);
		model.addAttribute("smsg", "Counsellor Saved");
		return "rview";
		
	}
	
	
}
