package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.QAttendantJpaEntity.attendantJpaEntity;
import static dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.QMemberJpaEntity.memberJpaEntity;

@Repository
@RequiredArgsConstructor
public class MembersAsAttendantsDao {

    private final JPAQueryFactory queryFactory;

    public List<MemberJpaEntity> findAllMembersWithAttendants(Long lectureId, AttendanceStatus attendanceStatus) {

        /**
         * JPQL - Load AttendantsId
         */
//        String jpql = "SELECT a.memberId.id " +
//                "FROM AttendantJpaEntity a " +
//                "WHERE a.attendanceJpaEntity.lecture.id = :lectureId " +
//                "AND a.attendanceJpaEntity.attendanceStatus = :attendanceStatus";
//
//        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
//        query.setParameter("lectureId", lectureId);
//        query.setParameter("attendanceStatus", attendanceStatus);

        /**
         * 서브 쿼리 -> Cross Join 발생 !
         * Join문이 없는데 Cross Join이 발생하는 이유
         * : ORM에서는 객체끼리 식별자를 통해 참조가 가능하지만 실제 DB에서 조인을 해야한다. 그래서 크로스 조인이 발생하는 것
         * Subquery는 내부 쿼리가 완료될 때까지 외부 쿼리가 대기해야 하므로 성능 저하의 원인 -> 조인으로 최적화
         */
//        return queryFactory
//                .selectFrom(memberJpaEntity)
//                .where(memberJpaEntity.id.in(
//                        JPAExpressions
//                                .select(attendantJpaEntity.memberId.id)
//                                .from(attendantJpaEntity)
//                                .where(
//                                        attendantJpaEntity.attendanceJpaEntity.lecture.id.eq(lectureId),
//                                        attendantJpaEntity.attendanceJpaEntity.attendanceStatus.eq(attendanceStatus)
//                                )
//                ))
//                .orderBy(
//                        memberJpaEntity.studentInfo.grade.asc(),
//                        memberJpaEntity.studentInfo.room.asc(),
//                        memberJpaEntity.studentInfo.number.asc()
//                )
//                .fetch();

        /**
         * Inner Join - 'memberJpaEntity'와 'attendantJpaEntity'
         */
        return queryFactory
                .selectFrom(memberJpaEntity)
                .join(attendantJpaEntity).on(memberJpaEntity.id.eq(attendantJpaEntity.memberId.id))
                .where(
                        attendantJpaEntity.attendanceJpaEntity.lecture.id.eq(lectureId),
                        attendantJpaEntity.attendanceJpaEntity.attendanceStatus.eq(attendanceStatus)
                )
                .orderBy(
                        memberJpaEntity.studentInfo.grade.asc(),
                        memberJpaEntity.studentInfo.room.asc(),
                        memberJpaEntity.studentInfo.number.asc()
                )
                .fetch();
    }
}
