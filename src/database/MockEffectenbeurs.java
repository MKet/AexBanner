package database;

import Domain.Fonds;
import Domain.IFonds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockEffectenbeurs implements IEffectenbeurs {

    private List<IFonds> koersen = new ArrayList();
    int rangeMin = 0;
    int rangeMax = 15;

    public MockEffectenbeurs(){

        //Create a bunch of funds
        Fonds Aex = new Fonds("AEX");
        Fonds Nasd = new Fonds("NASD");
        Fonds EurUsd = new Fonds("EURUSD");

        //Add them to the list
        koersen.add(Aex);
        koersen.add(Nasd);
        koersen.add(EurUsd);
    }

    @Override
    public List<IFonds> getKoersen() {

        Random random = new Random();
        for (IFonds fonds : koersen) {
            Fonds f = (Fonds)fonds;
            double randomValue = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
            f.setKoers(randomValue);
        }

        return koersen;
    }


}