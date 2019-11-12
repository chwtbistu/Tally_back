package com.bistu.tally.helper;

import java.util.ArrayList;

import lombok.Data;

@Data
public class YagolRequest_2 extends Request {
	private ArrayList<SeriesBean_2> series = new ArrayList<SeriesBean_2>();

	public void setCategoriesToMonth() {
		this.categories=new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			this.categories.add(i + "");
		}
	}

	public void setCategoriesToClassify() {
		this.categories=new ArrayList<String>();
		this.categories.add("学习");
		this.categories.add("运动");
		this.categories.add("交通");
		this.categories.add("衣服");
		this.categories.add("工具");
		this.categories.add("食物");
	}
}