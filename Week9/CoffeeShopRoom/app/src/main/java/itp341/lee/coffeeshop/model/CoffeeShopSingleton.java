package itp341.lee.coffeeshop.model;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

// Globally accessible (by any class) that manages a collection of coffeeshops
public class CoffeeShopSingleton {

    private static CoffeeShopSingleton singleton;

    private Context context;

    private CoffeeShopDao coffeeShopDao;

    private static final String DB_NAME = "coffeeshop_db";

    private CoffeeShopSingleton(Context context){
        this.context = context;

        final CoffeeShopDatabase coffeeShopDatabase = Room.databaseBuilder(context, CoffeeShopDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();

        coffeeShopDao = coffeeShopDatabase.coffeeShopDao();
    }

    // TODO: - Singleton get method
    public static CoffeeShopSingleton get(Context context){
        if (singleton == null){
            singleton = new CoffeeShopSingleton(context);
        }

        return singleton;
    }

    // CRUD : - Create, read, update, delete

    // TODO: - get all coffeeshops
    public List<CoffeeShop>getCoffeeShops(){
        return coffeeShopDao.getCoffeeShops();
    }

    // TODO - get a single coffeeshop
    public CoffeeShop getCoffeeShop(long id){
        return coffeeShopDao.findById(id);
    }

    // TODO- add a coffeeshop
    public void addCoffeeShop(CoffeeShop coffeeShop){
        coffeeShopDao.insert(coffeeShop);
    }

    // TODO - remove a coffee shop
    public void removeCoffeeShop(CoffeeShop coffeeShop){
        coffeeShopDao.delete(coffeeShop);
    }

    // TODO - update coffee shop
    public void updateCoffeeShop(CoffeeShop coffeeShop){
      coffeeShopDao.update(coffeeShop);
    }
}
