package learn.second.domain.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import learn.second.domain.model.Admin;
import learn.second.domain.reposiory.admin.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public boolean userCount() {

		int count = adminRepository.userCount();

		boolean flg = false;

		if (count > 0) {
			flg = true;
		}

		return flg;
	}

	public void insertAdmin(String adminId, String password) {

		// パスワードを暗号化する
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passwordHash = encoder.encode(password);

		adminRepository.insertAdmin(adminId, passwordHash);
	}

	@Override
	public List<Admin> findAll() {
		List<Admin> adminList = adminRepository.findAll();

		if (adminList.size() < 1) {
			Admin admin = new Admin();
			adminList.add(admin);
		}

		return adminList;
	}

	public void deleteAll() {
		adminRepository.deleteAll();

	}

	@Override
	public List<Integer> insertAdminList(List<Admin> list) {
		List<Integer> errorRowList = new ArrayList<Integer>();
		int rowCount = 0;

		for (Admin admin : list) {
			rowCount++;
			try {
				adminRepository.insertAdmin(admin);

			} catch (Exception e) {
				errorRowList.add(rowCount);
			}
		}
		return errorRowList;
	}

}
