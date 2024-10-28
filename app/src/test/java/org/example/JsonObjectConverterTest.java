package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonObjectConverterTest {
    private JsonObjectConverter jsonObjectConverter;

    @BeforeEach
    public void setUp() {
        jsonObjectConverter = new JsonObjectConverter();
    }

    @Test
    public void emptyJson() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(Token.START);
        tokens.add(Token.END);
        Assertions.assertEquals(new HashMap<>(), jsonObjectConverter.parse(tokens));
    }

    @Test
    public void singleStringValue() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(Token.START);
        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "name"));
        tokens.add(Token.QUOTE);
        tokens.add(Token.COLON);
        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "lokesh"));
        tokens.add(Token.QUOTE);
        tokens.add(Token.END);

        Map<String, Object> expected = new HashMap<>();
        expected.put("name", "lokesh");
        Assertions.assertEquals(expected, jsonObjectConverter.parse(tokens));
    }

    @Test
    public void multipleStringValues() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(Token.START);
        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "firstName"));
        tokens.add(Token.QUOTE);
        tokens.add(Token.COLON);
        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "lokesh"));
        tokens.add(Token.QUOTE);
        tokens.add(Token.COMMA);
        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "lastName"));
        tokens.add(Token.QUOTE);
        tokens.add(Token.COLON);
        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "pant"));
        tokens.add(Token.QUOTE);
        tokens.add(Token.COMMA);
        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "address"));
        tokens.add(Token.QUOTE);
        tokens.add(Token.COLON);
        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "205, Avenue"));
        tokens.add(Token.QUOTE);
        tokens.add(Token.END);

        Map<String, Object> expected = new HashMap<>();
        expected.put("firstName", "lokesh");
        expected.put("lastName", "pant");
        expected.put("address", "205, Avenue");
        Assertions.assertEquals(expected, jsonObjectConverter.parse(tokens));
    }

    @Test
    public void nestedExample() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(Token.START);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "name"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COLON);

        tokens.add(Token.START);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "first"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COLON);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "lokesh"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COMMA);


        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "last"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COLON);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "pant"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.END);


        tokens.add(Token.COMMA);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "address"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COLON);


        tokens.add(Token.START);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "house number"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COLON);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "205"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COMMA);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "street"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COLON);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "5th Avenue"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COMMA);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "city"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COLON);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "Wonderland"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COMMA);


        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "pin"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.COLON);

        tokens.add(Token.QUOTE);
        tokens.add(new Token(TokenType.TEXT, "12345"));
        tokens.add(Token.QUOTE);

        tokens.add(Token.END);


        tokens.add(Token.END);

        final Map<String, Object> expectedName = new HashMap<>();
        expectedName.put("first", "lokesh");
        expectedName.put("last", "pant");

        Map<String, Object> expectedAddress = new HashMap<>();

        expectedAddress.put("street", "5th Avenue");
        expectedAddress.put("house number", "205");
        expectedAddress.put("pin", "12345");
        expectedAddress.put("city", "Wonderland");


        Map<String, Object> expected = new HashMap<>();
        expected.put("name", expectedName);
        expected.put("address", expectedAddress);
        Assertions.assertEquals(expected, jsonObjectConverter.parse(tokens));
    }
}
