package com.nucpoop.covserver.model.covdata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name = "response")
public class CovData {
    @XmlElement
    Header header;
    @XmlElement
    Body body;

}

@Getter
@XmlRootElement
class Header {
    @XmlElement
    int resultCode;
    @XmlElement
    String resultMsg;
}
