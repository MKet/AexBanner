package server;

import shared.IEffectenbeurs;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    // Set port number
    private static final int portNumber = 1500;

    // Set binding name for student administration
    private static final String bindingName = "Effectenbeurs";

    // References to registry and student administration
    private Registry registry;
    private IEffectenbeurs beurs;

    // Constructor
    public Server() {

        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

        try {
            beurs = new RMIEffectenbeurs();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Bind student administration using registry
        try {
            registry.rebind(bindingName, beurs);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind student administration");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }

        // Bind student administration using registry
        if (registry != null) {
            try {
                beurs  = (IEffectenbeurs) registry.lookup(bindingName);
            } catch (RemoteException ex) {
                System.out.println("Client: Cannot bind student administration");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                beurs = null;
            } catch (NotBoundException ex) {
                System.out.println("Client: Cannot bind student administration");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                beurs = null;
            }
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
    }

}
