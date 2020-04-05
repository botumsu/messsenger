package rmi;

import model.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ChatClient extends Remote {

    String getName() throws RemoteException;

    Map<String, Player> getPlayers() throws RemoteException;

    void notifyLogin(Player player) throws RemoteException;

    void notifyLogout(Player player) throws RemoteException;

}
