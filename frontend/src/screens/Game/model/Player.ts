import { Role } from '@screens/Game/model/Role';

export interface IPlayer {
    id: string;
    availableWallsAmount: number;
    role: Role;
}
