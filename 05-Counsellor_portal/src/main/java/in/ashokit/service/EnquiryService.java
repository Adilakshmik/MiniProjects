 package in.ashokit.service;

import java.util.List;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Enquiry;

public interface EnquiryService {
	
	public Dashboard getDashboardInfo(Integer cid);
	public Enquiry addEnquiry(Enquiry enquiry,Integer cid) ;
	public List<Enquiry> getAllEnquiry(Enquiry enquiry,Integer cid);
	public List<Enquiry> getFilters(Enquiry enquiry,Integer cid);
	public Enquiry getEnquiry(Integer id);
	public Enquiry updateEnquiry(Enquiry enquiry, Integer cid);
	
}
