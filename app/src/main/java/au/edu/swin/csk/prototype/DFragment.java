package au.edu.swin.csk.prototype;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Somesh on 9/11/2015.
 */
public class DFragment extends DialogFragment {

    private static final String TAG="Somesh/ Dfragment";
    protected String[] activityNames = new String[]{"Cooking", "Running", "Sports", "Drinking", "Working", "Studying"};
    protected String[] childrenNames = new String[]{"John", "Ryan", "Daniel", "Charles", "Luke", "Adam", "Ross"};
    protected String[] loNames = new String[]{"LO 1","LO 2","LO 3","LO 4","LO 5","LO 6","LO 7","LO 8","LO 9" };
    ArrayList mSelectedActivities;
    ArrayList mSelectedChildren;
    ArrayList mSelectedLO;
    AlertDialog.Builder builder;
    /*protected boolean checkedActivityNames[] = new boolean[activityNames.length];
    protected boolean checkedChildrenNames[] = new boolean[childrenNames.length];
    protected boolean checkedLoNames[] = new boolean[loNames.length];*/


    public static DFragment newInstance(int title, int dialogIdentifier) {
        DFragment frag = new DFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putInt("dialogIdentifier", dialogIdentifier);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        int dialogIdentifier = getArguments().getInt("dialogIdentifier");
        switch (dialogIdentifier) {
            case 1: {


                mSelectedActivities = new ArrayList();
                builder = new AlertDialog.Builder(getActivity());


                builder.setTitle(title)
                        .setMultiChoiceItems(activityNames, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override

                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedActivities.add(activityNames[which]);
                                    //checkedActivityNames[which]=isChecked;
                                }

                                else if(mSelectedActivities.contains(activityNames[which]))
                                    {
                                        //checkedActivityNames[which]=!isChecked;
                                        mSelectedActivities.remove(activityNames[which]);

                                }

                            }})
                                // Set the action buttons
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK, so save the mSelectedItems results somewhere
                                // or return them to the component that opened the dialog
                                //showBooleanValues(checkedActivityNames);
                                returnDataToPicture(mSelectedActivities, 1);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getActivity(), R.string.activity_not_selected, Toast.LENGTH_SHORT);

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
                                    //checkedChildrenNames[which]=isChecked;
                                    mSelectedChildren.add(childrenNames[which]);

                                }
                                else if(mSelectedChildren.contains(childrenNames[which]))
                                {
                                   // checkedChildrenNames[which]=!isChecked;
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
                                returnDataToPicture(mSelectedChildren, 2);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getActivity(), R.string.children_not_selected, Toast.LENGTH_SHORT);

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
                                    //checkedLoNames[which]=isChecked;
                                    mSelectedLO.add(loNames[which]);
                                } else if (mSelectedLO.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                   // checkedLoNames[which]=!isChecked;
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
                                returnDataToPicture(mSelectedLO, 3);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getActivity(), R.string.lo_not_selected, Toast.LENGTH_SHORT);

                            }
                        });
                break;
            }
        }
        return builder.create();

    }

    public void returnDataToPicture(ArrayList selectedValues, int dialogIdentifier)
    {
        String s="";
        for(int i=0; i<selectedValues.size(); i++)
        {
            s= s  + selectedValues.get(i) + ", ";
        }
        Picture callingActivity = (Picture) getActivity();
        callingActivity.onUserSelectValue(s.substring(0, s.lastIndexOf(",")), dialogIdentifier);
    }
/*
    public void showBooleanValues(boolean mCheckedActivityNames[])
    {
        for(int i=0; i<mCheckedActivityNames.length; i++)
        {
            Log.d(TAG, String.valueOf(mCheckedActivityNames[i]));

        }
    }*/
}