package com.searchengine.documentprocessing.services.parsers;

import java.io.IOException;
import java.io.InputStream;

public interface IParser {
    public String readContent(InputStream fileStream) throws IOException;
}
