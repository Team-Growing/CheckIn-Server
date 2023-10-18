package dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.aggregate.SuggestionJpaEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionRepository extends JpaRepository<SuggestionJpaEntity, Long> {

    Long countBy();

    @EntityGraph(attributePaths = {"member"})
    List<SuggestionJpaEntity> findAllByOrderByCreatedAtDesc(PageRequest pageRequest);
}
