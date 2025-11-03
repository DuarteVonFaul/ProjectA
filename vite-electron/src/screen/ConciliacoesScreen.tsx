import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';

// Componentes, Tipos e o hook de Lojas
import { Input } from '../shared/components/common/Input';
import { Select } from '../shared/components/common/Select';
import Button from '../shared/components/common/Button';
import StatusIndicator from '../shared/components/common/StatusIndicator';
import Urls from '../shared/utils/urls';

// Tipos para a resposta da API de detalhes
import { Reconciliation, ReconciliationStatus } from '../shared/models/reconciliation';
import { ApiResponse } from '../shared/models/pageResponse';
import { useLojas } from '../shared/context/useLojas';

const ConciliacoesScreen: React.FC = () => {
    // --- Estados do Componente ---
    const [reconciliations, setReconciliations] = useState<Reconciliation[]>([]);
    const [pageNumber, setPageNumber] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [loading, setLoading] = useState(false);

    // Usa o hook para buscar as lojas. Renomeamos `loading` e `error` para evitar conflitos.
    const { lojas, loading: lojasLoading, error: lojasError } = useLojas();

    const [filters, setFilters] = useState({
        dataConciliada: new Date().toLocaleDateString('sv-SE'), // Formato YYYY-MM-DD local
        loja: '', // Vazio significa "Todas"
        status: -1, // -1 significa "Todos"
    });

    // --- Mapeamento e Funções Auxiliares ---
    const mapApiStatusToComponentStatus = (status: number): ReconciliationStatus => {
        switch (status) {
            case 0: return 'success';
            case 1: return 'warning';
            default: return 'error';
        }
    };

    const formatDateTime = (dateTimeString: string) => {
        if (!dateTimeString) return '-';
        return new Date(dateTimeString).toLocaleString('pt-BR');
    };

    // --- Lógica de API ---
    const fetchReconciliations = useCallback(async (page: number) => {
        setLoading(true);
        try {
            const requestBody = {
                dataConciliada: filters.dataConciliada,
                loja: filters.loja === '' ? null : filters.loja,
                status: Number(filters.status),
            };
            const response = await axios.post<ApiResponse>(
                Urls.reconciliationPage(page),
                requestBody
            );
            
            const mappedContent = response.data.content.map(item => ({
                ...item,
                status: mapApiStatusToComponentStatus(Number(item.status)),
            }));

            setReconciliations(mappedContent);
            setTotalPages(response.data.totalPages);
            setPageNumber(response.data.number);

        } catch (error) {
            console.error("Erro ao buscar conciliações:", error);
            setReconciliations([]);
            setTotalPages(0);
        } finally {
            setLoading(false);
        }
    }, [filters]);

    // --- Efeitos ---
    useEffect(() => {
        // Busca os dados sempre que a função fetchReconciliations (e seus filtros) mudam.
        fetchReconciliations(0);
    }, [fetchReconciliations]);

    // --- Handlers de Eventos ---
    const handleFilterChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setFilters(prevFilters => ({
            ...prevFilters,
            [name]: value,
        }));
    };

    const handleListarClick = () => {
        setPageNumber(0);
        fetchReconciliations(0);
    };

    const handlePageChange = (newPage: number) => {
        fetchReconciliations(newPage);
    };

    return (
        <div className="p-4 md:p-8 bg-gray-50 min-h-full">
            <div className="bg-white p-6 rounded-lg shadow-md">
                {/* Filtros */}
                <div className="grid grid-cols-1 md:grid-cols-4 gap-4 items-end mb-6">
                    <Input
                        type="date"
                        label="Data Conciliada"
                        name="dataConciliada"
                        value={filters.dataConciliada}
                        onChange={handleFilterChange}
                    />
                    <Select
                        label="Loja"
                        name="loja"
                        value={filters.loja}
                        onChange={handleFilterChange}
                        disabled={lojasLoading}
                    >
                        <option value="">Todas as Lojas</option>
                        {lojasError && <option disabled>Erro ao carregar lojas</option>}
                        {lojas.map(loja => (
                            <option key={loja.codigo} value={loja.codigo}>
                                {`${loja.codigo}: ${loja.nome}`}
                            </option>
                        ))}
                    </Select>
                    <Select
                        label="Status"
                        name="status"
                        value={filters.status}
                        onChange={handleFilterChange}
                    >
                        <option value={-1}>Todos</option>
                        <option value={0}>Sucesso</option>
                        <option value={1}>Pendente</option>
                        <option value={2}>Erro</option>
                    </Select>
                    <div>
                        <Button variant="primary" onClick={handleListarClick} disabled={loading}>
                            {loading ? 'Listando...' : 'Listar'}
                        </Button>
                    </div>
                </div>

                {/* Tabela de Dados */}
                <div className="overflow-x-auto">
                    <table className="min-w-full">
                        <thead className="border-b-2 border-gray-200">
                            <tr>
                                <th className="px-6 py-3 text-left text-xs font-bold text-gray-500 uppercase tracking-wider">Cod.</th>
                                <th className="px-6 py-3 text-left text-xs font-bold text-gray-500 uppercase tracking-wider">Loja</th>
                                <th className="px-6 py-3 text-left text-xs font-bold text-gray-500 uppercase tracking-wider">Caixa</th>
                                <th className="px-6 py-3 text-left text-xs font-bold text-gray-500 uppercase tracking-wider">Detalhes</th>
                                <th className="px-6 py-3 text-left text-xs font-bold text-gray-500 uppercase tracking-wider">Data Conciliada</th>
                                <th className="px-6 py-3 text-left text-xs font-bold text-gray-500 uppercase tracking-wider">Data Hora Conciliação</th>
                                <th className="px-6 py-3 text-left text-xs font-bold text-gray-500 uppercase tracking-wider">Status</th>
                            </tr>
                        </thead>
                        <tbody className="bg-white">
                            {loading ? (
                                <tr>
                                    <td colSpan={7} className="text-center py-8 text-gray-500">Carregando...</td>
                                </tr>
                            ) : reconciliations.length > 0 ? (
                                reconciliations.map((item) => (
                                    <tr key={item.id} className="border-b border-gray-200 hover:bg-gray-50">
                                        <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{item.id}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{item.loja}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{item.caixa}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">{item.detalhe}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">{item.dataConciliada}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">{formatDateTime(item.dataConciliacao)}</td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                                            <StatusIndicator status={item.status as ReconciliationStatus} />
                                        </td>
                                    </tr>
                                ))
                            ) : (
                                <tr>
                                    <td colSpan={7} className="text-center py-8 text-gray-500">Nenhum registro encontrado.</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>

                {/* Controles de Paginação */}
                {totalPages > 0 && !loading && (
                    <div className="flex justify-between items-center mt-4">
                        <Button onClick={() => handlePageChange(pageNumber - 1)} disabled={pageNumber <= 0}>
                            Anterior
                        </Button>
                        <span className="text-sm text-gray-700">
                            Página {pageNumber + 1} de {totalPages}
                        </span>
                        <Button onClick={() => handlePageChange(pageNumber + 1)} disabled={pageNumber >= totalPages - 1}>
                            Próxima
                        </Button>
                    </div>
                )}
            </div>
        </div>
    );
};

export default ConciliacoesScreen;