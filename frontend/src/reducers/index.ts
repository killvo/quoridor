import { combineReducers } from 'redux';
import { connectRouter } from 'connected-react-router';
import { reducer as toastr } from 'react-redux-toastr';
/* PlopJS import placeholder. Do not remove */
import game from '@screens/Game/reducers';
import stompClient from './stompClient';

const createRootReducer = history => combineReducers({
  router: connectRouter(history),
  toastr,
  stompClient,
  game
  /* PlopJS reducer placeholder. Do not remove */
});

export default createRootReducer;
