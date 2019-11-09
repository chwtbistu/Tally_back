package com.bistu.tally.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bistu.tally.helper.BaseEntity;

import lombok.Data;

@Table(name = "t_Bill")
@Entity(name = "Bill")
@Data
public class Bill extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "bill_category", nullable = false)
	private int category;

	@Column(name = "bill_classify", nullable = false)
	private String classify;

	@Column(name = "bill_amount", nullable = false)
	private float amount;

	@Column(name = "bill_remarks", nullable = true)
	private String remarks;
}
