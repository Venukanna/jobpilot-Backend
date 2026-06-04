package com.jobpilot.jobpilot_backend.resume.service.impl;

import com.jobpilot.jobpilot_backend.common.exception.BadRequestException;
import com.jobpilot.jobpilot_backend.resume.dto.ResumeResponse;
import com.jobpilot.jobpilot_backend.resume.entity.Resume;
import com.jobpilot.jobpilot_backend.resume.repository.ResumeRepository;
import com.jobpilot.jobpilot_backend.resume.service.ResumeService;
import com.jobpilot.jobpilot_backend.user.entity.User;
import com.jobpilot.jobpilot_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public ResumeResponse uploadResume(MultipartFile file) {

        if (file.isEmpty()) {
            throw new BadRequestException("File is required");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null ||
                !(fileName.toLowerCase().endsWith(".pdf")
                        || fileName.toLowerCase().endsWith(".docx"))) {

            throw new BadRequestException(
                    "Only PDF and DOCX files are allowed");
        }

        try {

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            String email = authentication.getName();

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new BadRequestException("User not found"));

            Path uploadPath = Paths.get(uploadDir)
                    .toAbsolutePath()
                    .normalize();

            Files.createDirectories(uploadPath);

            String cleanedFileName = fileName.trim();

            String storedFileName =
                    user.getId() + "_" + cleanedFileName;

            Path filePath =
                    uploadPath.resolve(storedFileName);

            file.transferTo(filePath.toFile());

            Resume resume = Resume.builder()
                    .user(user)
                    .fileName(cleanedFileName)
                    .filePath(filePath.toString())
                    .build();

            Resume savedResume = resumeRepository.save(resume);

            return ResumeResponse.builder()
                    .id(savedResume.getId())
                    .fileName(savedResume.getFileName())
                    .filePath(savedResume.getFilePath())
                    .build();

        } catch (Exception ex) {

            throw new BadRequestException(
                    "Failed to upload resume"
            );
        }
    }
}