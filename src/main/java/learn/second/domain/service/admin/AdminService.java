package learn.second.domain.service.admin;

import java.util.List;

import learn.second.domain.model.Admin;

public interface AdminService {
	public boolean userCount();

	public void insertAdmin(String adminId, String password);

	public List<Admin> findAll();

	public void deleteAll();

	public List<Integer> insertAdminList(List<Admin> list);
}
