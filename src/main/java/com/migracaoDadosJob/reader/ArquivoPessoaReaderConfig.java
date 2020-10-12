package com.migracaoDadosJob.reader;

import com.migracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import java.util.Date;

@Configuration
public class ArquivoPessoaReaderConfig {


    @Bean
    public FlatFileItemReader<Pessoa> arquivoPessoaReader() {

        return new FlatFileItemReaderBuilder<Pessoa>()
                .name("arquivoPessoaReader")
                .resource(new FileSystemResource("files/pessoas.csv"))
                .delimited()
                .names("nome", "email", "dataNascimento", "idade", "id")
                .addComment("--")
                .fieldSetMapper(fieldSetMapper())
                .build();

    }

    private FieldSetMapper<Pessoa> fieldSetMapper() {
        return new FieldSetMapper<Pessoa>() {
            @Override
            public Pessoa mapFieldSet(FieldSet fieldSet) throws BindException {
                Pessoa p = new Pessoa();

                p.setNome(fieldSet.readString("nome"));
                p.setEmail(fieldSet.readString("email"));
                p.setIdade(fieldSet.readInt("idade"));
                p.setId(fieldSet.readInt("id"));
                p.setDataNascimento(new Date(fieldSet.readDate("dataNascimento", "yyyy-MM-dd HH:mm:ss").getTime()));

                return p;
            }
        };
    }

}
