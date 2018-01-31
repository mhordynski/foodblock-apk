package pl.mhordynski.foodblock.blockchain.services;


import java.util.ArrayList;

public interface IProductService
{
    boolean exists(String hash);
    String getName(String hash);
    ArrayList<String> getEvents(String hash);
}
