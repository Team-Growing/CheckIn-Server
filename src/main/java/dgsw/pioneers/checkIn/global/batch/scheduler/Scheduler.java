package dgsw.pioneers.checkIn.global.batch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final JobLauncher jobLauncher;
    private final Job produceAttendanceJob;

    @Scheduled(cron = "0 0 23 ? * SUN", zone = "Asia/Seoul")
    public void setAttendanceJob() {
        try {
            final JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(produceAttendanceJob, jobParameters);
            log.info("<<<<<<<<<< AttendanceJob Start {} >>>>>>>>>>", LocalDateTime.now());
        } catch (Exception e) {
            log.error("fail to process file", e);
        }
    }
}
