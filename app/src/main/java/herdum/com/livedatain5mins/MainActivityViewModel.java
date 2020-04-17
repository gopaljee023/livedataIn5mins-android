package herdum.com.livedatain5mins;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

//jee1
public class MainActivityViewModel extends ViewModel  implements LifecycleObserver {

    boolean isContinue;
    int count;
    //jee3
    MutableLiveData<Integer> liveCounter;
    Thread counterThread;

    public MainActivityViewModel(){
        isContinue = false;
        liveCounter = new MutableLiveData<Integer>();


        counterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isContinue) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    count++;
                    liveCounter.postValue(count);
                }
            }
        });
    }

    public void start(){
        if(!isContinue){
            isContinue = true;
            counterThread.start();
        }
    }

    public void stop(){
        isContinue = false;
    }

    //jee4
    LiveData<Integer> getCounterLiveData(){
        return liveCounter;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void funtionalityBasedonResumeOfActivity(){
        Log.d("MainActivityViewModel", "funtionalityBasedonResumeOfActivity: "+"resume called");
    }


}
