import React, { useEffect } from 'react';
import { history } from '@helpers/history.helper';
import styles from './styles.module.scss';

// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface INotFoundPageProps {
}

const NotFound: React.FC<INotFoundPageProps> = () => {
  useEffect(() => {
    history.replace({ state: { footerLightBlue: true } });
  }, []);

  return (
    <div className={`${styles.container} content_wrapper`}>
      <h1>404</h1>
      <h2>Looks like we can&apos;t find that page</h2>
      <h4>
        It appears that you have reached this page in error, and our servers cannot find what you are looking for.
        Let’s try this again.
      </h4>
    </div>
  );
};

export default NotFound;
