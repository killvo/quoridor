import { Routine } from 'redux-saga-routines';
import { createReducer, PayloadAction } from '@reduxjs/toolkit';
import {
  startTwoPeopleGameRoutine,
  startWithBotGameRoutine,
  stopGameRoutine
} from '@screens/Game/routines';
import { IStartTwoPeopleResponse } from '@screens/Game/model/StartTwoPeopleResponse';
import { IPlayer } from '@screens/Game/model/Player';
import { IStartWithBotResponse } from '@screens/Game/model/StartWithBotResponse';

export interface IGameReducerState {
  firstPlayer: IPlayer;
  secondPlayer: IPlayer;
}

const initialState: IGameReducerState = {
  firstPlayer: undefined,
  secondPlayer: undefined
};

// export const gameReducer = createReducer(initialState, {
//   [startTwoPeopleGameRoutine.SUCCESS]: (state, { payload }: PayloadAction<IStartTwoPeopleResponse>) => {
//     state.response = payload;
//   },
//   [startWithBotGameRoutine.SUCCESS]: (state, { payload }: PayloadAction<IStartWithBotResponse>) => {
//     state.firstPlayer = payload.firstPlayer;
//     state.secondPlayer = payload.botPlayer;
//   },
//   [stopGameRoutine.SUCCESS]: state => {
//     state.firstPlayer = undefined;
//     state.secondPlayer = undefined;
//   }
// });

export const gameReducer = (state = initialState, action: Routine<any>) => {
  switch (action.type) {
    case startTwoPeopleGameRoutine.SUCCESS:
      const payload = (action.payload as IStartTwoPeopleResponse);
      return {
        ...state,
        firstPlayer: payload.firstPlayer,
        secondPlayer: payload.secondPlayer
      };
    default:
      return state;
  }
};
