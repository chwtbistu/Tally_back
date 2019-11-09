package com.bistu.tally.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bistu.tally.dao.entity.Bill;
import com.bistu.tally.dao.repository.BillRepository;

@Service
public class BillService {
	@Autowired
	private BillRepository billRepository;

	/**
	 * 增加账单
	 * 
	 * @param userid   用户id
	 * @param date     本地时间
	 * @param category 收支类型，1为支出，2为收入
	 * @param classify 类别
	 * @param amount   金额
	 * @param remarks  备注，可以为空
	 * @return
	 */
	public @ResponseBody boolean addBill(Long userid, Date date, int category, String classify, float amount,
			String remarks) {
		Bill bill = new Bill();
		bill.setUserId(userid);
		bill.setTime(date);
		bill.setCategory(category);
		bill.setClassify(classify);
		bill.setAmount(amount);
		bill.setRemarks(remarks);
		billRepository.save(bill);
		return true;
	}

	/**
	 * 通过账单id，修改账单
	 * 
	 * @param amount
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean updateBill(float amount, Long id) {
		if (billRepository.updateBill(amount, id) == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 通过账单id删除账单
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean deleteBill(Long id) {
		if (billRepository.deleteBill(id) == 1) {
			return true;
		} else {
			return false;
		}
	}
}
