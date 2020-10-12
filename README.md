### Descrição
 - Este projeto exemplifica a migração de dados de arquivos `.csv` para um banco de dados
 
 
 ### Configuração
 - Executar o arquivo `scripts.sql` em um banco de dados para criar a estruturar esperada pelo projeto
   - Atualize as dependências no `pom.xml` e no arquivo `application.properties` para refletir a conexão com o banco utilizado
   
### Execução
 - Ao executar o projeto, espera-se que os dados dos arquivos `dados_bancarios.csv` e `pessoas.csv` sejam importados para
 o banco de dados, com exceção do primeiro registro do arquivo `pessoas.csv`, que deve ser registrado em um novo arquivo: 
 `pessoas_invalidas.csv`, exemplificado o registro de registros que não puderam ser importados.
 Obs.: Cada arquivo possui um total de 10.000 registros.
   
 