package com.sge.boavista.Repositories;

import com.sge.boavista.Entities.Message;
import com.sge.boavista.Services.DBConfig;
import com.sge.boavista.Utils.basic.BasicRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class CreatedTableConciliacaoDetailsRepository extends BasicRepository {


    private final String SCRIPT = "EXECUTE BLOCK AS \n" +
                    "BEGIN \n" +
                    "  IF (NOT EXISTS(SELECT 1 FROM RDB$RELATIONS WHERE RDB$RELATION_NAME = 'TI_CONCILIACAO')) THEN \n" +
                    "  BEGIN \n" +
                    "    EXECUTE STATEMENT 'CREATE GENERATOR GEN_TI_CONCILIACAO_ID;'; \n" +

                    "    EXECUTE STATEMENT \n" +
                    "      'CREATE TABLE TI_CONCILIACAO ( \n" +
                    "        TI_ID INTEGER NOT NULL PRIMARY KEY, \n" +
                    "        TI_LOJA VARCHAR(3), \n" +
                    "        TI_CAIXA VARCHAR(3), \n" +
                    "        TI_DETALHE VARCHAR(255), \n" +
                    "        TI_DATACONCILIADA DATE, \n" +
                    "        TI_DATACONCILIACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP, \n" +
                    "        TI_STATUS INTEGER DEFAULT 0 \n" +
                    "      )'; \n" +


                    "    EXECUTE STATEMENT \n" +
                    "      'CREATE OR ALTER TRIGGER TI_CONCILIACAO_BI FOR TI_CONCILIACAO \n" +
                    "      ACTIVE BEFORE INSERT POSITION 0 \n" +
                    "      AS \n" +
                    "      BEGIN \n" +
                    "        if (new.TI_ID is null) then \n" +
                    "          new.TI_ID = gen_id(GEN_TI_CONCILIACAO_ID, 1); \n" +
                    "      END'; \n" +
                    "  END \n" +
                    "END";

    /**
     * Verifica se a tabela TI_CONCILIACAO existe e, se não, a cria.
     * Este método pode ser chamado uma vez na inicialização da aplicação.
     */
    public Message Execute(){
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource());
            jdbcTemplate.execute(this.SCRIPT);
            System.out.println("Tabela TI_CONCILIACAO verificada/criada com sucesso.");
            return new Message( 200,
                                "Criação da tabela TI_CONCILIACAO com sucesso",
                                true);
        }catch (Exception ex){
            return  new Message( 400,
                    "Erro ao Criar Tabela TI_CONCILIACAO: " + ex.getMessage(),
                    false);
        }

    }
}
