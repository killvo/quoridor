import React from 'react';
import { Provider } from 'react-redux';
import { ConnectedRouter } from 'connected-react-router';
import ReduxToastr from 'react-redux-toastr';
import { history } from '@helpers/history.helper';
import { store } from '@root/store';
import Routing from '@containers/Routing';

const App: React.FC = () => (
  <Provider store={store}>
    <ReduxToastr
      timeOut={5000}
      newestOnTop={false}
      position="top-right"
      transitionIn="fadeIn"
      transitionOut="fadeOut"
      closeOnToastrClick
    />
    <ConnectedRouter history={history}>
      <Routing />
    </ConnectedRouter>
  </Provider>
);

export default App;
