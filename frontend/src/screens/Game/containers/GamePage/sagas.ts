import { all, call, put, takeEvery } from 'redux-saga/effects';
import { toastr } from 'react-redux-toastr';
import { PayloadAction } from '@reduxjs/toolkit';
import { startGameRoutine } from '@screens/Game/routines';
import { IGameStartRequest } from '@screens/Game/model/GameStartRequest';
import { startGame } from '@screens/Game/services/game.service';

function* tryStartGame({ payload }: PayloadAction<IGameStartRequest>) {
  try {
    yield call(startGame, payload);
    yield put(startGameRoutine.success());
    toastr.success('Success', 'Game started!');
  } catch (e) {
    toastr.error('Can\'t start the game', e?.message);
    yield put(startGameRoutine.failure(e?.message));
  }
}

export default function* spacesSagas() {
  yield all([
    yield takeEvery(startGameRoutine.TRIGGER, tryStartGame)
  ]);
}
