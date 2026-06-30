package com.cs.demo.cntrl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.demo.entity.Opbal;
import com.cs.demo.repo.OpbalRepo;

@RestController
public class OpbalCntrl {
	@Autowired
	private OpbalRepo repo;
	
	@PostMapping("insert_opbal")
	public Opbal saveOpbal(@RequestBody Opbal o) {
		
		return repo.save(o);	
		
	}
	
	@GetMapping("/opbal/{uid}")
	public Optional<Opbal> getOpbalance(@PathVariable("uid") long uid){
		Optional<Opbal> l=repo.getOpbalDetails(uid);
		return l;
	}

}
