/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author CltControl
 */
public class SistemaCitasTest {
    
    public SistemaCitasTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of registrarPaciente method, of class SistemaCitas.
     */
    @Test
    public void TestRegistrarPaciente() {
        System.out.println("Registrar paciente");
        String cedula = "0987654321";
        String nombre = "Maycol";
        String correo = "hola@mundo.com";
        SistemaCitas sistema = new SistemaCitas();
        sistema.registrarPaciente(cedula, nombre, correo);
        boolean result = sistema.existePaciente(cedula);
        assertTrue(result);
    }
    
    @Test
    public void agendarCita() {
        System.out.println("Agendar Cita");
        String cedulaPacienteNoExistente = "0987654321";
        String especialidad = "Cardiología";
        LocalDateTime fecha = LocalDateTime.now();
        SistemaCitas sistema = new SistemaCitas();
        boolean result = sistema.agendarCita(cedulaPacienteNoExistente, especialidad, fecha);
        assertFalse(result);
    }

    @Test
    public void testSolicitarExamenPacienteValido() {
        System.out.println("SC3: Solicitar examen con paciente válido");
        String cedula = "1234567890";
        String nombre = "Ana García";
        String correo = "ana.garcia@email.com";
        String tipoExamen = "Hemograma Completo";

        SistemaCitas sistema = new SistemaCitas();

        sistema.registrarPaciente(cedula, nombre, correo);

        assertTrue(sistema.existePaciente(cedula), "El paciente debe estar registrado");

        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalOut = System.out;
        System.setOut(new java.io.PrintStream(outputStream));

        sistema.verHistorial(cedula);
        String historialAntes = outputStream.toString();

        System.setOut(originalOut);
        outputStream.reset();
        System.setOut(new java.io.PrintStream(outputStream));

        sistema.solicitarExamen(cedula, tipoExamen);

        sistema.verHistorial(cedula);
        String historialDespues = outputStream.toString();

        System.setOut(originalOut);

        assertTrue(historialDespues.contains(tipoExamen),
                "El historial debe contener el tipo de examen solicitado");
        assertTrue(historialDespues.contains(nombre),
                "El historial debe contener el nombre del paciente");
        assertTrue(historialDespues.contains("Pendiente"),
                "El examen recién creado debe tener estado 'Pendiente'");

        System.out.println("✓ Examen agregado correctamente a la lista");
    }

    @Test
    public void testSolicitarExamenPacienteNoRegistrado() {
        System.out.println("SC4: Solicitar examen con paciente no registrado");

        String cedulaNoExistente = "9999999999";
        String tipoExamen = "Examen de Orina";

        SistemaCitas sistema = new SistemaCitas();

        assertFalse(sistema.existePaciente(cedulaNoExistente),
                "El paciente no debe estar registrado");

        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalOut = System.out;
        System.setOut(new java.io.PrintStream(outputStream));

        sistema.solicitarExamen(cedulaNoExistente, tipoExamen);

        sistema.verHistorial(cedulaNoExistente);
        String historial = outputStream.toString();

        System.setOut(originalOut);

        assertTrue(historial.trim().isEmpty() || !historial.contains(tipoExamen),
                "No se debe añadir examen para paciente no registrado");
        assertFalse(historial.contains(tipoExamen),
                "El historial no debe contener el examen solicitado");

        System.out.println("✓ Examen inválido no fue añadido");
    }

    @Test
    public void testVerHistorialConYSinHistorial() {
        System.out.println("SC5: Ver historial con y sin datos");

        SistemaCitas sistema = new SistemaCitas();

        String cedulaConHistorial = "1111111111";
        String cedulaSinHistorial = "2222222222";
        String nombreConHistorial = "Carlos López";
        String nombreSinHistorial = "María Torres";
        String correo1 = "carlos@email.com";
        String correo2 = "maria@email.com";

        sistema.registrarPaciente(cedulaConHistorial, nombreConHistorial, correo1);
        sistema.registrarPaciente(cedulaSinHistorial, nombreSinHistorial, correo2);

        sistema.solicitarExamen(cedulaConHistorial, "Radiografía de Tórax");
        sistema.solicitarExamen(cedulaConHistorial, "Electrocardiograma");

        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalOut = System.out;
        System.setOut(new java.io.PrintStream(outputStream));

        sistema.verHistorial(cedulaConHistorial);
        String historialConDatos = outputStream.toString();

        outputStream.reset();

        sistema.verHistorial(cedulaSinHistorial);
        String historialSinDatos = outputStream.toString();

        System.setOut(originalOut);

        assertFalse(historialConDatos.trim().isEmpty(),
                "El historial del paciente con datos no debe estar vacío");
        assertTrue(historialConDatos.contains("Radiografía de Tórax"),
                "El historial debe mostrar la radiografía solicitada");
        assertTrue(historialConDatos.contains("Electrocardiograma"),
                "El historial debe mostrar el electrocardiograma solicitado");
        assertTrue(historialConDatos.contains(nombreConHistorial),
                "El historial debe mostrar el nombre del paciente");

        assertTrue(historialSinDatos.trim().isEmpty(),
                "El historial del paciente sin datos debe estar vacío");
        assertFalse(historialSinDatos.contains("Radiografía"),
                "El historial vacío no debe contener exámenes");
        assertFalse(historialSinDatos.contains("Electrocardiograma"),
                "El historial vacío no debe contener exámenes");

        System.out.println("✓ Historial mostrado correctamente para ambos casos");
        System.out.println("  - Con historial: " + (!historialConDatos.trim().isEmpty()));
        System.out.println("  - Sin historial: " + (historialSinDatos.trim().isEmpty()));
    }

}
