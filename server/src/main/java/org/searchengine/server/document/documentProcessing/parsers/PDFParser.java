package org.searchengine.server.document.documentProcessing.parsers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;

public class PDFParser implements IParser {

    public String readContent(InputStream fileStream) throws IOException {
        try{

        PDDocument document = PDDocument.load(fileStream);
        PDFTextStripper textStripper = new PDFTextStripper();
        return textStripper.getText(document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
