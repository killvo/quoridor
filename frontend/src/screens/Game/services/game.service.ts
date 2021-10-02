import { callApi } from '@helpers/api.helper';
import { IGameStartRequest } from '@screens/Game/model/GameStartRequest';

export const startGame = async (request: IGameStartRequest) => callApi({
  endpoint: '/api/game/start',
  method: 'POST',
  requestData: {
    ...request
  } as IGameStartRequest
});
