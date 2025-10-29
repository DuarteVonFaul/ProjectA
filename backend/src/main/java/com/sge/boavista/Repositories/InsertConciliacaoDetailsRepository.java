package com.sge.boavista.Repositories;

import com.sge.boavista.Entities.DTO.SGE.ConciliacaoDetailsDTO;
import com.sge.boavista.Entities.Message;
import com.sge.boavista.Services.DBConfig;
import com.sge.boavista.Utils.basic.BasicRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class InsertConciliacaoDetailsRepository extends BasicRepository {

    private final String SCRIPT = "UPDATE OR INSERT INTO TI_CONCILIACAO (TI_DETALHE, TI_DATACONCILIADA, TI_DATACONCILIACAO, TI_STATUS, TI_LOJA, TI_CAIXA) " +
            "VALUES (?, ?, ?, ?, ?, ?) matching (TI_DATACONCILIADA, TI_LOJA, TI_CAIXA)";

    /**
     * Salva um novo registro de log na tabela.
     * @return O objeto ConciliacaoLog com o ID gerado.
     */
    public Message Execute(String detalhe, Integer status, String data, String loja, String caixa){
        try{

            JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource());
            ConciliacaoDetailsDTO log = new ConciliacaoDetailsDTO();
            log.setDetalhe(detalhe);
            log.setDataConciliada(LocalDate.parse(data));
            log.setDataConciliacao(LocalDateTime.now());
            log.setStatus(status);
            log.setLoja(loja);
            log.setCaixa(caixa);

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                // É importante especificar o nome da coluna da chave primária para o driver do Firebird
                PreparedStatement ps = connection.prepareStatement(SCRIPT, new String[]{"TI_ID"});
                ps.setString(1, log.getDetalhe());
                ps.setDate(2, java.sql.Date.valueOf(log.getDataConciliada()));
                ps.setTimestamp(3, java.sql.Timestamp.valueOf(log.getDataConciliacao()));
                ps.setInt(4, log.getStatus());
                ps.setString(5, log.getLoja());
                ps.setString(6, log.getCaixa());
                return ps;
            }, keyHolder);

            Map<String, Object> keys = keyHolder.getKeys();
            if (keys != null && !keys.isEmpty()) {
                Object idValue = keys.get("TI_ID");
                if (idValue instanceof Number) {
                    log.setId(((Number) idValue).intValue());
                }
            }

            return new Message<String>( 200,
                    log.toString(),
                    true);

        }catch (Exception ex){
            return new Message<String>( 400,
                    "Erro ao registrar processo de Conciliacao Loja: " + loja
                                                                    + " Caixa: " + data
                                                                    + " Data:  " + caixa,
                    false);
        }
    }


}
