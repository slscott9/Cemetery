package hfad.com.databaserecent;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.w3c.dom.Text;
import java.util.ArrayList;


/*
    CustomAdapter is what we pass the recycler view for its adapter
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> { //custom adapter for recycler view references our card view

    private Context context;
    private Activity activity;
    private ArrayList names, location, graveNums, id;


    CustomAdapter(Activity activity, Context context, ArrayList names, ArrayList location, ArrayList graveNums, ArrayList id){ //When we create this custom adapter in main we pass it the arrays with the data and the context of the app.
        this.context = context;
        this.names = names;
        this.location = location;
        this.graveNums = graveNums;
        this.id = id;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvLocation, tvGraveNum; //Create textview objects so we can get references to them
        LinearLayout mainLayout; //this will reference our my row layout

        public MyViewHolder(@NonNull View itemView) { //then get the references to the textView objects using itemView parameter
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvGraveNum = itemView.findViewById(R.id.tvGraveNums);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false); // my row xml is the separate layout we created for the cardview
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) { //Now we need to set text of each TextView using a holder object


        //When we bind the view holder it will set the text of each TextView inside of our cardview layout with array values of each position
        holder.tvName.setText(String.valueOf(names.get(position)));
        holder.tvLocation.setText(String.valueOf(location.get(position)));
        holder.tvGraveNum.setText(String.valueOf(graveNums.get(position)));


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                 //When an item from the recycler view is clicked we pass the values of the text views in an intent so we can display them to the
                                                                            //user for update activity
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("name", String.valueOf(names.get(position)));
                intent.putExtra("location", String.valueOf(location.get(position)));
                intent.putExtra("graveNum", String.valueOf(graveNums.get(position)));
                intent.putExtra("id", String.valueOf(id.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }
}
