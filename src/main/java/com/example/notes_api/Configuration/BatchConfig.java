package com.example.notes_api.Configuration;



import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.ChunkOrientedStepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;




@Configuration
public class BatchConfig extends DefaultBatchConfiguration { //@EnableBatchProcessing is no needed using extends


    @Autowired
    private ItemProcessor<Object, Object> processor;
    @Autowired
    private ItemReader<Object> reader;
    @Autowired
    private ItemWriter<Object> writer;

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("myJob", jobRepository)
                .start(step(jobRepository, platformTransactionManager))
                .build();
    }



    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new ChunkOrientedStepBuilder<Object,Object>("job1",jobRepository,100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


}