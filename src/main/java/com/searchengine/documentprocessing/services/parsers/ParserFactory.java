package com.searchengine.documentprocessing.services.parsers;

public class ParserFactory {
    public static IParser getParser(String fileExtension) throws Exception {
        switch (fileExtension) {
            case ".txt":
                return new PlainTextParser();
            case ".pdf":
                return new PDFParser();
            default:
                throw new Exception("File extension not supported");
        }
    }
}
