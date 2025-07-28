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
}
