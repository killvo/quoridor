import React from 'react';
import Tile from '@screens/Game/components/GameArea/components/Tile';
import WallPointer from '@screens/Game/components/GameArea/components/WallPointer';
import { Orientation } from '@screens/Game/model/Orientation';
import { IBindingCallback1 } from '@models/Callbacks';
import { IPlaceWallRequest } from '@screens/Game/model/PlaceWallRequest';
import { IPlayer } from '@screens/Game/model/Player';
import styles from './styles.module.scss';
import {IPlayerWithPosition} from "@screens/Game/model/PlayerWithPosition";

export interface IGameAreaProps {
  wallOrientation: Orientation;
  handlePlaceWall: IBindingCallback1<IPlaceWallRequest>;
  onPlayerSelect: IBindingCallback1<IPlayerWithPosition>;
}

const GameArea: React.FC<IGameAreaProps> = (
  {
    wallOrientation, handlePlaceWall, onPlayerSelect
  }
) => {
  const getTiles = () => {
    const tiles = [];
    for (let x = 0; x < 9; x++) {
      for (let y = 0; y < 9; y++) {
        tiles.push(
          <Tile
            onPlayerSelect={onPlayerSelect}
            x={x}
            y={y}
          />
        );
      }
    }
    return tiles;
  };

  const getWallsPointers = () => {
    const wallsPointers = [];
    for (let x = 0; x < 8; x++) {
      for (let y = 0; y < 8; y++) {
        wallsPointers.push(
          <WallPointer
            x={x}
            y={y}
            wallOrientation={wallOrientation}
            handlePlaceWall={handlePlaceWall}
          />
        );
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
