package au.edu.swin.csk.KinderApp;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
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

import com.getbase.floatingactionbutton.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * This fragment is used to create and deal with the data associated with pictures.
 * We are implementing two interfaces here: DialogInterface.OnClickListener, and DFragment.DialogClickListener.
 * First interface deals with DialogInterface and the second one with DFragment
 * @Author Somesh Bahuguna
 */
public class FormFragment extends Fragment implements DialogInterface.OnClickListener, DFragment.DialogClickListener {


    private String evidenceID;
    private static final String TAG = "Somesh/ Picture Frag";
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private ImageView photo_ImageView;
    private Button select_Activity;
    private Button select_LO;
    private Button select_Children;
    private TextView image_Text;
    private FloatingActionButton delete_Image;
    private FloatingActionButton add_Image;
    private FloatingActionButton save_Image;
    private Bitmap photo;
    private TextView dateView;
    private AlertDialog dialog;
    private int callIdentifier;
    final int THUMBSIZE = 512;
    Bitmap thumbImage;
    String thumbnailImageName="";
    private ArrayList<String> selectedChildrenIDs = new ArrayList<>();
    private ArrayList<String> selectedLearningOutcomes = new ArrayList<>();

    private FloatingActionsMenu pictureMenu;
    private int groupID;
    private EditText activity_Edit;
    private EditText children_Edit;
    private EditText lo_Edit;
    private EditText comment_Edit;
    private String mCurrentPhotoPath;
    private String imageFileName;
    private Button changeDate;
    private String selectedEvidenceID;
    private KinderDBCon k;
    private ArrayList<String> preSelectedValues;

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

        k = new KinderDBCon(getActivity());
        k.open();

        callIdentifier=getArguments().getInt("identifier");
        groupID=getArguments().getInt("groupID");
        selectedEvidenceID=getArguments().getString("evidenceCode");
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.picture_layout, container, false);
    }



    public void initializeUI() {

        //  pictureMenu=(FloatingActionsMenu)getView().findViewById(R.id.fab_picturemenu);
        image_Text = (TextView) getView().findViewById(R.id.image_text);
        add_Image = (FloatingActionButton) getView().findViewById(R.id.fab_add);
        delete_Image = (FloatingActionButton) getView().findViewById(R.id.fab_delete);
        //pictureMenu.addButton(add_Image);

        photo_ImageView = (ImageView) getView().findViewById(R.id.showpicture_image);

        save_Image=(FloatingActionButton)getView().findViewById(R.id.fab_save);
        save_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveFormDataToDatabase();

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
        //String ct = DateFormat.getDateInstance().format(new Date());
        Date _currentDate= new Date();
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        String currentDate=sdf.format(_currentDate);
        //dateView.setText(ct);
        dateView.setText(currentDate);

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
        readDataFromDatabase(callIdentifier);

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
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        dateView.setText(sdf.format(myCalendar.getTime()));
    }

    public void deleteFormData()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(R.string.delete_data_message);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                //Clearing the image and deleting it from ImagesForKinder
                photo_ImageView.setImageDrawable(null);
                image_Text.setVisibility(View.VISIBLE);

                if (mCurrentPhotoPath != null) {
                    File file = new File(mCurrentPhotoPath);
                    Log.d(TAG, String.valueOf(mCurrentPhotoPath) + "mCurrentPhotoPath, the one getting deleted");
                    file.delete();
                    galleryAddPic(mCurrentPhotoPath);

                }

                else if(imageFileName!=null)

                {
                    File file = new File(imageFileName);
                    Log.d(TAG, String.valueOf(file) + "file getting deleted");
                    Log.d(TAG, String.valueOf(imageFileName) + "imageFileName, the one getting deleted");
                    file.delete();
                    galleryAddPic(imageFileName);
                }

                photo_ImageView.setImageResource(R.color.primary_material_dark);
                comment_Edit.setText(null);
                activity_Edit.setText(null);
                children_Edit.setText(null);
                lo_Edit.setText(null);
                k.deleteEvidenceByID(selectedEvidenceID);
                Bundle bundle = new Bundle();
                bundle.putInt("id", groupID);
                ((MainActivity)getActivity()).showMainFragment(bundle);
                Toast.makeText(getActivity(), "Data deleted successfully!", Toast.LENGTH_SHORT).show();


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
        DialogFragment dialogFragment = DFragment.newInstance(R.string.activities_dialog_title, 1, groupID, null);
        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(getActivity().getFragmentManager(), "dialog");

    }

    public void showChildrenList()
    {
        DialogFragment dialogFragment = DFragment.newInstance(R.string.children_dialog_title, 2, groupID, null);
        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(getActivity().getFragmentManager(), "dialog");
    }

    public void showLoList()
    {
        DialogFragment dialogFragment = DFragment.newInstance(R.string.lo_dialog_title, 3, groupID, null);
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

                        File photoFile = createImageFile();

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

    public void saveThumbnailOfCurrentImage(String fullImagePath)
    {
        Log.d("someshbahuguna", fullImagePath);
        thumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(fullImagePath), THUMBSIZE, THUMBSIZE);
        File myDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/KinderThumbnails/");
        if (!myDir.isDirectory())
        {myDir.mkdirs();}
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        thumbnailImageName = "thumbWombat" + timeStamp + ".jpg";
        File file = new File (myDir, thumbnailImageName);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            thumbImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        photo_ImageView.setImageBitmap(thumbImage);
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
            //setPic();
            saveThumbnailOfCurrentImage(mCurrentPhotoPath);
            galleryAddPic(mCurrentPhotoPath);

        }
        else if (requestCode ==GALLERY_PICTURE && resultCode==Activity.RESULT_OK)
        {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            image_Text.setVisibility(View.INVISIBLE);
            photo = BitmapFactory.decodeFile(picturePath);
            // photo_ImageView.setImageBitmap(photo);
            File sourceFile = new File(picturePath);
            try {
                copyImageFromGallery(sourceFile, picturePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void copyImageFromGallery(File sourceFile, String picturePath) throws IOException {
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
        Log.d(TAG, picturePath + "picturePath source File");
        Log.d(TAG, imageFileName + "imageFileName Destination File");
        saveThumbnailOfCurrentImage(picturePath);
        //  galleryAddPic();
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    public void saveFormDataToDatabase()
    {

        String completionStatus=getFormCompletionStatus();
        //Gets the rowid inserted as Long type, used static values for testing

        if (callIdentifier==1)
        {
            k.deleteEvidenceByID(selectedEvidenceID);

        }

        Long newEvidenceID = k.InsertIntoEvidenceTable(dateView.getText().toString(), comment_Edit.getText().toString()
                , groupID, activity_Edit.getText().toString(), thumbnailImageName, completionStatus, children_Edit.getText().toString(), lo_Edit.getText().toString());
        Toast.makeText(getActivity(), "Row inserted has ID = " + newEvidenceID, Toast.LENGTH_SHORT).show();
        //convert Long to int
        evidenceID = String.valueOf(newEvidenceID);
        for (int i=0; i<selectedChildrenIDs.size(); i++)
        {
            k.InsertIntoEvidenceChildTable(Integer.parseInt(selectedChildrenIDs.get(i)), Integer.parseInt(evidenceID));

        }
        for (int i=0; i<selectedLearningOutcomes.size(); i++)
        {
            k.InsertIntoEvidenceLOutcomeTable(Integer.parseInt(evidenceID), selectedLearningOutcomes.get(i));

        }
        //then add insert to join table and assign individual children to the newly created evidence id
        //should have for loop to insert more than child but evidence code is the same

    }

    public String getFormCompletionStatus()
    {
        Boolean formStatus;
        if (thumbnailImageName.isEmpty() || activity_Edit.getText().toString().isEmpty() || comment_Edit.getText().toString().isEmpty()
                || lo_Edit.getText().toString().isEmpty() || children_Edit.getText().toString().isEmpty())
        {
            formStatus=false;
        }
        else{
            formStatus=true;
        }
        return formStatus.toString();
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
        Log.d(TAG, selectedValuesString);
        switch (dialogIdentifier)
        {

            case 2:
            {
                for (int i=0; i<selectedValues.size(); i++)
                {

                    selectedChildrenIDs.add(k.getChildIDByName((String) selectedValues.get(i)));

                }
                children_Edit.setText(selectedValuesString);
                break;
            }
            case 3:
            {
                for (int i=0; i<selectedValues.size(); i++)
                {

                    selectedLearningOutcomes=selectedValues;
                }
                lo_Edit.setText(selectedValuesString);
                break;
            }
        }
    }

    @Override
    public void onNoClick(int toastMessage) {

        Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onYesClickActivity(String selectedActivity, int dialogIdentifier) {
        activity_Edit.setText(selectedActivity);

    }

    public void readDataFromDatabase(int callIdentifier)
    {
        ArrayList<String> selectedEvidenceInfo=new ArrayList<>();
        if (callIdentifier==1)
        {
            selectedEvidenceInfo=k.getEvidenceData(selectedEvidenceID);
            dateView.setText(selectedEvidenceInfo.get(0).toString());
            comment_Edit.setText(selectedEvidenceInfo.get(1).toString());
            activity_Edit.setText(selectedEvidenceInfo.get(2).toString());
            thumbnailImageName=(selectedEvidenceInfo.get(3).toString());
            if (thumbnailImageName.equals(""))
            {
                photo_ImageView.setImageBitmap(null);
                image_Text.setVisibility(View.VISIBLE);

            }
            else {
                File imgFile = new File("/storage/emulated/0/Pictures/KinderThumbnails/" + thumbnailImageName);
                if (imgFile.exists()) {

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    photo_ImageView.setImageBitmap(myBitmap);
                    image_Text.setVisibility(View.VISIBLE);

                } else {
                    photo_ImageView.setImageBitmap(null);
                    image_Text.setVisibility(View.VISIBLE);
                }
            }
            children_Edit.setText(selectedEvidenceInfo.get(4).toString());
            lo_Edit.setText(selectedEvidenceInfo.get(5).toString());

            Log.d("someshBahuguna", String.valueOf(selectedEvidenceInfo));
        }

    }
}