package me.peridot.mckparticle.util;

public class PointRotor {

    //PointRotor 2.0, Euler equation so powerful...
    //point - array with point that will be rotated (x, y)
    //center - array with point around which point will be rotated (x, y)
    //angle - angle in degrees
    public static double[] rotatePointDeg(double[] point, double[] center, double angle) {
        return rotatePointRad(point, center, Math.toRadians(angle), 0);
    }

    //length - if needed, increases distance of rotated point from center by given amount
    public static double[] rotatePointDeg(double[] point, double[] center, double angle, double length) {
        return rotatePointRad(point, center, Math.toRadians(angle), length);
    }

    //point - array with point that will be rotated (x, y)
    //center - array with point around which point will be rotated (x, y)
    //angle - angle in radians
    public static double[] rotatePointRad(double[] point, double[] center, double angle) {
        return rotatePointRad(point, center, angle, 0);
    }

    //length - if needed, increases distance of rotated point from center by given amount
    public static double[] rotatePointRad(double[] point, double[] center, double angle, double length) {
        double sin = Math.sin(angle), cos = Math.cos(angle);
        if (length == 0)
            return new double[]{center[0] + (point[0] - center[0]) * cos - (point[1] - center[1]) * sin, center[1] + (point[0] - center[0]) * sin + (point[1] - center[1]) * cos};
        point[0] -= center[0];
        point[1] -= center[1];
        double[] result = new double[]{point[0] * cos - point[1] * sin, point[0] * sin + point[1] * cos};
        double r = Math.hypot(point[0], point[1]);
        result[0] += center[0] + result[0] * length / r;
        result[1] += center[1] + result[1] * length / r;
        return result;
    }

}
