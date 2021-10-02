import { all } from 'redux-saga/effects';
import gameSagas from '@screens/Game/containers/GamePage/sagas';

export default function* browseSpacesSagas() {
  yield all([
    gameSagas()
  ]);
}
