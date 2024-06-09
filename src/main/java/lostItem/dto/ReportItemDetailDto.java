package lostItem.dto;

import lostItem.domain.model.ReportItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportItemDetailDto {
    private ReportItem reportItem;
    private String s3Url;
}