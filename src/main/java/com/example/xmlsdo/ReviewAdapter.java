package com.example.xmlsdo;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReviewAdapter extends XmlAdapter<ReviewAdapter.ReviewWrapper, Map<String, String>> {

    @Override
    public Map<String, String> unmarshal(ReviewWrapper wrapper) {
        Map<String, String> map = new LinkedHashMap<>();
        List<JAXBElement<Review>> reviewJAXBElements = wrapper.getReviews();
        for(int i = 0; i < reviewJAXBElements.size(); i++){
            Element review = (Element) reviewJAXBElements.get(i);
            Element author = (Element) review.getFirstChild();
            Element text = (Element) review.getChildNodes().item(1);
            map.put(author.getFirstChild().getNodeValue(), text.getFirstChild().getNodeValue());
        }
        return map;
    }

    @Override
    public ReviewWrapper marshal(Map<String, String> map) {
        ReviewWrapper wrapper = new ReviewWrapper();
        List<JAXBElement<Review>> reviews = new ArrayList<>();
        for (Map.Entry<String, String> record : map.entrySet()) {
            Review review = new Review();
            review.setAuthor(record.getKey());
            review.setText(record.getValue());
            JAXBElement<Review> reviewJAXBElement = new JAXBElement<>(new QName("review"), Review.class, review);
            reviews.add(reviewJAXBElement);
        }
        wrapper.setReviews(reviews);
        return wrapper;
    }

    @XmlSeeAlso(Review.class)
    static class ReviewWrapper{
        @Getter(onMethod_ = {@XmlAnyElement})
        @Setter
        private List<JAXBElement<Review>> reviews;
    }

    static class Review{
        @Getter(onMethod_ = {@XmlElement(name = "author")}) @Setter
        private String author;
        @Getter(onMethod_ = {@XmlElement(name = "text")}) @Setter
        private String text;
    }
}
