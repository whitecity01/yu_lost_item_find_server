package lostItem.controller.reportItem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lostItem.domain.service.ReportItemService;
import lostItem.domain.service.S3Service;
import lostItem.dto.ReportItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class ReportItemController {
    private final S3Service s3Service;
    private final ReportItemService reportItemservice;
    @PostMapping("/reportItem")
    public ResponseEntity<?> uploadImage(@RequestPart("reportItemDto") @Valid ReportItemDto reportItem, @RequestPart("image") MultipartFile imageDto) {
        reportItemservice.reportItem(reportItem);
        s3Service.uploadImageToS3(imageDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
