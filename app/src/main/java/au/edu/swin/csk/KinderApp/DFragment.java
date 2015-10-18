package au.edu.swin.csk.KinderApp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Somesh on 9/11/2015.
 */
public class DFragment extends DialogFragment {

    int groupId;
    int dialogIdentifier;
    private DialogClickListener callback;
    KinderDBCon k;
    private static final String TAG="Somesh/ Dfragment";
    protected String[] activityNames = new String[]{"Cooking", "Running", "Sports", "Drinking", "Working", "Studying"};
    protected String[] childrenNames;
    protected String[] loNames = new String[]{"LO 1","LO 2","LO 3","LO 4","LO 5","LO 6","LO 7","LO 8","LO 9" };
    ArrayList mSelectedActivities;
    ArrayList mSelectedChildren;
    ArrayList mSelectedLO;
    AlertDialog.Builder builder;
    String selectedActivity;
    ArrayList preSelectedValues;
    Boolean SelectedValues;

    //The following interface is used to pass the data back to the pictureFragment
    public interface DialogClickListener {
         void onYesClick(ArrayList selectedValues, int dialogIdentifier);
         void onNoClick(int toastMessage);
         void onYesClickActivity(String selectedActivity, int dialogIdentifier);

    }

    public static DFragment newInstance(int title, int dialogIdentifier, int groupID, ArrayList preSelectedValues) {
        DFragment frag = new DFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putInt("groupID", groupID);
        //args.putStringArrayList("preselectedvalues", preSelectedValues);
        args.putInt("dialogIdentifier", dialogIdentifier);
        frag.setArguments(args);
        return frag;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        groupId=getArguments().getInt("groupID");
        dialogIdentifier=getArguments().getInt("dialogIdentifier");
       // preSelectedValues=getArguments().getStringArrayList("preselectedvalues");
        databaseToArray(groupId, dialogIdentifier, preSelectedValues);
        try {
            callback = (DialogClickListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement DialogClickListener interface");
        }
        int title = getArguments().getInt("title");
        int dialogIdentifier = getArguments().getInt("dialogIdentifier");
        switch (dialogIdentifier) {
            case 1: {


                mSelectedActivities = new ArrayList();
                builder = new AlertDialog.Builder(getActivity());


                builder.setTitle(title)
                        .setSingleChoiceItems(activityNames, -1, new DialogInterface.OnClickListener() {
                            @Override


                            public void onClick(DialogInterface dialog, int which) {
                                // If the user checked the item, add it to the selected items
                                selectedActivity=null;
                                selectedActivity=activityNames[which];

                            }

                        }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onYesClickActivity(selectedActivity, 1);

                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onNoClick(R.string.activity_not_selected);

                    }
                });
                break;

            }

            case 2:
            {

                mSelectedChildren = new ArrayList();
                builder = new AlertDialog.Builder(getActivity());


                builder.setTitle(title)
                        .setMultiChoiceItems(childrenNames, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                   mSelectedChildren.add(childrenNames[which]);

                                }
                                else if(mSelectedChildren.contains(childrenNames[which]))
                                {
                                   mSelectedChildren.remove(childrenNames[which]);

                                }

                            }
                        })
                                // Set the action buttons
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK, so save the mSelectedItems results somewhere
                                // or return them to the component that opened the dialog
                                callback.onYesClick(mSelectedChildren, 2);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                callback.onNoClick(R.string.children_not_selected);


                            }
                        });
                break;
            }
            case 3:
            {
                mSelectedLO = new ArrayList();
                 builder = new AlertDialog.Builder(getActivity());


                builder.setTitle(title)
                        .setMultiChoiceItems(loNames, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedLO.add(loNames[which]);
                                } else if (mSelectedLO.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedLO.remove(Integer.valueOf(which));
                                }

                                else if(mSelectedLO.contains(loNames[which]))
                                {
                                    mSelectedLO.remove(loNames[which]);

                                }
                            }
                        })
                                // Set the action buttons
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK, so save the mSelectedItems results somewhere
                                // or return them to the component that opened the dialog
                                callback.onYesClick(mSelectedLO, 3);

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                callback.onNoClick(R.string.lo_not_selected);

                            }
                        });
                break;
            }
        }
        return builder.create();

    }


    public void databaseToArray(int groupId, int dialogIdentifier, ArrayList preSelectedValues)
    {
/*        if (dialogIdentifier==2 && !preSelectedValues.isEmpty())
        {

        }*/
        Log.d("groupID", String.valueOf(groupId));
        ArrayList<String> childrenNamesArrayList;
        k=new KinderDBCon(getActivity());
        k.open();
        childrenNamesArrayList=k.getAllChildNames(groupId);
        childrenNames=childrenNamesArrayList.toArray(new String[childrenNamesArrayList.size()]);
    }
}