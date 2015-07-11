package andres_sjsu.simpletodo;

import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import model.Item;

public class MainActivity extends ActionBarActivity {
    private ArrayList<Item> items;
    private ListView lvItems;
    private TodoCursorAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);

        items = new ArrayList<Item>();
        readItems();

        itemsAdapter = new TodoCursorAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }

    public void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long rowId) {


                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Are you sure you want to delete this ToDo?");
                adb.setIcon(android.R.drawable.ic_dialog_alert);
                adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Item item = items.remove(position);
                        itemsAdapter.notifyDataSetChanged();
                        item.delete();
                        // saveItems();
                        Toast.makeText(MainActivity.this, "ToDo was deleted", Toast.LENGTH_SHORT).show();
                    } });

                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                       // finish();
                       // Toast.makeText(MainActivity.this, "back", Toast.LENGTH_SHORT).show();

                    } });
                adb.show();

                return true;

            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long rowId) {
                showEditItemDialog(itemsAdapter.getItem(position));
            }
        });

    }

    // Load / Save items

    public void readItems() {
        items.addAll(Item.getAll());
    }

    private void showAddItemDialog() {
        FragmentManager fm = getSupportFragmentManager();
        TodoDialog todoItemDialog = TodoDialog.newInstance(
                "Add Todo Item", null);
        todoItemDialog.setFinishDialogListener(new TodoDialog.TodoItemDialogListener() {
            public void onFinishDialog(String description, Date date,
                                       Item.Priority priority) {
                Item item = new Item(description, date, priority);
                items.add(item);
                itemsAdapter.notifyDataSetChanged();
                item.save();
            }
        });

        todoItemDialog.show(fm, "fragment_todo_item_view");
    }

    private void showEditItemDialog(final Item item) {
        FragmentManager fm = getSupportFragmentManager();
        TodoDialog todoItemDialog = TodoDialog.newInstance(
                "Edit Todo Item", item);
        todoItemDialog.setFinishDialogListener(new TodoDialog.TodoItemDialogListener() {
            public void onFinishDialog(String description, Date date,
                                       Item.Priority priority) {
                item.setDescription(description);
                item.setDueDate(date);
                item.setPriority(priority);
                itemsAdapter.notifyDataSetChanged();
                item.save();
            }
        });

        todoItemDialog.show(fm, "fragment_todo_item_view");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                showAddItemDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}