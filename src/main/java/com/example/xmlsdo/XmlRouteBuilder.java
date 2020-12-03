package com.example.xmlsdo;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class XmlRouteBuilder extends RouteBuilder {

    private static final Logger LOGGER = LogManager.getLogger(XmlRouteBuilder.class);

    @Autowired
    XmlSdoProcessor xmlSdoProcessor;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("{{camel.rest.component}}")
                .host("{{camel.rest.host}}")
                .port("{{camel.rest.port}}");

        rest("{{route.xml-json-sdo}}")
                .post()
                .route()
                .process(xmlSdoProcessor)
                .to("{{route.json-out-file}}")
        .log(LoggingLevel.DEBUG, "${body}");
    }
}
