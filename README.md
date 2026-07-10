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
- Spring Boot `@ConfigurationProperties`
- Constructor Dependency Injection
- Modular generator architecture
- Configurable simulation timestamp
- Configurable simulation duration
- TCP client for transmitting generated records
- Runtime statistics (Generated / Sent / Failed)

---

## Tech Stack

- Java 21
- Spring Boot 4.1
- Maven
- TCP Socket Programming

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

## Current Progress

- [x] Realistic CDR generation
- [x] Realistic NAT generation
- [x] External configuration using `application.yml`
- [x] Configurable timestamp
- [x] Configurable simulation duration
- [x] TCP client implementation
- [x] Runtime transmission statistics
- [ ] UDP client implementation
- [ ] Runtime configuration using `-D` properties
- [ ] CSV / Flat file generation
- [ ] Enhanced logging

---

## Learning Objectives

This project was built to gain hands-on experience with:

- Spring Boot fundamentals
- Dependency Injection
- Configuration management
- Object-Oriented Design
- TCP Socket Programming
- File I/O
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