package th.co.dptf.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.co.dptf.service.UserService;


@RestController
@RequestMapping("/gest")
public class GestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/get_exits_token")
	public Object GetAccessToken(@RequestBody HashMap<String,String> info) {
		System.out.println(info.get("username"));
		return userService.GetClientId(info.get("username").trim());
	}

}
