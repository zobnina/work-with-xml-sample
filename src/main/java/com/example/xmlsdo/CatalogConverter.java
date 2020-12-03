package com.example.xmlsdo;

import com.example.xmlsdo.model.Catalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class CatalogConverter {
    public String catalogToXml(Catalog catalog) throws JAXBException {
        JAXBContext contextObj = JAXBContext.newInstance(Catalog.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        OutputStream outputStream = new ByteArrayOutputStream();
        marshallerObj.marshal(catalog, outputStream);
        return outputStream.toString();
    }

    public String xmlToCatalog(String xml){
        return null;
    }
}
