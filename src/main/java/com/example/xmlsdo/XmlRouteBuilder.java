package com.example.xmlsdo;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class XmlRouteBuilder extends RouteBuilder {
    private static final Logger LOGGER = LogManager.getLogger(XmlRouteBuilder.class);

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("{{camel.rest.component}}")
                .host("{{camel.rest.host}}")
                .port("{{camel.rest.port}}");

        rest("{{route.add-product-sdo}}")
                .post()
                .route()
                .process(exchange -> {
                    String xmlString = exchange.getIn().getBody(String.class);
                    XMLDocument xmlProductDocument = XMLHelper.INSTANCE.load(xmlString);
                    DataObject productDataObject = xmlProductDocument.getRootObject();
                    DataObject reviewsFataObject = productDataObject.getDataObject("reviews");
                    List<DataObject> reviewsDataObjects = reviewsFataObject.getList("review");
                    Map<String, String> reviews = new LinkedHashMap<>();
                    for(DataObject reviewDataObject: reviewsDataObjects){
                        String author = reviewDataObject.getString("author");
                        String text = reviewDataObject.getString("text");
                        reviews.put(author,text);
                    }
                    Product product = new Product();
                    product.setName(productDataObject.getString("name"));
                    product.setPrice(productDataObject.getDouble("price"));
                    product.setAvailability(productDataObject.getBoolean("availability"));
                    product.setReviews(reviews);
                    LOGGER.info(product);
                });
    }
}
