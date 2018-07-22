package ofa.cursos.android.app01.myresto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import ofa.cursos.android.app01.myresto.modelo.Pedido;
import ofa.cursos.android.app01.myresto.modelo.PedidoDAO;
import ofa.cursos.android.app01.myresto.modelo.PedidoDAOMemory;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private Pedido pedidoActual;
    private PedidoDAO pedidoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pedidoActual = new Pedido();
        txtNombre = findViewById(R.id.txtNombreCliente);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);
        Button btnAddProducto = findViewById(R.id.btnAddProducto);


        pedidoDao = new PedidoDAOMemory();

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidoActual.setNombre(txtNombre.getText().toString());
                pedidoDao.agregar(pedidoActual);

                Intent i = new Intent(MainActivity.this, ListaPedidosActivity.class);
                //i.putExtra("pedidoActual", pedidoActual);
                startActivity(i);

                Toast.makeText(MainActivity.this, "Pedido creado"+ pedidoActual.getNombre(), Toast.LENGTH_LONG).show();
                Log.d("APP_MY_RESTO", "Pedido confirmado!!!! ");
                Log.d("APP_MY_RESTO", pedidoActual.toString());
                pedidoActual = new Pedido();
                txtNombre.setText("");
            }
        });


        btnAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listaMenu= new Intent(MainActivity.this,DetallePedidoActivity.class);
                startActivity(listaMenu);
            }
        });

    }
}
