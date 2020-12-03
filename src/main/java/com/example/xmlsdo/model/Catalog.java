package com.example.xmlsdo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "productCatalog")
@NoArgsConstructor
public class Catalog {
    @Getter(onMethod_ = {@XmlElementWrapper(name = "productCards"), @XmlElement(name = "product")})
    @Setter
    List<Product> products;
}
