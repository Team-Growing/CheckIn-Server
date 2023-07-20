package dgsw.pioneers.checkIn.global.batch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@RequiredArgsConstructor
//@Configuration
//public class ProduceAttendanceSheetJobConfig {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job produceAttendanceSheetJob() {
//        return jobBuilderFactory.get("produceSheetJob")
//                .start(produceSheetStep())
//                .on("FAILED")
//                .to(failStep1())
//                .on("*")
//                .end()
////                // testStep1 후에
////                .from(testStep1())
////                // testStep1 수행 결과에 상관 없이
////                .on("*")
////                // tesetStep2로 다음 Step 수행
////                .to(testStep2())
////                // testStep2 수행 결과에 상관 없이
////                // 다음 수행으로 testStep3 지정
////                .on("*")
////                // stepFlow 종료(FlowBuilder 반환)
////                .end()
////                // 해당 Job 종료(FlowBuilder 종료)
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step produceSheetStep() {
//        return stepBuilderFactory.get("produceSheetStep")
//                .tasklet((contribution, chunkContext) ->{
//
//
//
//                    contribution.setExitStatus(ExitStatus.FAILED);
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step failStep1() {
//        return stepBuilderFactory.get("failStep1")
//                .tasklet((contribution, chunkContext) ->{
//
//                    // Batch 로직 fail 분기 로직
//                    ...
//
//                    // 정상 종료 시 상태 Return
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
////    @Bean
////    public Step testStep2() {
////        return stepBuilderFactory.get("testStep2")
////                .tasklet((contribution, chunkContext) ->{
////
////                    // Batch 로직 구현
////                    ...
////
////                    // 정상 종료 시 상태 Return
////                    return RepeatStatus.FINISHED;
////                })
////                .build();
////    }
//}
