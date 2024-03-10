package lostItem.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "reportItem")
public class ReportItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportItem_id")
    private Long id; // id

    @Column(nullable = false, length = 255)
    private String title; // 제목

    @Column(nullable = false, length = 255)
    private String assignLocation; // 맡긴 위치

    @Column(nullable = false, length = 255)
    private String findDate; // date type 수정

    @Column(nullable = false, length = 255)
    private String latitude; // 위도

    @Column(nullable = false, length = 255)
    private String longitude; // 경도

    @Column(nullable = false, length = 255)
    private String itemCategory; // 아이템 카테고리

    public ReportItem(String title,String assignLocation, String findDate, String latitude, String longitude, String itemCategory) {
        this.id = null; //jpa가 알아서 관리해주기 때문에 null
        this.title = title;
        this.assignLocation = assignLocation;
        this.findDate = findDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.itemCategory = itemCategory;
    }
}
