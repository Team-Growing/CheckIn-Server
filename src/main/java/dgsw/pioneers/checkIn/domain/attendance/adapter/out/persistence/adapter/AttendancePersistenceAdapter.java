package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.AttendanceMapper;
import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.AttendanceRepository;
import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.AttendanceJpaEntity;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.CreateAttendancePort;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.UpdateAttendanceCodePort;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.adapter.LectureLoadAdapter;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class AttendancePersistenceAdapter implements CreateAttendancePort, UpdateAttendanceCodePort {

    private final AttendanceRepository attendanceRepository;
    private final LectureLoadAdapter lectureLoadAdapter;
    private final AttendanceMapper attendanceMapper;

    @Override
    public void createAttendance(Attendance attendance) {

        AttendanceJpaEntity attendanceJpaEntity = attendanceMapper.mapToJpaEntity(
                attendance,
                lectureLoadAdapter.loadLectureJpaEntity(attendance.getLectureId().getValue()));

        attendanceRepository.save(attendanceJpaEntity);
    }

    @Override
    public void updateAttendanceCode(Attendance attendance) {

        AttendanceJpaEntity attendanceJpa = attendanceRepository.findById(attendance.getAttendanceId().getValue()).get();
        //위의 코드에서 select query가 나가지 않은 이유는 같은 트렌젝션이기 때문에 JPA 1차 캐시에서 조회하였기 때문

        attendanceJpa.updateCode(attendance.getCode());
    }
}
