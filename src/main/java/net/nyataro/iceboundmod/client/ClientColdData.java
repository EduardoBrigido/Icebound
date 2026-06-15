package net.nyataro.iceboundmod.client;


public class ClientColdData {
    private static double coldLevel;

    public static void set(double cold) {
        ClientColdData.coldLevel = cold;
    }

    public static double getColdLevel() {
        return coldLevel;
    }
}

