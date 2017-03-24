package lyadrienne.generalassemb.git.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityFirstScreen extends AppCompatActivity {

    private TextView mNolist;
    private Button mAdditem;
    private ListView mListView;
    private BaseAdapter mBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_first_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("To Do List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        //Add button once clicked sends the user to the New List Activity to add an item
        mListView = (ListView) findViewById(R.id.itemsinlist);
        mAdditem = (Button) findViewById(R.id.additem);
        mAdditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityFirstScreen.this, NewListActivity.class)); //sends user to New List Activity
            }
        });

        final ListView listView = (ListView) findViewById(R.id.itemsinlist);

//        final ListHolder listHolder = ListHolder.getsInstance(); //instance of singleton
//        if (listHolder.getmListEvent().size() == 0){
//            mNolist.setVisibility(View.VISIBLE); //if there is not items in the list display message
//        } else {
//            mNolist.setVisibility(View.GONE); //if items in list display list
//        }

        mBaseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return ListHolder.getsInstance().getmListEvent().size();
            }

            @Override
            public Object getItem(int position) {
                return ListHolder.getsInstance().getmListEvent().get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    LayoutInflater li = LayoutInflater.from(MainActivityFirstScreen.this);
                    convertView = li.inflate(android.R.layout.simple_list_item_1, null);
                }
                TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
                textView.setText(ListHolder.getsInstance().getmListEvent().get(position).getmListTitle());
                return convertView;
            }

        };

        listView.setAdapter(mBaseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //move from one screen to the next
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivityFirstScreen.this, RevisionListActivity.class); //the intention to move from screens
                intent.putExtra("ID", position); //telling the intent to
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        final int index = intent.getIntExtra("ID", 0);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){ //to remove items from list
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListHolder.getsInstance().getmListEvent().remove(index);
                mBaseAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivityFirstScreen.this, "Item deleted", Toast.LENGTH_SHORT).show();
                return false;
            }

        });
    }

    @Override
    protected void onResume() { //tells the array to update with the new information
        super.onResume();
        mBaseAdapter.notifyDataSetChanged();
    }
}
