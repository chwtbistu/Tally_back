package com.bistu.tally.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bistu.tally.bean.ResultInfo;
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
	@GetMapping({ "/bill/add/{userid}&{category}&{classify}&{amount}&{remarks}" })
	public ResultInfo addBill(@PathVariable("userid") Long userid, @PathVariable("category") int category,
			@PathVariable("classify") String classify, @PathVariable("amount") float amount,
			@PathVariable("remarks") String remarks) {
		log.info("get requesting...");
		if (billService.addBill(userid, new Date(), category, classify, amount, remarks)) {
			ResultInfo resultInfo = ResultInfo.success();
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
	@GetMapping({ "/bill/update/{id}&{amount}" })
	public ResultInfo updateBill(@PathVariable("id") Long id, @PathVariable("amount") float amount) {
		log.info("get requesting...");
		if (billService.updateBill(amount, id)) {
			ResultInfo resultInfo = ResultInfo.success();
			return resultInfo;
		} else {
			ResultInfo resultInfo = ResultInfo.failure();
			return resultInfo;
		}
	}

}
