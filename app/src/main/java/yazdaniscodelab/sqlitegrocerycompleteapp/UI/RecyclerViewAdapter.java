package yazdaniscodelab.sqlitegrocerycompleteapp.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import yazdaniscodelab.sqlitegrocerycompleteapp.Model.Grocery;
import yazdaniscodelab.sqlitegrocerycompleteapp.R;

/**
 * Created by Yazdani on 12/17/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Grocery> groceryList;

    public RecyclerViewAdapter(Context context, List<Grocery> groceryList) {
        this.context = context;
        this.groceryList = groceryList;
    }


    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent,false);

        return new MyViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Grocery grocery=groceryList.get(position);
        holder.groceryItemName.setText(grocery.getName());
        holder.quentity.setText(grocery.getQuentity());
        holder.dateadded.setText(grocery.getDateItemAdded());

    }


    @Override
    public int getItemCount() {
        return groceryList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView groceryItemName;
        public TextView quentity;
        public TextView dateadded;
        public Button edtButton;
        public Button deleteButton;

        public int id;

        public MyViewHolder(View itemView,Context ctx) {
            super(itemView);
            context=ctx;

            groceryItemName=itemView.findViewById(R.id.name);
            quentity=itemView.findViewById(R.id.qty);
            dateadded=itemView.findViewById(R.id.date);
            edtButton=itemView.findViewById(R.id.edit);
            deleteButton=itemView.findViewById(R.id.deete);

            edtButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.edit:
                    break;
                case R.id.deete:

                    break;
            }
        }
    }



}
