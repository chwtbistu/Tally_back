package com.bistu.tally.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bistu.tally.bean.ResultInfo;
import com.bistu.tally.dao.entity.Bill;
import com.bistu.tally.service.BillService;
import com.bistu.tally.util.DateFactory;
import com.bistu.tally.util.SeriesBean;
import com.bistu.tally.util.YagolRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BillController {
	@Autowired
	private BillService billService;

	private DateFactory dateFactory = new DateFactory();
	private YagolRequest yagolRequest=new YagolRequest();

	/**
	 * 增加账单
	 * 
	 * @param userid
	 * @param category
	 * @param classify
	 * @param amount
	 * @param remarks
	 * @return
	 */
	@PostMapping(value = "/bill/add/{userid}&{category}&{classify}&{amount}&{remarks}", produces = "application/json;charset=utf-8")
	public ResultInfo addBill(@PathVariable("userid") Long userid, @PathVariable("category") int category,
			@PathVariable("classify") String classify, @PathVariable("amount") float amount,
			@PathVariable("remarks") String remarks) {
		log.info("get requesting...");
		log.info(classify);
		Bill bill = new Bill();
		bill = billService.addBill(userid, new Date(), category, classify, amount, remarks);
		if (bill != null) {
			ResultInfo resultInfo = ResultInfo.success();
			resultInfo.setData(bill);
			return resultInfo;
		} else {
			ResultInfo resultInfo = ResultInfo.failure();
			return resultInfo;
		}
	}

	/**
	 * 修改账单
	 * 
	 * @param id
	 * @param amount
	 * @return
	 * @throws ParseException
	 */
	@PostMapping({ "/bill/update/{id}&{date}&{category}&{classify}&{amount}&{remarks}" })
	public ResultInfo updateBill(@PathVariable("id") Long id, @PathVariable("date") String dateString,
			@PathVariable("category") int category, @PathVariable("classify") String classify,
			@PathVariable("remarks") String remarks, @PathVariable("amount") float amount) throws ParseException {
		log.info("get requesting...");
		log.info(dateString);
		if (billService.updateBill(category, classify, amount, remarks, dateString, id)) {
			ResultInfo resultInfo = ResultInfo.success();
			resultInfo.setData(billService.findByBillId(id));
			return resultInfo;
		} else {
			ResultInfo resultInfo = ResultInfo.failure();
			return resultInfo;
		}
	}

	/**
	 * 通过账单id删除账单
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping({ "/bill/delete/{id}" })
	public ResultInfo deleteBill(@PathVariable("id") Long id) {
		if (billService.deleteBill(id)) {
			ResultInfo resultInfo = ResultInfo.success();
			return resultInfo;
		} else {
			ResultInfo resultInfo = ResultInfo.failure();
			return resultInfo;
		}
	}

	/**
	 * 得到指定账单id的账单
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping({ "/bill/get/{id}" })
	public ResultInfo getBillFromId(@PathVariable("id") Long id) {
		log.info("get requesting...");
		Bill bill = new Bill();
		bill = billService.findByBillId(id);
		if (bill != null) {
			ResultInfo resultInfo = ResultInfo.success();
			resultInfo.setData(bill);
			log.info(bill.getTime().toString());
			return resultInfo;
		} else {
			ResultInfo resultInfo = ResultInfo.failure();
			return resultInfo;
		}
	}

	/**
	 * 通过用户id，得到该用户所有账单
	 * 
	 * @param userid
	 * @return
	 */
	@GetMapping({ "/bill/get/user/{userid}" })
	public ResultInfo getBillFromUserId(@PathVariable("userid") Long userid) {
		log.info("get requsting...");
		ResultInfo resultInfo = ResultInfo.success();
		resultInfo.setData(billService.findByUserId(userid));
		return resultInfo;
	}

}
