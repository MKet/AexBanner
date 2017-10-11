package client;

import shared.IEffectenbeurs;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

    private static final String bindingName = "Effectenbeurs";

    // References to registry and student administration
    private Registry registry = null;
    private IEffectenbeurs beurs = null;

    // Constructor
    public RMIClient() {

        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(1500);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }
    }

    public IEffectenbeurs getbeurs() {
        try {
            return (IEffectenbeurs) registry.lookup(bindingName);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        } catch (NotBoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
