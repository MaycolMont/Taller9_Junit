/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

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
public class MedicoTest {
    
    public MedicoTest() {
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
     * Test of toString method, of class Medico.
     */
    @Test
    public void testConstructorM1() {
        System.out.println("toString");
        String nombre = "Maycol";
        String especialidad = "Endocrinología";
        Medico medico = new Medico(nombre, especialidad);
        String expResult = nombre + " - " + especialidad;
        String result = medico.toString();
        assertEquals(expResult, result);
    }
 
    @Test
    public void testConstructorValidarNombreVacio() {
        System.out.println("Validar Nombre Vacío");
        String nombre = null;
        String especialidad = "Cardiología";
        try {
            Medico medico = new Medico(nombre , especialidad);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid argument", e.getMessage());
        }
    }
}
