package com.bistu.tally.dao.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bistu.tally.dao.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
	/**
	 * 通过账单id，修改账单金额
	 * 
	 * @param amount
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update Bill bill set bill.category=?1,bill.classify=?2,bill.amount=?3,bill.remarks=?4 where bill.id=?5")
	public int updateBill(int category, String classify, float amount, String remakrs, Long id);

	/**
	 * 通过账单id，进入t_bill表删除账单
	 * 
	 * @param id
	 * @return
	 */
	@Modifying
	@Query(nativeQuery = true, value = "delete from t_bill where id=?1")
	public int deleteBill(Long id);

	ArrayList<Bill> findByUserId(Long userid);
	@Query("select b from Bill b where b.id=?1")
	ArrayList<Bill> findByBillId(Long id);
}
