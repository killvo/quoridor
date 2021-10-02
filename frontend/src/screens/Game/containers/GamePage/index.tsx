import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { IBindingAction } from '@models/Callbacks';
import {
  extractBoard,
  extractGameResults,
  extractGameStarted,
  extractLoading
} from '@screens/Game/reducers';
import { startGameRoutine } from '@screens/Game/routines';
import styles from './styles.module.scss';

export interface IGamePageProps {
  loading: boolean;
  gameStarted: boolean;
  board: any;
  gameResults: any;
  startGame: IBindingAction;
}

const GamePage: React.FC<IGamePageProps> = (
  {
    loading, gameStarted, board, gameResults, startGame
  }
) => (
  <div className={`${styles.container} content_wrapper`}>
    Game content
  </div>
);

const mapStateToProps = state => ({
  loading: extractLoading(state),
  gameStarted: extractGameStarted(state),
  board: extractBoard(state),
  gameResults: extractGameResults(state)
});

const mapDispatchToProps = {
  startGame: startGameRoutine.trigger
};

export default connect(mapStateToProps, mapDispatchToProps)(GamePage);
