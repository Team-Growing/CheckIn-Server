package dgsw.pioneers.checkIn.global.config.batch;

import com.fasterxml.jackson.databind.ObjectMapper;
import dgsw.pioneers.checkIn.global.batch.step.dto.AttendanceJobDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final ObjectMapper objectMapper;

    @Bean
    public ExecutionContextSerializer customSerializer() {

        Jackson2ExecutionContextStringSerializer defaultSerializer = new Jackson2ExecutionContextStringSerializer(AttendanceJobDto.class.getName());
        defaultSerializer.setObjectMapper(objectMapper);
        return defaultSerializer;
    }

    @Bean
    public BatchConfigurer myBatchConfigurer(DataSource dataSource,
                                             ExecutionContextSerializer executionContextSerializer,
                                             PlatformTransactionManager transactionManager) {
        return new DefaultBatchConfigurer(dataSource) {
            @Override
            protected JobExplorer createJobExplorer() throws Exception {

                JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
                jobExplorerFactoryBean.setDataSource(dataSource);
                jobExplorerFactoryBean
                        .setSerializer(executionContextSerializer);
                jobExplorerFactoryBean.afterPropertiesSet();
                return jobExplorerFactoryBean.getObject();
            }

            @Override
            protected JobRepository createJobRepository() throws Exception {

                JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
                jobRepositoryFactoryBean.setDataSource(dataSource);
                jobRepositoryFactoryBean
                        .setSerializer(executionContextSerializer);
                jobRepositoryFactoryBean.setTransactionManager(transactionManager);
                jobRepositoryFactoryBean.afterPropertiesSet();
                return jobRepositoryFactoryBean.getObject();
            }
        };
    }
}
