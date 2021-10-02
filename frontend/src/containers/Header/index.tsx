import React from 'react';
import classNames from 'classnames';
import styles from './styles.module.scss';

// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface IHeaderProps {
}

const Header: React.FC<IHeaderProps> = () => (
  <>
    <div className={styles.container} id="navbar">
      <div className={classNames(styles.wrapper, 'content_wrapper')}>
        Header items
      </div>
    </div>
    <div className={styles.header_filler} />
  </>
);

export default Header;
