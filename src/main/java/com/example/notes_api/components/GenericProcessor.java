package com.example.notes_api.components;


import com.example.notes_api.Repositories.RateLimitRepository;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
public class GenericProcessor implements ItemProcessor<Object,Object> {

    RateLimitRepository rateLimitRepository = RateLimitRepository.getInstance();

    Logger logger = LoggerFactory.getLogger(GenericProcessor.class);

    public Object process(@NonNull Object item) throws Exception {
        rateLimitRepository.getRequestCountsPerIpAddress().clear();
        logger.info("Resetting all counts");
        return item;
    }
}