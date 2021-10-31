import { Routine } from 'redux-saga-routines';
import {
  makeMoveRoutine,
  placeWallRoutine,
  startTwoPeopleGameRoutine,
  startWithBotGameRoutine,
  stopGameRoutine
} from '@screens/Game/routines';
import { IStartTwoPeopleResponse } from '@screens/Game/model/StartTwoPeopleResponse';
import { IStartWithBotResponse } from '@screens/Game/model/StartWithBotResponse';
import { IPlayerWithPosition } from '@screens/Game/model/PlayerWithPosition';
import { IPlaceWallResponse } from '@screens/Game/model/PlaceWallResponse';
import { IMakeMoveResponse } from '@screens/Game/model/MakeMoveResponse';

export interface IGameReducerState {
  firstPlayer: IPlayerWithPosition;
  secondPlayer: IPlayerWithPosition;
  walls: object;
  lastPlayerId: string;
  winner: string;
}

const initialState: IGameReducerState = {
  firstPlayer: undefined,
  secondPlayer: undefined,
  walls: undefined,
  lastPlayerId: undefined,
  winner: undefined
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
        secondPlayer: undefined,
        lastPlayerId: undefined,
        walls: undefined,
        winner: undefined
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
    case makeMoveRoutine.SUCCESS:
      const makeMoveResponse = (action.payload as IMakeMoveResponse);
      const playerThatMoved = state.firstPlayer.player.id === makeMoveResponse.id
        ? 'firstPlayer'
        : 'secondPlayer';
      const updatedStateWithMoved = {
        ...state,
        lastPlayerId: makeMoveResponse.id,
        winner: makeMoveResponse.winner
      };
      updatedStateWithMoved[playerThatMoved] = {
        ...state[playerThatMoved],
        x: makeMoveResponse.x,
        y: makeMoveResponse.y
      };

      return updatedStateWithMoved;
    default:
      return state;
  }
};
