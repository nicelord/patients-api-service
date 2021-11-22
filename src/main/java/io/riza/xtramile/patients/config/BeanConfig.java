package io.riza.xtramile.patients.config;

import io.riza.xtramile.patients.port.in.PatientCommand;
import io.riza.xtramile.patients.port.out.PatientStore;
import io.riza.xtramile.patients.service.PatientCommandImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    PatientCommand patientService(PatientStore patientStore){
        return new PatientCommandImpl(patientStore);
    }

}
