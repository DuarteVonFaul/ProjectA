package com.sge.boavista.Repositories;

import com.sge.boavista.Entities.DTO.SGE.HeaderDTO;
import com.sge.boavista.Entities.Message;
import com.sge.boavista.Utils.HeaderUtils;
import com.sge.boavista.Utils.basic.BasicRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConfigAPIRepository extends BasicRepository {

    private final String SCRIPT = "SELECT API_VALOR AS APIVALOR, " +
            " CASE API_PARAMETRO" +
            " WHEN 1 THEN 'secret'" +
            " WHEN 2 THEN 'client'"+
            " END AS NAME"+
            " FROM TB_API"+
            " WHERE API_LOJA = '999'"+
            " AND API_ATIVO = 'S'"+
            " AND API_ID = 15";


    public Message Execute(){

      try{

          JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
          HeaderDTO headerData = new HeaderDTO();
          jdbcTemplate.query(SCRIPT, (rs) -> {
              String name = rs.getString("NAME");
              String value = rs.getString("APIVALOR");
              if ("client".equals(name)) {
                  headerData.setClient(value);
              } else if ("secret".equals(name)) {
                  headerData.setSecret(value);
              }
          });
          HeaderUtils util = new HeaderUtils(headerData.getSecret(), headerData.getClient());

          return new Message<HeaderUtils>(200,util,true);


      }catch (Exception ex){

          return new Message<String>(400,
                                    "Erro ao construir cabeçalho de requisicao: " + ex.getMessage(),
                                    true);

      }


    }



}
