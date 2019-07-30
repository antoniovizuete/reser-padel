package com.reser.padel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import com.reser.padel.controllers.PersonasController;
import com.reser.padel.domain.Persona;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReserPadelApplication.class)
public class PersonaTest {

	@Autowired
	private PersonasController personasController;
	
	@MockBean
	private BindingResult bindingResult;
	
	@Before
	public void setUp() {
		Persona persona1 = new Persona();
		persona1.setEmail("person1@email.com");
		persona1.setNombre("Persona 1");
		persona1.setNivelPadel(1);
		personasController.create(persona1, bindingResult);

		Persona persona2 = new Persona();
		persona2.setEmail("person2@email.com");
		persona2.setNombre("Persona 2");
		persona2.setNivelPadel(2);
		personasController.create(persona2, bindingResult);
	}
	
	@After
	public void tearDown() {
		personasController.delete("person1@email.com");
		personasController.delete("person2@email.com");
	}
	
	@Test
	public void testCreatePersona() {
		// Arrange
		Persona persona = new Persona();
		persona.setEmail("prueba1@email.com");
		persona.setNombre("Prueba 1");
		persona.setNivelPadel(5);
		
		// Act
		Persona resultado = personasController.create(persona, bindingResult);
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Persona diferente", persona, resultado);
		
		personasController.delete(persona.getEmail());
	}
	
	@Test
	public void testCreatePersonaMailIncorrecto() {
		// Arrange
		Persona persona = new Persona();
		persona.setEmail("email.incorrecto");
		persona.setNombre("Prueba 1");
		persona.setNivelPadel(5);
		
		try {
			// Act
			personasController.create(persona, bindingResult);
		} catch (Exception e) {
			// Assert
			assertEquals("Control de Email no implementado", "create.persona.email: El email proporcionado no es válido", e.getMessage());
		}

		try {
			personasController.delete(persona.getEmail());
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testCreatePersonaSinNombre() {
		// Arrange
		Persona persona = new Persona();
		persona.setEmail("prueba1@email.com");
		persona.setNombre("");
		persona.setNivelPadel(5);
		
		try {
			// Act
			personasController.create(persona, bindingResult);
		} catch (Exception e) {
			// Assert
			assertEquals("Control de Nombre no implementado", "create.persona.nombre: Se debe proporcionar un nombre al usuario", e.getMessage());
		}
		
		try {
			personasController.delete(persona.getEmail());
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testCreatePersonaNivelAltoIncorrecto() {
		// Arrange
		Persona persona = new Persona();
		persona.setEmail("prueba1@email.com");
		persona.setNombre("Prueba 1");
		persona.setNivelPadel(15);
		
		try {
			// Act
			personasController.create(persona, bindingResult);
		} catch (Exception e) {
			// Assert
			assertEquals("Control de Nivel no implementado", "create.persona.nivelPadel: El nivel no debe ser mayor de 10", e.getMessage());
		}
		
		try {
			personasController.delete(persona.getEmail());
		} catch (Exception e) {
		}
	}
		
	@Test
	public void testCreatePersonaNivelBajoIncorrecto() {
		// Arrange
		Persona persona = new Persona();
		persona.setEmail("prueba1@email.com");
		persona.setNombre("Prueba 1");
		persona.setNivelPadel(-5);
		
		try {
			// Act
			personasController.create(persona, bindingResult);
		} catch (Exception e) {
			// Assert
			assertEquals("Control de Nivel no implementado", "create.persona.nivelPadel: El nivel no debe ser menor de 0", e.getMessage());
		}
		
		try {
			personasController.delete(persona.getEmail());
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testFindAllPersona() {
		// Arrange
		List<Persona> personas = new ArrayList<>();

		Persona persona1 = new Persona();
		persona1.setEmail("person1@email.com");
		persona1.setNombre("Persona 1");
		persona1.setNivelPadel(1);
		personas.add(persona1);

		Persona persona2 = new Persona();
		persona2.setEmail("person2@email.com");
		persona2.setNombre("Persona 2");
		persona2.setNivelPadel(2);
		personas.add(persona2);
		
		// Act
		List<Persona> resultado = personasController.findAll();
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Listado de Personas diferente", personas, resultado);
	}
	
	@Test
	public void testFindByIdPersona() {
		// Arrange
		Persona persona = new Persona();
		persona.setEmail("person1@email.com");
		persona.setNombre("Persona 1");
		persona.setNivelPadel(1);

		// Act
		Persona resultado = personasController.findById(persona.getEmail());
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Persona diferente", persona, resultado);
	}
	
	@Test
	public void testUpdatePersona() {
		// Arrange
		Persona persona = new Persona();
		persona.setEmail("person1@email.com");
		persona.setNombre("Persona 1 update");
		persona.setNivelPadel(9);

		// Act
		personasController.update("person1@email.com", persona, bindingResult);
		Persona resultado = personasController.findById("person1@email.com");
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Persona diferente", persona, resultado);
	}
	
	@Test
	public void testDeletePersona() {
		// Arrange
		Persona persona = new Persona();
		persona.setEmail("person3@email.com");
		persona.setNombre("Persona 3");
		persona.setNivelPadel(5);
		personasController.create(persona, bindingResult);
		
		// Act
		personasController.delete("person3@email.com");

		// Assert
		try {
			personasController.findById("person3@email.com");
		} catch (Exception e) {
			assertEquals("Persona no borrada correctamente", "Persona person3@email.com no encontrada", e.getMessage());
		}
	}

}
