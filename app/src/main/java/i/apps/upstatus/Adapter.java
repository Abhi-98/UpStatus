package i.apps.upstatus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter<trains> {

    //the list values in the List of type hero
    List<trains> trainsList;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values


    public Adapter(List<trains> trainsList, Context context, int resource) {
        super(context,resource,trainsList);
        this.trainsList = trainsList;
        this.context = context;
        this.resource = resource;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        TextView stn = view.findViewById(R.id.station);
        TextView code = view.findViewById(R.id.code);
        TextView dis = view.findViewById(R.id.distance);
        TextView dep = view.findViewById(R.id.dip);
        TextView arr = view.findViewById(R.id.arrival);

        //getting the hero of the specified position
        trains trains = trainsList.get(position);

        //adding values to the list item
        stn.setText(trains.getStn());
        code.setText(trains.getCode());
        dis.setText(trains.getDis());
        dep.setText(trains.getDep());
        arr.setText(trains.getArr());


        //finally returning the view
        return view;
    }


}