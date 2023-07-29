package dgsw.pioneers.checkIn.global.batch.job;

import dgsw.pioneers.checkIn.global.batch.step.processor.AttendanceDateProcessor;
import dgsw.pioneers.checkIn.global.batch.step.reader.LectureListReader;
import dgsw.pioneers.checkIn.global.batch.step.writer.AttendanceWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AttendanceJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final LectureListReader lectureListReader;
    private final AttendanceDateProcessor attendanceDateProcessor;
    private final AttendanceWriter attendanceWriter;

    @Bean
    public Job produceAttendanceJob() {
        return jobBuilderFactory.get("produceAttendanceJob")
                .start(readLectureListStep())
                .next(processAttendanceDateStep())
                .next(writeAttendanceStep())
                .build();
    }

    @Bean
    public Step readLectureListStep() {
        return stepBuilderFactory.get("readLectureListStep")
                .tasklet(lectureListReader)
                .build();
    }

    @Bean
    public Step processAttendanceDateStep() {
        return stepBuilderFactory.get("processAttendanceDateStep")
                .tasklet(attendanceDateProcessor)
                .build();
    }

    @Bean
    public Step writeAttendanceStep() {
        return stepBuilderFactory.get("writeAttendanceStep")
                .tasklet(attendanceWriter)
                .build();
    }
}
