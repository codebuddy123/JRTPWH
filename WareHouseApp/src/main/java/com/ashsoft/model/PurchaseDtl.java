package com.ashsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ashsoft.model.Part;
import com.ashsoft.model.PurchaseOrder;

import lombok.Data;

@Data
@Entity
@Table(name = "purchase_dtl_tab")
public class PurchaseDtl {
	
	@Id
	@GeneratedValue(generator = "purchase_dtl_gen")
	@SequenceGenerator(name = "purchase_dtl_gen", sequenceName = "purchase_dtl_gen_seq" )
	@Column(name = "purchase_dtl_id_col")
	private Integer id;
	
	@Column(name="purchase_dtl_qty_col")
	private Integer qty;
	
	@ManyToOne
	@JoinColumn(name = "part_id_fk_col")
	private Part part;
	
	@ManyToOne
	@JoinColumn(name = "po_order_id_fk_col")
	private PurchaseOrder po;
}
