package com.bistu.tally.util;

import lombok.Data;

@Data
public class SeriesBean {
	private String name;
	private float[] data;

	public SeriesBean(String name, float[] data) {
		this.name = name;
		this.data = data;
	}

}
