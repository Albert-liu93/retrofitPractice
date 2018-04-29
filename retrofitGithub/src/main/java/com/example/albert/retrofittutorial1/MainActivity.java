package com.example.albert.retrofittutorial1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context mContext = this;

        final ListView listview = (ListView) findViewById(R.id.listview1);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        GitHubClient client = retrofit.create(GitHubClient.class);

        Call<List<GitHubRepo>> call = client.reposForUser("Albert-liu93");

        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();

                listview.setAdapter(new GitHubRepoAdapter(mContext, repos));

            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(mContext, "Failure!", Toast.LENGTH_LONG).show();
            }
        });





    }

    public class GitHubRepoAdapter extends ArrayAdapter<GitHubRepo> {

        private Context context;
        private List<GitHubRepo> values;

        public GitHubRepoAdapter(Context context, List<GitHubRepo> values) {
            super(context, R.layout.activity_listview, values);

            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;

            if (row == null) {
                LayoutInflater inflater =
                        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.activity_listview, parent, false);
            }

            TextView textView = (TextView) row.findViewById(R.id.label);

            GitHubRepo item = values.get(position);
            String message = item.getName();
            textView.setText(message);

            return row;
        }
    }

}
