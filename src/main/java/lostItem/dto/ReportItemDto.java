package lostItem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportItemDto {

    @NotBlank(message = "제목은 필수값입니다.")
    private String title;

    @NotBlank(message = "맡긴 장소는 필수값입니다.")
    private String assignLocation;

    @NotBlank(message = "찾은 날짜는 필수값입니다.")
    private String findDate;

    @NotBlank(message = "위도는 필수 값입니다.")
    private String latitude;

    @NotBlank(message = "경도는 필수 값입니다.")
    private String longitude;

    @NotBlank(message = "아이템 카테고리는 필수 값입니다.")
    private String itemCategory;

}
