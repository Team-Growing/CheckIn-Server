package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberJpaEntity, String > {

    List<MemberJpaEntity> findAllByMemberRoleOrderByName(MemberRole memberRole);
}
