package itp341.lee.coffeeshop.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

// Globally accessible (by any class) that manages a collection of coffeeshops
public class CoffeeShopSingleton {

    private static CoffeeShopSingleton singleton;

    private Context context;

    private CoffeeShopSingleton(Context context){
        this.context = context;
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
        return CoffeeShop.listAll(CoffeeShop.class);
    }

    // TODO - get a single coffeeshop
    public CoffeeShop getCoffeeShop(long id){
        return CoffeeShop.findById(CoffeeShop.class, id);
    }

    // TODO- add a coffeeshop
    public void addCoffeeShop(CoffeeShop coffeeShop){
        coffeeShop.save();
    }

    // TODO - remove a coffee shop
    public void removeCoffeeShop(long id){
        final CoffeeShop coffeeShop = CoffeeShop.findById(CoffeeShop.class, id);
        coffeeShop.delete();
    }

    // TODO - update coffee shop
    public void updateCoffeeShop(long id, CoffeeShop coffeeShop){
       coffeeShop.setId(id);
       coffeeShop.save();
    }
}
