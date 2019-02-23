package controller;

import model.P4_HitData;



public class AreaCheckerController {

    public P4_HitData isPointInArea(P4_HitData hitData){
        long currentTime = System.nanoTime();
        double X = hitData.getX();
        double Y = hitData.getY();
        double R = hitData.getR();
        boolean isPointInArea;
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
        int a = (0 - R*1/2) * (-R - 0) - (0 - 0) * (0 + R);
        int b = (x[2] - x[0]) * (y[3] - y[2]) - (x[3] - x[2]) * (y[2] - y[0]);
        int c = (x[3] - x[0]) * (y[1] - y[3]) - (x[1] - x[3]) * (y[3] - y[0]);

        if ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0))
        {

        }
        else
        {
            Console.WriteLine("Не принадлежит треугольнике");
        }
        return new P4_HitData(X, Y, R, (System.nanoTime() - currentTime) / 1000000000d, System.currentTimeMillis(), isPointInArea);
    }
}
