package dgsw.pioneers.checkIn.domain.question.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.aggregate.QuestionJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionJpaEntity, Long> {

    @EntityGraph(attributePaths = {"member"})
    List<QuestionJpaEntity> findAllByOrderByCreatedAtDesc();
}
