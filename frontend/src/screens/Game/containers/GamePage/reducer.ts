import { Routine } from 'redux-saga-routines';
import { startGameRoutine } from '@screens/Game/routines';
import { IGameStartResponse } from '@screens/Game/model/GameStartResponse';

export interface IGameReducerState {
  loading: boolean;
  gameStarted: boolean;
  board: any;
  results: any;
  status: string;
}

const initialState: IGameReducerState = {
  loading: false,
  gameStarted: false,
  board: {},
  results: {},
  status: ''
};

export const gameReducer = (state = initialState, action: Routine<any>) => {
  switch (action.type) {
    case startGameRoutine.TRIGGER:
      return {
        ...state,
        loading: true
      };
    case startGameRoutine.SUCCESS:
      const payload = (action.payload as IGameStartResponse);
      return {
        ...state,
        gameStarted: true,
        loading: false,
        status: payload.status
      };
    case startGameRoutine.FAILURE:
      return {
        ...state,
        loading: false
      };
    default:
      return state;
  }
};
