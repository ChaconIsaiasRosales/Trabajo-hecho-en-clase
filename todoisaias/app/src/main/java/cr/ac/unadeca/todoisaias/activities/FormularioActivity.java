package cr.ac.unadeca.todoisaias.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.unadeca.todoisaias.R;
import cr.ac.unadeca.todoisaias.database.modies.TodoTable;


public class FormularioActivity extends AppCompatActivity {

    private TextView lblNombre;
    private TextView lblActividad;

    private EditText txtNombre;
    private EditText txtActividad;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        lblNombre = findViewById(R.id.lblnombre);
        lblActividad = findViewById(R.id.lblactividad);
        txtActividad = findViewById(R.id.txtactividad);
        txtNombre = findViewById(R.id.txtnombre);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

    }

    private boolean validacion(){
        boolean send = true;
        if(txtNombre.getText().toString().isEmpty()){
            send = false;
        }
        if(txtActividad.getText().toString().isEmpty()){
            send = false;
        }
        return send;
    }

    private void guardar(){
        if(validacion()){
            TodoTable registro = new TodoTable();
            registro.nombre = txtNombre.getText().toString();
            registro.actividad = txtActividad.getText().toString();
            registro.save();
            finish();

        }else{
            Toast.makeText(this,
                    getResources().getString(R.string.error_valid),
                    Toast.LENGTH_LONG).show();

        }
    }
}
