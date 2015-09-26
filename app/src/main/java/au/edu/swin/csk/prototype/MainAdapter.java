package au.edu.swin.csk.prototype;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fahadyousef on 25/09/15.
 */
public class MainAdapter extends BaseAdapter
{
    private static final String TAG = "dd" ;
    ArrayList<Card> list;
    Context context;
    KinderDBCon k;
    int groupID;

    MainAdapter(Context c, KinderDBCon k , int groupID)
    {
        this.context = c;
        this.k = k;
        this.groupID = groupID;

        list = new ArrayList<Card>();
        //Resources res = context.getResources();
        int[] activityImages = {R.drawable.cooking1, R.drawable.cooking2, R.drawable.cooking3,
                R.drawable.cooking4, R.drawable.cooking5, R.drawable.cooking6, R.drawable.cooking7,
                R.drawable.cooking8, R.drawable.cooking9, R.drawable.cooking10,
                R.drawable.cooking11, R.drawable.cooking12, R.drawable.cooking12,
                R.drawable.cooking12, R.drawable.cooking12, R.drawable.cooking12,
                R.drawable.cooking12, R.drawable.cooking12};

        ArrayList<String> evidenceDateActivity;
        evidenceDateActivity = k.getEvidenceInfo(groupID);

//        String[] tempChildNames = res.getStringArray(R.array.childArray);
//        String[] tempActivityNames = res.getStringArray(R.array.activityArray);

        for (int i=0; i<evidenceDateActivity.size() ; i++)
        {
            Card tempCard = new Card(activityImages[i], evidenceDateActivity.get(i));
            list.add(tempCard);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder
    {
        ImageView cardImage;
        TextView cardDate;
        TextView cardActivity;

        ViewHolder(View v)
        {
            cardImage = (ImageView) v.findViewById(R.id.imageView);
            cardActivity = (TextView) v.findViewById(R.id.cardActivity);
            cardDate = (TextView) v.findViewById(R.id.cardDate);

        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder;
        if (row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item_main, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        Card temp = list.get(i);
        holder.cardImage.setImageResource(temp.imageId);
        holder.cardDate.setText(temp.date);
        holder.cardActivity.setText(temp.activityName);
        return row;
    }

}
