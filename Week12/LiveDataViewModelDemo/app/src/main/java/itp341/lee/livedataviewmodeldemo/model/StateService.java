package itp341.lee.livedataviewmodeldemo.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StateService {

    private MutableLiveData<String> currentState;

    private List<String> states;

    private static StateService shared;

    public static StateService get(){
        if (shared == null){
            shared = new StateService();
        }

        return shared;
    }

    private StateService(){
        states = Arrays.asList("California", "New York", "Texas", "Florida");
        currentState = new MutableLiveData<>();
        currentState.setValue(states.get(0));
    }

    public LiveData<String> getCurrentState(){
        return currentState;
    }

    public void updateCurrentState(){
        Random randomizer = new Random();
        int randomIndex = randomizer.nextInt(states.size());
        String randomState = states.get(randomIndex);
        currentState.setValue(randomState);
    }


}
