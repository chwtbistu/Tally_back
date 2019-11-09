package com.bistu.tally.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bistu.tally.dao.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

	@Modifying
	@Query("update Bill bill set bill.amount=?1 where bill.id=?2")
	public int updateBill(float amount, Long id);
}
