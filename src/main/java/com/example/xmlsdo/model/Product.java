package com.example.xmlsdo.model;

import com.example.xmlsdo.ReviewAdapter;
import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

@XmlType
@NoArgsConstructor
@ToString
public class Product {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double price;
    @Getter(onMethod_ = {@XmlAttribute})
    @Setter
    private boolean availability;
    @Getter
    @Setter
    private Size size;
    @Getter(onMethod_ = {@XmlElement, @XmlJavaTypeAdapter(ReviewAdapter.class)})
    @Setter
    private Map<String, String> reviews;
}
