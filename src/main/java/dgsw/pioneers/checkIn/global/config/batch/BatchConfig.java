package dgsw.pioneers.checkIn.global.config.batch;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
    private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public JobRepository createJobRepository() throws Exception {

        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();

        Jackson2ExecutionContextStringSerializer defaultSerializer = new Jackson2ExecutionContextStringSerializer();
        defaultSerializer.setObjectMapper(objectMapper);

        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.afterPropertiesSet();
        jobRepositoryFactoryBean.setSerializer(defaultSerializer);

        return jobRepositoryFactoryBean.getObject();
    }
}
