import { Routine } from 'redux-saga-routines';
import {
  placeWallRoutine,
  startTwoPeopleGameRoutine,
  startWithBotGameRoutine,
  stopGameRoutine
} from '@screens/Game/routines';
import { IStartTwoPeopleResponse } from '@screens/Game/model/StartTwoPeopleResponse';
import { IStartWithBotResponse } from '@screens/Game/model/StartWithBotResponse';
import { IPlayerWithPosition } from '@screens/Game/model/PlayerWithPosition';
import { IPlaceWallResponse } from '@screens/Game/model/PlaceWallResponse';

export interface IGameReducerState {
  firstPlayer: IPlayerWithPosition;
  secondPlayer: IPlayerWithPosition;
  walls: object;
  lastPlayerId: string;
}

const initialState: IGameReducerState = {
  firstPlayer: undefined,
  secondPlayer: undefined,
  walls: undefined,
  lastPlayerId: undefined
};

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
    case stopGameRoutine.SUCCESS:
      return {
        ...state,
        firstPlayer: undefined,
        secondPlayer: undefined
      };
    case placeWallRoutine.SUCCESS:
      const placeWallResponse = (action.payload as IPlaceWallResponse);
      const playerThatPlaced = state.firstPlayer.player.id === placeWallResponse.id
        ? 'firstPlayer'
        : 'secondPlayer';
      const updatedPlayer = playerThatPlaced === 'firstPlayer'
        ? { ...state.firstPlayer.player, availableWallsAmount: placeWallResponse.wallsAmount }
        : { ...state.secondPlayer.player, availableWallsAmount: placeWallResponse.wallsAmount };

      const updatedState = {
        ...state,
        walls: {
          ...state.walls,
          [`${placeWallResponse.x}${placeWallResponse.y}`]: placeWallResponse.orientation
        },
        lastPlayerId: placeWallResponse.id
      };
      updatedState[playerThatPlaced] = { ...state[playerThatPlaced], player: updatedPlayer };

      return updatedState;
    default:
      return state;
  }
};
