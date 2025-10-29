package com.sge.boavista.Repositories;

import com.sge.boavista.Entities.DTO.SGE.StoryDTO;
import com.sge.boavista.Entities.Message;
import com.sge.boavista.Services.DBConfig;
import com.sge.boavista.Utils.basic.BasicRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class SearchStoryRepository extends BasicRepository {

    private final String SCRIPT = "Select EMP_CODI, EMP_RAZA FROM TB_EMP";

    public Message Execute(){

        try{
            JdbcTemplate conn = new JdbcTemplate(this.dataSource());

            List<StoryDTO> stories = conn.query(
                    SCRIPT,
                    (rs,rowNum) ->{
                        return new StoryDTO(rs.getString("EMP_CODI"),
                                rs.getString("EMP_RAZA"));
                    }
            );

            return new Message<List<StoryDTO>>(200,
                                                stories,
                                                true);
        }catch (Exception ex){
            return new Message<String>(400,
                                    "Falha ao Carregar as Lojas: " + ex.getMessage(),
                                    false);
        }



    }



}
