package lostItem.domain.repository;

import lostItem.domain.model.ReportItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportItemRepository extends JpaRepository<ReportItem, Long> {
    Optional<ReportItem> findByTitle(String title); // 타이틀 검색
    List<ReportItem> findTop10ByTitleContainingIgnoreCase(String title); // 검색어 포함된 타이틀 최대 10개
}
