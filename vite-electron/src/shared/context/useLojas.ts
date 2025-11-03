// src/hook/useLojas.ts

import { useState, useEffect } from 'react';
import axios from 'axios';

// Define a interface para o objeto de loja, conforme o retorno da API
export interface Loja {
  codigo: string;
  nome: string;
}

export const useLojas = () => {
  // Estado para armazenar a lista de lojas
  const [lojas, setLojas] = useState<Loja[]>([]);
  // Estado para controlar o carregamento
  const [loading, setLoading] = useState<boolean>(true);
  // Estado para armazenar erros
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    // Função assíncrona para buscar os dados
    const fetchLojas = async () => {
      try {
        const response = await axios.get<Loja[]>('http://localhost:8001/int/boavista/lojas');
        setLojas(response.data);
      } catch (err: any) {
        console.error("Erro ao buscar lojas:", err);
        setError("Não foi possível carregar a lista de lojas.");
      } finally {
        setLoading(false);
      }
    };

    // Chama a função de busca quando o hook é montado
    fetchLojas();
  }, []); // O array de dependências vazio garante que isso rode apenas uma vez

  // Retorna o estado para que os componentes possam usá-lo
  return { lojas, loading, error };
};