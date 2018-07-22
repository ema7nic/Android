package ofa.cursos.android.app01.myresto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ofa.cursos.android.app01.myresto.modelo.DetallePedido;
import ofa.cursos.android.app01.myresto.modelo.Pedido;
import ofa.cursos.android.app01.myresto.modelo.PedidoDAO;
import ofa.cursos.android.app01.myresto.modelo.PedidoDAOMemory;
import ofa.cursos.android.app01.myresto.modelo.ProductoMenu;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private Pedido pedidoActual;
    private PedidoDAO pedidoDao;

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==999){
                Integer cantidad = data.getIntExtra("cantidad",0);
                ProductoMenu prod= data.getParcelableExtra("producto");
                DetallePedido detalle = new DetallePedido();
                detalle.setCantidad(cantidad);
                detalle.setProductoPedido(prod);
                pedidoActual.addItemDetalle(detalle);
                EditText txtDetalle = findViewById(R.id.multiText);
                txtDetalle.setText(
                        txtDetalle.getText()
                        .append(
                                prod.getNombre()+ " $"+
                                        (prod.getPrecio()*cantidad)+"\r\n"
                        ).toString()
                );
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pedidoActual = new Pedido();
        txtNombre = findViewById(R.id.txtNombreCliente);
        View  btnConfirmar = findViewById(R.id.btnConfirmar);
        View  btnAddProducto = findViewById(R.id.btnBuscarProducto);


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
                startActivityForResult(listaMenu,999);
            }
        });
    }
}
