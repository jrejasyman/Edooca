package dao;

import java.util.List;


public interface IEstudiante <T> {
    
     void Registrar (T modelos)throws Exception;
     
     void Modificar (T modelos)throws Exception;
     
     void Eliminar (T modelos) throws Exception;
     
     List<T> listar (T modelos) throws Exception;
             
    
}
