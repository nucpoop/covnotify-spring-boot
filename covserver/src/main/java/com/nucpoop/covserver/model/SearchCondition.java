package com.nucpoop.covserver.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchCondition {
	int pageNo;
	int numberOfRows;
	String startCreateDt;
	String endCreateDt;
}
