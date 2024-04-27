package in.ashokit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendMail(String to,String sub,String body) {
		
		try {
		SimpleMailMessage msg=new SimpleMailMessage();
		msg.setTo(to);
		msg.setText(body);
		msg.setSubject(sub);
		mailSender.send(msg);
		
	}catch(Exception e) {
		e.printStackTrace();
	}
		return true;
	}
}
