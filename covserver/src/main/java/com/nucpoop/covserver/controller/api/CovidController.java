package com.nucpoop.covserver.controller.api;

import java.io.IOException;

import com.nucpoop.covserver.model.SearchCondition;
import com.nucpoop.covserver.util.Utils;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cov")
public class CovidController {

	private Logger logger = LoggerFactory.getLogger(CovidController.class);
	
    @RequestMapping("/")
	public ResponseEntity<String> cov(@RequestParam(value = "date") String date) throws IOException {
		SearchCondition searchCondition = SearchCondition.builder().pageNo(1).numberOfRows(1).startCreateDt(date).endCreateDt(date).build();
		JSONObject jsonObject = XML.toJSONObject(Utils.getCovData(searchCondition).toString());
		return ResponseEntity.ok(jsonObject.toString());
	}

	@RequestMapping("/local")
	public ResponseEntity<String> covLocal(@RequestParam(value = "date") String date) throws IOException{
		SearchCondition condition = SearchCondition.builder().pageNo(1).numberOfRows(1).startCreateDt(date).endCreateDt(date).build();
		JSONObject jsonObject = XML.toJSONObject(Utils.getCovDataLocal(condition).toString());
		return ResponseEntity.ok(jsonObject.toString());
	}
}
