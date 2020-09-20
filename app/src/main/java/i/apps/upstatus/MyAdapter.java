package i.apps.upstatus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter  extends ArrayAdapter<Seats> {

    //the list values in the List of type hero
    List<Seats> seatsList;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public MyAdapter(List<Seats> seatsList, Context context, int resource) {
        super(context, resource, seatsList);
        this.seatsList = seatsList;
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
        TextView txtClass = view.findViewById(R.id.txtClass);
        TextView code = view.findViewById(R.id.code);
        TextView avail = view.findViewById(R.id.avail);

        //getting the hero of the specified position
        Seats seats = seatsList.get(position);

        //adding values to the list item
        txtClass.setText(seats.getClss());
        code.setText(seats.getCode());
        avail.setText(seats.getAvail());


        //finally returning the view
        return view;
    }


}
