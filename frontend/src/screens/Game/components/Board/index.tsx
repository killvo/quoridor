import React from 'react';
import { IBindingCallback1 } from '@models/Callbacks';
import { IMakeMoveRequest } from '@screens/Game/model/MakeMoveRequest';
import { IPlaceWallRequest } from '@screens/Game/model/PlaceWallRequest';
import styles from './styles.module.scss';

export interface IBoardProps {
  makeMove: IBindingCallback1<IMakeMoveRequest>;
  placeWall: IBindingCallback1<IPlaceWallRequest>;
}

const Board: React.FC<IBoardProps> = (
  {
    makeMove, placeWall
  }
) => (
  <div className={styles.container} />
);

export default Board;
