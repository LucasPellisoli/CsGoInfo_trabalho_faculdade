package br.com.lucas.pellisoli.csgostatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {


  private EditText inputName;
  private EditText inputPrice;
  private EditText inputKillAward;
  private EditText inputMagazineSize;
  private Button btnSave;
  private Guns guns;
  private String action;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_formulario);

    this.inputName = findViewById( R.id.input_guns_name );
    this.inputPrice = findViewById( R.id.input_guns_price );
    this.inputKillAward = findViewById( R.id.input_guns_Kill_award );
    this.inputMagazineSize = findViewById( R.id.input_guns_magazine_size );
    this.btnSave = findViewById( R.id.btn_save);
    this.action = this.getIntent().getStringExtra("action");
    if( this.action.equals("edit")){
      loadForm();
    }

    this.btnSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        save();
      }
    });
  }

  private void loadForm(){
    int idGuns = this.getIntent().getIntExtra("idGuns", 0);
    if( idGuns != 0) {
      guns = GunsDAO.getGunsById(this, idGuns);

      this.inputName.setText(guns.getName());
      this.inputPrice.setText(guns.getPrice());
      this.inputKillAward.setText(guns.getKill_award());
      this.inputMagazineSize.setText(guns.getMagazine_size());
    }
  }

  private void save(){

    if (this.action.equals("new")) {
      this.guns = new Guns();
    }

    this.guns.setName(this.inputName.getText().toString());
    this.guns.setPrice(this.inputPrice.getText().toString());
    this.guns.setKill_award(this.inputKillAward.getText().toString());
    this.guns.setMagazine_size(this.inputMagazineSize.getText().toString());

    if( this.action.equals("edit")){
      GunsDAO.edit(this.guns, this);
      finish();
    }else {
      GunsDAO.insert(this.guns, this);
      this.inputName.setText("");
      this.inputPrice.setText("");
      this.inputKillAward.setText("");
      this.inputMagazineSize.setText("");
    }
  }
}