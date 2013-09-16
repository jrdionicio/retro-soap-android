package net.androidsensei.retro.soap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.os.AsyncTask;

public class WebServiceTask extends AsyncTask<String, Void, Integer> {

	private static final String NAMESPACE = "http://soap.retro.androidsensei.net/";	
	private static final String URL = "http://192.168.1.9:8080/retro-soap/calculatorService";
	
	private MainActivity activity;
	
	public WebServiceTask(MainActivity activity) {
		this.activity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		activity.setResultado("Conectando a WebService...");		
	}
	
	@Override
	protected Integer doInBackground(String... params) {
		
		Integer resultado = -1;
		
		try {
			 
		    // Modelo el request
		    SoapObject request = new SoapObject(NAMESPACE, params[0]);
		    request.addProperty("arg0", Integer.valueOf(params[1]));
		    request.addProperty("arg1", Integer.valueOf(params[2]));
		 
		    // Modelo el Sobre
		    SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);		    
		    sobre.setOutputSoapObject(request);
		 
		    // Modelo el transporte
		    HttpTransportSE transporte = new HttpTransportSE(URL);
		 
		    // Llamada
		    transporte.call(NAMESPACE+params[0], sobre);
		 
		    // Resultado
		    SoapPrimitive r = (SoapPrimitive) sobre.getResponse();
		 
		    resultado = Integer.valueOf(r.toString());
		 
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		//Simulando un mayor tiempo de tardanza del webservice
		//Ya que el webservice y la app se ejecutan en la misma PC el tiempo de respuesta seria super rapido
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}		
		return resultado;
	}

	@Override
	protected void onPostExecute(Integer result) {		
		activity.setResultado("Resultado: " + result);		
	}
	
}
