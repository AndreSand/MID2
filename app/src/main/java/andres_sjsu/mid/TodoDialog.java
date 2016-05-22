package andres_sjsu.mid;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Date;

import model.Item;

/**
 * Created by andres on 7/7/15.
 */
public class TodoDialog extends DialogFragment {
    private EditText mEditText;
    private Spinner mPrioSpinner;
    private DatePicker mDatePicker;
    public Item todoItem;

    public interface TodoItemDialogListener {
        void onFinishDialog(String description, Date date, Item.Priority priority);
    }

    private TodoItemDialogListener mListener;

    public void setFinishDialogListener(TodoItemDialogListener listener) {
        mListener = listener;
    }

    public TodoDialog() {
    }

    @SuppressWarnings("deprecation")
    public static TodoDialog newInstance(String title, Item item) {
        TodoDialog frag = new TodoDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        if (item != null) {
            args.putString("description", item.getDescription());
            Date date = item.getDueDate();
            args.putInt("year", date.getYear());
            args.putInt("month", date.getMonth());
            args.putInt("day", date.getDate());

            args.putInt("priority", item.getPriority().ordinal());
        } else {
            args.putString("description", "");
            args.putInt("priority", 1);
        }

        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_item_view, container);
        String title = getArguments().getString("title", "");
        getDialog().setTitle(title);

        // Set task description
        mEditText = (EditText) view.findViewById(R.id.txt_task_description);
        mEditText.setText(getArguments().getString("description", ""));

        AdView mAdView = (AdView)view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Set priority
        mPrioSpinner = (Spinner) view.findViewById(R.id.spn_priority);
        int priority = getArguments().getInt("priority");
        mPrioSpinner.setSelection(priority);

        // Set due date
        mDatePicker = (DatePicker) view.findViewById(R.id.dp_due_date);
        int year = getArguments().getInt("year", 0);

        if (year != 0) {
            int month = getArguments().getInt("month");
            int day = getArguments().getInt("day");

            mDatePicker.updateDate(year + 1900, month, day);
        }

        final Button button = (Button) view.findViewById(R.id.btn_save_item);

        //Description checker don't accept empty field
        /*
        String strUserName = mEditText.getText().toString();

        if(strUserName.equals("")) {
            mEditText.setError("Your message");

        } else {
            button.setEnabled(true);
        }
*/

        //save button below
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();

                @SuppressWarnings("deprecation")
                Date date = new Date(mDatePicker.getYear() - 1900, mDatePicker
                        .getMonth(), mDatePicker.getDayOfMonth());
                Item.Priority priority = Item.Priority.values()[mPrioSpinner.getSelectedItemPosition()];
                mListener.onFinishDialog(mEditText.getText().toString(), date,
                        priority);
            }
        });

        return view;
    }

}