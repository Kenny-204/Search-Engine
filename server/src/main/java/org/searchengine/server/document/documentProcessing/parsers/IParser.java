package org.searchengine.server.document.documentProcessing.parsers;

import java.io.IOException;
import java.io.InputStream;

public interface IParser {
    public String readContent(InputStream fileStream) throws IOException;
}
