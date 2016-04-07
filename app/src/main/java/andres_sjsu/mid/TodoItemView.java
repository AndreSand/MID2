package andres_sjsu.mid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;

import model.Item;

/**
 * Created by andres on 7/7/15.
 */
public class TodoItemView extends RelativeLayout {
    private TextView mDetailsView;
    private TextView mPriorityView;
    private TextView mDueDateView;

    public TodoItemView(Context c) {
        this(c, null);
    }

    public TodoItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TodoItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.item_todo,
                this, true);
        setupChildren();
    }

    public static TodoItemView inflate(ViewGroup parent) {
        TodoItemView todoItemView = (TodoItemView) LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_list,
                parent, false);
        return todoItemView;
    }

    private void setupChildren() {
        mDetailsView = (TextView) findViewById(R.id.todoitem_DescTextView);
        mDetailsView.setTextColor(getResources().getColor(R.color.light_blue));
        mDueDateView = (TextView) findViewById(R.id.todoitem_DueDateTextView);
        mPriorityView = (TextView) findViewById(R.id.todoitem_PriorityTextView);
    }

    public void setItem(Item item) {
        mDetailsView.setText(item.getDescription());
        mDueDateView.setText(item.getDueDateString());
        switch (item.getPriority()) {
            case LOW_PRIORITY:
                mPriorityView.setText("Low");
                mPriorityView.setTextColor(getResources().getColor(R.color.black));
                mPriorityView.setVisibility(View.VISIBLE);
                break;
            case NORMAL_PRIORITY:
                mPriorityView.setVisibility(View.GONE);
                break;
            case HIGH_PRIORITY:
                mPriorityView.setText("High");
                mPriorityView.setVisibility(View.VISIBLE);
                mPriorityView.setTextColor(getResources().getColor(R.color.black));
                break;
        }
        Date dueDate = item.getDueDate();
        Date now = new Date();
        if (dueDate.before(now)) {
            mDueDateView.setTextColor(getResources().getColor(R.color.black));
        } else {
            mDueDateView.setTextColor(getResources().getColor(R.color.black));
        }
    }
}