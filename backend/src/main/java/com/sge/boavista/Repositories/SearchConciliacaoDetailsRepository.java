package com.sge.boavista.Repositories;

import com.sge.boavista.Entities.DTO.SGE.ConciliacaoDetailsDTO;
import com.sge.boavista.Entities.DTO.SGE.FilterDetailsDTO;
import com.sge.boavista.Entities.Message;
import com.sge.boavista.Services.DBConfig;

import com.sge.boavista.Utils.basic.BasicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class SearchConciliacaoDetailsRepository extends BasicRepository {

    /**
     * Busca os logs de forma paginada.
     * @param numeroPagina O número da página (começando em 0).
     * @param tamanhoPagina A quantidade de itens por página.
     * @return Um objeto Page com os resultados.
     */
    public Message Execute(int numeroPagina, int tamanhoPagina, FilterDetailsDTO filter){

        try{

            JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource());
            Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);

            String sqlFilter = "";

            if(filter.getDataConciliada() != null){
                sqlFilter += " AND TI_DATACONCILIADA = '" + filter.getDataConciliada().toString()+ "'";
            }
            if(filter.getDataConciliacao() != null){
                sqlFilter += " AND TI_DATACONCILIACAO = '" + filter.getDataConciliacao().toString()+ "'";
            }
            if(filter.getLoja() != null){
                sqlFilter += " AND TI_LOJA = '" + filter.getLoja()+ "'";
            }
            if(filter.getStatus() != -1){
                sqlFilter += " AND TI_STATUS = " + filter.getStatus();
            }

            // 1. SQL para contar o total de registros
            String countSql = "SELECT COUNT(*) FROM TI_CONCILIACAO WHERE 1=1";
            countSql += sqlFilter;


            Integer total = jdbcTemplate.queryForObject(countSql, Integer.class);
            if (total == null) total = 0;

            // 2. SQL para buscar os dados da página específica (sintaxe do Firebird)
            String dataSql = "SELECT * FROM TI_CONCILIACAO WHERE 1=1";
            dataSql += sqlFilter;
            dataSql += "ORDER BY TI_ID DESC ROWS ? TO ?";
            int firstRow = numeroPagina * tamanhoPagina + 1;
            int lastRow = firstRow + tamanhoPagina - 1;

            System.out.println(dataSql);

            List<ConciliacaoDetailsDTO> logs = jdbcTemplate.query(dataSql, new Object[]{firstRow, lastRow}, (rs, rowNum) -> {
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

            return new Message<Page<ConciliacaoDetailsDTO>>(
                                200,
                                new PageImpl<>(logs, pageable, total),
                                true);


        }catch (Exception ex){
            return new Message<String>(
                                400,
                                "Erro ao buscar os Detalhes das Conciliacoes : " + ex.getMessage(),
                                false);
        }


    }


}
