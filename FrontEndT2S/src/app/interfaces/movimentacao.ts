import { Conteiner } from "./conteiner";

export interface Movimentacao {
    id: number,
    tipo: string,
    dt_hr_inicio: string,
    dt_hr_fim: string,
    conteiner: Conteiner
}
