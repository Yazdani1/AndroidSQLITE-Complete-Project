package yazdaniscodelab.sqlitegrocerycompleteapp;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import yazdaniscodelab.sqlitegrocerycompleteapp.Data.DatabaseHandaler;
import yazdaniscodelab.sqlitegrocerycompleteapp.Model.Grocery;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnfab;
    private DatabaseHandaler db;

    private EditText item;
    private EditText mquentity;
    private Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DatabaseHandaler(this);

        btnfab=findViewById(R.id.fab_plus_xml);

        btnfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
    }

    public void dialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
        View myview=inflater.inflate(R.layout.item,null);
        builder.setView(myview);

        final AlertDialog dialog=builder.create();

        item=myview.findViewById(R.id.item);
        mquentity=myview.findViewById(R.id.quantity);
        btnsave=myview.findViewById(R.id.btnsave);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!item.getText().toString().isEmpty() && !mquentity.getText().toString().isEmpty()){
                    saveGroceryDb(view);
                }
                //dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void saveGroceryDb(View v){

        Grocery grocery=new Grocery();

        String name=item.getText().toString();
        String quentity=mquentity.getText().toString();

        grocery.setName(name);
        grocery.setQuentity(quentity);

        db.addGrocery(grocery);

        Snackbar.make(v,"Item Added",Snackbar.LENGTH_LONG).show();

        Log.d("Item id:",String.valueOf(db.getGroceryCount()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),ListViewActivity.class));
            }
        },1000);

    }

}
