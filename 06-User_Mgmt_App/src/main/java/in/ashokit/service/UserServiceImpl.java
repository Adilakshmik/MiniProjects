package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ashokit.dto.QuoteDto;
import in.ashokit.dto.RegisterDto;
import in.ashokit.dto.ResetDto;
import in.ashokit.dto.UserDto;
import in.ashokit.entity.CityEntity;
import in.ashokit.entity.CountryEntity;
import in.ashokit.entity.StateEntity;
import in.ashokit.entity.UserEntity;
import in.ashokit.repo.CityRepo;
import in.ashokit.repo.CountryRepo;
import in.ashokit.repo.StateRepo;
import in.ashokit.repo.UserRepo;
import in.ashokit.utils.EmailUtils;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	
	private QuoteDto[] quotations=null;
	
	Random r=new Random();
	

	@Override
	public Map<Integer,String> getCountry() {
		Map<Integer,String> countryMap=new HashMap<>();
		List<CountryEntity> countriesList = countryRepo.findAll();
		countriesList.forEach(c->countryMap.put(c.getCountryId(), c.getCountryName()));
		return countryMap;
	}
	@Override
	public Map<Integer,String> getState(Integer cid,CountryEntity countryEntity,StateEntity stateEntity){
		Map<Integer,String> stateMap=new HashMap<>();
		countryEntity.setCountryId(cid);
		stateEntity.setCountry(countryEntity);
		Example<StateEntity> of = Example.of(stateEntity);
		 List<StateEntity> all = stateRepo.findAll(of);
		 all.forEach(s->stateMap.put(s.getStateId(),s.getStateName()));
		return stateMap;
	}

	@Override
	public Map<Integer,String> getCity(Integer sid, CityEntity cityEntity,StateEntity stateEntity) {
		Map<Integer,String> countryMap=new HashMap<>();
		stateEntity.setStateId(sid);
		cityEntity.setState(stateEntity);
		Example<CityEntity> of = Example.of(cityEntity);
		List<CityEntity> all = cityRepo.findAll(of);
		all.forEach(c->countryMap.put(c.getCityId(),c.getCityName()));
		
		return countryMap;
	}

	@Override
	public UserDto getUser(String email) {
		   UserEntity entity = userRepo.findByEmail(email);
		   ModelMapper mapper=new ModelMapper();
		   return   mapper.map(entity, UserDto.class);
		 
	}
	@Override
	public boolean registerUser(RegisterDto registerDto) {
		ModelMapper mapper=new ModelMapper();
		UserEntity entity = mapper.map(registerDto, UserEntity.class);
		CountryEntity country = countryRepo.findById(registerDto.getCountryId()).orElseThrow();
		StateEntity state = stateRepo.findById(registerDto.getStateId()).orElseThrow();
		 CityEntity city = cityRepo.findById(registerDto.getCityId()).orElseThrow();
		 entity.setCountry(country);
		 entity.setState(state);
		 entity.setCity(city);
		entity.setPwd(generateRandomPwd());
		entity.setUpdatedPwd("No");
		UserEntity save = userRepo.save(entity);
		String pwd = save.getPwd();
		String email = save.getEmail();
		String sub="Generated Password-Ashok IT";
		String body="Congratulations.. your Account Created Successfuly.This is Your Auto-generated Password "+pwd;
		return emailUtils.sendMail(email , sub, body);
		
		}
	@Override
	public UserDto getUser(String email,String pwd) {
		  UserEntity user= userRepo.findByEmailAndPwd(email,pwd);
		  if(user!=null) {
			 
		  ModelMapper mapper=new ModelMapper();
			return mapper.map(user, UserDto.class);
			
			}else {
			  return null;
		  }
		  }
	@Override
	public boolean resetPwd(ResetDto resetDto,String pwd) {
		UserEntity userEntity = userRepo.findByPwd(pwd);
		userEntity.setUpdatedPwd("YES");
		userEntity.setPwd(resetDto.getUpdatedPwd());
		 userRepo.save(userEntity);
		return true;
		}
	@Override
	public String getQuote() {
		if(quotations==null) {
		String url="https://type.fit/api/quotes";
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		String body = responseEntity.getBody();
		ObjectMapper mapper=new ObjectMapper();
		try {
		quotations = mapper.readValue(body, QuoteDto[].class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
		if(quotations!=null) {
			int index = r.nextInt(quotations.length-1);
			return quotations[index].getText();
		}else {
			return null;
		}
		}
			
		
	private String generateRandomPwd() {
		String alphaNumericCharacters="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		StringBuilder randomString=new StringBuilder();
		for(int i=0;i<5;i++) {
			
			int randomIndex = r.nextInt(alphaNumericCharacters.length());
			char randomChar = alphaNumericCharacters.charAt(randomIndex);
			randomString.append(randomChar);
		}
		return randomString.toString();
	}
}
