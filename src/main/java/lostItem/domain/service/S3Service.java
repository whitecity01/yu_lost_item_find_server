package lostItem.domain.service;

import lostItem.dto.S3UrlDto;
import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    public void uploadImageToS3(MultipartFile image, String title);
    String getImageUrl(String fileName);
}
