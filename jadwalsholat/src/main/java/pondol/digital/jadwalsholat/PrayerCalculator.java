package pondol.digital.jadwalsholat;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static pondol.digital.jadwalsholat.Constants.BASE_HOUR_ANGLE;
import static pondol.digital.jadwalsholat.Constants.MID_DAY_HOUR;
import static pondol.digital.jadwalsholat.Constants.SUN_ALTITUDE_NIGHT_CONSTANT;
import static pondol.digital.jadwalsholat.Constants.SUN_ALTITUDE_RATIO;
import static pondol.digital.jadwalsholat.JulianDayUtil.hourAngle;
import static pondol.digital.jadwalsholat.MathUtil.acotDeg;
import static pondol.digital.jadwalsholat.MathUtil.tanDeg;


public class PrayerCalculator {

    public static double zuhr(double longitude, double timeZone, double timeAtGeolocationPoint) {
        return MID_DAY_HOUR + timeZone - longitude / BASE_HOUR_ANGLE - timeAtGeolocationPoint;
    }

    public static double asr(double zuhrBaseTime, double latitude, double declinationDegrees, double asrRatio) {
        double sunAltitude = acotDeg(asrRatio + tanDeg(abs(declinationDegrees - latitude))); // altitude of the sun
        return zuhrBaseTime + hourAngle(latitude, sunAltitude, declinationDegrees) / BASE_HOUR_ANGLE;
    }

    public static double maghrib(double zuhrBaseTime, double latitude, double declinationDegrees, double height) {
        double sunAltitude = SUN_ALTITUDE_NIGHT_CONSTANT - SUN_ALTITUDE_RATIO * sqrt(height);
        return zuhrBaseTime + hourAngle(latitude, sunAltitude, declinationDegrees) / BASE_HOUR_ANGLE;
    }

    public static double isha(double zuhrBaseTime, double latitude, double declinationDegrees, double ishaAngle) {
        double sunAltitude = -ishaAngle;
        return zuhrBaseTime + hourAngle(latitude, sunAltitude, declinationDegrees) / BASE_HOUR_ANGLE;
    }

    public static double fajr(double zuhrBaseTime, double latitude, double declinationDegrees, double fajrAngle) {
        double sunAltitude = -fajrAngle;
        return zuhrBaseTime - hourAngle(latitude, sunAltitude, declinationDegrees) / BASE_HOUR_ANGLE;
    }

    public static double sunrise(double zuhrBaseTime, double latitude, double declinationDegrees, double height) {
        double sunAltitude = SUN_ALTITUDE_NIGHT_CONSTANT - SUN_ALTITUDE_RATIO * sqrt(height); // equals to getMaghribAdjustment
        return zuhrBaseTime - hourAngle(latitude, sunAltitude, declinationDegrees) / BASE_HOUR_ANGLE;
    }
}
