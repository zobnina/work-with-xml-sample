package com.example.xmlsdo;

import com.fasterxml.jackson.databind.ObjectMapper;
import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class XmlJsonProcessor implements Processor {
    private static final Logger LOGGER = LogManager.getLogger(XmlJsonProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        String xmlString = exchange.getIn().getBody(String.class);
        XMLDocument xmlProductDocument = XMLHelper.INSTANCE.load(xmlString);
        DataObject productDataObject = xmlProductDocument.getRootObject();
        Product product = createProduct(productDataObject);
        ObjectMapper productMapper = new ObjectMapper();
        String productJson = productMapper.writeValueAsString(product);
        exchange.getIn().setBody(productJson);
    }

    private Product createProduct(DataObject productDataObject) {
        Product product = new Product();
        product.setName(productDataObject.getString("name"));
        product.setPrice(productDataObject.getDouble("price"));
        product.setAvailability(productDataObject.getBoolean("availability"));
        DataObject reviewsDataObject = productDataObject.getDataObject("reviews");
        product.setReviews(createReviews(reviewsDataObject));
        String logMessage = String.format("Product: %s", product);
        LOGGER.debug(logMessage);
        return product;
    }

    private Map<String, String> createReviews(DataObject reviewsDataObject) {
        List<?> reviewsDataObjects = reviewsDataObject.getList("review");
        Map<String, String> reviews = new LinkedHashMap<>();
        for (Object reviewObject : reviewsDataObjects) {
            DataObject reviewDataObject = ((DataObject) reviewObject);
            String author = reviewDataObject.getString("author");
            String text = reviewDataObject.getString("text");
            reviews.put(author, text);
        }
        String logMessage = String.format("Reviews: %s", reviews);
        LOGGER.debug(logMessage);
        return reviews;
    }
}
