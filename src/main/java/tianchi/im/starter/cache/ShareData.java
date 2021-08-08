package tianchi.im.starter.cache;

import io.vertx.core.Vertx;
import io.vertx.core.shareddata.SharedData;

import java.util.Map;

public class ShareData {

    public static void getSharedData(Vertx vertx){
        SharedData sd = vertx.sharedData();
        sd.getAsyncMap("roomMap");
    }
}
