package in.ashokit.utils;

import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConstants {
	
	private AppConstants () {
		
	}

	public static final String ERR_MSG="emsg";
	public static final String SUCC_MSG="smsg";
	public static final String MSG="msg";
	public static final String QUOTE="quote";
	
	public static final String RESET="reset";
	
	public static final String INVALID_CREDENTIALS="InvalidCredentials";
	public static final String PWD_MIS_MATCH="PwdMatchError";
	public static final String REGISTER_SUCCESS="RegisterSucc";
	public static final String ALREADY_REGISTER="AlreadyRegister";
	public static final String INVALID_PWD="InvalidPassword";
	
	public static final String DASHBOARD="dashboard";
	public static final String RESETDTO="resetDto";
	public static final String REGISTERDTO="registerDto";

}
