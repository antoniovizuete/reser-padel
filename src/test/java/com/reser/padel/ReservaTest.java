package com.reser.padel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
import com.reser.padel.controllers.PistasController;
import com.reser.padel.controllers.ReservasController;
import com.reser.padel.domain.Persona;
import com.reser.padel.domain.Pista;
import com.reser.padel.domain.Reserva;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReserPadelApplication.class)
public class ReservaTest {

	@Autowired
	private ReservasController reservasController;
	
	@Autowired
	private PersonasController personasController;
	
	@Autowired
	private PistasController pistasController;
	
	@MockBean
	private BindingResult bindingResult;
	
	private Persona persona1;
	private Persona persona2;
	private Pista pista1;
	private Pista pista2;

	@Before
	public void setUp() {
		persona1 = new Persona();
		persona1.setEmail("persona1@email.com");
		persona1.setNombre("Persona 1");
		persona1.setNivelPadel(5);
		personasController.create(persona1, bindingResult);

		persona2 = new Persona();
		persona2.setEmail("persona2@email.com");
		persona2.setNombre("Persona 2");
		persona2.setNivelPadel(1);
		personasController.create(persona2, bindingResult);

		pista1 = new Pista();
		pista1.setNombre("Pista 1");
		pista1.setApertura(LocalTime.of(8, 00));
		pista1.setCierre(LocalTime.of(22, 00));
		pista1.setPrecio(9.5);
		pistasController.create(pista1, bindingResult);

		pista2 = new Pista();
		pista2.setNombre("Pista 2");
		pista2.setApertura(LocalTime.of(9, 00));
		pista2.setCierre(LocalTime.of(20, 00));
		pista2.setPrecio(2.5);
		pistasController.create(pista2, bindingResult);

		Reserva reserva1 = new Reserva();
		reserva1.setPersona(persona1);
		reserva1.setPista(pista1);
		reserva1.setFecha(LocalDateTime.of(2019, 8, 11, 10, 00));
		reserva1.setPagada(false);
		reservasController.create(reserva1, bindingResult);

		Reserva reserva2 = new Reserva();
		reserva2.setPersona(persona1);
		reserva2.setPista(pista1);
		reserva2.setFecha(LocalDateTime.of(2019, 8, 12, 11, 00));
		reserva2.setPagada(true);
		reservasController.create(reserva2, bindingResult);
	}
	
	@After
	public void tearDown() {
		reservasController.findAll().forEach(reserva->reservasController.delete(reserva.getId()));
		personasController.findAll().forEach(persona->personasController.delete(persona.getEmail()));
		pistasController.findAll().forEach(pista->pistasController.delete(pista.getId()));
	}
	
	@Test
	public void testCreateReserva() {
		// Arrange
		Persona persona = new Persona();
		persona.setEmail("persona1@email.com");
		persona.setNombre("Persona 1");
		persona.setNivelPadel(5);
		
		Pista pista = new Pista();
		pista.setId(pista1.getId());
		pista.setNombre("Pista 1");
		pista.setApertura(LocalTime.of(8, 00));
		pista.setCierre(LocalTime.of(22, 00));
		pista.setPrecio(9.5);
		
		Reserva reserva = new Reserva();
		reserva.setPersona(persona);
		reserva.setPista(pista);
		reserva.setFecha(LocalDateTime.of(2019, 8, 10, 10, 00));
		reserva.setPagada(true);
		
		// Act
		Reserva resultado = reservasController.create(reserva, bindingResult);
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Reserva diferente", reserva, resultado);

		reservasController.delete(resultado.getId());
	}
	
	@Test
	public void testCreateReservaSinPersona() {
		// Arrange
		Reserva resultado = null;

		Persona persona = null;
		
		Pista pista = new Pista();
		pista.setId(pista1.getId());
		pista.setNombre("Pista 1");
		pista.setApertura(LocalTime.of(8, 00));
		pista.setCierre(LocalTime.of(22, 00));
		pista.setPrecio(9.5);
		
		Reserva reserva = new Reserva();
		reserva.setPersona(persona);
		reserva.setPista(pista);
		reserva.setFecha(LocalDateTime.of(2019, 8, 10, 10, 00));
		reserva.setPagada(true);
		
		try {
			// Act
			resultado = reservasController.create(reserva, bindingResult);
		} catch (Exception e) {
			// Assert
			assertEquals("Control de Persona no implementado", "create.reserva.persona: Se debe informar una persona en la reserva", e.getMessage());
		}
		
		try {
			reservasController.delete(resultado.getId());
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testCreateReservaSinPista() {
		// Arrange
		Reserva resultado = null;

		Persona persona = new Persona();
		persona.setEmail("persona1@email.com");
		persona.setNombre("Persona 1");
		persona.setNivelPadel(5);
		
		Pista pista = null;
		
		Reserva reserva = new Reserva();
		reserva.setPersona(persona);
		reserva.setPista(pista);
		reserva.setFecha(LocalDateTime.of(2019, 8, 10, 10, 00));
		reserva.setPagada(true);
		
		try {
			// Act
			resultado = reservasController.create(reserva, bindingResult);
		} catch (Exception e) {
			// Assert
			assertEquals("Control de Pista no implementado", "create.reserva.pista: Se debe informar una pista en la reserva", e.getMessage());
		}
		
		try {
			reservasController.delete(resultado.getId());
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testCreateReservaSinFecha() {
		// Arrange
		Reserva resultado = null;

		Persona persona = new Persona();
		persona.setEmail("persona1@email.com");
		persona.setNombre("Persona 1");
		persona.setNivelPadel(5);
		
		Pista pista = new Pista();
		pista.setId(pista1.getId());
		pista.setNombre("Pista 1");
		pista.setApertura(LocalTime.of(8, 00));
		pista.setCierre(LocalTime.of(22, 00));
		pista.setPrecio(9.5);
		
		Reserva reserva = new Reserva();
		reserva.setPersona(persona);
		reserva.setPista(pista);
		reserva.setFecha(null);
		reserva.setPagada(true);
		
		try {
			// Act
			resultado = reservasController.create(reserva, bindingResult);
		} catch (Exception e) {
			// Assert
			assertEquals("Control de Fecha no implementado", "create.reserva.fecha: Se debe informar una fecha y hora de reserva", e.getMessage());
		}
		
		try {
			reservasController.delete(resultado.getId());
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testCreateReservaFechaPasada() {
		// Arrange
		Reserva resultado = null;

		Persona persona = new Persona();
		persona.setEmail("persona1@email.com");
		persona.setNombre("Persona 1");
		persona.setNivelPadel(5);
		
		Pista pista = new Pista();
		pista.setId(pista1.getId());
		pista.setNombre("Pista 1");
		pista.setApertura(LocalTime.of(8, 00));
		pista.setCierre(LocalTime.of(22, 00));
		pista.setPrecio(9.5);
		
		Reserva reserva = new Reserva();
		reserva.setPersona(persona);
		reserva.setPista(pista);
		reserva.setFecha(LocalDateTime.of(2018, 8, 10, 10, 00));
		reserva.setPagada(true);
		
		try {
			// Act
			resultado = reservasController.create(reserva, bindingResult);
		} catch (Exception e) {
			// Assert
			assertEquals("Control de Fecha no implementado", "create.reserva.fecha: La fecha de reserva debe ser a partir de hoy", e.getMessage());
		}
		
		try {
			reservasController.delete(resultado.getId());
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testCreateReservaSinPagada() {
		// Arrange
		Reserva resultado = null;

		Persona persona = new Persona();
		persona.setEmail("persona1@email.com");
		persona.setNombre("Persona 1");
		persona.setNivelPadel(5);
		
		Pista pista = new Pista();
		pista.setId(pista1.getId());
		pista.setNombre("Pista 1");
		pista.setApertura(LocalTime.of(8, 00));
		pista.setCierre(LocalTime.of(22, 00));
		pista.setPrecio(9.5);
		
		Reserva reserva = new Reserva();
		reserva.setPersona(persona);
		reserva.setPista(pista);
		reserva.setFecha(LocalDateTime.of(2019, 8, 10, 10, 00));
		reserva.setPagada(null);
		
		try {
			// Act
			resultado = reservasController.create(reserva, bindingResult);
		} catch (Exception e) {
			// Assert
			assertEquals("Control de Fecha no implementado", "create.reserva.pagada: Se debe informar si está pagada la reserva o no", e.getMessage());
		}
		
		try {
			reservasController.delete(resultado.getId());
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testFindAllReserva() {
		// Arrange
		List<Reserva> reservas = new ArrayList<>();

		Reserva reserva1 = new Reserva();
		reserva1.setId(reservasController.findAll().get(0).getId());
		reserva1.setPersona(persona1);
		reserva1.setPista(pista1);
		reserva1.setFecha(LocalDateTime.of(2019, 8, 11, 10, 00));
		reserva1.setPagada(false);
		reservas.add(reserva1);

		Reserva reserva2 = new Reserva();
		reserva2.setId(reservasController.findAll().get(1).getId());
		reserva2.setPersona(persona1);
		reserva2.setPista(pista1);
		reserva2.setFecha(LocalDateTime.of(2019, 8, 12, 11, 00));
		reserva2.setPagada(true);
		reservas.add(reserva2);
		
		// Act
		List<Reserva> resultado = reservasController.findAll();
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Listado de Reservas diferente", reservas, resultado);
	}
	
	@Test
	public void testFindByIdReserva() {
		// Arrange
		Reserva reserva = new Reserva();
		reserva.setId(reservasController.findAll().get(0).getId());
		reserva.setPersona(persona1);
		reserva.setPista(pista1);
		reserva.setFecha(LocalDateTime.of(2019, 8, 11, 10, 00));
		reserva.setPagada(false);

		// Act
		Reserva resultado = reservasController.findById(reserva.getId());
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Reserva diferente", reserva, resultado);
	}
	
	@Test
	public void testUpdateReserva() {
		// Arrange
		Reserva reserva = new Reserva();
		reserva.setId(reservasController.findAll().get(0).getId());
		reserva.setPersona(persona2);
		reserva.setPista(pista2);
		reserva.setFecha(LocalDateTime.of(2019, 8, 22, 13, 00));
		reserva.setPagada(true);

		// Act
		reservasController.update(reserva.getId(), reserva, bindingResult);
		Reserva resultado = reservasController.findById(reserva.getId());
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Reserva diferente", reserva, resultado);
	}
	
	@Test
	public void testDeleteReserva() {
		// Arrange
		Reserva reserva = new Reserva();
		reserva.setId(reservasController.findAll().get(0).getId());
		reserva.setPersona(persona1);
		reserva.setPista(pista2);
		reserva.setFecha(LocalDateTime.of(2019, 8, 20, 12, 00));
		reserva.setPagada(true);
		Reserva resultado = reservasController.create(reserva, bindingResult);
		
		// Act
		reservasController.delete(resultado.getId());

		// Assert
		try {
			reservasController.findById(resultado.getId());
		} catch (Exception e) {
			assertEquals("Reserva no borrada correctamente", "Reserva " + resultado.getId() + " no encontrada", e.getMessage());
		}
	}
}
