package com.nucpoop.covserver.model.covdata;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement
public class Body {
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