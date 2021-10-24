import React, { useState } from 'react';
import { connect } from 'react-redux';
import { IBindingAction, IBindingCallback1 } from '@models/Callbacks';
import {
  makeMoveRoutine,
  placeWallRoutine,
  restartGameRoutine,
  startTwoPeopleGameRoutine,
  startWithBotGameRoutine,
  stopGameRoutine
} from '@screens/Game/routines';
import MenuPanel from '@screens/Game/components/MenuPanel';
import { IMakeMoveRequest } from '@screens/Game/model/MakeMoveRequest';
import { IPlaceWallRequest } from '@screens/Game/model/PlaceWallRequest';
import Board from '@screens/Game/components/Board';
import ControlsMenu from '@screens/Game/components/ControlsMenu';
import { Orientation } from '@screens/Game/model/Orientation';
import { IPlayer } from '@screens/Game/model/Player';
import styles from './styles.module.scss';

export interface IGamePageProps extends IActions, IState {
}

interface IActions {
  startTwoPeopleGame: IBindingAction;
  startWithBotGame: IBindingAction;
  stopGame: IBindingAction;
  restartGame: IBindingAction;
  makeMove: IBindingCallback1<IMakeMoveRequest>;
  placeWall: IBindingCallback1<IPlaceWallRequest>;
}

interface IState {
  firstPlayer: IPlayer;
  secondPlayer: IPlayer;
}

const GamePage: React.FC<IGamePageProps> = (
  {
    startTwoPeopleGame, startWithBotGame, stopGame, restartGame, makeMove, placeWall, firstPlayer, secondPlayer
  }
) => {
  const [wallOrientation, setWallOrientation] = useState<Orientation>();

  const handleToggleWallOrientation = () => {
    const newOrientation = wallOrientation === Orientation.HORIZONTAL ? Orientation.VERTICAL : Orientation.HORIZONTAL;
    setWallOrientation(newOrientation);
  };

  return (
    <div className={`${styles.container} content_wrapper`}>
      <MenuPanel
        startTwoPeopleGame={startTwoPeopleGame}
        startWithBotGame={startWithBotGame}
        stopGame={stopGame}
        restartGame={restartGame}
      />
      <Board
        makeMove={makeMove}
        placeWall={placeWall}
        firstPlayer={firstPlayer}
        secondPlayer={secondPlayer}
      />
      <ControlsMenu
        toggleWallOrientation={handleToggleWallOrientation}
        wallOrientation={wallOrientation}
      />
    </div>
  );
};

const mapStateToProps = state => ({
  // loading: extractLoading(state),
  // gameStarted: extractGameStarted(state),
  // board: extractBoard(state),
  // gameResults: extractGameResults(state),
  // status: extractStatus(state)
});

const mapDispatchToProps = {
  startTwoPeopleGame: startTwoPeopleGameRoutine.trigger,
  startWithBotGame: startWithBotGameRoutine.trigger,
  stopGame: stopGameRoutine.trigger,
  restartGame: restartGameRoutine.trigger,
  makeMove: makeMoveRoutine.trigger,
  placeWall: placeWallRoutine.trigger
};

export default connect(mapStateToProps, mapDispatchToProps)(GamePage);
