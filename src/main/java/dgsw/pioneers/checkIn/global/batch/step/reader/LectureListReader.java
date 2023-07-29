package dgsw.pioneers.checkIn.global.batch.step.reader;

import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureLoadUseCase;
import dgsw.pioneers.checkIn.global.batch.step.dto.AttendanceJobDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class LectureListReader implements Tasklet, StepExecutionListener {

    private final LectureLoadUseCase lectureLoadUseCase;
    private List<AttendanceJobDto> attendances;

    @Override
    public void beforeStep(StepExecution stepExecution) {

        attendances = new ArrayList<>();
        log.debug("attendances Reader initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        attendances = lectureLoadUseCase.loadAllCoursePeriodLecture().stream()
                .map(AttendanceJobDto::new).collect(Collectors.toList());

        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("attendances", this.attendances);
        log.debug("attendances Reader ended.");

        return ExitStatus.COMPLETED;
    }
}
