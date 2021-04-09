package com.ashsoft.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_tab")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_uid_col")
	private Integer uid;
	@Column(name = "user_uname_col")
	private String uname;
	@Column(name = "user_email_col")
	private String email;
	@Column(name = "user_pass_col")
	private String pass;

	@ElementCollection
	@CollectionTable(name = "user_roles_table", joinColumns = @JoinColumn(name = "user_uid_col"))
	@Column(name = "user_roles_col")
	private Set<String> roles;
}
