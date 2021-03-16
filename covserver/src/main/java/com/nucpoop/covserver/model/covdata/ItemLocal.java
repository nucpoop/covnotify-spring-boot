package com.nucpoop.covserver.model.covdata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name = "item")
public class ItemLocal {
    @XmlElement
    String createDt;
    @XmlElement
    int deathCnt;
    @XmlElement
    int defCnt;
    @XmlElement
    String gubun;
    @XmlElement
    String gubunCn;
    @XmlElement
    String gubunEn;
    @XmlElement
    int incDec;
    @XmlElement
    int isolClearCnt;
    @XmlElement
    int isolIngCnt;
    @XmlElement
    int localOccCnt;
    @XmlElement
    int oveFlowCnt;
    @XmlElement
    double qurRate;
    @XmlElement
    int seq;
    @XmlElement
    String stdDay;
    @XmlElement
    String updateDt;    
}
