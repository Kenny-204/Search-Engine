package com.searchengine.documentprocessing.services;

import com.searchengine.documentprocessing.services.utils.StopWords;
import opennlp.tools.stemmer.PorterStemmer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Normalizer {


    PorterStemmer stemmer = new PorterStemmer();

    public List<Tokenizer.Token> Normalize(List<Tokenizer.Token> tokens) {
        return tokens.stream().filter(token -> !StopWords.stopWords.contains(token.token)).map(token -> new Tokenizer.Token(stemmer.stem(token.token), token.position)).toList();

    }
}
