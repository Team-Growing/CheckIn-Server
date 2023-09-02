package dgsw.pioneers.checkIn.global.batch.step.writer;

import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceGenerateUseCase;
import dgsw.pioneers.checkIn.global.annotation.batch.Writer;
import dgsw.pioneers.checkIn.global.batch.step.dto.AttendanceJobDto;
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

import java.util.List;

@Slf4j
@Writer
@RequiredArgsConstructor
public class AttendanceWriter implements Tasklet, StepExecutionListener {

    private final AttendanceGenerateUseCase attendanceGenerateUseCase;
    private List<AttendanceJobDto> attendances;

    @Override
    public void beforeStep(StepExecution stepExecution) {

        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.attendances = (List<AttendanceJobDto>) executionContext.get("attendances");

        log.debug("attendances Writer initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        this.attendances.forEach(attendanceJobDto -> {
            attendanceGenerateUseCase.generateAttendance(attendanceJobDto.mapToDomainEntity());
        });

        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        log.debug("attendances Writer ended.");

        return ExitStatus.COMPLETED;
    }
}
