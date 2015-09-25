package au.edu.swin.csk.prototype;

import java.util.ArrayList;

/**
 * Created by Fahad on 9/09/2015.
 */
public class Card {


    String date;
    String activityName;
    int imageId;

    Card(int imageId, String info)
    {
        if (info.length() !=0 ) {
            this.date = info.substring(0, info.indexOf(","));
            this.activityName = info.substring(info.indexOf(",") + 1, info.length());
        }
        this.imageId = imageId;
        //this.date = date;
        //this.activityName = activityName;
    }
}

