import { Reconciliation } from "./reconciliation";


export interface ApiResponse {
    content: Reconciliation[];
    totalPages: number;
    totalElements: number;
    number: number;
}