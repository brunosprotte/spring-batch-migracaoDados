package com.migracaoDadosJob.step;

import com.migracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MigrarPessoaStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step migrarPessoaStep(
            final ItemReader<Pessoa> arquivoPessoaReader,
            final ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter,
            final FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter
    ) {
        return stepBuilderFactory.get("migrarPessoaStep")
                .<Pessoa, Pessoa>chunk(10000)
                .reader(arquivoPessoaReader)
                .writer(pessoaClassifierWriter)
                .stream(arquivoPessoasInvalidasWriter)
                .build();
    }

}
