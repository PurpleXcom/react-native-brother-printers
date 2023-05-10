// ReactNativeBrotherPrintersModule.java

package com.rnbrotherprinters;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class ReactNativeBrotherPrintersModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public ReactNativeBrotherPrintersModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ReactNativeBrotherPrinters";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    public void discoverPrinters() {
        NetworkSearchOption option = new NetworkSearchOption(15, false);
        PrinterSearchResult result = PrinterSearcher.startNetworkSearch(context, option, new Consumer<Channel>() {
            @Override
            public void accept(Channel channel) {
                String modelName = channel.getExtraInfo().get(Channel.ExtraInfoKey.ModelName);
                String ipaddress = channel.getChannelInfo();
                Log.d("TAG", "Model : $modelName, IP Address: $ipaddress")
            }
        });
        callback.invoke(result)
    }

}
