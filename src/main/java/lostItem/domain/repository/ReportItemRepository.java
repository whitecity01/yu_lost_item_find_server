package lostItem.domain.repository;

import lostItem.domain.model.ReportItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportItemRepository extends JpaRepository<ReportItem, Long> {
    Optional<ReportItem> findByTitle(String title); // 타이틀 검색
}
