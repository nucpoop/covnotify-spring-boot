package com.nucpoop.covserver.model.covdata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name = "response")
public class CovDataLocal {
    @XmlElement
    HeaderLocal header;
    @XmlElement
    BodyLocal body;

}

@Getter
@XmlRootElement(name = "header")
class HeaderLocal {
    @XmlElement
    int resultCode;
    @XmlElement
    String resultMsg;
}
