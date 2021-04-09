package com.ashsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="sale_order_tab")
public class SaleOrder {
	
	@Id
	@GeneratedValue(generator ="sale_order_gen" )
	@SequenceGenerator(name ="sale_order_gen",sequenceName = "sale_order_gen_seq" )
	@Column(name = "sorder_id_col")
	private Integer id;
	@Column(name = "order_code_col")
	private String orderCode;
	@Column(name = "sorder_ref_col")
	private String refNo;
	@Column(name = "sorder_stockMode_col")
	private String stockMode;
	@Column(name = "sorder_stockSrc_col")
	private String stockSrc;
	@Column(name = "sorder_defStatus_col")
	private String defStatus;
	@Column(name = "sorder_desc_col")
	private String desc;
	
	//Integrations
	@ManyToOne
	@JoinColumn(name="shipment_id_fk_col")
	private ShipmentType shipmentType;
	
	@ManyToOne
	@JoinColumn(name = "whuser_id_fk_col")
	private WhUserType customer;
}
