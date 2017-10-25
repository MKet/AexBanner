package server;

import shared.*;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RMIEffectenbeurs extends UnicastRemoteObject implements IEffectenbeurs {

    private Timer t;
    private List<IListener> listeners = new ArrayList<>();
    private List<IFonds> koersen = new ArrayList<>();
    private int rangeMin = 0;
    private int rangeMax = 15;

    public RMIEffectenbeurs() throws RemoteException {
        t = new Timer(2000, (e) -> Update());
        t.start();

        //Create a bunch of funds
        Fonds Aex = new Fonds("AEX");
        Fonds Nasd = new Fonds("NASD");
        Fonds EurUsd = new Fonds("EURUSD");

        //Add them to the list
        koersen.add(Aex);
        koersen.add(Nasd);
        koersen.add(EurUsd);
    }

    private void Update() {
        Random random = new Random();
        for (IFonds fonds : koersen) {
            Fonds f = (Fonds)fonds;
            double randomValue = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
            f.setKoers(randomValue);
        }

        StringBuilder sKoers = new StringBuilder();
        for(IFonds f : koersen){
            sKoers.append(f.toString());
        }
        String koersen = sKoers.toString();

        for (IListener listener: listeners) {
            try {
                listener.setKoersen(koersen);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Override
    public List<IFonds> getKoersen() {
        return koersen;
    }

    @Override
    public void addListener(IListener listener) throws RemoteException {
        listeners.add(listener);
    }

    @Override
    public void removeListener(IListener listener) throws RemoteException {
        listeners.remove(listener);
    }
}
