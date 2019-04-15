package dao;

import java.util.List;
import modelo.Estudiante;


public interface IEstudiante <T> {
    
     void Registrar (Estudiante estudiante)throws Exception;
     
     void Modificar (Estudiante estudiante)throws Exception;
     
     void Eliminar (Estudiante estudiante) throws Exception;
     
     List<Estudiante> listarEst () throws Exception;
             
    
}
