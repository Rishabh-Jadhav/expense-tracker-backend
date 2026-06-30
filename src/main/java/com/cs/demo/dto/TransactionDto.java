package com.cs.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransactionDto {
	
	 LocalDate getDate();
	    String getNote();
	    String getType();
	    BigDecimal getDebit();
	    BigDecimal getCredit();
	    BigDecimal getClbal();

}
