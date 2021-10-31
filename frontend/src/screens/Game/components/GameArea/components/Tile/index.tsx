import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { IBindingCallback1 } from '@models/Callbacks';
import { IPlayerWithPosition } from '@screens/Game/model/PlayerWithPosition';
import { extractFirstPlayer, extractSecondPlayer } from '@screens/Game/reducers';
import styles from './styles.module.scss';
import classNames from 'classnames';
import { IMakeMoveRequest } from '@screens/Game/model/MakeMoveRequest';

interface IState {
  firstPlayer: IPlayerWithPosition;
  secondPlayer: IPlayerWithPosition;
}

export interface ITileProps extends IState{
    x: number;
    y: number;
    onPlayerSelect: IBindingCallback1<IPlayerWithPosition>;
    handleMakeMove: IBindingCallback1<IMakeMoveRequest>;
    selectedPlayer: IPlayerWithPosition;
}

const Tile: React.FC<ITileProps> = (
  {
    x, y, onPlayerSelect, firstPlayer, secondPlayer,
    selectedPlayer, handleMakeMove
  }
) => {
  const [player, setPlayer] = useState<IPlayerWithPosition>();
  const [playerName, setPlayerName] = useState<string>();

  const clearTile = () => {
    setPlayer(undefined);
    setPlayerName(undefined);
  };

  useEffect(
    () => {
      if (firstPlayer && secondPlayer) {
        if ((x === firstPlayer.x) && (y === firstPlayer.y)) {
          setPlayer(firstPlayer);
          setPlayerName('firstPlayer');
        } else if ((x === secondPlayer.x) && (y === secondPlayer.y)) {
          setPlayer(secondPlayer);
          setPlayerName('secondPlayer');
        } else {
          clearTile();
        }
      } else {
        clearTile();
      }
    },
    [firstPlayer, secondPlayer, x, y]
  );

  const onTileClick = () => {
    if (player) {
      onPlayerSelect(player);
    } else {
      handleMakeMove({ x, y, id: undefined });
    }
  };

  return (
    <div
      className={classNames(
        styles.tile,
        playerName === 'firstPlayer' && styles.first_player,
        playerName === 'secondPlayer' && styles.second_player
      )}
      onClick={onTileClick}
    />
  );
};

const mapStateToProps = state => ({
  firstPlayer: extractFirstPlayer(state),
  secondPlayer: extractSecondPlayer(state)
});

export default connect(mapStateToProps, null)(Tile);

