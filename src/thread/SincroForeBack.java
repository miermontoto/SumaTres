package thread;

/**
 *
 * @author JuanMier
 */
interface SincroForeBack {
    
    void Run(); // Lanzar el proceso.
    
    // Información que la tarea manda a la interfaz.
    void Start();
    void Finish();
    void Update(); // Indica que el tablero se debe actualizar.
    void Progress(int val); // Indica el progreso del loop.
    boolean Stop(); // Si retorna verdadero, el proceso debe terminar.
    
}
