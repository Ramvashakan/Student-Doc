package com.valaithalam.studoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import static android.provider.AlarmClock.EXTRA_MESSAGE;





public class profile extends AppCompatActivity {

    String[] scheme_name = {"National Scholarship For School Students","All India Talent search exam for school students"
            ,"First graduates scholarship","Madras university Fess Education",
            "TamilNadu Government scholarship Schemes","Talent Based Scholarship","Central government scholarship schemes"
            ,"Central government organization "," Women students scholarship","Muslim students scholarship",
            "PG studies scholarship","Sports students scholarship","Private organizations scholarship in India","Educational loan "
            ,"Differently abled students scholarship","Fellowship for Phd and research"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,scheme_name);


        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                Intent intent = new Intent(profile.this, National_sclship.class);
                String message = "abc";
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(profile.this, MainActivity.class));
                finish();
                //return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
