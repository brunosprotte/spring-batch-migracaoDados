package com.migracaoDadosJob.writer;

import com.migracaoDadosJob.dominio.DadosBancarios;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BancoDadosBancariosWriterConfig {

    private final String DADOS_BANCARIOS =
            " INSERT INTO dados_bancarios (id, pessoa_id, banco, conta, agencia)" +
                    " VALUES (:id, :pessoaId, :agencia, :conta, :banco) ";

    @Bean
    public JdbcBatchItemWriter<DadosBancarios> bancoDadosBancariosItemWriter(
            @Qualifier("appDataSource") DataSource dataSource
    ) {

        return new JdbcBatchItemWriterBuilder<DadosBancarios>()
                .dataSource(dataSource)
                .sql(DADOS_BANCARIOS)
                .beanMapped()
                .build();
    }
}
