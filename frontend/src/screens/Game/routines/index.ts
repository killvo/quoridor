import { createRoutine } from 'redux-saga-routines';

const createGameRoutine = actionName => createRoutine(`GAME:${actionName}`);

export const startGameRoutine = createGameRoutine('START');
