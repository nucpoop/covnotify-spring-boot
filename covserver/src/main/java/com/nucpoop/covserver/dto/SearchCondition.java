package com.nucpoop.covserver.dto;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class SearchCondition {
	int pageNo;
	int numberOfRows;
	int startCreateDt;
	int endCreateDt;
}
