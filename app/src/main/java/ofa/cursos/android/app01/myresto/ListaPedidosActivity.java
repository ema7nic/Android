package ofa.cursos.android.app01.myresto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import ofa.cursos.android.app01.myresto.modelo.Pedido;
import ofa.cursos.android.app01.myresto.modelo.PedidoDAO;
import ofa.cursos.android.app01.myresto.modelo.PedidoDAOMemory;
import ofa.cursos.android.app01.myresto.modelo.ProductoMenu;

public class ListaPedidosActivity extends AppCompatActivity {

    private PedidoDAO pedidoDaoList = new PedidoDAOMemory();
    private ArrayAdapter<Pedido> adaptadorLista;
    private ListView listaPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        Button btnNuevoPedido = findViewById(R.id.btnNuevosPedidos);
        //Intent i = getIntent();
        //pedidoDaoList.agregar((Pedido)i.getSerializableExtra("pedidoActual"));

        listaPedidos = findViewById(R.id.listaPedidos);
        Log.d("APP_MY_RESTO", String.valueOf(pedidoDaoList.listarTodos().size()));
        adaptadorLista = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, pedidoDaoList.listarTodos());

        listaPedidos.setAdapter(adaptadorLista);
        //Toast.makeText(ListaPedidosActivity.this, "Pedido creado"+pedidoDaoList.listarTodos().get(1).toString(), Toast.LENGTH_LONG).show();

        btnNuevoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        this.listaPedidos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Pedido itemValue = (Pedido) listaPedidos.getItemAtPosition(position);

                pedidoDaoList.eliminar(itemValue);
                adaptadorLista.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),
                        "Borrar elemento de posicion :" + position + " Id: " + itemValue.getId() + " nombre: " + itemValue.getNombre(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
