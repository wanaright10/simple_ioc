package com.dongwang.model;


import com.dongwang.annotation.Bean;
import lombok.Data;

/**
 * Created by Dong Wang.
 * Created on 15/11/11 13:54
 */
@Data
@Bean
public class ClassB {
    @Bean
    private ClassC c;
    @Bean
    private ClassD d;
}
