import { Orientation } from '@screens/Game/model/Orientation';

export interface IPlaceWallRequest {
    id: string;
    x: number;
    y: number;
    orientation: Orientation;
}
