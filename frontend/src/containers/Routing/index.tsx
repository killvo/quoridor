import React from 'react';
import { Route, Switch } from 'react-router-dom';
import GamePage from '@screens/Game/containers/GamePage';
import Header from '@containers/Header';
import Footer from '@containers/Footer';
import NotFound from '@components/404NotFound';
import { ENDPOINTS } from '@containers/Routing/endpoints';
import styles from './styles.module.scss';

// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface IRoutingProps {
}

const { ROOT } = ENDPOINTS;

const Routing: React.FunctionComponent<IRoutingProps> = () => (
  <div className={styles.fill}>
    <Header />
    <div className={styles.content}>
      <Switch>
        <Route exact path={ROOT} component={GamePage} />
        <Route component={NotFound} />
      </Switch>
    </div>
    <Footer />
  </div>
);

export default Routing;
