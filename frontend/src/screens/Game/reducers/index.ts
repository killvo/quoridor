import { combineReducers } from 'redux';
import { gameReducer } from '@screens/Game/containers/GamePage/reducer';
import { RootState } from '@root/store';

export default combineReducers({
  game: gameReducer
});

const game = (state: RootState) => state.game.game;

// export const extractGameResults = state => game(state).results;
// export const extractBoard = state => game(state).board;
// export const extractLoading = state => game(state).loading;
// export const extractStatus = state => game(state).status;
// export const extractGameStarted = state => game(state).gameStarted;
// export const extractGame = state => game(state);
