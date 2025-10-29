package com.sge.boavista.Repositories;

import com.sge.boavista.Entities.DTO.SGE.ConcProssDTO;
import com.sge.boavista.Entities.DTO.SGE.VendaDTO;
import com.sge.boavista.Entities.Message;
import com.sge.boavista.Utils.basic.BasicRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class TranstefRepository extends BasicRepository {



    public Message findDatesToConciliar(String date_checkpoint, String date_final) {

        try{
            JdbcTemplate conn = new JdbcTemplate(this.dataSource());

            String sql = "SELECT TEF_LOJA, TEF_DHOST FROM TB_TRANSTEF WHERE TEF_CONCILIACAO = 'N' AND TEF_ADM NOT LIKE 'PIX%'";
            if(date_checkpoint != null) {
                sql += " AND TEF_DHOST > '" + date_checkpoint;
            }
            if(date_final != null){
                sql += " AND TEF_DHOST < '"+date_final+"'";
            }else {
                sql += "' AND TEF_DHOST < '" + LocalDate.now().toString() + "'";
            }
            sql += " GROUP BY TEF_LOJA, TEF_DHOST";

            List<ConcProssDTO> result = conn.query(
                    sql,
                    (rs,rowNum) ->{
                        return new ConcProssDTO(LocalDate.parse(rs.getString("TEF_DHOST")),
                                rs.getString("TEF_LOJA"));
                    }
            );

            return new Message<List<ConcProssDTO>>(200,result,true);

        }catch (Exception ex){
            return new Message<String>(400,
                                    "Erro ao Processar as datas para conciliacao: " + ex.getMessage(),
                                    false);
        }


    }

    public Message findDatesToFaturar(String date_checkpoint, String date_final) {
        try{
            JdbcTemplate conn = new JdbcTemplate(this.dataSource());

            String sql = "SELECT TEF_LOJA, TEF_DHOST FROM TB_TRANSTEF WHERE TEF_CONCILIACAO = 'S'";
            if(date_checkpoint != null) {
                sql += " AND TEF_DHOST > '" + date_checkpoint;
            }
            if(date_final != null){
                sql += " AND TEF_DHOST < '"+date_final+"'";
            }else {
                sql += "' AND TEF_DHOST < '" + LocalDate.now().toString() + "'";
            }
            sql += " GROUP BY TEF_LOJA, TEF_DHOST";
                List<ConcProssDTO> result = conn.query(
                        sql,
                        (rs,rowNum) ->{
                            return new ConcProssDTO(LocalDate.parse(rs.getString("TEF_DHOST")),
                                    rs.getString("TEF_LOJA"));
                        }
                );

                return new Message<List<ConcProssDTO>>(200,result,true);
        }catch (Exception ex){
            return new Message<String>(400,
                    "Erro ao Processar as datas para conciliacao: " + ex.getMessage(),
                    false);
        }
    }

    public Message findSales(ConcProssDTO process) {
        try{
            String sql = "SELECT TEF_LOJA, \n" +
                    "TEF_CAIX, \n" +
                    "TEF_TPTRAN, \n" +
                    "TEF_TRANSACAO, \n" +
                    "IIF(TEF_CANC = 'S', TEF_NSUC, TEF_NSU) as TEF_NSU, \n" +
                    "TEF_IDTR, \n" +
                    "TEF_VALO, \n" +
                    "TEF_REDE,\n" +
                    "TEF_NAUT, \n" +
                    "ADC_SITEFRETBANDEIRA, \n" +
                    "IIF(TEF_CANC = 'S', 1, 0) AS TEF_CANC,\n" +
                    "TEF_NPARC \n" +
                    "FROM (select t.*,ADC_SITEFRETBANDEIRA\n" +
                    "from tb_transtef t,tb_teftptran,tb_redecartao,tb_admcartao\n" +
                    "where tef_canc='N' and tef_exc='N'\n" +
                    "and tef_valo<>0 and tef_rede<>'' and tef_nsu<>'0' and tef_loja= ?\n" +
                    "and tef_dmov = ?\n" +
                    "and not exists(select * from tb_transtef f where f.tef_loja=t.tef_loja and f.tef_caix=t.tef_caix and\n" +
                    "f.tef_rede=t.tef_rede and f.tef_dmov=t.tef_dmov and f.tef_canc='S' and f.tef_nsuc=t.tef_nsu and f.tef_nsuc<>'0')\n" +
                    "and adc_ativo='S' and adc_rede=rc_codi and rc_ativo='S' and tef_adm<>'' and\n" +
                    "upper(trim(tef_adm)) like iif(position('==',adc_desctef)=0,trim(adc_desctef)||'%',trim(substring(adc_desctef from 1 for position('==',adc_desctef)-1)))\n" +
                    "and adc_desctef<>'' and tef_tptran=ttt_codi and ttt_operacao='CRT'\n" +
                    "and ((rc_desctef=tef_rede) or (rc_descricao=tef_rede))\n" +
                    "order by rc_descricao,adc_descricao,tef_loja,tef_dmov,tef_nsu) view_table ";

            JdbcTemplate conn = new JdbcTemplate(this.dataSource());
            List<VendaDTO> resut = conn.query(
                    sql,
                    new Object[]{process.getLoja(), process.getDate()},
                    (rs, rowNum) -> {
                        String loja = rs.getString("TEF_LOJA");
                        String caix = String.format("%03d", rs.getInt("TEF_CAIX"));
                        int idtr = rs.getInt("TEF_IDTR");
                        String id =  loja + caix + idtr;

                        VendaDTO obj = new VendaDTO(
                                id,
                                rs.getString("TEF_LOJA"),
                                String.format("%03d", rs.getInt("TEF_CAIX")),
                                rs.getInt("TEF_IDTR"),
                                rs.getString("TEF_TRANSACAO"),
                                rs.getString("TEF_NSU"),
                                rs.getString("TEF_TPTRAN"),
                                rs.getBigDecimal("TEF_VALO"),
                                rs.getString("TEF_REDE"),
                                rs.getString("ADC_SITEFRETBANDEIRA"),
                                rs.getInt("TEF_CANC"),
                                rs.getString("TEF_NAUT"),
                                rs.getInt("TEF_NPARC")
                        );

                        return obj;
                    }
            );

            return new Message<List<VendaDTO>>(200,resut,true);

        }catch (Exception ex){
            return new Message<String>(400,
                                    "Erro ao buscar vendas para conciliação: " + ex.getMessage(),
                                    false);
        }
    }

}
