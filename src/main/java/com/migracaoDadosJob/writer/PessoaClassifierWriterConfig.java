package com.migracaoDadosJob.writer;

import com.migracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaClassifierWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter(
            final JdbcBatchItemWriter<Pessoa> bancoPessoaWriter,
            final FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter
    ) {
        return new ClassifierCompositeItemWriterBuilder<Pessoa>()
                .classifier(classifier(bancoPessoaWriter, arquivoPessoasInvalidasWriter))
                .build();
    }

    private Classifier<Pessoa, ItemWriter<? super Pessoa>> classifier(final JdbcBatchItemWriter<Pessoa> bancoPessoaWriter,
                                                                      final FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter) {

        return new Classifier<Pessoa, ItemWriter<? super Pessoa>>() {
            @Override
            public ItemWriter<? super Pessoa> classify(Pessoa pessoa) {
                if (pessoa.isValida()) {
                    return bancoPessoaWriter;
                }
                return arquivoPessoasInvalidasWriter;
            }
        };

    }

}
