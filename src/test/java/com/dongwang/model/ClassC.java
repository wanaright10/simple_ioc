package com.dongwang.model;


import com.dongwang.annotation.Bean;
import lombok.Data;

/**
 * Created by Dong Wang.
 * Created on 15/11/11 13:55
 */
@Data
@Bean
public class ClassC {
    @Bean
    private ClassD d;
}
