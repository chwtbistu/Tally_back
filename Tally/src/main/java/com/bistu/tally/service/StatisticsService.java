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

	/**
	 * 查找指定日子，指定收支类型的账单
	 * 
	 * @param userid
	 * @param category
	 * @param day
	 * @return
	 */
	public ArrayList<Bill> findByUserIdAndCategoryAndDay(Long userid, int category, int day) {
		return billRepository.findByUserIdAndCategoryAndMonth(userid, category, day);
	}

	/**
	 * 获得指定年份，指定收支类型的订单
	 * 
	 * @param userid
	 * @param category
	 * @param year
	 * @return
	 */
	public ArrayList<Bill> findByUserIdAndCategoryAndYear(Long userid, int category, int year) {
		return billRepository.findByUserIdAndCategoryAndMonth(userid, category, year);
	}

	/**
	 * 通过消费类型获得指定用户的订单
	 * 
	 * @param userid
	 * @param classify
	 * @param month
	 * @param category
	 * @return
	 */
	public ArrayList<Bill> findByUserIdAndClassify(Long userid, String classify, int month, int category) {
		return billRepository.findByUserIdAndClassifyAndMonthAndCategoty(userid, classify, month, category);
	}
}
