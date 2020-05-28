
# SIJOGA & SOSIFOD

## Configuração do ambiente:

- JDK 1.8 [link para download](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- NetBeans 11.3 [Windows](https://downloads.apache.org/netbeans/netbeans/11.3/Apache-NetBeans-11.3-bin-windows-x64.exe) [Linux](https://downloads.apache.org/netbeans/netbeans/11.3/Apache-NetBeans-11.3-bin-linux-x64.sh) [MaxOS](https://downloads.apache.org/netbeans/netbeans/11.3/Apache-NetBeans-11.3-bin-macosx.dmg)
- GlaskFish 5.1 [link para download](https://projects.eclipse.org/projects/ee4j.glassfish/downloads) (baixar a versão **FULL**)
- PostgreSQL 11/12 [link para download](https://www.postgresql.org/download/)

**Instruções:**
- Instalar o *JDK* e configurar o `JAVA_HOME` (mesma coisa do semestre passado);
- Instalar o *NetBeans* (sem segredos);
- Extrair os arquivos compactados do *GlassFish* em algum diretório com as devidas permissões;
- Abrir o *NetBeans* e configurar o *GlassFish* como um servidor de aplicação: acesse o menu *Tools > Servers* e clique em *Add server...*. Siga o assitente de configuração do servidor (em determinado momento você deverá informar o diretório onde o *GlassFish fo extraído*)
- Importe os projetos e seja feliz!!!

:warning: *Vocês não devem ter problemas importando projetos, mas a criação de novos projetos parece ficar restrita à utilização de gerenciadores de dependências nessa versão do* NetBeans. *Eu criei os projetos com* **Maven**, *mas se vocês descobrirem como criar um projeto do jeito "clássico", compartilhe.*

## Configuração do banco de dados e Hibernate

Para fazer a configuração do banco de dados, crie os dois database (um para cada sistema) no PostgreSQL. Eu estou usando em minha máquina o `dac_sijoga` e o `dac_sosifod`.

O arquivo de configuração do *Hibernate* usado pelo projeto **não é armazenada no repositório**, pois ele contém dados locais, como usuário e senha para conectar ao banco, que vai ser diferente para cada pessoa. Contudo, eucriei uma cópia com extensão `*.example`, que deve ser copiada e renomeada sem essa extensão. Aí, nesse arquivo `hibernate.cfg.xml` você deve inserir os dados do seu banco, por isso, verifique a URL da JDBC (inclusive o nome do banco) e as credenciais (usuário e senha) de acesso.

O arquivo de exemplo está na pasta `<projeto>/src/main/resources`, e a cópia deve ser feita nessa pasta mesmo, que é onde o *Hibernate* vai buscar no momento que a aplicação iniciar.

**Não é necessário rodar scripts CREATE TABLE**. Com a propriedade `hibernate.hbm2ddl.auto` do *Hibernate* marcada como `create`, todo esquema de tabelas é recriado quando a aplicação é iniciada, a partir da configuração das classes-entidades.
