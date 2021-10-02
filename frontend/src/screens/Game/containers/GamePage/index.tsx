import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { Button } from 'semantic-ui-react';
import { IBindingCallback1 } from '@models/Callbacks';
import {
  extractBoard,
  extractGameResults,
  extractGameStarted,
  extractLoading, extractStatus
} from '@screens/Game/reducers';
import { startGameRoutine } from '@screens/Game/routines';
import { IGameStartRequest } from '@screens/Game/model/GameStartRequest';
import styles from './styles.module.scss';

export interface IGamePageProps {
  loading: boolean;
  gameStarted: boolean;
  board: any;
  gameResults: any;
  startGame: IBindingCallback1<IGameStartRequest>;
  status: string;
}

const GamePage: React.FC<IGamePageProps> = (
  {
    loading, gameStarted, board, gameResults, startGame, status
  }
) => {
  const handleGameStart = () => {
    startGame({
      firstPlayerName: 'testPlayer1',
      secondPlayerName: 'testPlayer2'
    });
  };

  return (
    <div className={`${styles.container} content_wrapper`}>
      <div>
        Game loading:
        { loading ? 'true' : 'false' }
      </div>
      <div>
        Game started:
        { gameStarted ? 'true' : 'false' }
      </div>
      <div className={styles.status}>
        Game status:
        {'\n'}
        { status }
      </div>
      <div>
        <Button onClick={handleGameStart}>Start Game</Button>
      </div>
    </div>
  );
};

const mapStateToProps = state => ({
  loading: extractLoading(state),
  gameStarted: extractGameStarted(state),
  board: extractBoard(state),
  gameResults: extractGameResults(state),
  status: extractStatus(state)
});

const mapDispatchToProps = {
  startGame: startGameRoutine.trigger
};

export default connect(mapStateToProps, mapDispatchToProps)(GamePage);
