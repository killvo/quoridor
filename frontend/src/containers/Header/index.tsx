import React from 'react';
import classNames from 'classnames';
import { Button } from 'semantic-ui-react';
import styles from './styles.module.scss';

// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface IHeaderProps {
}

const Header: React.FC<IHeaderProps> = () => (
  <>
    <div className={styles.container} id="navbar">
      <div className={classNames(styles.wrapper, 'content_wrapper')}>
        <div className={styles.title}>
          Quiridor Game
        </div>
        <div className={styles.button}>
          <Button
            basic
            href="https://en.wikipedia.org/wiki/Quoridor"
            target="_blank"
            content="Open wiki"
          />
        </div>
      </div>
    </div>
    <div className={styles.header_filler} />
  </>
);

export default Header;
