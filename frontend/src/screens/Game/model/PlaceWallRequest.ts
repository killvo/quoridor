import { Orientation } from '@screens/Game/model/Orientation';

export interface IPlaceWallRequest {
    playerId: string;
    xCorner: number;
    yCorner: number;
    orientation: Orientation;
}
