package dao;

import java.util.List;

import org.springframework.stereotype.Component;

import model.MailAuthenticationModel;

@Component
public interface MailAuthenticationDAO {

	public void addAuthenticationPool(String userId, String MailAddress);

	public List<MailAuthenticationModel> searchAuthenticationPool(String MailAddress);
	public List<MailAuthenticationModel> searchAuthenticationPool(String MailAddress, String userId);

	public void updateStatus(String mailAddress);
}
