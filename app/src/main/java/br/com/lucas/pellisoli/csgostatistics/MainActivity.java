package br.com.lucas.pellisoli.csgostatistics;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

  private ListView lvGuns;
  private ArrayAdapter adapter;
  private List<Guns> listGuns;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        intent.putExtra("action", "new");
        startActivity( intent );
      }
    });

    this.lvGuns = findViewById(R.id.lvGuns);

    loadGuns();
    configListView();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
  private void configListView(){
    lvGuns.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Guns gunsSelectd = listGuns.get(position);
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        intent.putExtra("action", "edit");
        intent.putExtra("idGuns", gunsSelectd.getId() );
        startActivity( intent );
      }
    });

    lvGuns.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        Guns gunsSelectd = listGuns.get(position);
        deleteGuns( gunsSelectd );
        return true;
      }
    });
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    loadGuns();
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  private void loadGuns(){
    this.listGuns = GunsDAO.getGunsList(this);
    adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listGuns);
    lvGuns.setAdapter( adapter );
  }

  private void deleteGuns( Guns guns ){
    AlertDialog.Builder alerta = new AlertDialog.Builder(this);
    alerta.setIcon(android.R.drawable.ic_input_delete);
    alerta.setTitle(R.string.dialog_delete_title);
    alerta.setMessage(getString(R.string.dialog_delete_text, guns.getName()));
    alerta.setNeutralButton(R.string.dialog_delete_bnt_cancel, null);
    alerta.setPositiveButton(R.string.dialog_delete_bnt_confirm, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        GunsDAO.delete( guns.getId(), MainActivity.this);
        loadGuns();
      }
    });
    alerta.show();
  }
}