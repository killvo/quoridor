import { callApi } from '@helpers/api.helper';
import { IMakeMoveRequest } from '@screens/Game/model/MakeMoveRequest';
import { IPlaceWallRequest } from '@screens/Game/model/PlaceWallRequest';

export const gameService = {
  startTwoPeopleGame: () => callApi({
    endpoint: '/api/init/start_two_people',
    method: 'POST'
  }),
  startWithBotGame: () => callApi({
    endpoint: '/api/init/start_with_bot',
    method: 'POST'
  }),
  restartGame: () => callApi({
    endpoint: '/api/init/restart',
    method: 'POST'
  }),
  stopGame: () => callApi({
    endpoint: '/api/init/stop',
    method: 'POST'
  }),
  makeMove: (requestData: IMakeMoveRequest) => callApi({
    endpoint: '/api/move/make_move',
    method: 'POST',
    requestData
  }),
  placeWall: (requestData: IPlaceWallRequest) => callApi({
    endpoint: '/api/move/place_wall',
    method: 'POST',
    requestData
  }),
  getBotMove: () => callApi({
    endpoint: '/api/move/bot_move',
    method: 'POST'
  })
};

