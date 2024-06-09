package lostItem.domain.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lostItem.Exception.collections.s3.ImageUploadException;
import lostItem.dto.S3UrlDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.UUID;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3ServiceImpl implements S3Service{
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName; //버킷 이름

    @Override
    public void uploadImageToS3(MultipartFile image, String title) {
        String originName = image.getOriginalFilename(); // 원본 이미지 이름
        String ext = originName.substring(originName.lastIndexOf(".")); // 확장자
        String fileName = title + ext; // 제목을 파일 이름으로 사용
        ObjectMetadata metadata = new ObjectMetadata(); // 메타데이터 설정
        metadata.setContentType("image/" + ext);

        try {
            amazonS3.putObject(new PutObjectRequest(
                    bucketName, fileName, image.getInputStream(), metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead));
            log.info("Image uploaded to S3 with file name: {}", fileName);
        } catch (IOException e) {
            throw new ImageUploadException("Failed to upload image to S3", e);
        }
    }

    @Override
    public String getImageUrl(String fileName) { // 새로운 메서드 구현
        return amazonS3.getUrl(bucketName, fileName).toString();
    }
}