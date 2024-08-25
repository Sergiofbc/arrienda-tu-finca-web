package com.example.ArriendaTuFinca.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.convention.MatchingStrategies;

@Configuration
public class ModelMapperConfiguration {
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); //matching es pasar de un objeto a otro de la mejor forma
        return modelMapper;
    }
}
