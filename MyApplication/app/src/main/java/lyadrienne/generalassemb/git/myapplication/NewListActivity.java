package lyadrienne.generalassemb.git.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewListActivity extends AppCompatActivity {

    EditText mTitle;
    Button mSubmitTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar); // toolbar for title
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("To Do List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);


        final ListHolder listHolder = ListHolder.getsInstance();

        mTitle = (EditText) findViewById(R.id.listtitle); //Title of new list
        mSubmitTitle = (Button) findViewById(R.id.submitnewlist); //submit button for title

        mSubmitTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mTitle.getText().toString().isEmpty()){
                   finish();
                } else {
                    listHolder.addListHolder(new Listlist(mTitle.getText().toString())); //gets the text to add to the Singleton
                    Intent intent = new Intent(NewListActivity.this, RevisionListActivity.class); //move data from here to there
                    intent.putExtra("ID", ListHolder.getsInstance().getmListEvent().size()-1); // telling the intent where to put the new title. set ID, Singleton, getinstance, add to the list, add it to the last position
                    startActivity(intent); //send information to revision list activity
                    finish(); //clears data from previous screen
                }

            }
        });

    }
}
