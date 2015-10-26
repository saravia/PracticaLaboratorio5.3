package com.example.saravia.practicalaboratorio53;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView lblMensaje;
    private ListView lstLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos las referencias a los controles
        lblMensaje = (TextView)findViewById(R.id.LblMensaje);
        lstLista = (ListView)findViewById(R.id.LstLista);

        //Rellenamos la lista con datos de ejemplo
        String[] datos =
                new String[]{"Topicos III","Grafica","Moviles","Tesis I","Cloud"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, datos);

        lstLista.setAdapter(adaptador);

        //Asociamos los menús contextuales a los controles
        registerForContextMenu(lblMensaje);
        registerForContextMenu(lstLista);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        if(v.getId() == R.id.LblMensaje)
            inflater.inflate(R.menu.menu_ctx_etiqueta, menu);
        else if(v.getId() == R.id.LstLista)
        {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo)menuInfo;

            menu.setHeaderTitle(
                    lstLista.getAdapter().getItem(info.position).toString());

            inflater.inflate(R.menu.menu_ctx_lista, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.CtxLblOpc1:
                lblMensaje.setText("Opcion 1 pulsada!");
                return true;
            case R.id.CtxLblOpc2:
                lblMensaje.setText("Opcion 2 pulsada!");
                return true;
            case R.id.CtxLstOpc1:
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Susti con fe", Toast.LENGTH_SHORT);

                toast1.show();
                return true;
            case R.id.CtxLstOpc2:
                lblMensaje.setText("Lista" + info.position + ": Opcion 2 pulsada!");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}