package com.bistu.tally.util;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;

import lombok.Data;

@Data
public class YagolRequest {

	private ArrayList<String> categories = new ArrayList<String>();
	private ArrayList<SeriesBean> series = new ArrayList<SeriesBean>();

	public YagolRequest(){
		categories.add(0, "1");
		categories.add(1, "2");
		categories.add(2, "3");
		categories.add(3, "4");
		float[] temp= {1,2,3};
		series.add(0,new SeriesBean("支出", temp));
		series.add(1,new SeriesBean("支出", temp));
		series.add(2,new SeriesBean("支出", temp));
		series.add(3,new SeriesBean("支出", temp));
	}

}
