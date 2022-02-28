/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package thread;

/**
 *
 * @author JuanMier
 */
public interface SincroForeBack {
    
    void Run(); // Lanzar el proceso.
    
    // Información que la tarea manda a la interfaz.
    void Start();
    void Finish();
    void Update(); // Indica que el tablero se debe actualizar.
    void Progress(int val); // Indica el progreso del loop.
    boolean Stop(); // Si retorna verdadero, el proceso debe terminar.
    
}
