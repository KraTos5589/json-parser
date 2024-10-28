package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonObjectConverter {

    public Map<String, Object> parse(List<Token> tokens) {
        final Map<String, Object> map = new HashMap<>();

        boolean parsingStarted = false;
        boolean keyValue = true;
        String key = null;

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (TokenType.START.equals(token.getTokenType())) {
                if (parsingStarted) {
                    final List<Token> innerObject = new ArrayList<>();
                    innerObject.add(token);
                    while (token.getTokenType() != TokenType.END) {
                        i++;
                        token = tokens.get(i);
                        innerObject.add(token);
                    }
                    map.put(key, parse(innerObject));
                    keyValue = true;
                }
                parsingStarted = true;
            }

            if (TokenType.TEXT.equals(token.getTokenType())) {
                if (keyValue) {
                    keyValue = false;
                    key = token.getText();
                } else {
                    keyValue = true;
                    map.put(key, token.getText());
                }
            }
        }

        return map;
    }
}
