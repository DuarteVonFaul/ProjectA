import { useState, useEffect, useCallback } from 'react';
import Urls from '../utils/urls';


type ConfigState = {
  dbHost: string;
  dbPort: string;
  dbPath: string;
  username: string;
  password: string;
  establishmentCode: string;
  environment: 'Homolog' | 'Develop' | 'Mock';
  syncTime: string; 
};

const STORAGE_KEY = 'app-config';

export const useConfig = () => {
  const [config, setConfig] = useState<ConfigState>({
    dbHost: '000.000.0.0',
    dbPort: '3050',
    dbPath: '/path/to/database.fdb',
    username: 'sysdba',
    password: 'masterkey',
    establishmentCode: '999',
    environment: 'Develop',
    syncTime: '03:00', 
  });

  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  const loadConfig = useCallback(() => {
    try {
      const savedConfig = localStorage.getItem(STORAGE_KEY);
      if (savedConfig) {
        const parsedConfig = JSON.parse(savedConfig);
        setConfig(prevConfig => ({ ...prevConfig, ...parsedConfig })); 
        console.log('Configurações carregadas do localStorage.', parsedConfig);
        return parsedConfig;
      }
    } catch (err) {
      console.error("Falha ao carregar configuração do localStorage", err);
    }
  }, []);

  useEffect(() => {
    loadConfig();
  }, [loadConfig]);

  const saveConfig = async (newConfig: ConfigState) => {
    setLoading(true);
    setError(null);
    try {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(newConfig));
      setConfig(newConfig);

      const requestBody = {
        url: `jdbc:firebirdsql://${newConfig.dbHost}:${newConfig.dbPort}/${newConfig.dbPath}`,
        username: newConfig.username,
        password: newConfig.password,
        codEstab: newConfig.establishmentCode,
      };

      const response = await fetch(Urls.databaseConfig, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(requestBody),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Falha ao comunicar com a API');
      }

      console.log('API respondeu com sucesso:', await response.text());
    } catch (err: any) {
      console.error("Erro ao salvar configuração:", err);
      setError(err.message || 'Ocorreu um erro desconhecido.');
    } finally {
      setLoading(false);
    }
  };

  return { config, setConfig, saveConfig, loadConfig, loading, error };
};