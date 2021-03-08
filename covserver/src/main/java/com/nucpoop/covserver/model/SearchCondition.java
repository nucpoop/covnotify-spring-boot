package com.nucpoop.covserver.model;

import lombok.Builder;

//import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SearchCondition {
	
	int pageNo;
	int numberOfRows;
	String startCreateDt;
	String endCreateDt;

	public SearchCondition(int pageNo, int numberOfRows, String startCreateDt, String endCreateDt) {
		this.pageNo = pageNo;
		this.numberOfRows = numberOfRows;
		this.startCreateDt = startCreateDt;
		this.endCreateDt = endCreateDt;
	}
}
