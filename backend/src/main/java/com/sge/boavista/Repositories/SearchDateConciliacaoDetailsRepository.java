package com.sge.boavista.Repositories;

import com.sge.boavista.Entities.DTO.SGE.ConciliacaoDetailsDTO;
import com.sge.boavista.Entities.Message;
import com.sge.boavista.Services.DBConfig;
import com.sge.boavista.Utils.basic.BasicRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public class SearchDateConciliacaoDetailsRepository extends BasicRepository {

    private final String SCRIPT = "SELECT * FROM TI_CONCILIACAO WHERE TI_DATACONCILIADA = ? AND TI_LOJA = ? ";

    public Message Execute(LocalDate date, String story){

        try{

            JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource());

            List<ConciliacaoDetailsDTO> resultados = jdbcTemplate.query(
                    SCRIPT,
                    new Object[]{date,story},
                    (rs, rowNum) -> {
                        ConciliacaoDetailsDTO log = new ConciliacaoDetailsDTO();
                        log.setId(rs.getInt("TI_ID"));
                        log.setDetalhe(rs.getString("TI_DETALHE"));
                        log.setDataConciliada(rs.getDate("TI_DATACONCILIADA").toLocalDate());
                        log.setDataConciliacao(rs.getTimestamp("TI_DATACONCILIACAO").toLocalDateTime());
                        log.setStatus(rs.getInt("TI_STATUS"));
                        log.setLoja(rs.getString("TI_LOJA"));
                        log.setCaixa(rs.getString("TI_CAIXA"));
                        return log;
                    });

            return new Message<List< ConciliacaoDetailsDTO >>( 200,
                    resultados,
                    true);
        }catch (Exception ex){

            return new Message<String>( 400,
                    "Erro ao Buscar por datas ja Conciliadas " + ex.getMessage(),
                    true);

        }



    }


}
