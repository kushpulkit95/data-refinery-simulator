# Data Refinery Simulator

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![Status](https://img.shields.io/badge/Status-In%20Progress-yellow)

A configurable **telecom data simulator** built using **Java 21** and **Spring Boot** as part of my internship project.

The simulator generates realistic **Call Detail Records (CDR)** and **NAT Log** data, supports configurable runtime parameters, and transmits generated records over TCP to simulate real-world telecom data pipelines.

---

## Features

- Generate realistic **CDR (Call Detail Record)** data
- Generate realistic **NAT Log** data
- External configuration using `application.yml`
- Runtime configuration using Java `-D` system properties
- Spring Boot `@ConfigurationProperties`
- Constructor Dependency Injection
- Interface-based sender architecture
- Modular generator architecture
- CDR transmission over **TCP**
- NAT Log transmission over **UDP**
- Configurable simulation timestamp
- Configurable simulation duration
- Runtime transmission statistics (Generated / Sent / Failed)

---

## Tech Stack

- Java 21
- Spring Boot 4.1
- Maven
- TCP Socket Programming
- UDP Socket Programming

---

## Project Structure

```text
src
├── config          # Configuration classes
├── generator       # CDR & NAT record generators
├── model           # Data models
├── network         # TCP sender
├── runner          # Simulator workflow
└── util            # Utility/helper classes
```

---

## Sample Configuration

```yaml
simulator:
  record-count: 5

  data-type: cdr,nat
  # Supported values:
  # cdr
  # nat
  # cdr,nat

  timestamp: 2024-01-01
  timeperiod: 30000

  cdrhost: localhost
  cdrport: 5000

  nathost: localhost
  natport: 5001
```

---

## Runtime Configuration

The simulator supports overriding configuration values at runtime using Java system properties (`-D` arguments). This allows different simulation scenarios to be executed without modifying the `application.yml` file.

### Supported Properties

| Property | Description |
|----------|-------------|
| `recordcount` | Number of records generated per data type |
| `datatype` | Data type to generate (`CDR`, `NAT`, or `BOTH`) |
| `timestamp` | Starting timestamp for generated records |
| `timeperiod` | Simulation duration in milliseconds |
| `cdrhost` | TCP receiver host |
| `cdrport` | TCP receiver port |
| `nathost` | UDP receiver host |
| `natport` | UDP receiver port |

### Example

Generate **15,000 CDR** and **15,000 NAT** records over a period of **1000 milliseconds**:

```bash
java -Drecordcount=15000 ^
     -Ddatatype=BOTH ^
     -Dtimeperiod=1000 ^
     -jar target/data-refinery-simulator-0.0.1-SNAPSHOT.jar
```

**PowerShell**

```powershell
java -Drecordcount=15000 -Ddatatype=BOTH -Dtimeperiod=1000 -jar target/data-refinery-simulator-0.0.1-SNAPSHOT.jar
```

If a property is not supplied, the simulator automatically falls back to the corresponding value defined in `application.yml`.

---

## Current Progress

- [x] Realistic CDR generation
- [x] Realistic NAT generation
- [x] External configuration using `application.yml`
- [x] Configurable timestamp
- [x] Configurable simulation duration
- [x] TCP client implementation
- [x] Runtime transmission statistics
- [x] UDP client implementation
- [x] Runtime configuration using `-D` properties
- [ ] Enhanced logging

---

## Learning Objectives

This project was built to gain hands-on experience with:

- Spring Boot fundamentals
- Dependency Injection
- Configuration management
- Object-Oriented Design
- Interface-based design
- TCP & UDP Socket Programming
- Java File I/O
- Clean software architecture
- Modular application design

---

## Running the Simulator

Clone the repository:

```bash
git clone https://github.com/<kushpulkit95>/data-refinery-simulator.git
```

Navigate to the project directory:

```bash
cd data-refinery-simulator
```

Run the application:

```bash
mvn spring-boot:run
```

Alternatively, open the project in your IDE and run:

```
DataRefinerySimulatorApplication
```

---

## Related Project

During development, a lightweight TCP receiver was built to verify communication between the simulator and the receiving endpoint.

Repository:
- https://github.com/kushpulkit95/receiver-server

---

## Author

**Pulkit Kush**

B.Tech Computer Science (Data Science)

Java • Spring Boot • Networking • Software Engineering