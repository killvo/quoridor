import React, { useState } from 'react';
import { IBindingCallback1 } from '@models/Callbacks';
import { IMakeMoveRequest } from '@screens/Game/model/MakeMoveRequest';
import { IPlaceWallRequest } from '@screens/Game/model/PlaceWallRequest';
import WallsIndicator from '@screens/Game/components/WallsIndicator';
import { IPlayer } from '@screens/Game/model/Player';
import GameArea from '@screens/Game/components/GameArea';
import { Orientation } from '@screens/Game/model/Orientation';
import styles from './styles.module.scss';
import { IPlayerWithPosition } from '@screens/Game/model/PlayerWithPosition';

export interface IBoardProps {
  makeMove: IBindingCallback1<IMakeMoveRequest>;
  placeWall: IBindingCallback1<IPlaceWallRequest>;
  firstPlayer: IPlayerWithPosition;
  secondPlayer: IPlayerWithPosition;
  wallOrientation: Orientation;
  lastPlayerId: string;
}

const Board: React.FC<IBoardProps> = (
  {
    makeMove, placeWall, firstPlayer, secondPlayer, wallOrientation,
    lastPlayerId
  }
) => {
  const [selectedPlayer, setSelectedPlayer] = useState<IPlayerWithPosition>();

  const handlePlaceWall = (request: IPlaceWallRequest) => {
    if (selectedPlayer?.player && (selectedPlayer.player.id !== lastPlayerId)) {
      placeWall({ ...request, id: selectedPlayer.player.id });
    }
  };

  const handlePlayerSelect = (player: IPlayerWithPosition) => {
    if (player) {
      setSelectedPlayer(player);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.walls_container}>
        <WallsIndicator availableWallsAmount={secondPlayer?.player ? secondPlayer.player.availableWallsAmount : 0} />
      </div>
      <div className={styles.game_area}>
        <GameArea
          onPlayerSelect={handlePlayerSelect}
          wallOrientation={wallOrientation}
          handlePlaceWall={handlePlaceWall}
        />
      </div>
      <div className={styles.walls_container}>
        <WallsIndicator availableWallsAmount={firstPlayer?.player ? firstPlayer.player.availableWallsAmount : 0} />
      </div>
    </div>
  );
};

export default Board;
