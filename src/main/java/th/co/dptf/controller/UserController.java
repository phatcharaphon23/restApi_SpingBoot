package th.co.dptf.controller;

import java.io.Console;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import th.co.dptf.entity.RsponseCustomize;
import th.co.dptf.entity.User;
import th.co.dptf.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add_user")
	public Object AddUser(@RequestBody User user) {
		return userService.AddUser(user);
	}
	
	@PostMapping("/resset_password")
	public Object ResetPassword(@RequestBody HashMap<String,String> info) {
		String pwd = info.get("password");
		String id = info.get("id");
		int as = Integer.parseInt(id);
				
//		System.out.println();
		return userService.ResetPassword(pwd, as);
	}
	
	@PostMapping("/list")
	public Object GetUserList(@RequestBody HashMap<String,Integer> info) {
		System.out.println(" ---");
		int page = info.get("page");
		System.out.println(page);
		return userService.Userlist(page);
	}
	
	@PostMapping("/delete_user")
	public Object DeleteUser(@RequestBody HashMap<String,Integer> info) {
		int id = info.get("id");
		return userService.DeleteUser(id);
	}
}
