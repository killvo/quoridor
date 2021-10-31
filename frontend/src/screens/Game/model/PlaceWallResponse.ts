import { Orientation } from '@screens/Game/model/Orientation';

export interface IPlaceWallResponse {
    id: string;
    x: number;
    y: number;
    orientation: Orientation;
    wallsAmount: number;
}
