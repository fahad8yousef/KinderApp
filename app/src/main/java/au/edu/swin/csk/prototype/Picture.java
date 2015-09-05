package au.edu.swin.csk.prototype;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Somesh on 8/31/2015.
 */
public class Picture extends Activity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    ImageView photo_ImageView;
    TextView image_Text;
    ImageView delete_Image;
    ImageView add_Image;
    ImageView save_Image;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_layout);
        initializeUI();

    }
    public void initializeUI()
    {
        image_Text=(TextView)findViewById(R.id.image_text);
        add_Image=(ImageView)findViewById(R.id.takepicture_image);
        save_Image=(ImageView)findViewById(R.id.savepicture_image);
        delete_Image=(ImageView)findViewById(R.id.deletepicture_image);
        photo_ImageView=(ImageView)findViewById(R.id.showpicture_image);
        add_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // start the image capture Intent
                startActivityForResult(intent,
                        CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });
        delete_Image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            photo_ImageView.setImageDrawable(null);
            image_Text.setVisibility(View.VISIBLE);
            photo_ImageView.setImageResource(R.color.material_grey_300);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                photo = (Bitmap) intent.getExtras().get("data");
                image_Text.setVisibility(View.INVISIBLE);
                photo_ImageView.setImageBitmap(photo);
            }
        }
    }


}

