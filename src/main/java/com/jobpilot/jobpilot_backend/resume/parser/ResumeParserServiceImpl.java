package com.jobpilot.jobpilot_backend.resume.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Service
@Slf4j
public class ResumeParserServiceImpl implements ResumeParserService {

    @Override
    public String extractText(String filePath) {

        try {

            if (filePath.endsWith(".pdf")) {
                return extractPdfText(filePath);
            }

            if (filePath.endsWith(".docx")) {
                return extractDocxText(filePath);
            }

            throw new RuntimeException("Unsupported file type");

        } catch (Exception ex) {

            log.error("Resume parsing failed", ex);

            throw new RuntimeException(
                    "Failed to parse resume"
            );
        }
    }

    private String extractPdfText(String filePath)
            throws Exception {

        File file = new File(filePath);

        try (PDDocument document =
                     Loader.loadPDF(file)) {

            PDFTextStripper stripper =
                    new PDFTextStripper();

            return stripper.getText(document);
        }
    }

    private String extractDocxText(String filePath)
            throws Exception {

        try (
                FileInputStream fis =
                        new FileInputStream(filePath);

                XWPFDocument document =
                        new XWPFDocument(fis)
        ) {

            StringBuilder text = new StringBuilder();

            document.getParagraphs()
                    .forEach(paragraph ->
                            text.append(paragraph.getText())
                                    .append("\n"));

            return text.toString();
        }
    }
}