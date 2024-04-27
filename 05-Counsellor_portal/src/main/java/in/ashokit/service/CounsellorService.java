package in.ashokit.service;

import org.springframework.stereotype.Service;

import in.ashokit.entity.Counsellor;

@Service
public interface CounsellorService {
	
	public boolean saveCounsellor(Counsellor counsellor);
	
	public Counsellor getCounsellor(Counsellor counsellor);
}
