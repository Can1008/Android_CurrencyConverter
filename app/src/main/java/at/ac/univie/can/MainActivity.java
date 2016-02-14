package at.ac.univie.can;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class MainActivity extends ActionBarActivity {
    public final static String extra_string = "at.ac.univie.can.extrastring";
    public final static String extra_spinner = "at.ac.univie.can.extraspinner";


    public String spinnerselect;

    /*
    Bindet values>strings>string-array mit Spinner über Adapter
    R.layout.spinner_edit und R.layout.spinner_drop_edit sind custom layouts
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.rates_array, R.layout.spinner_edit);
        adapter.setDropDownViewResource(R.layout.spinner_drop_edit);
        spinner.setAdapter(adapter);
    }

    /*
    showRates, Methode bei button_onclick, erkennt Usereingabe > wandelt in String um > Fehler-kontrolle > bei gültiger Eingabe
    > startet neue Activity und übergibt ausgewählte Währung und Usereingabe
     */

    public void showRates(View view)
    {
        EditText userinput = (EditText) findViewById(R.id.editText);
        String checkinput = userinput.getText().toString();
        if (checkinput.trim().isEmpty())
        {
            Toast.makeText(this,"Enter a valid Value",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this,DisplayRatesActivity.class);

            Spinner spinner = (Spinner)findViewById(R.id.spinner);
            Double input = Double.parseDouble(userinput.getText().toString());
            spinnerselect= spinner.getSelectedItem().toString();
            intent.putExtra(extra_string,input);
            intent.putExtra(extra_spinner,spinnerselect);
            startActivity(intent);
        }


    }



}
