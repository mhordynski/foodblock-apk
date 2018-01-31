package pl.mhordynski.foodblock.blockchain.models;

import java.util.ArrayList;

import pl.mhordynski.foodblock.blockchain.services.IProductService;
import pl.mhordynski.foodblock.blockchain.services.MockProductService;

public class Product
{
    private String hash;
    private String name;
    private ArrayList<String> events = new ArrayList<>();

    private final IProductService productService = new MockProductService();

    public Product(String hash)
    {
        if(!isValidHash(hash))
        {
            throw new IllegalArgumentException("Not valid product hash: " + hash);
        }

        this.hash = hash;
        this.fetch();
    }

    private void fetch()
    {
        this.name = productService.getName(this.hash);
        this.events = productService.getEvents(this.hash);
    }

    public String getName()
    {
        return this.name;
    }

    public static boolean isValidHash(String hash)
    {
        if(hash.length() < 5)
        {
            return false;
        }
        if(!hash.startsWith("0000"))
        {
            return false;
        }

        return true;
    }

    public ArrayList<String> getEvents()
    {
        return this.events;
    }
}
