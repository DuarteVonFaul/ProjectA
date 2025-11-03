import React, { createContext, useContext, useState, ReactNode } from 'react';

// --- 1. Definição dos Tipos ---

// Tipo para os status permitidos
export type ServiceStatus = 'Parado' | 'Iniciado' | 'Sincronizando';

// Interface para a estrutura de um serviço
export interface IService {
  id: number;
  name: string;
  status: ServiceStatus;
  lastExecution: string | null;
  checkpoint: string | null; // Um input date-picker retorna string (YYYY-MM-DD)
}

// Interface para o valor que o Contexto fornecerá
interface IServiceContext {
  services: IService[];
  toggleServiceStatus: (serviceId: number) => void;
  syncService: (serviceId: number) => void;
  updateCheckpoint: (serviceId: number, newDate: string) => void;
}

// --- 2. Estado Inicial (Tipado) ---

const initialServices: IService[] = [
  {
    id: 1,
    name: 'Conciliação',
    status: 'Parado',
    lastExecution: null,
    checkpoint: null,
  },
  {
    id: 2,
    name: 'Faturamento Debito',
    status: 'Parado',
    lastExecution: null,
    checkpoint: null,
  },
  {
    id: 3,
    name: 'Faturamento Credito',
    status: 'Parado',
    lastExecution: null,
    checkpoint: null,
  },
  {
    id: 4,
    name: 'Baixa Debito',
    status: 'Parado',
    lastExecution: null,
    checkpoint: null,
  },
  {
    id: 5,
    name: 'Baixa Credito',
    status: 'Parado',
    lastExecution: null,
    checkpoint: null,
  },
];

// --- 3. Criação do Contexto ---

// O Contexto é criado com um tipo, mas com valor inicial 'undefined'
// Vamos checar no hook customizado se ele está 'undefined' (fora do provider)
const ServicesContext = createContext<IServiceContext | undefined>(undefined);

// --- 4. Criação do Provedor ---

// Definimos as props do Provider, que incluem 'children'
interface ServicesProviderProps {
  children: ReactNode;
}

export function ServicesProvider({ children }: ServicesProviderProps) {
  const [services, setServices] = useState<IService[]>(initialServices);

  const getNow = (): string => new Date().toLocaleString('pt-BR');

  // Ação para Iniciar/Parar
  const toggleServiceStatus = (serviceId: number) => {
    setServices((currentServices) =>
      currentServices.map((service) => {
        if (service.id === serviceId) {
          const newStatus: ServiceStatus =
            service.status === 'Parado' ? 'Iniciado' : 'Parado';
          return { ...service, status: newStatus };
        }
        return service;
      })
    );
  };

  // Ação para Sincronizar
  const syncService = (serviceId: number) => {
    setServices((currentServices) =>
      currentServices.map((service) =>
        service.id === serviceId ? { ...service, status: 'Sincronizando' } : service
      )
    );

    // Simula a chamada ao Electron
    setTimeout(() => {
      setServices((currentServices) =>
        currentServices.map((service) =>
          service.id === serviceId
            ? {
                ...service,
                status: 'Parado',
                lastExecution: getNow(),
              }
            : service
        )
      );
    }, 2000);
  };

  // Ação para mudar a data
  const updateCheckpoint = (serviceId: number, newDate: string) => {
    setServices((currentServices) =>
      currentServices.map((service) =>
        service.id === serviceId ? { ...service, checkpoint: newDate } : service
      )
    );
  };

  // O valor do contexto que será passado
  const value: IServiceContext = {
    services,
    toggleServiceStatus,
    syncService,
    updateCheckpoint,
  };

  return (
    <ServicesContext.Provider value={value}>
      {children}
    </ServicesContext.Provider>
  );
}

// --- 5. Hook Customizado (Tipado) ---

export function useServices(): IServiceContext {
  const context = useContext(ServicesContext);
  if (context === undefined) {
    throw new Error('useServices deve ser usado dentro de um ServicesProvider');
  }
  return context;
}