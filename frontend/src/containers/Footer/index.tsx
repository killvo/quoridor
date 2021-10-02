import React from 'react';
import classNames from 'classnames';
import styles from './styles.module.scss';

// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface IFooterProps {
}

const Footer: React.FC<IFooterProps> = () => (
  <>
    <div className={classNames('content_wrapper', styles.footer, styles.vertical_space)}>
      Footer Content
    </div>
  </>
);

export default Footer;
