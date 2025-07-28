import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



public class PacienteTest {

    private String cedulaValida;
    private String nombre;
    private String correo;
    private Paciente paciente;

    @BeforeEach
    public void setUp() {

        cedulaValida = "1234567890";
        nombre = "Juan Pérez";
        correo = "juan.perez@email.com";
        paciente = null;


    }

    @AfterEach
    public void tearDown() {

        cedulaValida = null;
        nombre = null;
        correo = null;
        paciente = null;


    }

    @Test

    public void testP1_CrearPacienteConDatosValidos() {

        paciente = new Paciente(cedulaValida, nombre, correo);

        // Assert - Verificar que se creó correctamente
        assertNotNull(paciente, "El paciente debe ser creado correctamente");
        assertEquals(cedulaValida, paciente.getCedula(), "La cédula debe coincidir");
        assertEquals(nombre, paciente.getNombre(), "El nombre debe coincidir");
        assertEquals(correo, paciente.getCorreo(), "El correo debe coincidir");

        // Verificar que el toString funciona correctamente
        String expectedToString = nombre + " (" + cedulaValida + ")";
        assertEquals(expectedToString, paciente.toString(), "El toString debe tener el formato correcto");
    }

    @Test
    public void testP2_CrearPacienteConCedulaVacia() {

        String cedulaVacia = "";

        assertThrows(IllegalArgumentException.class, () -> {
            paciente = new Paciente(cedulaVacia, nombre, correo);
        }, "Debe lanzar IllegalArgumentException cuando la cédula está vacía");

    }
}