package au.edu.swin.csk.prototype;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fahad on 9/09/2015.
 */
public class Card {


    String childName;
    String activityName;
    int imageId;

    Card(int imageId, String childName, String activityName)
    {
        this.imageId = imageId;
        this.childName = childName;
        this.activityName = activityName;
    }
}
class mainAdapter extends BaseAdapter
{
    ArrayList<Card> list;
    Context context;

    mainAdapter(Context context)
    {
        this.context = context;
        list = new ArrayList<Card>();
        Resources res = context.getResources();
        int[] activityImages = {R.drawable.cooking1, R.drawable.cooking2, R.drawable.cooking3,
                R.drawable.cooking4, R.drawable.cooking5, R.drawable.cooking6, R.drawable.cooking7,
                R.drawable.cooking8, R.drawable.cooking9, R.drawable.cooking10,
                R.drawable.cooking11, R.drawable.cooking12};

        String[] tempChildNames = res.getStringArray(R.array.childArray);
        String[] tempActivityNames = res.getStringArray(R.array.activityArray);

        for (int i=0; i<12 ; i++)
        {
            Card tempCard = new Card(activityImages[i], tempChildNames[i], tempActivityNames[i]);
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
        TextView cardText;
        TextView cardActivity;
        ViewHolder(View v)
        {
            cardImage = (ImageView) v.findViewById(R.id.imageView);
            cardText = (TextView) v.findViewById(R.id.cardChild);
            cardActivity = (TextView) v.findViewById(R.id.cardActivity);

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
        holder.cardText.setText(temp.childName);
        holder.cardActivity.setText(temp.activityName);

        return row;
    }
}
