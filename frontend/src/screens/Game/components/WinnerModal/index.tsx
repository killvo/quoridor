import React from 'react';
import { Button, Modal, ModalActions, ModalContent, ModalHeader } from 'semantic-ui-react';
import { IBindingAction } from '@models/Callbacks';

export interface IWinnerModalProps {
    winner: string;
    onClose: IBindingAction;
    onNewSession: IBindingAction;
}

const WinnerModal: React.FC<IWinnerModalProps> = (
  {
    winner, onClose, onNewSession
  }
) => (
  <Modal
    open={!!winner}
    onClose={onClose}
    closeIcon
    size="small"
  >
    <ModalHeader>
      We have the winner!
    </ModalHeader>
    <ModalContent>
      Won player with id
      &nbsp;
      {winner}
    </ModalContent>
    <ModalActions>
      <Button
        onClick={onNewSession}
        color="green"
        content="Start New Session"
      />
    </ModalActions>
  </Modal>
);

export default WinnerModal;
