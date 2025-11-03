export type StatusServico = 'Parado' | 'Rodando' | 'Erro';
export type StatusConsumo = 'Em Espera' | 'Consumindo' | 'Pausado';

export interface DashboardStatusState {
  statusServico: StatusServico;
  statusConsumo: StatusConsumo;
  dataConciliacao: string | null;
  dataBaixa: string | null;
  syncLoading: boolean;
  syncError: string | null;
}