import React from 'react';
import { IBindingCallback1 } from '@models/Callbacks';
import { IMakeMoveRequest } from '@screens/Game/model/MakeMoveRequest';
import { IPlaceWallRequest } from '@screens/Game/model/PlaceWallRequest';
import WallsIndicator from '@screens/Game/components/WallsIndicator';
import { IPlayer } from '@screens/Game/model/Player';
import styles from './styles.module.scss';

export interface IBoardProps {
  makeMove: IBindingCallback1<IMakeMoveRequest>;
  placeWall: IBindingCallback1<IPlaceWallRequest>;
  firstPlayer: IPlayer;
  secondPlayer: IPlayer;
}

const Board: React.FC<IBoardProps> = (
  {
    makeMove, placeWall, firstPlayer, secondPlayer
  }
) => (
  <div className={styles.container}>
    <div className={styles.walls_container}>
      <WallsIndicator availableWallsAmount={secondPlayer?.availableWallsAmount} />
    </div>
    <div className={styles.game_area}>
      game area here
    </div>
    <div className={styles.walls_container}>
      <WallsIndicator availableWallsAmount={firstPlayer?.availableWallsAmount} />
    </div>
  </div>
);

export default Board;
