import { FormEvent } from 'react';
import {useConfig} from '../shared/context/useConfig';

// Hook e componentes de UI
import { Input } from '../shared/components/common/Input';
import Button from '../shared/components/common/Button';

const ConfiguracaoScreen = () => {
  // 1. Usa o hook para obter tudo o que precisa: estado, funções e status
  const { config, setConfig, saveConfig, loadConfig, loading, error } = useConfig();

  // 2. O manipulador de change agora chama o setConfig do hook
  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setConfig(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };

  // 3. O manipulador de submit se torna muito mais simples
  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    await saveConfig(config); // Apenas chama a função de salvar do hook
    if (!error) {
        alert('Configurações salvas e enviadas com sucesso!');
    }
  };

  // 4. A URL agora usa o prefixo fixo 'jdbc:firebirdsql://'
  const jdbcUrl = `jdbc:firebirdsql://${config.dbHost}:${config.dbPort}/${config.dbPath}`;

  return (
    <div className="p-8 max-w-4xl mx-auto bg-white rounded-lg shadow-md mt-10">
      <h1 className="text-3xl font-bold text-gray-800 mb-6 border-b pb-4">
        Configurações do Sistema
      </h1>
      
      <form onSubmit={handleSubmit}>
        <div className="mb-8">
          <h2 className="text-xl font-semibold text-gray-700 mb-4">Banco de Dados</h2>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
            <Input label="IP ou Host" id="dbHost" name="dbHost" value={config.dbHost} onChange={handleChange} />
            <Input label="Porta" id="dbPort" name="dbPort" value={config.dbPort} onChange={handleChange} />
            <Input label="Caminho/Database" id="dbPath" name="dbPath" value={config.dbPath} onChange={handleChange} />
          </div>
          {/* 5. Novos campos para username e password */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
            <Input label="Usuário" id="username" name="username" value={config.username} onChange={handleChange} />
            <Input label="Senha" id="password" name="password" type="password" value={config.password} onChange={handleChange} />
          </div>
          <Input label="URL JDBC (Gerada)" id="jdbcUrl" name="jdbcUrl" value={jdbcUrl} readOnly />
        </div>
        
        <div className="mb-8">
          <h2 className="text-xl font-semibold text-gray-700 mb-4">Geral</h2>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <Input label="Código do Estabelecimento" id="establishmentCode" name="establishmentCode" value={config.establishmentCode} onChange={handleChange} />
            <Input label="Hora de Sincronização" id="syncTime" name="syncTime" type="time" value={config.syncTime} onChange={handleChange} />
          </div>
        </div>

         <div className="flex items-center justify-end space-x-4 border-t pt-6">
            {/* Mensagem de erro */}
            {error && <p className="text-sm text-red-600 mr-auto">{error}</p>}
            
            <Button 
                type="button" 
                variant="secondary" 
                onClick={loadConfig} 
                disabled={loading}
            >
                Restaurar Salvo
            </Button>
            
            {/* Botão de Salvar */}
            <Button type="submit" disabled={loading}>
                {loading ? 'Salvando...' : 'Salvar Configurações'}
            </Button>
        </div>
      </form>
    </div>
  );
};

export default ConfiguracaoScreen;