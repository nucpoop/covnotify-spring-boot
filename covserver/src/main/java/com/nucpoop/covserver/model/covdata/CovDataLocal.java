package com.nucpoop.covserver.model.covdata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name = "response")
public class CovDataLocal {
    @XmlElement
    Header header;
    @XmlElement
    BodyLocal body;

}

@Getter
@XmlRootElement
class Header {
    @XmlElement
    int resultCode;
    @XmlElement
    String resultMsg;
}
