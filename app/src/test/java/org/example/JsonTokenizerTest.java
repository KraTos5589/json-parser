package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonTokenizerTest {
    private JsonTokenizer jsonTokenizer;

    @BeforeEach
    public void setUp() {
        jsonTokenizer = new JsonTokenizer();
    }

    @Test
    public void emptyJson() {
        List<Token> expectedTokens = new ArrayList<>();
        expectedTokens.add(Token.START);
        expectedTokens.add(Token.END);
        Assertions.assertEquals(expectedTokens, jsonTokenizer.tokenize("{}"));
    }

    @Test
    public void emptyJsonWithSpaces() {
        List<Token> expectedTokens = new ArrayList<>();
        expectedTokens.add(Token.START);
        expectedTokens.add(Token.END);
        Assertions.assertEquals(expectedTokens, jsonTokenizer.tokenize(" {   }    "));
    }

    @Test
    public void textEndingWithSpace() {
        List<Token> expectedTokens = new ArrayList<>();
        expectedTokens.add(new Token(TokenType.TEXT, "xyz"));
        Assertions.assertEquals(expectedTokens, jsonTokenizer.tokenize(" xyz    "));
    }

    @Test
    public void textEndingWithQuote() {
        List<Token> expectedTokens = new ArrayList<>();
        expectedTokens.add(Token.QUOTE);
        expectedTokens.add(new Token(TokenType.TEXT, "xyz"));
        expectedTokens.add(Token.QUOTE);
        Assertions.assertEquals(expectedTokens, jsonTokenizer.tokenize(" \"xyz\"    "));
    }

    @Test
    public void textEndingWithOpeningBracket() {
        List<Token> expectedTokens = new ArrayList<>();
        expectedTokens.add(Token.END);
        expectedTokens.add(new Token(TokenType.TEXT, "xyz"));
        expectedTokens.add(Token.START);
        Assertions.assertEquals(expectedTokens, jsonTokenizer.tokenize(" }xyz{    "));
    }

    @Test
    public void textEndingWithClosingBracket() {
        List<Token> expectedTokens = new ArrayList<>();
        expectedTokens.add(Token.START);
        expectedTokens.add(new Token(TokenType.TEXT, "xyz"));
        expectedTokens.add(Token.END);
        Assertions.assertEquals(expectedTokens, jsonTokenizer.tokenize(" {xyz}    "));
    }

    @Test
    public void textEndingWithColon() {
        List<Token> expectedTokens = new ArrayList<>();
        expectedTokens.add(Token.COLON);
        expectedTokens.add(new Token(TokenType.TEXT, "xyz"));
        expectedTokens.add(Token.COLON);
        Assertions.assertEquals(expectedTokens, jsonTokenizer.tokenize(" :xyz:    "));
    }


    @Test
    public void simpleText() {
        List<Token> expectedTokens = new ArrayList<>();
        expectedTokens.add(Token.START);
        expectedTokens.add(Token.QUOTE);
        expectedTokens.add(Token.COLON);
        expectedTokens.add(Token.QUOTE);
        expectedTokens.add(Token.END);
        Assertions.assertEquals(expectedTokens, jsonTokenizer.tokenize("{\":\"}"));
    }

    @Test
    public void complicatedText() {
        List<Token> expectedTokens = new ArrayList<>();
        expectedTokens.add(Token.START);
        expectedTokens.add(Token.QUOTE);
        expectedTokens.add(Token.END);
        expectedTokens.add(Token.COLON);
        expectedTokens.add(Token.COLON);

        expectedTokens.add(new Token(TokenType.TEXT, "xyz"));

        expectedTokens.add(Token.QUOTE);
        expectedTokens.add(Token.QUOTE);
        expectedTokens.add(Token.COLON);
        expectedTokens.add(Token.QUOTE);
        expectedTokens.add(Token.END);

        expectedTokens.add(Token.START);
        expectedTokens.add(Token.END);

        expectedTokens.add(Token.START);
        expectedTokens.add(Token.END);

        expectedTokens.add(new Token(TokenType.TEXT, "abc"));

        expectedTokens.add(Token.START);
        expectedTokens.add(Token.COLON);
        expectedTokens.add(Token.COLON);
        expectedTokens.add(Token.QUOTE);

        expectedTokens.add(new Token(TokenType.TEXT, "pqr"));

        expectedTokens.add(Token.END);
        expectedTokens.add(Token.END);
        Assertions.assertEquals(expectedTokens, jsonTokenizer.tokenize("{\"}:: xyz  \"\":\"} {} {} abc  {::\" pqr  }}"));
    }
}
