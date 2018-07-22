package ofa.cursos.android.app01.myresto.modelo;

import java.util.List;

public interface ProductoDao {
    List<ProductoMenu> listarMenu();
    void cargarDatos(String[] datos);
}
