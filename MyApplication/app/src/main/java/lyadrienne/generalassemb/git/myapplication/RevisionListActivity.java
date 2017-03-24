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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RevisionListActivity extends AppCompatActivity {

    EditText mRevisionItem, mRevisionDescrip; //item and description entered by user on the revision list activity page.
    Button mSubmitFromRevision; //submit button for the revision list activity page
    ListView mItemView;
    BaseAdapter mArrayAdapter2;


    //TODO ability to delete and edit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_list);

        Intent intent = getIntent();
        final int index = intent.getIntExtra("ID", -1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("To Do List: "+ ListHolder.getsInstance().getmListEvent().get(index).getListTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        mRevisionItem = (EditText) findViewById(R.id.revisionitem);
        mRevisionDescrip = (EditText) findViewById(R.id.revisiondescrip);
        mSubmitFromRevision = (Button) findViewById(R.id.submitfromrevision);
        mItemView = (ListView) findViewById(R.id.indivitemslist);

        final ListHolder listHolder = ListHolder.getsInstance();

        ListView listView = (ListView) findViewById(R.id.indivitemslist);


        //final int index = intent.getIntExtra("ID", -1); //intent for the index of the ArrayList

        mSubmitFromRevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRevisionItem.getText().toString().isEmpty()){ //if string empty show error message
                    Toast.makeText(RevisionListActivity.this, "Please enter an item", Toast.LENGTH_SHORT).show();
                } else {
                    Listlist2 listlist2 = new Listlist2(mRevisionItem.getText().toString(), mRevisionDescrip.getText().toString()); //sends information to the singleton.
                    ListHolder.getsInstance().getmListEvent().get(index).addmListlist2(listlist2); //singleton instance of add to list go to this index add to listlist
                    mArrayAdapter2.notifyDataSetChanged();
                    mRevisionItem.getText().clear();
                    mRevisionDescrip.getText().clear();
                }
            }

        });

        mItemView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){ //to remove items from list
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListHolder.getsInstance().getmListEvent().get(index).getListlist2().remove(position);
                mArrayAdapter2.notifyDataSetChanged();
                Toast.makeText(RevisionListActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                return false;
            }

        });
        mItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //edit item
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Listlist2 itemsList = ListHolder.getsInstance().getmListEvent().get(index).getListlist2().get(position);
                mRevisionItem.setText(itemsList.getItemTitle());
                mRevisionDescrip.setText(itemsList.getItemDescrip());
//                mArrayAdapter2.notifyDataSetChanged();
//                mRevisionItem.getText().clear();
//                mRevisionDescrip.getText().clear();
            }
        });

        mArrayAdapter2 = new BaseAdapter() {
            @Override
            public int getCount() {
                return ListHolder.getsInstance().getmListEvent().get(index).getListlist2().size();
            } //this will populate the list with objects from the holder. -> get index -> get List -> size

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
                View v = convertView;
                if(convertView == null){
                    LayoutInflater li = LayoutInflater.from(RevisionListActivity.this);
                    v = li.inflate(android.R.layout.simple_list_item_2, null);
                }
                Listlist2 currentitem = ListHolder.getsInstance().getmListEvent().get(index).getListlist2().get(position);
                //variable to hold the path to the item
                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(currentitem.getItemTitle()); //setting text using the variable
                TextView textView1 = (TextView) v.findViewById(android.R.id.text2);
                textView1.setText(currentitem.getItemDescrip());
                return v;
            }

        };
        mItemView.setAdapter(mArrayAdapter2); //refreshes the array
    }
}
