#XML Parser

This is an application for converting XML to JSON and JSON to XML.

REST controller has following requests:
* POST localhost:8081/xml/json/sdo - to convert XML to JSON using SDO
* POST localhost:8081/xml/json - to convert XML to JSON using JAXB
* POST localhost:8081/json/xml - to convert JSON to XMl using JAXB

A valid xml-body for request is placed in /resources/from