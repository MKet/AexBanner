package server;

import shared.IEffectenbeurs;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

public class Server {

    // Set port number
    private static final int portNumber = 1500;

    // Set binding name for AEXBanner
    private static final String bindingName = "Effectenbeurs";

    // References to registry and AEXBanner
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
            System.out.println("Server: Registry created on IP address :" + registry.toString());
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }
        

        // Bind AEX Banner using registry
        try {
            registry.rebind(bindingName, beurs);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind student administration");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }

        // Bind AEX Banner using registry
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

    // Print IP addresses and network interfaces
    private static void printIPAddresses() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                System.out.println("Server: Full list of IP addresses:");
                for (InetAddress allMyIp : allMyIps) {
                    System.out.println("    " + allMyIp);
                }
            }
        } catch (java.net.UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Server: Full list of network interfaces:");
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    System.out.println("        " + enumIpAddr.nextElement().toString());
                }
            }
        } catch (SocketException ex) {
            System.out.println("Server: Cannot retrieve network interface list");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length!=1) {
            System.out.println("Error: no ipAddress given as command line parameter");
            return;
        }

        final String ipAddress = args[0];

        // Welcome message
        System.out.println("SERVER USING REGISTRY");

        System.out.println("[before] java.rmi.server.hostname=" + System.getProperty("java.rmi.server.hostname"));

        // RMI on distinct IP address
        System.setProperty("java.rmi.server.hostname", ipAddress );

        System.out.println("[after] java.rmi.server.hostname=" + System.getProperty("java.rmi.server.hostname"));


        // Print IP addresses and network interfaces
        printIPAddresses();

        Server server = new Server();
    }

}
