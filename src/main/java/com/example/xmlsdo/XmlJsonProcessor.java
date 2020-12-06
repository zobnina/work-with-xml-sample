package com.example.xmlsdo;

import com.example.xmlsdo.model.Catalog;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;

@Component
public class XmlJsonProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String xmlString = exchange.getIn().getBody(String.class);
        CatalogConverter catalogConverter = new CatalogConverter();
        Catalog catalog = catalogConverter.xmlToCatalog(xmlString);
        ObjectMapper catalogMapper = new ObjectMapper();
        String productJson = catalogMapper.writeValueAsString(catalog);
        exchange.getIn().setBody(productJson);
    }
}
