package com.example.xmlsdo;

import com.example.xmlsdo.model.Catalog;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonXmlProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonCatalog = exchange.getIn().getBody(String.class);
        ObjectMapper catalogMapper = new ObjectMapper();
        Catalog catalog = catalogMapper.readValue(jsonCatalog, Catalog.class);
        CatalogConverter catalogConverter = new CatalogConverter();
        String catalogString = catalogConverter.catalogToXml(catalog);
        log.debug(catalogString);
        exchange.getIn().setBody(catalogString);
    }
}
