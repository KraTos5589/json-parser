package org.example;

import java.util.Objects;

public class Token {
    public static final Token START = new Token(TokenType.START, "{");
    public static final Token END = new Token(TokenType.END, "}");
    public static final Token COLON = new Token(TokenType.CONNECTOR, ":");
    public static final Token QUOTE = new Token(TokenType.QUOTE, "\"");
    public static final Token COMMA = new Token(TokenType.COMMA, ",");
    private TokenType tokenType;
    private String text;

    public Token(TokenType tokenType, String text) {
        this.tokenType = tokenType;
        this.text = text;
    }
    public Token(String text) {
        this.tokenType = TokenType.TEXT;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenType=" + tokenType +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return tokenType == token.tokenType && Objects.equals(text, token.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenType, text);
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getText() {
        return text;
    }
}
