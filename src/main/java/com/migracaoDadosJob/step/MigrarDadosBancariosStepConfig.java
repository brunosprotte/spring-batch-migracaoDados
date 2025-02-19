package com.migracaoDadosJob.step;

import com.migracaoDadosJob.dominio.DadosBancarios;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MigrarDadosBancariosStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step migrarDadosBancariosStep(
            final ItemReader<DadosBancarios> arquivoDadosBancariosReader,
            final ItemWriter<DadosBancarios> bancoDadosBancariosItemWriter
    ) {
        return stepBuilderFactory.get("migrarDadosBancariosStep")
                .<DadosBancarios, DadosBancarios>chunk(10000)
                .reader(arquivoDadosBancariosReader)
                .writer(bancoDadosBancariosItemWriter)
                .build();
    }

}
