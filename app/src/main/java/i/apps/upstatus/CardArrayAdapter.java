package i.apps.upstatus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CardArrayAdapter  extends ArrayAdapter<trains> {
    private static final String TAG = "CardArrayAdapter";
     List<trains> cardList = new ArrayList<trains>();

    static class CardViewHolder {
        TextView stn;
        TextView code;
        TextView dis;
        TextView dep;
        TextView arr;
    }

    public CardArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(trains object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public trains getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.train_route, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.stn = row.findViewById(R.id.station);
            viewHolder.code = row.findViewById(R.id.code);
            viewHolder.dis = row.findViewById(R.id.distance);
            viewHolder.dep = row.findViewById(R.id.dip);
             viewHolder.arr = row.findViewById(R.id.arrival);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }
        trains trains = getItem(position);
        viewHolder.stn.setText(trains.getStn());
        viewHolder.code.setText(trains.getCode());
        viewHolder.dis.setText(trains.getDis());
        viewHolder.dep.setText(trains.getDep());
        viewHolder.arr.setText(trains.getArr());
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}