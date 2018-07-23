package ofa.cursos.android.app01.myresto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import ofa.cursos.android.app01.myresto.modelo.DetallePedido;
import ofa.cursos.android.app01.myresto.modelo.ProductoDAOMemory;
import ofa.cursos.android.app01.myresto.modelo.ProductoDao;
import ofa.cursos.android.app01.myresto.modelo.ProductoMenu;

public class DetallePedidoActivity extends AppCompatActivity {

    private ProductoDao productoDao;
    private ArrayAdapter<ProductoMenu> adaptadorLista;
    private ListView listpro;
    private ProductoMenu productoElegido;

    private TextView txtCantidad;
    private View  btnMenosProducto;
    private View  btnMasProducto;
    private View btnAgregarProducto;
    private Integer cantidadProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido);

        cantidadProducto = 0;
        btnMasProducto = findViewById(R.id.btnMasProducto);
        btnMenosProducto = findViewById(R.id.btnMenosProducto);
        btnAgregarProducto = findViewById(R.id.btnAddProducto);
        txtCantidad = findViewById(R.id.detPedProductoCantidad);

        txtCantidad.setText(cantidadProducto.toString());

        listpro = findViewById(R.id.listaProductos);
        productoDao = new ProductoDAOMemory();

        String[] listaProductos = getResources().getStringArray(R.array.listaProductos);
        productoDao.cargarDatos(listaProductos);
        adaptadorLista = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_single_choice, this.productoDao.listarMenu());
        listpro.setAdapter(adaptadorLista);

        listpro.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listpro.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        productoElegido = (ProductoMenu) parent.getAdapter().getItem(position);
                        Toast.makeText(DetallePedidoActivity.this, productoElegido.getNombre(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        btnMenosProducto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cantidadProducto > 0) {
                            cantidadProducto--;
                            if(cantidadProducto==0){
                                btnAgregarProducto.setEnabled(false);
                            }
                            txtCantidad.setText(cantidadProducto.toString());
                        }

                    }
                });

        btnMasProducto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cantidadProducto++;
                        btnAgregarProducto.setEnabled(true);
                        txtCantidad.setText(cantidadProducto.toString());
                    }
                });


        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("producto", productoElegido);
                intent.putExtra("cantidad", cantidadProducto);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
