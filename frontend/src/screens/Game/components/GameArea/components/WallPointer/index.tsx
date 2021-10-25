import React, {useEffect, useState} from 'react';
import classNames from 'classnames';
import { Orientation } from '@screens/Game/model/Orientation';
import { IBindingCallback1 } from '@models/Callbacks';
import { IPlaceWallRequest } from '@screens/Game/model/PlaceWallRequest';
import styles from './styles.module.scss';

export interface IWallPointerProps {
    x: number;
    y: number;
    wallOrientation: Orientation;
    handlePlaceWall: IBindingCallback1<IPlaceWallRequest>;
}

const WallPointer: React.FC<IWallPointerProps> = (
  {
    x, y, wallOrientation, handlePlaceWall
  }
) => {
  const [xCorner, setXCorner] = useState<number>(x);
  const [yCorner, setYCorner] = useState<number>(y);

  return (
    <div
      className={classNames(
        styles.wall_pointer,
        wallOrientation === Orientation.VERTICAL ? styles.vertical_wall : styles.horizontal_wall
      )}
      onClick={() => handlePlaceWall({ xCorner, yCorner, orientation: wallOrientation, playerId: 'mock-player-id' })}
    />
  );
};

export default WallPointer;
