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
@Table(name = "purchase_order_tab")
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(generator = "purchase_order_gen")
	@SequenceGenerator(name = "purchase_order_gen",sequenceName = "purchase_order_gen_seq")
	@Column(name = "porder_id_col")
	private Integer id;
	@Column(name="order_code_col")
	private String orderCode;
	@Column(name = "porder_ref_col")
	private String refNo;
	@Column(name = "porder_qcheck_col")
	private String qCheck;
	@Column(name = "porder_defStatus_col")
	private String defStatus;
	@Column(name = "porder_desc_col")
	private String desc;
	
	@ManyToOne
	@JoinColumn(name = "st_id_fk_col")
	private ShipmentType shipmentType;
	
	@ManyToOne
	@JoinColumn(name = "whuser_id_fk_col")
	private WhUserType vendor;
	
}
