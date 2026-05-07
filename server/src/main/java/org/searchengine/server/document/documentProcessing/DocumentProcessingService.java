package org.searchengine.server.document.documentProcessing;


import org.searchengine.server.document.documentProcessing.parsers.IParser;
import org.searchengine.server.document.documentProcessing.parsers.ParserFactory;
import org.springframework.stereotype.Service;


import java.io.InputStream;
import java.util.List;
@Service
public class DocumentProcessingService {
    private final Normalizer _normalizer;
    private final Tokenizer _tokenizer;

    public DocumentProcessingService(Normalizer normalizer, Tokenizer tokenizer) {
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
