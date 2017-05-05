package com.gohouse.service.physicalview.mapping;

public class PhysicalViewMapping {
	
	private Long id;
	
	private String target_db;
	
	private String target_table;
	
	private String source_table;
	
	private String target_table_column;
	
	private String source_table_column;
	
	private String target_table_relation_column;
	
	private String source_table_relation_column;
	
	private Boolean is_subject;

	private Boolean is_new_view;
	
	private Long creation_date;
	
	private Long modification_date;
	
	private Boolean is_deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTarget_db() {
		return target_db;
	}

	public void setTarget_db(String target_db) {
		this.target_db = target_db;
	}

	public String getTarget_table() {
		return target_table;
	}

	public void setTarget_table(String target_table) {
		this.target_table = target_table;
	}

	public String getSource_table() {
		return source_table;
	}

	public void setSource_table(String source_table) {
		this.source_table = source_table;
	}

	public String getTarget_table_column() {
		return target_table_column;
	}

	public void setTarget_table_column(String target_table_column) {
		this.target_table_column = target_table_column;
	}

	public String getSource_table_column() {
		return source_table_column;
	}

	public void setSource_table_column(String source_table_column) {
		this.source_table_column = source_table_column;
	}

	public String getTarget_table_relation_column() {
		return target_table_relation_column;
	}

	public void setTarget_table_relation_column(String target_table_relation_column) {
		this.target_table_relation_column = target_table_relation_column;
	}

	public String getSource_table_relation_column() {
		return source_table_relation_column;
	}

	public void setSource_table_relation_column(String source_table_relation_column) {
		this.source_table_relation_column = source_table_relation_column;
	}

	public Long getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Long creation_date) {
		this.creation_date = creation_date;
	}

	public Long getModification_date() {
		return modification_date;
	}

	public void setModification_date(Long modification_date) {
		this.modification_date = modification_date;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Boolean getIs_subject() {
		return is_subject;
	}

	public void setIs_subject(Boolean is_subject) {
		this.is_subject = is_subject;
	}

	public Boolean getIs_new_view() {
		return is_new_view;
	}

	public void setIs_new_view(Boolean is_new_view) {
		this.is_new_view = is_new_view;
	}

}
