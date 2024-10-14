package main.service.impl;

import main.service.SearchAndReplace;

public class SearchAndReplaceFactory {
        public static SearchAndReplace getProcessor(String type) {
            if ("txt".equalsIgnoreCase(type)) {
                return new TextSearchAndReplace();
            } else if ("xml".equalsIgnoreCase(type)) {
                return new XmlSearchAndReplace();
            } else {
                throw new IllegalArgumentException("Unsupported type: " + type);
            }
        }
    }
