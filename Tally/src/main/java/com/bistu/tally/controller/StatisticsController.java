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
import com.bistu.tally.util.YagolRequest;
import com.sun.net.httpserver.HttpServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StatisticsController {
	@Autowired
	private StatisticsService statisticsService;

	private YagolRequest yagolRequest = new YagolRequest();

	/**
	 * 获得指定用户，指定的收支类型的，账单数据，返回该用户所有该收支类型，否则返回指定月份/年份/日的账单
	 * http://127.0.0.1:8080/bill/category/?userid=1&category=1&month=11&day=0&year=0
	 * 
	 * @param userid
	 * @param category
	 * @param month
	 * @param day
	 * @param year
	 * @return
	 */
	@GetMapping("/bill/category")
	public ResultInfo getAllBillFromUserIdAndCategoryAndMonth(@RequestParam("userid") Long userid,
			@RequestParam("category") int category, @RequestParam("month") String month,
			@RequestParam("day") String day, @RequestParam("year") String year) {
		log.info("get requsting...");
		ResultInfo resultInfo = ResultInfo.success();
		if (Integer.parseInt(month) == 0) {
			if (Integer.parseInt(day) == 0) {
				if (Integer.parseInt(year) == 0) {
					// 都是零，让人怎么办嘛，返回所有的信息
					resultInfo.setData(statisticsService.findByUserIdAndCategory(userid, category));
					return resultInfo;
				} else {
					// TODO year
					resultInfo.setData(
							statisticsService.findByUserIdAndCategoryAndYear(userid, category, Integer.parseInt(year)));
					return resultInfo;
				}
			} else {
				// TODO day
				resultInfo.setData(
						statisticsService.findByUserIdAndCategoryAndDay(userid, category, Integer.parseInt(day)));
				return resultInfo;
			}
		} else {
			// TODO month
			resultInfo.setData(
					statisticsService.findByUserIdAndCategoryAndMonth(userid, category, Integer.parseInt(month)));
			return resultInfo;
		}
	}

	/**
	 * 通过用户id以及类别，寻找指定类别的账单，可以指定月份
	 * 
	 * @param userid
	 * @param classify
	 * @return
	 */
	@GetMapping({ "/bill/classify" })
	public ResultInfo getBillFromUserIdAndClassify(@RequestParam("userid") Long userid,
			@RequestParam("classify") String classify, @RequestParam("month") String month,
			@RequestParam("category") int category) {
		ResultInfo resultInfo = ResultInfo.success();
		resultInfo.setData(
				statisticsService.findByUserIdAndClassify(userid, classify, Integer.parseInt(month), category));
		return resultInfo;
	}
	
	

}
