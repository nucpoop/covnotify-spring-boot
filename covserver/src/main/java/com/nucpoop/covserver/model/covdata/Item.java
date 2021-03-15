package com.nucpoop.covserver.model.covdata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement
public class Item {
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
