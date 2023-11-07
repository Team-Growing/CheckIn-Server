package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.AttendanceRepository;
import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.AttendantRepository;
import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.AttendanceJpaEntity;
import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.AttendantJpaEntity;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendant;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.CreateAttendantPort;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.DeleteAttendantPort;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo.MemberIdJpaVO;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class AttendantPersistenceAdapter implements CreateAttendantPort, DeleteAttendantPort {

    private final EntityManager entityManager;
    private final AttendanceRepository attendanceRepository;
    private final AttendantRepository attendantRepository;

    @Override
    public void createAttendant(Attendance attendance) {

        AttendanceJpaEntity attendanceJpa = attendanceRepository.findById(attendance.getAttendanceId().getValue()).get();

        List<Attendant> attendants = attendance.getAttendants();
        Attendant newAttendant = attendants.get(attendants.size() - 1);

        attendanceJpa.addAttendant(
                AttendantJpaEntity.builder()
                        .attendanceJpa(attendanceJpa)
                        .memberId(new MemberIdJpaVO(newAttendant.getAttendantId().getValue()))
                        .applyDateTime(newAttendant.getApplyDateTime())
                        .build()
        );
        attendanceJpa.updateAttendStudent(attendance.getAttendStudent());
    }

    @Override
    public void deleteAttendant(Attendance.AttendanceId attendanceId, Attendant attendant) {

        /**
         * 출석자를 삭제하기 위하여 AttendantJpaEntity를 불러오는 과정에서
         * AttendantJpaEntity의 attendanceJpaEntity는 Lazy Loding 이지만 해당 엔터티가 트랜잭션 내에서 영속 상태(Persistence Context)에 존재한다.
         * 이 경우엔 POJO 에서 동기화 오류가 생길 수 있기 때문에, JPA는 삭제 쿼리를 날리지 않는다.
         * 그래서 이를 해결하기 위해 select 쿼리가 한번더 나가지만 EntityManager를 초기화 해주었다.
         */

        entityManager.clear();
        attendantRepository.deleteByMemberId_IdAndAttendanceJpaEntity_Id(
                attendant.getAttendantId().getValue(), attendanceId.getValue()
        );
    }
}
