package com.reser.padel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.reser.padel.controllers.PistasController;
import com.reser.padel.domain.Pista;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReserPadelApplication.class)
public class PistaTest {

	@Autowired
	private PistasController pistasController;
	
	@MockBean
	private BindingResult bindingResult;
	
	@Before
	public void setUp() {
		Pista pista1 = new Pista();
		pista1.setNombre("Pista 1");
		pista1.setApertura(LocalTime.of(8, 00));
		pista1.setCierre(LocalTime.of(22, 00));
		pista1.setPrecio(5.5);
		pistasController.create(pista1, bindingResult);

		Pista pista2 = new Pista();
		pista2.setNombre("Pista 2");
		pista2.setApertura(LocalTime.of(9, 00));
		pista2.setCierre(LocalTime.of(20, 00));
		pista2.setPrecio(7.5);
		pistasController.create(pista2, bindingResult);
	}
	
	@After
	public void tearDown() {
		pistasController.findAll().forEach(pista->pistasController.delete(pista.getId()));
	}
	
	@Test
	public void testCreatePista() {
		// Arrange
		Pista pista = new Pista();
		pista.setNombre("Prueba 1");
		pista.setApertura(LocalTime.of(8, 00));
		pista.setCierre(LocalTime.of(22, 00));
		pista.setPrecio(9.5);
		
		// Act
		Pista resultado = pistasController.create(pista, bindingResult);
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Pista diferente", pista, resultado);
		
		pistasController.delete(resultado.getId());
	}
	
	@Test
	public void testCreatePistaSinNombre() {
		// Arrange
		Pista resultado = null;

		Pista pista = new Pista();
		pista.setNombre("");
		pista.setApertura(LocalTime.of(8, 00));
		pista.setCierre(LocalTime.of(22, 00));
		pista.setPrecio(9.5);
		
		try {
			// Act
			resultado = pistasController.create(pista, bindingResult);
		} catch (Exception e) {
			// Assert
			assertEquals("Control de Nombre no implementado", "create.pista.nombre: Se debe proporcionar un nombre a la pista", e.getMessage());
		}
		
		try {
			pistasController.delete(resultado.getId());
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testCreatePistaPrecioIncorrecto() {
		// Arrange
		Pista resultado = null;

		Pista pista = new Pista();
		pista.setNombre("Prueba 1");
		pista.setApertura(LocalTime.of(8, 00));
		pista.setCierre(LocalTime.of(22, 00));
		pista.setPrecio(-5.5);
		
		try {
			// Act
			resultado = pistasController.create(pista, bindingResult);
		} catch (Exception e) {
			// Assert
			assertEquals("Control de Precio no implementado", "create.pista.precio: Debe indicarse una cantida correcta en el precio", e.getMessage());
		}
		
		try {
			pistasController.delete(resultado.getId());
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testFindAllPista() {
		// Arrange
		List<Pista> pistas = new ArrayList<>();

		Pista pista1 = new Pista();
		pista1.setId(pistasController.findAll().get(0).getId());
		pista1.setNombre("Pista 1");
		pista1.setApertura(LocalTime.of(8, 00));
		pista1.setCierre(LocalTime.of(22, 00));
		pista1.setPrecio(5.5);
		pistas.add(pista1);

		Pista pista2 = new Pista();
		pista2.setId(pistasController.findAll().get(1).getId());
		pista2.setNombre("Pista 2");
		pista2.setApertura(LocalTime.of(9, 00));
		pista2.setCierre(LocalTime.of(20, 00));
		pista2.setPrecio(7.5);
		pistas.add(pista2);
		
		// Act
		List<Pista> resultado = pistasController.findAll();
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Listado de Pistas diferente", pistas, resultado);
	}
	
	@Test
	public void testFindByIdPista() {
		// Arrange
		Pista pista = new Pista();
		pista.setId(pistasController.findAll().get(0).getId());
		pista.setNombre("Pista 1");
		pista.setApertura(LocalTime.of(8, 00));
		pista.setCierre(LocalTime.of(22, 00));
		pista.setPrecio(5.5);

		// Act
		Pista resultado = pistasController.findById(pista.getId());
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Pista diferente", pista, resultado);
	}
	
	@Test
	public void testUpdatePista() {
		// Arrange
		Pista pista = new Pista();
		pista.setId(pistasController.findAll().get(0).getId());
		pista.setNombre("Pista 1 update");
		pista.setApertura(LocalTime.of(10, 00));
		pista.setCierre(LocalTime.of(20, 00));
		pista.setPrecio(2.0);

		// Act
		pistasController.update(pista.getId(), pista, bindingResult);
		Pista resultado = pistasController.findById(pista.getId());
		
		// Assert
		assertNotNull("Null no esperado", resultado);
		assertEquals("Pista diferente", pista, resultado);
	}
	
	@Test
	public void testDeletePista() {
		// Arrange
		Pista pista = new Pista();
		pista.setNombre("Prueba 1");
		pista.setApertura(LocalTime.of(8, 00));
		pista.setCierre(LocalTime.of(22, 00));
		pista.setPrecio(9.5);
		Pista resultado = pistasController.create(pista, bindingResult);
		
		// Act
		pistasController.delete(resultado.getId());

		// Assert
		try {
			pistasController.findById(resultado.getId());
		} catch (Exception e) {
			assertEquals("Pista no borrada correctamente", "Pista " + resultado.getId() + " no encontrada", e.getMessage());
		}
	}
}
