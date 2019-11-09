package com.bistu.tally.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bistu.tally.bean.ResultInfo;
import com.bistu.tally.dao.entity.Bill;
import com.bistu.tally.service.StatisticsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StatisticsController {
	@Autowired
	private StatisticsService statisticsService;

	@GetMapping({ "/bill/statistics/{userid}" })
	public ResultInfo getAllBillFromUserId(@PathVariable("userid") Long userid) {
		log.info("get requesing...");
		ResultInfo resultInfo = ResultInfo.success();
		ArrayList<Bill> bills = new ArrayList<>();
		bills = statisticsService.findByUserId(userid);
		resultInfo.setData(bills);
		return resultInfo;
	}

}
