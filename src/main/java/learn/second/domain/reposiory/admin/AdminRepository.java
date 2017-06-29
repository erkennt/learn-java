package learn.second.domain.reposiory.admin;

import java.util.List;

import learn.second.domain.model.Admin;

public interface AdminRepository {
	int userCount();

	Admin findOne(String username);

	List<Admin> findAll();

	void insertAdmin(String adminId, String password);

	public void deleteAll();

	public void insertAdmin(Admin admin);
}