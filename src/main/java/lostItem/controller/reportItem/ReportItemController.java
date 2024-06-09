package lostItem.controller.reportItem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lostItem.domain.model.ReportItem;
import lostItem.domain.service.ReportItemService;
import lostItem.domain.service.S3Service;
import lostItem.dto.ReportItemDetailDto;
import lostItem.dto.ReportItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        s3Service.uploadImageToS3(imageDto,reportItem.getTitle());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/searchItems")
    public ResponseEntity<List<ReportItem>> searchItems(@RequestParam String query) {
        List<ReportItem> items = reportItemservice.searchItems(query);
        System.out.println(items);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/reportItem/{id}")
    public ResponseEntity<?> getReportItemDetail(@PathVariable Long id) {
        ReportItemDetailDto reportItemDetail = reportItemservice.getReportItemDetailWithS3Url(id);
        if (reportItemDetail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reportItemDetail);
    }


}
