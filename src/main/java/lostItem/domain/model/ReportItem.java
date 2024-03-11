package lostItem.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(nullable = false)
    private LocalDateTime findDate;

    @Column(nullable = false, length = 255)
    private String latitude; // 위도

    @Column(nullable = false, length = 255)
    private String longitude; // 경도

    @Column(nullable = false, length = 255)
    private String itemCategory; // 아이템 카테고리


}
