package in.ashokit.service;

import java.util.Map;

import in.ashokit.dto.RegisterDto;
import in.ashokit.dto.ResetDto;
import in.ashokit.dto.UserDto;
import in.ashokit.entity.CityEntity;
import in.ashokit.entity.CountryEntity;
import in.ashokit.entity.StateEntity;



public interface UserService {
	
	
	public Map<Integer,String> getCountry();
	public Map<Integer,String> getState(Integer cid,CountryEntity countryState,StateEntity stateEntity);
	public Map<Integer,String> getCity(Integer sid, CityEntity cityEntity,StateEntity stateEntity);
	public UserDto getUser(String email);
	public boolean registerUser(RegisterDto registerDto);
	public UserDto getUser(String email,String pwd);
	public boolean resetPwd(ResetDto resetDto,String pwd );
	public String getQuote();

}
