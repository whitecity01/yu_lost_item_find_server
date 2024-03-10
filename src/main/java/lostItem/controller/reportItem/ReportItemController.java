package lostItem.controller.reportItem;

import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
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

import java.util.Date;

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
