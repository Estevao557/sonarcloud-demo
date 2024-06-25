package org.cornercoding.sonarclouddemo.rest;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

@RestController
public class FileController {

    @PostMapping("/open-file")
    public ResponseEntity<?> openFile(@RequestBody FileRequest fileRequest) {
        String filePath = fileRequest.getFilePath();

        try {
            Path path = Paths.get(filePath);
            String content = new String(Files.readAllBytes(path));
            return ResponseEntity.ok().body(new FileResponse(content));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        }
    }

    static class FileRequest {
        private String filePath;

        // Getters and Setters
        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }

    static class FileResponse {
        private String content;

        public FileResponse(String content) {
            this.content = content;
        }

        // Getter
        public String getContent() {
            return content;
        }
    }

    static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        // Getter
        public String getError() {
            return error;
        }
    }
}
