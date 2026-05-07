package org.searchengine.server.document.documentProcessing;

import opennlp.tools.stemmer.PorterStemmer;
import org.searchengine.server.document.documentProcessing.utils.StopWords;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Normalizer {


    PorterStemmer stemmer = new PorterStemmer();

    public List<Tokenizer.Token> Normalize(List<Tokenizer.Token> tokens) {
        return tokens.stream().filter(token -> !StopWords.stopWords.contains(token.token)).map(token -> new Tokenizer.Token(stemmer.stem(token.token), token.position)).toList();

    }
}
