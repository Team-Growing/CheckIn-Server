package dgsw.pioneers.checkIn.global.lib.scheduler;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceTime;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceActivateUseCase;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final JobLauncher jobLauncher;
    private final Job produceAttendanceJob;
    private final AttendanceActivateUseCase attendanceActivateUseCase;

    //출석 생성
    //매주 일요일 오후 11시에 작업 실행
    @Scheduled(cron = "0 0 23 ? * SUN", zone = "Asia/Seoul")
    public void setAttendanceJob() {
        try {
            log.info("<<<<<<<<<< AttendanceJob Start {} >>>>>>>>>>", ZoneDateTimeUtil.nowToLocalDate());

            final JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(produceAttendanceJob, jobParameters);
        } catch (Exception e) {
            log.error("fail to process file", e);
        }
    }

    //8-9교시 출석 할성화
    //월-목 오후 4시에 작업 실행
    @Scheduled(cron = "0 0 16 * * MON-THU", zone = "Asia/Seoul")
    public void activateLesson89() {
        try {
            log.info("<<<<<<<<<< activate Lesson 10-11 Start & deactivate Lesson 8-9 {} >>>>>>>>>>", ZoneDateTimeUtil.nowToLocalDate());
            attendanceActivateUseCase.activate(AttendanceTime.LESSON_8_9);
        } catch (Exception e) {
            log.error("fail to process file", e);
        }
    }

    //8-9교시 출석 비활성화, 10-11교시 출석 활성호
    //월-목 오후 6시 30분에 작업 실행
    @Scheduled(cron = "0 30 18 * * MON-THU", zone = "Asia/Seoul")
    public void activateLesson1011AndDeactivateLesson89() {
        try {
            log.info("<<<<<<<<<< activate Lesson 8-9 Start {} >>>>>>>>>>", ZoneDateTimeUtil.nowToLocalDate());
            attendanceActivateUseCase.activate(AttendanceTime.LESSON_10_11);
            attendanceActivateUseCase.deactivate(AttendanceTime.LESSON_8_9);
        } catch (Exception e) {
            log.error("fail to process file", e);
        }
    }

    //10-11교시 출석 비활성화
    //월-목 오후 9시에 작업 실행
    @Scheduled(cron = "0 0 21 * * MON-THU", zone = "Asia/Seoul")
    public void deactivateLesson1011() {
        try {
            log.info("<<<<<<<<<< deactivate Lesson 10-11 Start {} >>>>>>>>>>", ZoneDateTimeUtil.nowToLocalDate());
            attendanceActivateUseCase.deactivate(AttendanceTime.LESSON_10_11);
        } catch (Exception e) {
            log.error("fail to process file", e);
        }
    }
}
