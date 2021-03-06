package com.example.xmlsdo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class XmlRouteBuilder extends RouteBuilder {

    @Autowired
    XmlSdoJsonProcessor xmlSdoJsonProcessor;

    @Autowired
    XmlJsonProcessor xmlJsonProcessor;

    @Autowired
    JsonXmlProcessor jsonXMLProcessor;

    @Override
    public void configure() {
        restConfiguration()
                .component("{{camel.rest.component}}")
                .host("{{camel.rest.host}}")
                .port("{{camel.rest.port}}");

        rest("{{route.xml-json-sdo}}")
                .post()
                .route()
                .process(xmlSdoJsonProcessor)
                .to("{{route.json-out-file}}");

        rest("{{route.xml-json}}")
                .post()
                .route()
                .process(xmlJsonProcessor)
                .to("{{route.json-out-file}}");


        rest("{{route.json-xml}}")
                .post()
                .route()
                .process(jsonXMLProcessor)
                .to("{{route.xml-out-file}}");

    }
}
