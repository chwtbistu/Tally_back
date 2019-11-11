package com.bistu.tally.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bistu.tally.bean.ResultInfo;
import com.bistu.tally.dao.entity.Bill;
import com.bistu.tally.service.StatisticsService;
import com.sun.net.httpserver.HttpServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StatisticsController {
	@Autowired
	private StatisticsService statisticsService;

	/**
	 * 获得指定用户，指定的收支类型的，账单数据，当第三个参数month为0时，返回该用户所有该收支类型，否则返回指定月份的账单
	 * 127.0.0.1:8080/bill/statistics/?userid=1&category=1&month=11
	 * @param userid
	 * @param category
	 * @param month
	 * @return
	 */
	@GetMapping("/bill/statistics/")
	public ResultInfo getAllBillFromUserIdAndCategoryAndMonth(@RequestParam("userid") Long userid,
			@RequestParam("category") int category, @RequestParam("month") String month) {
		log.info("get requsting...");
		ResultInfo resultInfo = ResultInfo.success();
		if (Integer.parseInt(month) == 0) {
			resultInfo.setData(statisticsService.findByUserIdAndCategory(userid, category));
			return resultInfo;
		} else {
			resultInfo.setData(
					statisticsService.findByUserIdAndCategoryAndMonth(userid, category, Integer.parseInt(month)));
			return resultInfo;
		}

	}

}
