package com.bistu.tally.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bistu.tally.dao.entity.Bill;
import com.bistu.tally.dao.repository.BillRepository;

@Service
public class StatisticsService {
	@Autowired
	private BillRepository billRepository;

	public ArrayList<Bill> findByUserId(Long userid) {
		ArrayList<Bill> bills = new ArrayList<Bill>();
		bills = billRepository.findByUserId(userid);
		return bills;
	}

	public ArrayList<Bill> findByUserIdAndCategory(Long userid, int category) {
		return billRepository.findByUserIdAndCategory(userid, category);
	}

	public ArrayList<Bill> findByUserIdAndCategoryAndMonth(Long userid, int category, int month) {
		return billRepository.findByUserIdAndCategoryAndMonth(userid, category, month);
	}

	public ArrayList<Bill> findByUserIdAndCategoryAndDay(Long userid, int category, int day) {
		return billRepository.findByUserIdAndCategoryAndMonth(userid, category, day);
	}

	public ArrayList<Bill> findByUserIdAndCategoryAndYear(Long userid, int category, int year) {
		return billRepository.findByUserIdAndCategoryAndMonth(userid, category, year);
	}

	public ArrayList<Bill> findByUserIdAndClassify(Long userid,String classify,int month,int category) {
		return billRepository.findByUserIdAndClassifyAndMonthAndCategoty(userid,classify,month,category);
	}
}
