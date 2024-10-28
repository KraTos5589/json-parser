package org.example;

import java.util.ArrayList;
import java.util.List;

public class JsonTokenizer {
    public List<Token> tokenize(String json) {
        final List<Token> tokens = new ArrayList<>();

        StringBuilder currentTextToken = new StringBuilder();
        boolean textTokenOngoing = false;

        for(int idx=0; idx<json.length(); idx++) {
            switch (json.charAt(idx)) {
                case '{' : if(textTokenOngoing) { tokens.add(new Token(currentTextToken.toString())); currentTextToken = new StringBuilder(); textTokenOngoing=false; }  tokens.add(Token.START); break;
                case '}' : if(textTokenOngoing) { tokens.add(new Token(currentTextToken.toString())); currentTextToken = new StringBuilder(); textTokenOngoing=false; } tokens.add(Token.END); break;
                case '"' : if(textTokenOngoing) { tokens.add(new Token(currentTextToken.toString())); currentTextToken = new StringBuilder(); textTokenOngoing=false; } tokens.add(Token.QUOTE); break;
                case ':' : if(textTokenOngoing) { tokens.add(new Token(currentTextToken.toString())); currentTextToken = new StringBuilder(); textTokenOngoing=false; }  tokens.add(Token.COLON); break;
                case ',' : if(textTokenOngoing) { tokens.add(new Token(currentTextToken.toString())); currentTextToken = new StringBuilder(); textTokenOngoing=false; }  tokens.add(Token.COMMA); break;
                case ' ' : if(textTokenOngoing) { tokens.add(new Token(currentTextToken.toString())); currentTextToken = new StringBuilder(); textTokenOngoing=false; } break;
                default : textTokenOngoing = true; currentTextToken.append(json.charAt(idx));
            }
        }

        return tokens;
    }
}

