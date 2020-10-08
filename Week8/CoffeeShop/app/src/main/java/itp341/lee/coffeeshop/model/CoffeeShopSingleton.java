package itp341.lee.coffeeshop.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

// Globally accessible (by any class) that manages a collection of coffeeshops
public class CoffeeShopSingleton {

    private static CoffeeShopSingleton singleton;

    private List<CoffeeShop> coffeeShops;

    private Context context;

    private CoffeeShopSingleton(Context context){
        this.context = context;

        coffeeShops = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            CoffeeShop coffeeShop = new CoffeeShop();
            coffeeShop.setName(String.format("Coffee shop #%d", i));
            coffeeShop.setCity("Los Angeles");
            coffeeShops.add(coffeeShop);
        }
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
        return coffeeShops;
    }

    // TODO - get a single coffeeshop
    public CoffeeShop getCoffeeShop(int index){
        return coffeeShops.get(index);
    }

    // TODO- add a coffeeshop
    public void addCoffeeShop(CoffeeShop coffeeShop){
        coffeeShops.add(coffeeShop);
    }

    // TODO - remove a coffee shop
    public void removeCoffeeShop(int index){
        coffeeShops.remove(index);
    }

    // TODO - update coffee shop
    public void updateCoffeeShop(int index, CoffeeShop coffeeShop){
        coffeeShops.set(index, coffeeShop);
    }
}
