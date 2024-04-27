package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepo;
import in.ashokit.repo.EnquiryRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	private EnquiryRepo enquiryRepo;
	
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Override
	public Dashboard getDashboardInfo(Integer cid) {
		Dashboard d=new Dashboard();
		Long totalCnt = enquiryRepo.getEnquiries(cid);
		Long openCnt = enquiryRepo.getEnquiries(cid, "new");
		Long enrolledCnt = enquiryRepo.getEnquiries(cid, "enrolled");
		Long lostCnt = enquiryRepo.getEnquiries(cid, "lost");
		d.setTotal(totalCnt);
		d.setOpen(openCnt);
		d.setEnrolled(enrolledCnt);
		d.setLost(lostCnt);
		return d;
	}
	
	public Enquiry addEnquiry(Enquiry enquiry,Integer cid) {
		 Counsellor id = counsellorRepo.findById(cid).orElseThrow();
		enquiry.setCounsellor(id);
		return enquiryRepo.save(enquiry);
			 
		}
		
		
		
	@Override
	public List<Enquiry> getAllEnquiry(Enquiry enquiry,Integer cid) {
		Counsellor id = counsellorRepo.findById(cid).orElseThrow();
		 enquiry.setCounsellor(id);
		 Example<Enquiry> example = Example.of(enquiry);
		 return  enquiryRepo.findAll(example );
			
	}
	@Override
	public List<Enquiry> getFilters(Enquiry enquiry, Integer cid) {
		 
		 Counsellor counsellor=new Counsellor ();
			counsellor.setCid(cid);
			Enquiry engineSearch=new Enquiry();
			engineSearch.setCounsellor(counsellor);
			
			if((!enquiry.getClassMode().equals("")) && enquiry.getClassMode()!=null) {
				engineSearch.setClassMode(enquiry.getClassMode());
			}
			if((!enquiry.getCourseName().equals("")) && enquiry.getCourseName()!=null) {
				engineSearch.setCourseName(enquiry.getCourseName());
			}
			if((!enquiry.getStatus().equals("")) && enquiry.getStatus()!=null) {
				engineSearch.setStatus(enquiry.getStatus());
			}
			
		Example<Enquiry> example = Example.of(engineSearch );
		return enquiryRepo.findAll(example );
		
	}
	
	@Override
	public Enquiry getEnquiry(Integer id) {
		
		 Optional<Enquiry> byId = enquiryRepo.findById(id);
		 Enquiry enquiry= null;
		 if(byId .isPresent()) {
			  enquiry = byId.get();
		 }
		return enquiry;
}
	
	public Enquiry updateEnquiry(Enquiry enq,Integer eid) {
		
		 Enquiry enquiry = enquiryRepo.findById(eid).orElseThrow();
		 enquiry .setName(enq.getName());
		 enquiry.setPhno(enq.getPhno());
		 enquiry.setClassMode(enq.getClassMode());
		 enquiry.setCourseName(enq.getCourseName());
		 enquiry.setStatus(enq.getStatus());
			return  enquiryRepo.save(enquiry);
		
		 
		}

	
}
	
	
	 

	
