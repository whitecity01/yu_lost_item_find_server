package lostItem.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lostItem.Exception.collections.business.DuplicateUniqueKeyException;
import lostItem.domain.model.ReportItem;
import lostItem.domain.repository.ReportItemRepository;
import lostItem.dto.ReportItemDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportItemServiceImpl implements ReportItemService{
    private final ReportItemRepository reportItemRepository;

    @Override
    public void reportItem(ReportItemDto reportItem){
        ReportItem reportItemEntity = new ReportItem(reportItem.getTitle(),reportItem.getAssignLocation(),reportItem.getFindDate(),reportItem.getLatitude(),reportItem.getLongitude(),reportItem.getItemCategory() );

        try{
            reportItemRepository.save(reportItemEntity);
        }
        catch(DataIntegrityViolationException e){
            throw new DuplicateUniqueKeyException();
        }
    }

}
