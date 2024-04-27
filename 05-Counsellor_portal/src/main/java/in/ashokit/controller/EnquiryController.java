 package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.Enquiry;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService eservice;
	
	@GetMapping("/addEnquiry")
	public String loadEnquiry(Model model) {
		model.addAttribute("enq",new Enquiry());
		return "eview";
	}
	
	@PostMapping("/addEnquiry")
	public String saveEnquiry(Model model,HttpServletRequest req,Enquiry enquiry1) {
		HttpSession session = req.getSession(false);
		Integer id =(Integer) session.getAttribute("cid");
		
		Enquiry enquiry = eservice.addEnquiry(enquiry1,id);
		
		if(enquiry!=null) {
		model.addAttribute("enq",new Enquiry());
		model.addAttribute("smsg", "Student Saved ");
		
		}else {
			model.addAttribute("enq",new Enquiry());
			model.addAttribute("emsg", "Student not Saved ");
			}
		return "eview";
	}
	
	@GetMapping("/enquiries")
	public String getAllEnquiries(Model model,HttpServletRequest req,Enquiry enquiry){
		HttpSession session = req.getSession(false);
		Integer id =(Integer) session.getAttribute("cid");
		List<Enquiry> allEnquiry = eservice.getAllEnquiry(new Enquiry(),id);
		model.addAttribute("enqs",allEnquiry);
		model.addAttribute("enq", new Enquiry());
		return "displayall";
	}  
	@PostMapping("/enquiries")
	public String getFilterEnquiries(Model model,HttpServletRequest req,Enquiry enquiry){
		HttpSession session = req.getSession(false);
		Integer id =(Integer) session.getAttribute("cid");
		List<Enquiry> allEnquiry = eservice.getFilters(enquiry, id);
		model.addAttribute("enqs",allEnquiry);
		 model.addAttribute("enq", new Enquiry());
		return "displayall";
	}
	
	@GetMapping("/update")
	public String getEnquiry(@RequestParam Integer id,Model model) {
		 Enquiry enquiry = eservice.getEnquiry(id);
		model.addAttribute("enq", enquiry);
		return "update";
	}
	
	@PostMapping("/update")
	public String updateEnquiry( Model model,Enquiry enquiry,HttpServletRequest req,Integer id) {
		 Enquiry updateEnquiry = eservice.updateEnquiry(enquiry,id);
		 if(updateEnquiry!=null) {
		model.addAttribute("smsg", "Enquiry updated");
		 }
		model.addAttribute("enq", enquiry);
		return "update";
	}
}
