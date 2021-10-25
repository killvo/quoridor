import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { IBindingCallback1 } from '@models/Callbacks';
import { IPlayerWithPosition } from '@screens/Game/model/PlayerWithPosition';
import { extractFirstPlayer, extractSecondPlayer } from '@screens/Game/reducers';
import styles from './styles.module.scss';
import classNames from "classnames";

interface IState {
  firstPlayer: IPlayerWithPosition;
  secondPlayer: IPlayerWithPosition;
}

export interface ITileProps extends IState{
    x: number;
    y: number;
    onPlayerSelect: IBindingCallback1<IPlayerWithPosition>;
}

const Tile: React.FC<ITileProps> = (
  {
    x, y, onPlayerSelect, firstPlayer, secondPlayer
  }
) => {
  const [player, setPlayer] = useState<IPlayerWithPosition>();

  useEffect(
    () => {
      if (firstPlayer && secondPlayer) {
        if ((x === firstPlayer.x) && (y === firstPlayer.y)) {
          setPlayer(firstPlayer);
        }
        if ((x === secondPlayer.x) && (y === secondPlayer.y)) {
          setPlayer(secondPlayer);
        }
      }
    },
    [firstPlayer, secondPlayer, x, y]
  );

  const handlePlayerSelect = () => {
    if (player) {
      onPlayerSelect(player);
    }
  };

  return (
    <div
      className={classNames(
        styles.tile,
        player && styles.player_on_tile
      )}
      onClick={handlePlayerSelect}
    />
  );
};

const mapStateToProps = state => ({
  firstPlayer: extractFirstPlayer(state),
  secondPlayer: extractSecondPlayer(state)
});

export default connect(mapStateToProps, null)(Tile);

