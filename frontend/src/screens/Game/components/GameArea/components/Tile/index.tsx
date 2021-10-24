import React from 'react';
import { IPlayer } from '@screens/Game/model/Player';
import styles from './styles.module.scss';

export interface ITileProps {
    x: number;
    y: number;
    player?: IPlayer;
}

const Tile: React.FC<ITileProps> = (
  {
    x, y, player
  }
) => (
  <div className={styles.tile} />
);

export default Tile;
