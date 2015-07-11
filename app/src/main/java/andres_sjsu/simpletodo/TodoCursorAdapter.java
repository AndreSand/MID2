package andres_sjsu.simpletodo;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import model.Item;

/**
 * Created by andres on 7/6/15.
 */
public class TodoCursorAdapter extends ArrayAdapter<Item> {
    public TodoCursorAdapter(Context c, List<Item> items) {
        super(c, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItemView todoItemView = (TodoItemView) convertView;
        if (null == todoItemView) {
            todoItemView = TodoItemView.inflate(parent);
        }
        todoItemView.setItem(getItem(position));
        return todoItemView;
    }
}