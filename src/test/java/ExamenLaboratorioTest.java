import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExamenLaboratorioTest {
    private ExamenLaboratorio examen;
    private Paciente paciente;

    @BeforeEach
    void setUp() {
        paciente = new Paciente("1234567890", "Juan Pérez", "juan.perez@email.com");
        examen = new ExamenLaboratorio(paciente, "Hemograma");
    }

    @AfterEach
    void tearDown() {
        paciente = null;
        examen = null;
    }

    @Test
    void registrarResultado() {
        String resultadoValido = "Valores normales";

        examen.registrarResultado(resultadoValido);

        String output = examen.toString();
        assertTrue(output.contains(resultadoValido),
                "El resultado debería estar registrado correctamente");
        assertFalse(output.contains("Pendiente"),
                "El estado no debería seguir siendo 'Pendiente'");
    }

    @Test
    void testToString() {
        String output = examen.toString();

        assertTrue(output.contains("Pendiente"),
                "El toString debería incluir 'Pendiente' para examen sin resultado");

        assertTrue(output.contains("Hemograma"),
                "El toString debería incluir el tipo de examen");

        assertTrue(output.contains("Juan Pérez"),
                "El toString debería incluir el nombre del paciente");

        assertTrue(output.contains("1234567890"),
                "El toString debería incluir la cédula del paciente");

        assertTrue(output.startsWith("Examen: "),
                "El toString debería comenzar con 'Examen: '");
    }

}