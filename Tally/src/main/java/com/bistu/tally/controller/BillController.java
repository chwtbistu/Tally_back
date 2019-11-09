package com.bistu.tally.controller;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BillController {
	@Autowired
	private BillService billService;

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
	@PostMapping({ "/bill/add/{userid}&{category}&{classify}&{amount}&{remarks}" })
	public ResultInfo addBill(@PathVariable("userid") Long userid, @PathVariable("category") int category,
			@PathVariable("classify") String classify, @PathVariable("amount") float amount,
			@PathVariable("remarks") String remarks) {
		log.info("get requesting...");
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
	 */
	@PostMapping({ "/bill/update/{id}&{amount}" })
	public ResultInfo updateBill(@PathVariable("id") Long id, @PathVariable("amount") float amount) {
		log.info("get requesting...");
		if (billService.updateBill(amount, id)) {
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

}
