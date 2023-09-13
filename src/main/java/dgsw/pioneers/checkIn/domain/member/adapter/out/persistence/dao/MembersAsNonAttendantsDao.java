package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.QLectureToMemberEntity.lectureToMemberEntity;
import static dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.QMemberJpaEntity.memberJpaEntity;

@Repository
@RequiredArgsConstructor
public class MembersAsNonAttendantsDao {

    private final JPAQueryFactory queryFactory;

    public List<MemberJpaEntity> findAllMembersWithAttendants(Long lectureId, List<String> memberIds) {

        return queryFactory
                .selectFrom(memberJpaEntity)
                .join(lectureToMemberEntity).on(memberJpaEntity.id.eq(lectureToMemberEntity.memberJpaEntity.id))
                .where(
                        lectureToMemberEntity.lectureJpaEntity.id.eq(lectureId),
                        lectureToMemberEntity.memberJpaEntity.id.notIn(memberIds)
                )
                .orderBy(
                        memberJpaEntity.studentInfo.grade.asc(),
                        memberJpaEntity.studentInfo.room.asc(),
                        memberJpaEntity.studentInfo.number.asc()
                )
                .fetch();
    }
}
