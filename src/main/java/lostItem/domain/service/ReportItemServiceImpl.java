package lostItem.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lostItem.Exception.collections.business.DuplicateUniqueKeyException;
import lostItem.domain.model.ReportItem;
import lostItem.domain.repository.ReportItemRepository;
import lostItem.dto.ReportItemDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportItemServiceImpl implements ReportItemService{
    private final ReportItemRepository reportItemRepository;

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
}
