package rmi;

import component.EventChannel;
import model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {

    Player getPlayer() throws RemoteException;

    void notifyLogin(Player player) throws RemoteException;

    void notifyLogout(Player player) throws RemoteException;

}
