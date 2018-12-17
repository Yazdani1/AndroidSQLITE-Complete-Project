package yazdaniscodelab.sqlitegrocerycompleteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView name;
    private TextView qnty;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name=findViewById(R.id.name_dts);
        qnty=findViewById(R.id.quntity_dts);
        date=findViewById(R.id.date_dts);

        Intent intent=getIntent();

        String mName=intent.getStringExtra("name");
        String mQnty=intent.getStringExtra("qty");
        String mDate=intent.getStringExtra("date");

        name.setText(mName);
        qnty.setText(mQnty);
        date.setText(mDate);

    }
}
