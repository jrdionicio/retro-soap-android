package net.androidsensei.retro.soap;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView txtResultado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtResultado = (TextView)findViewById(R.id.txtResultado);
		final Spinner spinner = (Spinner) findViewById(R.id.operaciones_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.operaciones_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		Button btnCalcular = (Button)findViewById(R.id.btnResultado);
		final EditText numero1 = (EditText)findViewById(R.id.editTextNumero1);
		final EditText numero2 = (EditText)findViewById(R.id.editTextNumero2);
		
		final MainActivity _act = this;
		btnCalcular.setOnClickListener(new OnClickListener() {			 
		    @Override
		    public void onClick(View v) {
		        WebServiceTask task = new WebServiceTask(_act);
		        
		        task.execute(new String[] {spinner.getSelectedItem().toString(),
		        		numero1.getText().toString(),
		        		numero2.getText().toString()});
		    }
		});		
	}

	public void setResultado(String resultado) {
		txtResultado.setText(resultado);
	}

}
