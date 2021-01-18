package com.nucpoop.covserver.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class CovResponse {
    @XmlElement
    Header header;
    @XmlElement
    Body body;

}

@XmlRootElement
class Header {
    @XmlElement
    int resultCode;
    @XmlElement
    String resultMsg;
}

@XmlRootElement
class Body {
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    List<Item> items;
    @XmlElement
    int numOfRows;
    @XmlElement
    int pageNo;
    @XmlElement
    int totalCount;
}

@XmlRootElement
class Item {
    @XmlElement
    double accDefRate;
    @XmlElement
    int accExamCnt;
    @XmlElement
    int accExamCompCnt;
    @XmlElement
    int careCnt;
    @XmlElement
    int clearCnt;
    @XmlElement
    String createDt;
    @XmlElement
    int deathCnt;
    @XmlElement
    int decideCnt;
    @XmlElement
    int examCnt;
    @XmlElement
    int resultNegCnt;
    @XmlElement
    int seq;
    @XmlElement
    String stateDt;
    @XmlElement
    String stateTime;
    @XmlElement
    String updateDt;
}
