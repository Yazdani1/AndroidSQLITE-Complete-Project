package yazdaniscodelab.sqlitegrocerycompleteapp.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import yazdaniscodelab.sqlitegrocerycompleteapp.Data.DatabaseHandaler;
import yazdaniscodelab.sqlitegrocerycompleteapp.DetailsActivity;
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

                    int position=getAdapterPosition();

                    Grocery grocery=groceryList.get(position);

                    Intent intent=new Intent(context, DetailsActivity.class);

                    intent.putExtra("name",grocery.getName());
                    intent.putExtra("qty",grocery.getQuentity());
                    intent.putExtra("date",grocery.getDateItemAdded());
                    intent.putExtra("id",grocery.getId());

                    context.startActivity(intent);

                }
            });
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.edit:
                    int position=getAdapterPosition();
                    Grocery grocery=groceryList.get(position);
                    editItem(grocery);
                    break;
                case R.id.deete:
                    int mposition=getAdapterPosition();
                    Grocery mgrocery=groceryList.get(mposition);
                    deleteData(mgrocery.getId());
                    break;
            }
        }

        public void editItem(final Grocery grocery){

            AlertDialog.Builder mydialog=new AlertDialog.Builder(context);
            LayoutInflater inflater=LayoutInflater.from(context);

            View mView=inflater.inflate(R.layout.item,null);
            mydialog.setView(mView);

            final EditText item=mView.findViewById(R.id.item);
            final EditText mquentity=mView.findViewById(R.id.quantity);
            Button btnsave=mView.findViewById(R.id.btnsave);

            final AlertDialog dialog=mydialog.create();

            btnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DatabaseHandaler db=new DatabaseHandaler(context);

                    String nameGrocery=item.getText().toString();
                    String nameQuentity=mquentity.getText().toString();

                    grocery.setName(nameGrocery);
                    grocery.setQuentity(nameQuentity);


                    if (!item.getText().toString().isEmpty() && !mquentity.getText().toString().isEmpty()){
                        db.updateGrocery(grocery);
                        notifyItemChanged(getAdapterPosition(),grocery);
                        dialog.dismiss();
                    }
                }
            });
            dialog.show();

        }

        public void deleteData(final int id){

            AlertDialog.Builder mydialog=new AlertDialog.Builder(context);
            LayoutInflater inflater=LayoutInflater.from(context);

            View mView=inflater.inflate(R.layout.confirmdialog,null);
            mydialog.setView(mView);

            final AlertDialog dialog=mydialog.create();

            Button btnNo=mView.findViewById(R.id.no);
            Button btnYes=mView.findViewById(R.id.yes);

            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHandaler db=new DatabaseHandaler(context);
                    db.deleteGrocery(id);
                    groceryList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }



}
