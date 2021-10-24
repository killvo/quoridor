import React from 'react';
import Tile from '@screens/Game/components/GameArea/components/Tile';
import styles from './styles.module.scss';
import WallPointer from "@screens/Game/components/GameArea/components/WallPointer";

export interface IGameAreaProps {
  className?: string;
}

const GameArea: React.FC<IGameAreaProps> = (
  {
    className
  }
) => {
  const getTiles = () => {
    const tiles = [];
    for (let x = 0; x < 9; x++) {
      for (let y = 0; y < 9; y++) {
        tiles.push(<Tile x={x} y={y} />);
      }
    }
    return tiles;
  };

  const getWallsPointers = () => {
    const wallsPointers = [];
    for (let x = 0; x < 8; x++) {
      for (let y = 0; y < 8; y++) {
        wallsPointers.push(<WallPointer x={x} y={y} />);
      }
    }
    return wallsPointers;
  };

  return (
    <div className={styles.game_area_container}>
      <div className={styles.tiles}>
        { getTiles() }
      </div>
      <div className={styles.walls_pointers}>
        { getWallsPointers() }
      </div>
    </div>
  );
};

export default GameArea;
