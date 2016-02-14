package at.ac.univie.can;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class DisplayRatesActivity extends ActionBarActivity {

    private TextView finalrate;
    private TextView finalinput;
    private TextView filler;
    private String url = "http://api.fixer.io/latest";
    @Override
    /*
    Bekommt Daten von Parent Activity, startet Request an fixer.io und holt Daten für ausgewählte
    Währung über Volley library
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_rates);
        Intent intent = getIntent();
        final Double input = intent.getExtras().getDouble(MainActivity.extra_string);
        final String getspinner = intent.getExtras().getString(MainActivity.extra_spinner);


        RequestQueue queue = Volley.newRequestQueue(this);

        finalrate= (TextView)findViewById(R.id.finalrate);
        finalinput= (TextView)findViewById(R.id.finalinput);
        filler = (TextView)findViewById(R.id.filler);
        finalinput.setText(input.toString()+" "+"EUR");

        filler.setText("equals");





        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            /*
                            Alle Objekte in Rates
                             */
                            JSONObject jsonrates = response.getJSONObject("rates");
                            /*
                            Double Wert von gewünschter Währung
                             */
                            Double specificRate = jsonrates.getDouble(getspinner);
                            /*
                            Multipliziert mit Eingabe
                             */
                            Double sum = specificRate*input;




                            /*
                            Formatiert Ausgabe auf 2 Dezimalstellen und zeigt an.
                             */

                            finalrate.setText(sum.toString().format("%.2f",sum)+" "+getspinner);
                        }
                        catch (JSONException e){
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        finalrate.setText("Network Error");


                    }

                });
        queue.add(jsObjRequest);
    }
}
