package com.sge.boavista.Controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "0.0.0.0")
public class ConciliacaoController {



    @PostMapping(path = "conciliacao")
    public void conciliacao(@RequestParam("DataInicio") String data){


    }




}
