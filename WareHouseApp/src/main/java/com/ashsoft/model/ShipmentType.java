package com.ashsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "shipment_type_tab")
public class ShipmentType {
	
	@Id
	@GeneratedValue
	@Column(name="ship_id_col")
	private Integer id;
	
	@Column(name="ship_mode_col")
	private String shipmentMode;
	
	@Column(name="ship_enable_col")
	private String enableShipment;
	
	@Column(name="ship_code_col")
	private String shipmentCode;
	
	@Column(name="ship_grade_col")
	private String shipmentGrade;
	
	@Column(name="ship_desc_col")
	private String shipmentDescription;
}
