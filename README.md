# 🚌 TransCesar S5 Team — Sistema de Gestión de Tickets Intermunicipales

> Sistema de consola en Java para la gestión de vehículos, personas, tickets y reservas de la empresa de transporte intermunicipal **TransCesar S.A.S.**
> Desarrollado aplicando **Programación Orientada a Objetos** y **Arquitectura en Capas**.

---

## 📋 Descripción del Proyecto

TransCesar S.A.S. requería modernizar su proceso de venta de tickets, que hasta entonces se gestionaba de forma completamente manual, generando errores en el registro de pasajeros, pérdida de información y dificultades para consultar las ventas del día.

El sistema permite:

- **Gestión de vehículos** — registro de Busetas, MicroBuses y Buses con sus rutas asignadas
- **Gestión de personas** — registro de conductores y pasajeros con cálculo automático de descuentos
- **Venta de tickets** — con validación de cupos, descuentos por tipo de pasajero y recargo en festivos
- **Sistema de reservas** — apartado de cupos con anticipación, expiración automática a las 24 horas y conversión a ticket
- **Reportes con filtros** — consultas por fecha, tipo de vehículo, tipo de pasajero y resumen del día
- **Persistencia** — todos los datos se guardan en archivos `.txt` y se cargan automáticamente al iniciar

El proyecto se desarrolló en tres fases:

| Fase | Período | Contenido |
|---|---|---|
| Taller 2 | 5–9 marzo 2026 | Sistema base completo |
| Escalabilidad | 15–16 marzo 2026 | Rutas, reglas de negocio avanzadas, reportes con filtros |
| Parcial Primer Corte | 21 marzo 2026 | Sistema de Reservas completo |

---

## 👥 Integrantes y Roles

| Integrante | GitHub | Rol | Responsabilidad principal |
|---|---|---|---|
| Daniel Turizo | [@ddturizo-eng](https://github.com/ddturizo-eng) | **Líder** | Repositorio, ramas, merges, interfaces, capa `view`, `Main.java` |
| daniel florez | [@IngDanielflorezz](https://github.com/IngDanielflorezz) | **Desarrollador 1** | Jerarquía `Vehiculo`, `VehiculoDAO`, `VehiculoService`, `FestivoService`, `ReservaService` |
| Jorge Herrera | [@JorgeHg2006](https://github.com/JorgeHg2006) | **Desarrollador 2** | Jerarquía `Persona`, `Ticket`, `Ruta`, `Reserva`, DAOs correspondientes |

**Contribuciones totales: 60 commits de 3 autores**

```
30  Jorge Herrera
16  ddturizo-eng
14  daniel florez
```

---

## 🏗️ Estructura de Paquetes

El proyecto sigue una arquitectura en 4 capas con flujo unidireccional estricto:

```
view → Logica (service) → Dao → Modelo
```

```
src/main/java/org/transcesar/
│
├── Modelo/                  ← Entidades del negocio
│   ├── Vehiculo.java        (abstract, implements Imprimible)
│   ├── Buseta.java          cap=19, tarifa=$8.000
│   ├── MicroBus.java        cap=25, tarifa=$10.000
│   ├── Bus.java             cap=45, tarifa=$15.000
│   ├── Ruta.java
│   ├── Persona.java         (abstract)
│   ├── Conductor.java
│   ├── Pasajero.java        (abstract, calcularDescuento() abstracto)
│   ├── PasajeroRegular.java         descuento 0%
│   ├── PasajeroEstudiante.java      descuento 15%
│   ├── PasajeroAdultoMayor.java     descuento 30%
│   ├── Ticket.java          (implements Calculable, Imprimible)
│   ├── Reserva.java         (implements Imprimible)
│   ├── Festivo.java
│   ├── Imprimible.java      (interface)
│   └── Calculable.java      (interface)
│
├── Dao/                     ← Persistencia en archivos .txt
│   ├── VehiculoDao.java
│   ├── PersonaDAO.java
│   ├── TicketDAO.java
│   ├── RutaDAO.java
│   └── ReservaDAO.java
│
├── Logica/                  ← Reglas de negocio y validaciones
│   ├── VehiculoService.java
│   ├── PersonaService.java
│   ├── TicketService.java
│   ├── RutaService.java
│   ├── FestivoService.java
│   └── ReservaService.java
│
└── view/                    ← Interfaz de consola
    ├── Main.java
    ├── MenuVehiculos.java
    ├── MenuPersonas.java
    ├── MenuTickets.java
    ├── MenuReportes.java
    └── MenuReservas.java
```

**Archivos de persistencia generados en tiempo de ejecución:**

```
buseta.txt / microbus.txt / bus.txt
conductores.txt / pasajeros.txt
tickets.txt / rutas.txt / reservas.txt
```

---

## 📝 Convenciones de Commits

El equipo siguió el estándar **Conventional Commits** en todas las ramas:

| Tipo | Cuándo usar | Ejemplo |
|---|---|---|
| `feat` | Nueva clase o funcionalidad | `feat: add clase Reserva con atributos estados e imprimirDetalle` |
| `fix` | Corrección de un bug o error | `fix: corregir getter y setter de ruta en Vehiculo a tipo Ruta` |
| `refactor` | Reestructurar sin cambiar comportamiento | `refactor: reorganizar getters y setters de Reserva` |
| `docs` | Comentarios o Javadoc | `docs: agregar javadoc a ReservaService` |
| `merge` | Merge de rama a main — solo Líder | `merge: integrar dev/sistema-reservas a main` |

**Ramas utilizadas:**

```
main                    ← rama principal — solo recibe merges del Líder
dev/view-main           ← Taller 2: capa view + interfaces
dev/vehiculos           ← Taller 2: jerarquía Vehiculo
dev/personas-tickets    ← Taller 2: jerarquía Persona + Ticket
dev/gestion-rutas       ← Escalabilidad R1: clase Ruta
dev/reglas-negocio      ← Escalabilidad R2: FestivoService + reglas TicketService
dev/reportes-filtros    ← Escalabilidad R3: MenuReportes con filtros
dev/sistema-reservas    ← Parcial R4: Sistema de Reservas completo
```

---

## 🔗 Repositorio

**[https://github.com/ddturizo-eng/TransCesar-S5-Team](https://github.com/ddturizo-eng/TransCesar-S5-Team)**

---

*Universidad Popular del Cesar — Programación III — Ing. Esp. Alfredo Bautista*