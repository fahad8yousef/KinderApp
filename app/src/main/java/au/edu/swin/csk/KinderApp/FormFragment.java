package au.edu.swin.csk.KinderApp;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment implements DialogInterface.OnClickListener, DFragment.DialogClickListener {



    private static final String TAG = "Somesh/ Picture Frag";
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
    String picturePath;
    private TextView dateView;
    private AlertDialog dialog;


    private EditText activity_Edit;
    private EditText children_Edit;
    private EditText lo_Edit;
    private EditText comment_Edit;
    private String mCurrentPhotoPath;
    private String imageFileName;
    private Button changeDate;

    //Setting up DialogFragment variables

    public FormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.picture_layout, container, false);
    }



    public void initializeUI() {
        image_Text = (TextView) getView().findViewById(R.id.image_text);
        add_Image = (ImageView) getView().findViewById(R.id.takepicture_image);
        delete_Image = (ImageView) getView().findViewById(R.id.deletepicture_image);
        photo_ImageView = (ImageView) getView().findViewById(R.id.showpicture_image);

        save_Image=(ImageView)getView().findViewById(R.id.savepicture_image);
        save_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savePictureToDatabase();

            }
        });
        select_Activity=(Button)getView().findViewById(R.id.select_activity);
        select_Activity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showActivityList();
            }
        });
        select_Children=(Button)getView().findViewById(R.id.select_children);
        select_Children.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                showChildrenList();
            }
        });
        select_LO=(Button)getView().findViewById(R.id.select_lo);
        select_LO.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                showLoList();
            }
        });
        dateView=(TextView)getView().findViewById(R.id.date_view);
        String ct = DateFormat.getDateInstance().format(new Date());
        dateView.setText(ct);
        changeDate=(Button)getView().findViewById(R.id.changeDate_button);
        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar
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

                deleteFormData();

            }
        });

        activity_Edit=(EditText)getView().findViewById(R.id.activity_edit);
        activity_Edit.setKeyListener(null);
        children_Edit=(EditText)getView().findViewById(R.id.children_edit);
        children_Edit.setKeyListener(null);
        lo_Edit=(EditText)getView().findViewById(R.id.lo_edit);
        lo_Edit.setKeyListener(null);
        comment_Edit=(EditText)getView().findViewById(R.id.comment_edit);
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

    public void deleteFormData()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("This will delete the image and any data associated with it. Do you wish to continue?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                //Clearing the image and deleting it from ImagesForKinder
                photo_ImageView.setImageDrawable(null);
                image_Text.setVisibility(View.VISIBLE);

                if (mCurrentPhotoPath != null) {
                    File file = new File(mCurrentPhotoPath);
                    Log.d(TAG, String.valueOf(mCurrentPhotoPath) + "mCurrentPhotoPath");
                    file.delete();
                    galleryAddPic(mCurrentPhotoPath);

                }

            else if(picturePath!=null)

            {
                File file = new File(picturePath);
                Log.d(TAG, String.valueOf(picturePath) + "picturePath");
                file.delete();
                galleryAddPic(picturePath);
            }

            photo_ImageView.setImageResource(R.color.primary_material_dark);
            comment_Edit.setText(null);
            activity_Edit.setText(null);
            children_Edit.setText(null);
            lo_Edit.setText(null);


            }
        });

        alert.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });

        alert.show();


    }

    public void showActivityList()
    {
        DialogFragment dialogFragment = DFragment.newInstance(R.string.activities_dialog_title, 1);
        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(getActivity().getFragmentManager(), "dialog");

    }

    public void showChildrenList()
    {
        DialogFragment dialogFragment = DFragment.newInstance(R.string.children_dialog_title, 2);
        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(getActivity().getFragmentManager(), "dialog");
    }

    public void showLoList()
    {
        DialogFragment dialogFragment = DFragment.newInstance(R.string.lo_dialog_title, 3);
        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(getActivity().getFragmentManager(), "dialog");

    }


    public void startDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                                MediaStore.ACTION_IMAGE_CAPTURE);

                        File photoFile = null;

                        photoFile = createImageFile();

                        if (photoFile != null) {

                            pictureActionIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                            startActivityForResult(pictureActionIntent, CAMERA_REQUEST);
                        }
                    }
                });
        builder.show();
    }

   public  void galleryAddPic(String imagePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

        public void setPic() {
        // Get the dimensions of the View
        int targetH = photo_ImageView.getHeight();
        int targetW = photo_ImageView.getWidth();
        Log.d(TAG,String.valueOf(targetH));
        Log.d(TAG,String.valueOf(targetW));

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        Log.d(TAG, mCurrentPhotoPath);
         photo = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            Log.d(TAG, String.valueOf(photo));
        photo_ImageView.setImageBitmap(photo);
    }
    public File createImageFile()
    {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Wombat" + timeStamp + "_";
        File myDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/ImagesForKinder/");
        if (!myDir.isDirectory())
        {myDir.mkdirs();}
        File image = null;
        try {
            image = File.createTempFile(imageFileName, ".jpg", myDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCurrentPhotoPath=image.getAbsolutePath();
        Log.d(TAG, mCurrentPhotoPath);
        return image;
    }

    //We have to use Activity.RESULT_OK because it's a constant of Activity class.

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST  && resultCode == Activity.RESULT_OK) {
            image_Text.setVisibility(View.INVISIBLE);
            setPic();
            galleryAddPic(mCurrentPhotoPath);

        }
        else if (requestCode ==GALLERY_PICTURE && resultCode==Activity.RESULT_OK)
        {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            Log.d(TAG, picturePath);
            image_Text.setVisibility(View.INVISIBLE);
            photo = BitmapFactory.decodeFile(picturePath);
            photo_ImageView.setImageBitmap(photo);
            File sourceFile = new File(picturePath);
            try {
                copyImageFromGallery(sourceFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void copyImageFromGallery(File sourceFile) throws IOException {
        File myDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/ImagesForKinder/");
        if (!myDir.isDirectory())
        {myDir.mkdirs();}
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFileName = "Wombat" + timeStamp + ".jpg";

        File destinationFile = new File(myDir, imageFileName);
        FileChannel source = null;
        FileChannel destination = null;
        Log.d(TAG, String.valueOf(sourceFile) + "Source File");
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destinationFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
        Log.d(TAG, String.valueOf(destinationFile) + "Destination File");
        galleryAddPic(imageFileName);
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
    public void savePictureToDatabase()
    {

        Toast.makeText(getActivity(), "Not implemented yet", Toast.LENGTH_SHORT).show();




    }
    //The following function is used to save the image visible on screen to a bundle, from where it will be retreived later on.
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        outState.putParcelable("bitmap", photo);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onActivityCreated(Bundle savedState) {

        initializeUI();
        super.onActivityCreated(savedState);
    }

    /*
    * fragment destroy*/
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    /*
    * remove frag from activity */
    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onYesClick(ArrayList selectedValues, int dialogIdentifier) {
        String selectedValuesString="";

        for(int i = 0; i<selectedValues.size(); i++)
        {
            selectedValuesString= selectedValuesString  + selectedValues.get(i) + ", ";
        }
        selectedValuesString = selectedValuesString.substring(0, selectedValuesString.lastIndexOf(","));
                Log.d(TAG,selectedValuesString);
        switch (dialogIdentifier)
        {
            case 1:
            {
                activity_Edit.setText(selectedValuesString);
                break;
            }
            case 2:
            {
                children_Edit.setText(selectedValuesString);
                break;
            }
            case 3:
            {
                lo_Edit.setText(selectedValuesString);
                break;
            }
        }
    }

    @Override
    public void onNoClick(int toastMessage) {

        Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_SHORT).show();

    }
}


