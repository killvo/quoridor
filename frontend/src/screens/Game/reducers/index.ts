import { combineReducers } from 'redux';
import { gameReducer } from '@screens/Game/containers/GamePage/reducer';
import { RootState } from '@root/store';

export default combineReducers({
  game: gameReducer
});

const game = (state: RootState) => state.game.game;

export const extractFirstPlayer = state => game(state).firstPlayer;
export const extractSecondPlayer = state => game(state).secondPlayer;
export const extractWalls = state => game(state).walls;
export const extractLastPlayerId = state => game(state).lastPlayerId;
export const extractWinner = state => game(state).winner;
