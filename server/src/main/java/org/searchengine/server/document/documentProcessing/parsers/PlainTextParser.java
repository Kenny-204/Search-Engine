package org.searchengine.server.document.documentProcessing.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PlainTextParser implements   IParser{
    public String readContent(InputStream fileStream) throws IOException {
        return new String(fileStream.readAllBytes(), StandardCharsets.UTF_8);
    };
}
