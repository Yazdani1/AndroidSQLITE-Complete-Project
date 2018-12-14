package yazdaniscodelab.sqlitegrocerycompleteapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnfab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        EditText item=myview.findViewById(R.id.item);
        EditText quentity=myview.findViewById(R.id.quantity);
        Button btnsave=myview.findViewById(R.id.btnsave);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveGroceryDb(view);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void saveGroceryDb(View v){

    }


}
