import { all, call, put, takeEvery } from 'redux-saga/effects';
import { toastr } from 'react-redux-toastr';
import { PayloadAction } from '@reduxjs/toolkit';
import { gameService } from '@screens/Game/services/game.service';
import {
  getBotMoveRoutine,
  makeMoveRoutine,
  placeWallRoutine, restartGameRoutine,
  startTwoPeopleGameRoutine,
  startWithBotGameRoutine, stopGameRoutine
} from '@screens/Game/routines';
import { IMakeMoveRequest } from '@screens/Game/model/MakeMoveRequest';
import { IPlaceWallRequest } from '@screens/Game/model/PlaceWallRequest';

function* tryStartTwoPeopleGame() {
  try {
    const response = yield call(gameService.startTwoPeopleGame);
    yield put(startTwoPeopleGameRoutine.success(response));
    toastr.success('Success', 'Game with two people started!');
  } catch (e) {
    toastr.error('Can\'t start the game with two people', e?.message);
    yield put(startTwoPeopleGameRoutine.failure(e?.message));
  }
}

function* tryStartWithBotGame() {
  try {
    const response = yield call(gameService.startWithBotGame);
    yield put(startWithBotGameRoutine.success(response));
    toastr.success('Success', 'Game with bot started!');
  } catch (e) {
    toastr.error('Can\'t start the game with bot', e?.message);
    yield put(startWithBotGameRoutine.failure(e?.message));
  }
}

function* tryStopGame() {
  try {
    const response = yield call(gameService.stopGame);
    yield put(stopGameRoutine.success(response));
    toastr.success('Success', 'Game stopped!');
  } catch (e) {
    toastr.error('Can\'t stop the game', e?.message);
    yield put(stopGameRoutine.failure(e?.message));
  }
}

function* tryRestartGame() {
  try {
    const response = yield call(gameService.restartGame);
    yield put(restartGameRoutine.success(response));
    toastr.success('Success', 'Game restarted!');
  } catch (e) {
    toastr.error('Can\'t restart the game', e?.message);
    yield put(restartGameRoutine.failure(e?.message));
  }
}

function* tryMakeMove({ payload }: PayloadAction<IMakeMoveRequest>) {
  try {
    const response = yield call(gameService.makeMove, payload);
    yield put(makeMoveRoutine.success(response));
    toastr.success('Success', 'Move was successful');
  } catch (e) {
    toastr.error('Can\'t make move', e?.message);
    yield put(makeMoveRoutine.failure(e?.message));
  }
}

function* tryPlaceWall({ payload }: PayloadAction<IPlaceWallRequest>) {
  try {
    const response = yield call(gameService.placeWall, payload);
    yield put(placeWallRoutine.success(response));
  } catch (e) {
    toastr.error('Can\'t place wall', e?.message);
    yield put(placeWallRoutine.failure(e?.message));
  }
}

function* tryGetBotMove() {
  try {
    const response = yield call(gameService.getBotMove);
    yield put(getBotMoveRoutine.success(response));
    toastr.success('Success', 'Bot moved successfully');
  } catch (e) {
    toastr.error('Can\'t get move from bot', e?.message);
    yield put(getBotMoveRoutine.failure(e?.message));
  }
}

export default function* spacesSagas() {
  yield all([
    yield takeEvery(startTwoPeopleGameRoutine.TRIGGER, tryStartTwoPeopleGame),
    yield takeEvery(startWithBotGameRoutine.TRIGGER, tryStartWithBotGame),
    yield takeEvery(stopGameRoutine.TRIGGER, tryStopGame),
    yield takeEvery(restartGameRoutine.TRIGGER, tryRestartGame),
    yield takeEvery(makeMoveRoutine.TRIGGER, tryMakeMove),
    yield takeEvery(placeWallRoutine.TRIGGER, tryPlaceWall),
    yield takeEvery(getBotMoveRoutine.TRIGGER, tryGetBotMove)
  ]);
}
