package lostItem.domain.service;

import lostItem.domain.model.ReportItem;
import lostItem.dto.ReportItemDetailDto;
import lostItem.dto.ReportItemDto;

import java.util.List;

public interface ReportItemService {
    public void reportItem(ReportItemDto reportItem);
    List<ReportItem> searchItems(String query);
    ReportItem getReportItemById(Long id);
    ReportItemDetailDto getReportItemDetailWithS3Url(Long id);
}
