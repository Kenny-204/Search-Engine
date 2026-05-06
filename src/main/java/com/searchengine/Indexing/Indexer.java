package com.searchengine.Indexing;

import com.searchengine.documentprocessing.services.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indexer {

    private final Map<String, Map<String, List<Integer>>> _index = new HashMap<>();

    public Map<String, Map<String, List<Integer>>> Index(List<Tokenizer.Token> tokens, String fileName) {

        tokens.forEach(token -> {
            if (_index.containsKey(token.token)) {
                if (_index.get(token.token).containsKey(fileName)) {
                    _index.get(token.token).get((fileName)).add(token.position);
                } else {
                    _index.get(token.token).put(fileName, new ArrayList<>(token.position));
                }
            } else {
                Map<String, List<Integer>> fileMap = new HashMap<>();
                List<Integer> positions = new ArrayList();
                positions.add(token.position);
                fileMap.put(fileName, positions);
                _index.put(token.token, fileMap);
            }
        });

        return _index;
    }
}
