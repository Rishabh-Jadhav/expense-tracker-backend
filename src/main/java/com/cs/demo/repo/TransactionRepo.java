package com.cs.demo.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cs.demo.dto.TransactionDto;
import com.cs.demo.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
	
	@Query(value="select date,note,credit,debit,clbal from transaction where uid=:uid",nativeQuery=true)
	List<TransactionDto> getTransactionDetails(@Param("uid") long uid);
	
	
	@Query(value="select sum(credit) from transaction where uid=:uid",nativeQuery=true)
	Long getTotalCredit(@Param("uid") long uid);
	
	@Query(value="select sum(debit) from transaction where uid=:uid",nativeQuery=true)
	Long getTotalDebit(@Param("uid") long uid);
	
	@Query(value="select date from transaction where uid=:uid order by tid desc limit 1",nativeQuery=true)
	LocalDate getRecentDate(@Param("uid") long uid);
	
	

}
