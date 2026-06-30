package com.cs.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cs.demo.dto.TransactionDto;
import com.cs.demo.entity.Opbal;

public interface OpbalRepo extends JpaRepository<Opbal, Long> {

	Opbal findByUid(long uid);
	
	@Query(value="select * from opbal where uid=:uid",nativeQuery=true)
	Optional<Opbal> getOpbalDetails(@Param("uid") long uid);

	

	

	
}
