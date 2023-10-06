package dgsw.pioneers.checkIn.global.batch.step.processor;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceTime;
import dgsw.pioneers.checkIn.global.annotation.batch.Processor;
import dgsw.pioneers.checkIn.global.batch.step.dto.AttendanceJobDto;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@Processor
@RequiredArgsConstructor
public class AttendanceDateProcessor implements Tasklet, StepExecutionListener {

    private List<AttendanceJobDto> attendances;

    @Override
    public void beforeStep(StepExecution stepExecution) {

        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.attendances = (List<AttendanceJobDto>) executionContext.get("attendances");

        log.debug("attendances Processor initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        this.attendances.forEach(attendanceJobDto -> {
            attendanceJobDto.setLectureDate(ZoneDateTimeUtil.nowToLocalDate().plusDays(attendanceJobDto.getDayOfWeek().getValue()));
            attendanceJobDto.setAttendanceTime(checkAttendanceTime(attendanceJobDto.getEndTime()));
        });

        return RepeatStatus.FINISHED;
    }

    private AttendanceTime checkAttendanceTime(LocalTime endTime) {

        if (endTime.isBefore(LocalTime.of(18, 30))) {
            return AttendanceTime.LESSON_8_9;
        } else {
            return AttendanceTime.LESSON_10_11;
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        log.debug("attendances Processor ended.");

        return ExitStatus.COMPLETED;
    }
}
