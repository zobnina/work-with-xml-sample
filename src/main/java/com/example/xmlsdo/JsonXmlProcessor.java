package com.example.xmlsdo;

import com.example.xmlsdo.model.Catalog;
import com.example.xmlsdo.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JsonXmlProcessor implements Processor {
    private static final Logger LOGGER = LogManager.getLogger(JsonXmlProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonCatalog = exchange.getIn().getBody(String.class);
        ObjectMapper catalogMapper = new ObjectMapper();
        Catalog catalog = catalogMapper.readValue(jsonCatalog, Catalog.class);
        CatalogConverter catalogConverter = new CatalogConverter();
        String catalogString = catalogConverter.catalogToXml(catalog);
        LOGGER.debug(catalogString);
        exchange.getIn().setBody(catalogString);
    }
}
