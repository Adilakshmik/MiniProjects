package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Counsellor;
import in.ashokit.repo.CounsellorRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService{

	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Override
	public boolean saveCounsellor(Counsellor counsellor) {
		Counsellor cemail = counsellorRepo.findByCemail(counsellor.getCemail());
		if(cemail!=null) {
			return false;
		}else {
		Counsellor save = counsellorRepo.save(counsellor);
		return save.getCid()!=null;
	}
	}

	@Override
	public Counsellor getCounsellor(Counsellor counsellor) {
		return  counsellorRepo.findByCemailAndCpwd(counsellor.getCemail(), counsellor.getCpwd());
		
	}
	
	
}
