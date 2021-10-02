import { all } from 'redux-saga/effects';
/* PlopJS import placeholder. Do not remove */
import gameSagas from '@screens/Game/sagas';

export default function* rootSaga() {
  yield all([
    /* PlopJS sagas placeholder. Do not remove */
    gameSagas()
  ]);
}
