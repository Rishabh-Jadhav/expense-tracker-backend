package com.cs.demo.cntrl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.demo.dto.TransactionDto;
import com.cs.demo.entity.Opbal;
import com.cs.demo.entity.Transaction;
import com.cs.demo.repo.OpbalRepo;
import com.cs.demo.repo.TransactionRepo;

@RestController
public class TransactionCntrl {
	
	@Autowired
	private TransactionRepo repo;
	
	@Autowired
	private OpbalRepo opbalrepo;
	
	
	
	
	//save credit API
	@PostMapping("/insert_credit")
	public Transaction saveCredit(@RequestBody Transaction t) {
		
		
	    // Get opening balance of the user

		Opbal opbal=opbalrepo.findByUid(t.getUid());
		
	    // Calculate new balance
		
		BigDecimal closingBalance=opbal.getOpbalance().add(t.getCredit());
		
	    // Set closing balance in transaction
		t.setClbal(closingBalance);
		
		// Update opening balance for next transaction
		 opbal.setOpbalance(closingBalance);
		 opbalrepo.save(opbal);
		 
		 // Save transaction
		 return repo.save(t);
	}
	
	
	
	//save Debit API
	@PostMapping("/insert_debit")
	public Transaction saveDebit(@RequestBody Transaction t) {
		
	    // Get opening balance of the user

		Opbal opbal=opbalrepo.findByUid(t.getUid());
		
	    // Calculate new balance

		BigDecimal closinBalance=opbal.getOpbalance().subtract(t.getDebit());
		
	    // Set closing balance in transaction

		t.setClbal(closinBalance);
		
		// Update opening balance for next transaction

		opbal.setOpbalance(closinBalance);
		opbalrepo.save(opbal);
		
		 // Save transaction

		return repo.save(t);
		
	}
	
	@GetMapping("/transaction_list/{uid}")
	public  Map<String,List<TransactionDto>> transactionData(@PathVariable("uid") long uid){
		List <TransactionDto> l=repo.getTransactionDetails(uid);
		Map<String,List<TransactionDto>> responce=new HashMap<>();
		responce.put("Transaction",l);
		return responce;
	}
	
	
	@GetMapping("total_credit/{uid}")
	public Map<String,Long> totalCredit(@PathVariable("uid") long uid) {
		long res=repo.getTotalCredit(uid);
		Map<String,Long> response=new HashMap<>();
		response.put("totalcredit", res);
		return response;
	}
		
		
	@GetMapping("total_debit/{uid}")
	public Map<String,Long> totalDebit(@PathVariable("uid") long uid){
		Long res=repo.getTotalDebit(uid);
		Map<String,Long> response=new HashMap<>();
		response.put("totaldebit", res);
		return response;
	}
	
	@GetMapping("/recent_date/{uid}")
	public LocalDate recentDate(@PathVariable("uid") long uid) {
	    return repo.getRecentDate(uid);
	}
	
		
		
}
	
	
	
		
		
		
		
		

	


