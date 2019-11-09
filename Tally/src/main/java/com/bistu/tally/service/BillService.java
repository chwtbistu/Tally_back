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

	@Transactional
	public boolean updateBill(float amount, Long id) {
		if (billRepository.updateBill(amount, id)==1) {
			return true;
		} else {
			return false;
		}
	}
}
