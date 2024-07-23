package org.example.pastebin.repository;

import org.example.pastebin.entity.TextBlock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface TextBlockRepository extends JpaRepository<TextBlock, Long> {

    Optional<TextBlock> findByUniqueKey(String uniqueKey);

    Page<TextBlock> findByExpirationTimeBefore(OffsetDateTime time, Pageable pageable);

}
