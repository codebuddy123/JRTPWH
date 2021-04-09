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
@Table(name = "part_tab")
public class Part {
	
	@Id
	@GeneratedValue(generator = "part_gen")
	@SequenceGenerator(name = "part_gen",sequenceName = "part_gen_seq")
	@Column(name = "part_id_col")
	private Integer id;
	@Column(name = "part_code_col")
	private String partCode;
	@Column(name = "part_wid_col")
	private String partWidth;
	@Column(name = "part_len_col")
	private String partlen;
	@Column(name = "part_height_col")
	private String partHeight;
	@Column(name = "part_basecost_col")
	private String baseCost;
	@Column(name = "part_basecurrency_col")
	private String baseCurrency;
	@Column(name = "part_desc_col")
	private String desc;
	
	//----Integrations---------
	@ManyToOne
	@JoinColumn(name="uom_id_fk_col")
	private Uom uom;  //HAS-A variable
	
	@ManyToOne
	@JoinColumn(name="om_sale_fk_col")
	private OrderMethod omSale;    //HAS-A variable
	
	@ManyToOne
	@JoinColumn(name = "om_purchase_fk_col")
	private OrderMethod omPurchase;      //HAS-A variable
}
