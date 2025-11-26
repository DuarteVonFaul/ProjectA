

const Urls = { databaseConfig: "http://localhost:8001/api/config",
               reconciliationPage: (page) => {return `http://localhost:8001/int/boavista/details/${page}`},
               concilicao: (data) => {return `http://localhost:8001/int/boavista/conciliacao/${data}`},
               FaturamentoDebito: (data) => {return `http://localhost:8001/int/boavista//transacoes/faturamento/debit/${data}`},
               FaturamentoCredito: (data) => {return `http://localhost:8001/int/boavista//transacoes/faturamento/credit/${data}`},

 }


export default Urls;