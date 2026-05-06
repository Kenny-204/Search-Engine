package com.searchengine.documentprocessing;

import com.searchengine.documentprocessing.services.Normalizer;
import com.searchengine.documentprocessing.services.Tokenizer;
import com.searchengine.documentprocessing.services.parsers.IParser;
import com.searchengine.documentprocessing.services.parsers.ParserFactory;

import java.io.InputStream;
import java.util.List;

public class DocumentProcessor {
    private final Normalizer _normalizer;
    private final Tokenizer _tokenizer;

    public DocumentProcessor(Normalizer normalizer, Tokenizer tokenizer) {
        this._normalizer = normalizer;
        this._tokenizer = tokenizer;
    }

    public List<Tokenizer.Token> process(InputStream fileStream, String fileName) {
        try {
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            IParser parser = ParserFactory.getParser(fileExtension);

            String text = parser.readContent(fileStream);
            List<Tokenizer.Token> tokenizedText = _tokenizer.Tokenize(text);
            return _normalizer.Normalize(tokenizedText);


        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }
}
