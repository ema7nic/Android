package ofa.cursos.android.app01.myresto.modelo;

import java.util.List;

public interface PedidoDAO {
    void agregar(Pedido pedido);
    void eliminar(Pedido pedido);
    List<Pedido> listarTodos();
}


