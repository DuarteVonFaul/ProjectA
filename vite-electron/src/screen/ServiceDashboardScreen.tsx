import { useState } from 'react';
import { useServices } from '../shared/context/ServicesContext';
import './ServiceDashboard.css'; // Vamos criar este arquivo para estilização

function ServiceDashboard(): React.JSX.Element {
  // Graças ao hook tipado, todos os valores abaixo são inferidos
  const { services, toggleServiceStatus, syncService, updateCheckpoint } = useServices();

  // Tipamos o parâmetro 'status'
  const getStatusClass = (status: ServiceStatus): string => {
    switch (status) {
      case 'Iniciado':
        return 'status-iniciado';
      case 'Sincronizando':
        return 'status-sincronizando';
      case 'Parado':
      default:
        return 'status-parado';
    }
  };

  return (
    <div className="dashboard-container">
      <table className="services-table">
        <thead>
          <tr>
            <th>Nome</th>
            <th>Status</th>
            <th>Última Execução</th>
            <th>Data Checkpoint</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {services.map((service) => (
            <tr key={service.id}>
              <td>{service.name}</td>
              <td>
                <span className={`status-badge ${getStatusClass(service.status)}`}>
                  {service.status}
                </span>
              </td>
              <td>{service.lastExecution || 'N/A'}</td>
              <td>
                <input
                  type="date"
                  className="checkpoint-datepicker"
                  // O valor de um input DEVE ser string, não null.
                  value={service.checkpoint || ''}
                  // O 'e' aqui é inferido como React.ChangeEvent<HTMLInputElement>
                  onChange={(e) => updateCheckpoint(service.id, e.target.value)}
                />
              </td>
              <td className="actions-cell">
                <button
                  className="btn btn-toggle"
                  onClick={() => toggleServiceStatus(service.id)}
                  disabled={service.status === 'Sincronizando'}
                >
                  {service.status === 'Parado' ? 'Iniciar' : 'Parar'}
                </button>
                <button
                  className="btn btn-sync"
                  onClick={() => syncService(service.id)}
                  disabled={service.status === 'Sincronizando'}
                >
                  Sincronizar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ServiceDashboard;