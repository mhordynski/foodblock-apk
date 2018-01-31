package pl.mhordynski.foodblock.blockchain.services;

import java.util.ArrayList;


public class MockProductService implements IProductService
{
    private final static String[] hashes = {
            "000071fc38e3307306c7ce04337f4c358c2a",
            "0000d830cc384706bb21e8378f107cb70626"
    };

    @Override
    public boolean exists(String hash)
    {
        for(String _hash : hashes)
        {
            if(hash.equals(_hash))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName(String hash)
    {
        switch (hash)
        {
            case "0000d830cc384706bb21e8378f107cb70626": return "Fish";
            case "000071fc38e3307306c7ce04337f4c358c2a": return "Eggs";
        }
        return "";
    }

    @Override
    public ArrayList<String> getEvents(String hash)
    {
        ArrayList<String> events = new ArrayList<>();
        switch (hash)
        {
            case "0000d830cc384706bb21e8378f107cb70626":
                events.add("28.01.18: Fished out of the pond near Cracow");
                events.add("29.01.18: Arrived to SampleCompany warehouse");
                events.add("30.01.18: Arrived to SuperShop warehouse");
                events.add("31.01.18: Put up on store shelf");
                return events;
            case "000071fc38e3307306c7ce04337f4c358c2a":
                events.add("17.01.18: Collected in Ciechocinek");
                events.add("19.01.18: Arrived to SuperShop warehouse");
                events.add("20.01.18: Put up on store shelf");
                return events;
        }
        return null;
    }
}
