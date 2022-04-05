package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Score")
public class Score {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name", length=50, nullable=false)
	@Size(min=2, max=50, message= "{score.name.size}")
	private String name;
	
	@Column
	@Range(min=0, max=100, message="{score.score.range}")
	private Integer chinese;
	
	@Column
	@Range(min=0, max=100, message="{score.score.range}")
	private Integer english;
	
	@Column
	@Range(min=0, max=100, message="{score.score.range}")
	private Integer math;
	
	@Column
	@Range(min=0, max=100, message="{score.score.range}")
	private Integer society;
	
	@Column
	@Range(min=0, max=100, message="{score.score.range}")
	private Integer science;
	
	@Column
	@Temporal(TemporalType.DATE)
	@NotNull(message="{score.date.null}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	@Column
	private Integer total;
	
	@Column
	private Double average;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getChinese() {
		return chinese;
	}

	public void setChinese(Integer chinese) {
		this.chinese = chinese;
	}

	public Integer getEnglish() {
		return english;
	}

	public void setEnglish(Integer english) {
		this.english = english;
	}

	public Integer getMath() {
		return math;
	}

	public void setMath(Integer math) {
		this.math = math;
	}

	public Integer getSociety() {
		return society;
	}

	public void setSociety(Integer society) {
		this.society = society;
	}

	public Integer getScience() {
		return science;
	}

	public void setScience(Integer science) {
		this.science = science;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}
	
	
	

}
