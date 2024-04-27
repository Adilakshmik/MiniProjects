package in.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ashokit.dto.LoginDto;
import in.ashokit.dto.RegisterDto;
import in.ashokit.dto.ResetDto;
import in.ashokit.dto.UserDto;
import in.ashokit.entity.CityEntity;
import in.ashokit.entity.CountryEntity;
import in.ashokit.entity.StateEntity;
import in.ashokit.entity.UserEntity;
import in.ashokit.service.UserService;
import in.ashokit.utils.AppConstants;
import in.ashokit.utils.AppProperties;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private AppProperties props;
	
	@GetMapping("/")
	public String loginPage(Model model) {
		model.addAttribute("loginDto", new LoginDto());
		return "index";
	}
	@PostMapping("/login")
	public String login(UserDto udto,Model model,ResetDto resetDto,UserEntity userEntity) {
		Map<String, String> messages = props.getMessages();
		 UserDto user = service.getUser(udto.getEmail(),udto.getPwd());
		 if(user==null) {
				model.addAttribute("loginDto", new LoginDto());
				model.addAttribute(AppConstants.ERR_MSG, messages.get(AppConstants.INVALID_CREDENTIALS));
				return "index";
			}
		 if("YES".equals(user.getUpdatedPwd())) {
			 return AppConstants.DASHBOARD;
			}else {
				 resetDto.setEmail(udto.getEmail());
				model.addAttribute(AppConstants.RESET, resetDto);
				return AppConstants.RESET;
			}
			}
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute(AppConstants.REGISTERDTO, new RegisterDto());
		model.addAttribute("countries", service.getCountry());
		return "registration";
	}
	@GetMapping("/states/{cid}")
	@ResponseBody
	public Map<Integer,String> getStates(@PathVariable("cid") Integer cid,CountryEntity countryEntity,StateEntity stateEntity) {
		return   service.getState(cid,countryEntity,stateEntity);
		
	}
	@GetMapping("/cities/{sid}")
	@ResponseBody
	public Map<Integer,String> getCities(@PathVariable ("sid")Integer sid,CityEntity cityEntity,StateEntity stateEntity) {
		return 	service.getCity(sid, cityEntity, stateEntity);
		
	}
	@PostMapping("/register")
	public String register(String email,Model model,RegisterDto registerDto) {
		model.addAttribute("countries", service.getCountry());
		Map<String, String> messages = props.getMessages();
		  UserDto user = service.getUser(email);
		if(user==null) {
			
			model.addAttribute(AppConstants.REGISTERDTO, new RegisterDto());
			model.addAttribute(AppConstants.SUCC_MSG, messages.get(AppConstants.REGISTER_SUCCESS));
			
			service.registerUser(registerDto);
						
		}else {
			
			model.addAttribute(AppConstants.REGISTERDTO, new RegisterDto());
			model.addAttribute(AppConstants.MSG, messages.get(AppConstants.ALREADY_REGISTER));
			}
		return "registration";
		}
	@GetMapping("/reset/{id}")
	public String resetPage(@PathVariable ("id") Integer id,Model model) {
		model.addAttribute(AppConstants.RESETDTO, new ResetDto());
		return AppConstants.RESET;
	}
	@PostMapping("/reset")
	public String reset(ResetDto resetDto,UserDto userDto,Model model) {
		Map<String, String> messages = props.getMessages();
		if(!(resetDto.getUpdatedPwd().equals(resetDto.getConfirmPwd()))) {
			model.addAttribute(AppConstants.RESETDTO, new ResetDto());
			model.addAttribute(AppConstants.ERR_MSG,messages.get(AppConstants.PWD_MIS_MATCH));
			return AppConstants.RESET;
		}
			UserDto user = service.getUser(resetDto.getEmail());
			if(user==null) {
				model.addAttribute(AppConstants.REGISTERDTO, new ResetDto());
				model.addAttribute(AppConstants.ERR_MSG,messages.get(AppConstants. INVALID_PWD));
				return AppConstants.RESET;
			}
		else {
		 service.resetPwd(resetDto,resetDto.getPwd());
		return AppConstants.DASHBOARD;
		}
			}
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		String quote = service.getQuote();
		model.addAttribute(AppConstants.QUOTE, quote);
		return AppConstants.DASHBOARD;
		
		
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "/";
	}
	}
