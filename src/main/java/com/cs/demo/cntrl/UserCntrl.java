package com.cs.demo.cntrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.demo.entity.Users;
import com.cs.demo.repo.UserRepo;

@RestController
public class UserCntrl {
	
	@Autowired
	private UserRepo repo;
	
	
	//register API
	
	@PostMapping("/registration")
	public Map<String,Object> addUser(@RequestBody Users u) {
		
		Map<String, Object> response = new HashMap<>();

		
		if(repo.existsByMobile(u.getMobile())){
			

	        response.put("message", "Mobile Number is already registered");
	       
			
		}else {
		
		 repo.save(u);
		 
		 response.put("uid", u.getUid());
	     response.put("name", u.getUname());
		 response.put("message", "User registered succesffully");
		}
		
		return response;

	}
	
 	
	@PostMapping("/login")
	public Map<String,Object> loginUsers(@RequestBody Users u) {
		
		Users user=repo.findByMobile(u.getMobile());
		
		Map<String, Object> response = new HashMap<>();

		
		if(user!=null) {
			
	        response.put("message", "Login Successful");
	        response.put("uid", user.getUid());
	        response.put("name", user.getUname());

		}else {


		    response.put("message", "Invalid Mobile Number");
		}
	
	
    return response;
}

	
	

}
