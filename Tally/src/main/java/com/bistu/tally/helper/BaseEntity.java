package com.bistu.tally.helper;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;			//编号作为主键，自增

	@Column(name = "user_id")
	private Long userId;		//用户编号
	
	@JsonFormat(timezone = "GMT+8")
	@Column(name = "time")
	private Date time;			//新增时间


}

