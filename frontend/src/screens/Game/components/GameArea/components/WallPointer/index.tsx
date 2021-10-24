import React from 'react';
import styles from './styles.module.scss';

export interface IWallPointerProps {
    x: number;
    y: number;
}

const WallPointer: React.FC<IWallPointerProps> = (
  {
    x, y
  }
) => (
  <div className={styles.wall_pointer} />
);

export default WallPointer;
