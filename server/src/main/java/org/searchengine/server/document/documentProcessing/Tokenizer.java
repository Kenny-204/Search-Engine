package org.searchengine.server.document.documentProcessing;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Tokenizer {

    public static class Token {
        public String token;
        public int position;

        public Token(String token, int position) {
            this.token = token;
            this.position = position;
        }
    }

    public  List<Token> Tokenize(String text) {
        List<Token> tokens = new ArrayList<>();
        String[] splitText = text.split("[\\s\\p{P}]+");

        for (int i = 0; i < splitText.length - 1; i++) {
            tokens.add(new Token(splitText[i].toLowerCase(), i));
        }
        return tokens;


    }
}
