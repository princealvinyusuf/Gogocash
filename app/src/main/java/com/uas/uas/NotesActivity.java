package com.uas.uas;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.uas.uas.activity.Adapter;
import com.uas.uas.activity.ApiInterface;
import com.uas.uas.activity.Notes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private List<Notes> notesList;
    ApiInterface apiInterface;
    Adapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {

                Intent intent = new Intent(NotesActivity.this, EditorActivity.class);
                intent.putExtra("id", notesList.get(position).getId());
                intent.putExtra("name", notesList.get(position).getName());
                intent.putExtra("deskripsi", notesList.get(position).getDeskripsi());
                intent.putExtra("picture", notesList.get(position).getPicture());
                startActivity(intent);

            }



            @Override
            public void onLoveClick(View view, int position) {

                final int id = notesList.get(position).getId();
                final Boolean love = notesList.get(position).getLove();
                final ImageView mLove = view.findViewById(R.id.love);

                if (love){
                    mLove.setImageResource(R.drawable.likeof);
                    notesList.get(position).setLove(false);
                    updateLove("update_love", id, false);
                    adapter.notifyDataSetChanged();
                } else {
                    mLove.setImageResource(R.drawable.likeon);
                    notesList.get(position).setLove(true);
                    updateLove("update_love", id, true);
                    adapter.notifyDataSetChanged();
                }

            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotesActivity.this, EditorActivity.class));
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Notes...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    public void getNotes(){

        Call<List<Notes>> call = apiInterface.getNotes();
        call.enqueue(new Callback<List<Notes>>() {
            @Override
            public void onResponse(Call<List<Notes>> call, Response<List<Notes>> response) {
                progressBar.setVisibility(View.GONE);
                notesList = response.body();
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                adapter = new Adapter(notesList, NotesActivity.this, listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Notes>> call, Throwable t) {
                Toast.makeText(NotesActivity.this, "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateLove(final String key, final int id, final Boolean love){

        Call<Notes> call = apiInterface.updateLove(key, id, love);

        call.enqueue(new Callback<Notes>() {
            @Override
            public void onResponse(Call<Notes> call, Response<Notes> response) {

                Log.i(MainActivity.class.getSimpleName(), "Response "+response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    Toast.makeText(NotesActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NotesActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Notes> call, Throwable t) {
                Toast.makeText(NotesActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNotes();
    }

}
