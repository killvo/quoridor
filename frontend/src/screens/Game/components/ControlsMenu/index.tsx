import React from 'react';
import { Button } from 'semantic-ui-react';
import { IBindingAction } from '@models/Callbacks';
import { Orientation } from '@screens/Game/model/Orientation';
import styles from './styles.module.scss';

export interface IControlsMenuProps {
    toggleWallOrientation: IBindingAction;
    wallOrientation: Orientation;
}

const ControlsMenu: React.FC<IControlsMenuProps> = (
  {
    wallOrientation, toggleWallOrientation
  }
) => (
  <div className={styles.container}>
    <h3>Choose wall orientation</h3>
    <Button
      onClick={toggleWallOrientation}
      content="Rotate"
      color="teal"
      fluid
      basic
    />
    <div className={styles.wall_preview}>
      <div className={wallOrientation === Orientation.HORIZONTAL ? styles.horizontal_wall : styles.vertical_wall} />
    </div>
  </div>
);

export default ControlsMenu;
