package com.example.notes_api.components;

import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;


@Component
public class GenericWriter implements ItemWriter<Object> {
    @Override
    public void write(Chunk<? extends Object> chunk) throws Exception { //im forced to use ? extends object intead of just object, basically overriding a method from the generic interface so it already defines that it parameter must be a ? extends of t
        return;
    }
}
