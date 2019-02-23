package controller;

import model.P4_HitData;



public class AreaCheckerController {

    public P4_HitData isPointInArea(P4_HitData hitData){
        long currentTime = System.nanoTime();
        double X = hitData.getX();
        double Y = hitData.getY();
        double R = hitData.getR();
        boolean isPointInArea;
        if (X > 0) {
            if (Y > 0) {
                isPointInArea = false;
            }
            else {
                isPointInArea = X * X + Y * Y <= (R * R);
            }
        }
        if((X < 0 && X < R) || X > 0){
            isPointInArea = false;
        }
        if((Y > 0 && Y > R) || (Y < 0 && Y < R)){
            isPointInArea = false;
        }
        if (Y > 0) {
            isPointInArea = Y <= X + R;
        } else {
            isPointInArea =  X >= -R / 2 && Y >= -R;
        }
        double a = (-R/2 - X) * (0 - 0) - (0 + R/2) * (0 - Y);
        double b = (0 - X) * (-R - 0) - (0 + R) * (0 - Y);
        double c = (-R - X) * (0 + R) - (-R/2 - 0) * (-R - Y);

        if ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0))
        {
            isPointInArea = true;
        }
        else
        {
            isPointInArea = false;
        }
        return new P4_HitData(X, Y, R, (System.nanoTime() - currentTime) / 1000000000d, System.currentTimeMillis(), isPointInArea);
    }
}
