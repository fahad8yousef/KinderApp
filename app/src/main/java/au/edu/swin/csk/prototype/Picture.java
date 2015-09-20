package au.edu.swin.csk.prototype;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Somesh on 8/31/2015.
 */
public class Picture extends FragmentActivity implements DialogInterface.OnClickListener {
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private ImageView photo_ImageView;
    private Button select_Activity;
    private Button select_LO;
    private Button select_Children;
    private TextView image_Text;
    private ImageView delete_Image;
    private ImageView add_Image;
    private ImageView save_Image;
    private Bitmap photo;
    private TextView dateView;
    private AlertDialog dialog;

    private EditText activity_Edit;
    private EditText children_Edit;
    private EditText lo_Edit;


    private Button changeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_layout);
        initializeUI();

    }

    public void initializeUI() {
        image_Text = (TextView) findViewById(R.id.image_text);
        add_Image = (ImageView) findViewById(R.id.takepicture_image);
        delete_Image = (ImageView) findViewById(R.id.deletepicture_image);
        photo_ImageView = (ImageView) findViewById(R.id.showpicture_image);
        save_Image=(ImageView)findViewById(R.id.savepicture_image);
        save_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savePictureToDatabase();

            }
        });
        select_Activity=(Button)findViewById(R.id.select_activity);
        select_Activity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showActivityList();
            }
        });
        select_Children=(Button)findViewById(R.id.select_children);
        select_Children.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v)
           {
               showChildrenList();
           }
        });
        select_LO=(Button)findViewById(R.id.select_lo);
        select_LO.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                showLoList();
            }
        });
        dateView=(TextView)findViewById(R.id.date_view);
        changeDate=(Button)findViewById(R.id.changeDate_button);
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Picture.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        add_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDialog();
            }
        });
        delete_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo_ImageView.setImageDrawable(null);
                image_Text.setVisibility(View.VISIBLE);
                photo_ImageView.setImageResource(R.color.material_blue_grey_950);
            }
        });

        activity_Edit=(EditText)findViewById(R.id.activity_edit);
        activity_Edit.setKeyListener(null);
        children_Edit=(EditText)findViewById(R.id.children_edit);
        children_Edit.setKeyListener(null);
        lo_Edit=(EditText)findViewById(R.id.lo_edit);
        lo_Edit.setKeyListener(null);
    }
//Setting up date picker.
    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateLabel();
        }

    };

    public void updateDateLabel()
    {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateView.setText(sdf.format(myCalendar.getTime()));
    }

    public void showActivityList()
    {
        DialogFragment newFragment = DFragment.newInstance(R.string.activities_dialog_title, 1);
        newFragment.show(getFragmentManager(), "dialog");

    }

    public void showChildrenList()
    {
        DialogFragment newFragment = DFragment.newInstance(R.string.children_dialog_title, 2);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void showLoList()
    {
        DialogFragment newFragment = DFragment.newInstance(R.string.lo_dialog_title, 3);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void onUserSelectValue(String selectedValues, int dialogIdentifier)
    {
        switch (dialogIdentifier)
        {
            case 1:
            {
                activity_Edit.setText(selectedValues);
                break;
            }
            case 2:
            {
                children_Edit.setText(selectedValues);
                break;
            }
            case 3:
            {
                lo_Edit.setText(selectedValues);
                break;
            }
        }
    }
    public void startDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//can't use getActivity() as it doesn't exists in Activity.
        builder.setTitle("Add from:");
        builder.setPositiveButton("Gallery",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent pictureActionIntent = new Intent(
                                Intent.ACTION_GET_CONTENT, null);
                        pictureActionIntent.setType("image/*");
                        pictureActionIntent.putExtra("return-data", true);
                        startActivityForResult(pictureActionIntent,
                                GALLERY_PICTURE);
                    }
                });

        builder.setNegativeButton("Camera",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent pictureActionIntent = new Intent(
                                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(pictureActionIntent,
                                CAMERA_REQUEST);

                    }
                });
        builder.show();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST  && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            image_Text.setVisibility(View.INVISIBLE);
            photo_ImageView.setImageBitmap(photo);
        }
        else if (requestCode ==GALLERY_PICTURE && resultCode==RESULT_OK)
        {

           Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            image_Text.setVisibility(View.INVISIBLE);
            photo_ImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }

        }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    public void savePictureToDatabase()
    {
        Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

