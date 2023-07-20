package dgsw.pioneers.checkIn.member.adapter.out.persistence;

import dgsw.pioneers.checkIn.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberJpaEntity, String > {
}
