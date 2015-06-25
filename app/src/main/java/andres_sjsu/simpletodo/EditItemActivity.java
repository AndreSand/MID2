package andres_sjsu.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by andres on 6/19/15.
 */
public class EditItemActivity extends Activity {
    private final int REQUEST_CODE = 20;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        final EditText txtProduct = (EditText) findViewById(R.id.editText);
        Bundle extras = getIntent().getExtras();

        Intent i = getIntent();
        // getting attached intent data
        String Item = extras.getString("Item");
        final String id = extras.getString("id");
        // displaying selected product name
        txtProduct.setText(Item);

        Button SaveBttn = (Button) findViewById(R.id.SaveButton);
        //setup button listener
        SaveBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Prepare data intent
                Intent data = new Intent();
                // Pass relevant data back as a result
                data.putExtra("Item", txtProduct.getText().toString());
                data.putExtra("id", String.valueOf(id));
                data.putExtra("code", 200); // ints work too
                // Activity finished ok, return the data
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes the activity, pass data to parent
            }
             });
    }
}