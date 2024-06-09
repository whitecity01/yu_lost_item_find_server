package lostItem.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lostItem.Exception.collections.business.DuplicateUniqueKeyException;
import lostItem.domain.model.ReportItem;
import lostItem.domain.repository.ReportItemRepository;
import lostItem.dto.ReportItemDetailDto;
import lostItem.dto.ReportItemDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportItemServiceImpl implements ReportItemService{
    private final ReportItemRepository reportItemRepository;
    private final S3ServiceImpl s3Service;

    @Override
    public void reportItem(ReportItemDto reportItemDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(reportItemDto.getFindDate(), DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            try {
                dateTime = LocalDateTime.parse(reportItemDto.getFindDate(), formatter);
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Invalid date format: " + reportItemDto.getFindDate());
            }
        }

        ReportItem reportItem = ReportItem.builder()
                .title(reportItemDto.getTitle())
                .assignLocation(reportItemDto.getAssignLocation())
                .findDate(dateTime)
                .latitude(reportItemDto.getLatitude())
                .longitude(reportItemDto.getLongitude())
                .itemCategory(reportItemDto.getItemCategory())
                .build();
        try{
            reportItemRepository.save(reportItem);
        }
        catch(DataIntegrityViolationException e){
            throw new DuplicateUniqueKeyException();
        }
    }

    @Override
    public List<ReportItem> searchItems(String query) {
        return reportItemRepository.findTop10ByTitleContainingIgnoreCase(query);
    }

    @Override
    public ReportItem getReportItemById(Long id) {
        return reportItemRepository.findById(id).orElse(null);
    }

    @Override
    public ReportItemDetailDto getReportItemDetailWithS3Url(Long id) {
        ReportItem reportItem = reportItemRepository.findById(id).orElse(null);
        if (reportItem == null) {
            return null;
        }

        String s3Url = s3Service.getImageUrl(reportItem.getTitle() + ".jpg");
        return new ReportItemDetailDto(reportItem, s3Url);
    }
}
