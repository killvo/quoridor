import { callApi } from '@helpers/api.helper';

export const startTwoPeopleGame = async () => callApi({
  endpoint: '/api/init/start_two_people',
  method: 'POST'
});
