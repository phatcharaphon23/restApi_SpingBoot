package th.co.dptf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.dptf.entity.PaginationCustmize;
import th.co.dptf.entity.RsponseCustomize;
import th.co.dptf.entity.User;
import th.co.dptf.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Value("${app.client.id}")
	private String 	CLIENT_ID;
	@Value("${app.client.secret}")
	private String 	CLIENT_SECRET;
	
	@Autowired
	private UserRepository userRepository;
	
	public RsponseCustomize GetClientId(String username) {
		RsponseCustomize re = new RsponseCustomize();
		re.setSuccess(false);
		re.setMessege("get clientID failed!");
//		System.out.println(username);
		HashMap<String, String>returnValue = new HashMap<String, String>();
		try {
			User exist = userRepository.usersExits(username);
//			System.out.println(exist.getUsername());
			if (exist != null) {
				returnValue.put("client_id", CLIENT_ID);
				returnValue.put("client_secret", CLIENT_SECRET);
				
				re.setSuccess(true);
				re.setMessege(returnValue);
				
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		return re;
	}
	
	
	public RsponseCustomize AddUser(User user) {
		RsponseCustomize re = new RsponseCustomize();
		re.setSuccess(true);
		re.setMessege("Add user successful");
		try { 
			User exist = userRepository.usersExits(user.getUsername());
			if (exist == null) {
				int save = userRepository.addUser(user.getId(),user.getUsername(),  user.getPassword());
			} else {
//				re.setUsername("UserExist");
				re.setSuccess(false);
				re.setMessege("UserExist");
			}
//			
		} catch (Exception ex) {
//			System.out.println(ex);
			re.setSuccess(false);
			re.setMessege(ex.getMessage());
		}
		return re;
		
	}
	public RsponseCustomize ResetPassword(String password, int id) {
//		RsponseCustomize re = "Reset password faild";
		RsponseCustomize re = new RsponseCustomize();
		re.setSuccess(false);
		re.setMessege("Reset password faild");
		try { 
			int changePassword = userRepository.resetPassword(password, id);
			if (changePassword > 0) {
				re.setSuccess(true);
				re.setMessege("Reset Password successfull!");
//				re = "Reset Password successfull!";
			}
		
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return re;
		
	}
	
	public RsponseCustomize Userlist(int page){
		RsponseCustomize re = new RsponseCustomize();
		PaginationCustmize info = new PaginationCustmize();
		int rows = userRepository.countRows();
		int pages = (int) Math.ceil((float) rows / 10);
		int fetch = page * 10;
		int start = fetch - 10;
		List<User> List = userRepository.userLists(start, fetch-1);

		info.setPages(pages);
		info.setPage(page);
		info.setInfo(List);

		re.setSuccess(true);
		re.setMessege(info);

		return re;
	}
	
	public RsponseCustomize DeleteUser(int id) {
		RsponseCustomize re = new RsponseCustomize();
		re.setSuccess(false);
		re.setMessege("Delete user faild!");
		try { 
			int deleteuser = userRepository.deleteuser(id);
			if (deleteuser > 0) {
				re.setSuccess(false);
				re.setMessege("Delet success");
			}
		
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return re;
		
	}
}
