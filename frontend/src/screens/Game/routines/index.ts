import { createRoutine } from 'redux-saga-routines';

const createGameRoutine = actionName => createRoutine(`GAME:${actionName}`);

export const startTwoPeopleGameRoutine = createGameRoutine('START_TWO_PEOPLE');
export const startWithBotGameRoutine = createGameRoutine('START_WITH_BOT');
export const stopGameRoutine = createGameRoutine('STOP');
export const restartGameRoutine = createGameRoutine('RESTART');

export const makeMoveRoutine = createGameRoutine('MAKE_MOVE');
export const placeWallRoutine = createGameRoutine('PLACE_WALL');
export const getBotMoveRoutine = createGameRoutine('GET_BOT_MOVE');
