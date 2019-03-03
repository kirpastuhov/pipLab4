package controller;

import model.P4_HitData;

import javax.servlet.http.HttpSession;


public class AreaCheckerController
{

    public P4_HitData isPointInArea(P4_HitData hitData, HttpSession session)
    {
        long currentTime = System.nanoTime();
        double X = hitData.getX();
        double Y = hitData.getY();
        double R = hitData.getR();
        boolean isPointInArea = true;
        if (X > 0)
        {
            if (Y > 0)
            {
                isPointInArea = false;
            } else
            {
                isPointInArea = X * X + Y * Y <= (R * R); // circle
            }
        } else
        {
            if (Y > 0)
            {
                isPointInArea = X >= -R && Y <= R; // square
            }
            if(Y < 0) {
                double a = (0 - X) * (-R - 0);
                double b = (0 - X) * (0 + R) - (-R / 2 - 0) * (-R - Y);
                double c = (-R / 2 - X) * (0 + R) - (0 + R / 2) * (0 + R);
                if ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0)) {
                    isPointInArea = true;
                } else {
                    isPointInArea = false;
                }
            }
        }

        int user_id = Integer.parseInt(session.getAttribute("Id").toString());
        return new P4_HitData(X, Y, R, (System.nanoTime() - currentTime) / 1000000000d, System.currentTimeMillis(), user_id, isPointInArea);
    }
}
