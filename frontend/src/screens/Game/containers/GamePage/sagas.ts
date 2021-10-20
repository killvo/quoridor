import { all, call, put, takeEvery } from 'redux-saga/effects';
import { toastr } from 'react-redux-toastr';
import { PayloadAction } from '@reduxjs/toolkit';
import { startTwoPeopleGame } from '@screens/Game/services/game.service';
import { startTwoPeopleGameRoutine } from '@screens/Game/routines';

function* tryStartTwoPeopleGame() {
  try {
    const response = yield call(startTwoPeopleGame);
    yield put(startTwoPeopleGameRoutine.success(response));
    toastr.success('Success', 'Game with two people started!');
  } catch (e) {
    toastr.error('Can\'t start the game with two people', e?.message);
    yield put(startTwoPeopleGameRoutine.failure(e?.message));
  }
}

export default function* spacesSagas() {
  yield all([
    yield takeEvery(startTwoPeopleGameRoutine.TRIGGER, tryStartTwoPeopleGame)
  ]);
}
