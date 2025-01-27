package pondol.digital.jadwalsholat;

public enum PrayersType {
    FAJR(0),
    SUNRISE(1),
    ZUHR(2),
    ASR(3),
    MAGHRIB(4),
    ISHA(5);

    private int index;

    public int getIndex() {
        return index;
    }

    PrayersType(int index) {
        this.index = index;
    }
}
