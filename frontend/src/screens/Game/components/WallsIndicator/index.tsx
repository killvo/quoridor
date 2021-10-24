import React from 'react';
import styles from './styles.module.scss';

export interface IWallsIndicatorProps {
  availableWallsAmount: number;
}

const WallsIndicator: React.FC<IWallsIndicatorProps> = (
  {
    availableWallsAmount
  }
) => {
  const getWalls = () => {
    const walls = [];
    if (!availableWallsAmount) {
      return [];
    }
    for (let i = 0; i < availableWallsAmount; i++) {
      walls[i] = (<div key={i} className={styles.wall} />);
    }
    return walls;
  };

  return (
    <div className={styles.walls_indicator}>
      {getWalls()}
    </div>
  );
};

export default WallsIndicator;
