export interface Reconciliation {
    id: number;
    loja:string;
    caixa:string;
    detalhe: string;
    dataConciliada: string;
    dataConciliacao: string;
    status: 'success' | 'warning' | 'error';
}

export type ReconciliationStatus = 'success' | 'warning' | 'error' ;