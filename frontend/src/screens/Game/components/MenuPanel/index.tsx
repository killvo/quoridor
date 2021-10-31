import React from 'react';
import { Button } from 'semantic-ui-react';
import { IBindingAction } from '@models/Callbacks';
import styles from './styles.module.scss';

export interface IMenuPanelProps {
    startTwoPeopleGame: IBindingAction;
    startWithBotGame: IBindingAction;
    stopGame: IBindingAction;
    restartGame: IBindingAction;
}

const MenuPanel: React.FC<IMenuPanelProps> = (
  {
    startTwoPeopleGame, startWithBotGame, stopGame, restartGame
  }
) => (
  <div className={styles.container}>
    <h3>Game menu</h3>
    <Button
      onClick={startTwoPeopleGame}
      content="Start Two People"
      color="teal"
      fluid
      basic
    />
    <Button
      onClick={startWithBotGame}
      content="Start With Bot"
      color="teal"
      fluid
      basic
    />
    <Button
      onClick={stopGame}
      content="Stop"
      color="red"
      fluid
      basic
    />
    <Button
      onClick={restartGame}
      content="Restart"
      color="purple"
      fluid
      basic
    />
  </div>
);

export default MenuPanel;
