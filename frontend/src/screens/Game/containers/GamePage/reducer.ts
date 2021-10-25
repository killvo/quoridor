import { Routine } from 'redux-saga-routines';
import {
  startTwoPeopleGameRoutine,
  startWithBotGameRoutine
} from '@screens/Game/routines';
import { IStartTwoPeopleResponse } from '@screens/Game/model/StartTwoPeopleResponse';
import { IStartWithBotResponse } from '@screens/Game/model/StartWithBotResponse';
import { IPlayerWithPosition } from '@screens/Game/model/PlayerWithPosition';

export interface IGameReducerState {
  firstPlayer: IPlayerWithPosition;
  secondPlayer: IPlayerWithPosition;
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
        firstPlayer: {
          player: payload.firstPlayer,
          x: 4,
          y: 8
        },
        secondPlayer: {
          player: payload.secondPlayer,
          x: 4,
          y: 0
        }
      };
    case startWithBotGameRoutine.SUCCESS:
      const payload2 = (action.payload as IStartWithBotResponse);
      return {
        ...state,
        firstPlayer: {
          player: payload2.firstPlayer,
          x: 4,
          y: 8
        },
        secondPlayer: {
          player: payload2.botPlayer,
          x: 4,
          y: 0
        }
      };
    default:
      return state;
  }
};
