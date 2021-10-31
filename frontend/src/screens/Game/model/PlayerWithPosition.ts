import { IPlayer } from '@screens/Game/model/Player';

export interface IPlayerWithPosition {
    player: IPlayer;
    x: number;
    y: number;
}
