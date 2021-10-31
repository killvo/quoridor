import React, {useEffect, useState} from 'react';
import classNames from 'classnames';
import {Orientation} from '@screens/Game/model/Orientation';
import {IBindingCallback1} from '@models/Callbacks';
import {IPlaceWallRequest} from '@screens/Game/model/PlaceWallRequest';
import styles from './styles.module.scss';

export interface IWallPointerProps {
    x: number;
    y: number;
    wallOrientation: Orientation;
    handlePlaceWall: IBindingCallback1<IPlaceWallRequest>;
    walls: object;
}

const WallPointer: React.FC<IWallPointerProps> = (
  {
    x, y, wallOrientation, handlePlaceWall, walls
  }
) => {
  const [placedWall, setPlacedWall] = useState();

  useEffect(
    () => {
      const key = `${x}${y}`;
      if (walls && walls[key]) {
        setPlacedWall(walls[key]);
      }
    },
    [walls, x, y]
  );

  return (
    <div
      className={classNames(
        styles.wall_pointer,
        wallOrientation === Orientation.VERTICAL ? styles.vertical_wall : styles.horizontal_wall,
        placedWall && (placedWall === 'HORIZONTAL') && styles.placed_horizontal,
        placedWall && (placedWall === 'VERTICAL') && styles.placed_vertical
      )}
      onClick={() => handlePlaceWall({ x, y, orientation: wallOrientation, id: undefined })}
    />
  );
};

export default WallPointer;
