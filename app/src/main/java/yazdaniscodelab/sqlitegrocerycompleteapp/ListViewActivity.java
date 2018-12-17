package yazdaniscodelab.sqlitegrocerycompleteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import yazdaniscodelab.sqlitegrocerycompleteapp.Data.DatabaseHandaler;
import yazdaniscodelab.sqlitegrocerycompleteapp.Model.Grocery;
import yazdaniscodelab.sqlitegrocerycompleteapp.UI.RecyclerViewAdapter;

public class ListViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Grocery>groceryList;
    private List<Grocery>listofitems;

    private DatabaseHandaler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        recyclerView=findViewById(R.id.recycler_list);
        db=new DatabaseHandaler(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groceryList=new ArrayList<>();
        listofitems=new ArrayList<>();

        groceryList=db.getAllGrocery();

        for (Grocery c:groceryList){
            Grocery grocery=new Grocery();
            grocery.setName(c.getName());
            grocery.setQuentity(c.getQuentity());
            grocery.setId(c.getId());
            grocery.setDateItemAdded(c.getDateItemAdded());
            listofitems.add(grocery);
        }

        recyclerViewAdapter=new RecyclerViewAdapter(this,listofitems);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

    }
}
