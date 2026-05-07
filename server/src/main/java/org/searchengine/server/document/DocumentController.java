package org.searchengine.server.document;


import org.searchengine.server.document.documentProcessing.DocumentProcessingService;
import org.searchengine.server.document.indexing.Indexer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentProcessingService documentProcessingService;

    public DocumentController(DocumentProcessingService documentProcessingService) {
        this.documentProcessingService = documentProcessingService;
    }

    public static class UploadResponse {
        public String status;
        public Map<String, Map<String, List<Integer>>> index;

        public UploadResponse(String status, Map<String, Map<String, List<Integer>>> index) {
            this.status = status;
            this.index = index;
        }
    }

    @PostMapping
    public ResponseEntity<UploadResponse> upload(@RequestPart("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                System.out.println("Hello");
            }
            var normalizedText = documentProcessingService.process(file.getInputStream(), file.getOriginalFilename());
            Map<String, Map<String, List<Integer>>> invertedIndex = Indexer.Index(normalizedText, file.getOriginalFilename());


            return ResponseEntity.ok(new UploadResponse("success", invertedIndex));
        } catch (IOException err) {
            throw new RuntimeException("There was an error uploading the file");

        }
    }
}
