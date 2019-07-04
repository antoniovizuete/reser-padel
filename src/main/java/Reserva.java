import java.time.LocalDateTime;

// CRUD:
//  - CREATE: POST
//  - READ: GET
//    - All
//    - 1
//  - UPDATE: PUT
//  - DELETE: DELETE
public class Reserva {
  private Integer id;
  private Persona persona;
  private Pista pista;
  private LocalDateTime fecha;
  private Boolean pagada;
}
