package com.example.notes_api.components;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericReader implements ItemReader<Object>{


    @Override
    public @Nullable Object read() throws Exception {
        return null;
    }
}
