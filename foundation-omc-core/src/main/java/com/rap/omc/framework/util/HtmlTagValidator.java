package com.rap.omc.framework.util;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlTagValidator {
    private Set<String> invalidTags;
    private Map<String, Set<String>> attrMap = new HashMap<String, Set<String>>();

    public void setInvalidTags(Set<String> invalidTags){
        this.invalidTags = invalidTags;
    }
    public void setValidAttrMap(Map<String, String> validAttrMap) {
        for( Map.Entry<String, String> e : validAttrMap.entrySet() ) {
            Set<String> set = new HashSet<String>();
            for( String ele : e.getValue().split(",") ) {
                set.add( ele.trim() );
            }
            this.attrMap.put(e.getKey(), set);
        }
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlTagValidator.class);
    /**
     * 
     * @param html
     * @return
     */
    public boolean validate(String html){

        html =  StringEscapeUtils.unescapeHtml(html);

        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("*");

        Iterator<Element> iter = elements.iterator();

        while( iter.hasNext() ) {
            Element ele = iter.next();

            String tagName = ele.nodeName();

            if ( invalidTags.contains(tagName) ) {
                LOGGER.error("XSS : tags are not allowed, tag={}", tagName);
                return false;
            } else if (  attrMap.containsKey(tagName) && checkTag(tagName, ele) == false ) {
                return false;
            }
        }
        return true;
    }
    /**
     * 
     * @param tagName
     * @param ele
     * @return
     */
    private boolean checkTag(String tagName, Element ele){
       Set<String> attrSet = attrMap.get(tagName);
       Attributes attrs = ele.attributes();
       Iterator<Attribute> iter = attrs.iterator();
       while( iter.hasNext() ) {
           Attribute attr = iter.next();
           if ( ! attrSet.contains( attr.getKey()) ) {
               LOGGER.error("XSS : attr is not allowed for tag={}, attr={}", tagName, attr.getKey());
               return(false);
           }
       }
       return true;
    }

}
