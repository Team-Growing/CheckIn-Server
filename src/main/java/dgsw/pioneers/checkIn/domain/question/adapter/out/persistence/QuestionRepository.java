package dgsw.pioneers.checkIn.domain.question.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.aggregate.QuestionJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionJpaEntity, Long> {

    @EntityGraph(attributePaths = {"member"})
    Optional<QuestionJpaEntity> findById(Long questionId);

    @EntityGraph(attributePaths = {"member"})
    List<QuestionJpaEntity> findAllByOrderByCreatedAtDesc();
}
