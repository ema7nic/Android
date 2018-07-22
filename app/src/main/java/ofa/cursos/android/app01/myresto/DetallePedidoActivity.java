package ofa.cursos.android.app01.myresto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import ofa.cursos.android.app01.myresto.modelo.ProductoDAOMemory;
import ofa.cursos.android.app01.myresto.modelo.ProductoDao;
import ofa.cursos.android.app01.myresto.modelo.ProductoMenu;

public class DetallePedidoActivity extends AppCompatActivity {

    private ProductoDao productoDao;
    private ArrayAdapter<ProductoMenu> adaptadorLista;
    private ListView listpro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);
        listpro = findViewById(R.id.listaProductos);
        productoDao = new ProductoDAOMemory();

        String[] listaProductos = getResources().getStringArray(R.array.listaProductos);
        productoDao.cargarDatos(listaProductos);

        adaptadorLista = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_single_choice, this.productoDao.listarMenu());
        listpro.setAdapter(adaptadorLista);
    }
}
