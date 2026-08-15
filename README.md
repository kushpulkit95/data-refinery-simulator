# Data Refinery Engine Simulator

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![Docker](https://img.shields.io/badge/Docker-Containerization-blue)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-red)
![AWS](https://img.shields.io/badge/AWS-EC2-orange)
![Kubernetes](https://img.shields.io/badge/Kubernetes-Orchestration-326CE5)
![Helm](https://img.shields.io/badge/Helm-Package_Manager-0F1689)
![TCP%2FUDP](https://img.shields.io/badge/Networking-TCP%2FUDP-informational)
![Status](https://img.shields.io/badge/Status-Completed-success)

A configurable telecom data simulation and receiver system developed as part of an internship project.

The project consists of two Java/Spring Boot applications:

- **Data Refinery Simulator** — generates configurable CDR and NAT records and transmits them using TCP and UDP.
- **Receiver Server** — continuously listens for incoming CDR/NAT records and persists them into flat CSV files.

The system was progressively containerized, automated through Jenkins, published to Docker Hub, deployed on AWS EC2 using Docker Compose, and subsequently deployed and validated using Kubernetes and Helm on a local Minikube environment.

---

## 1. Project Overview

The purpose of the project is to provide a controlled environment for generating telecom-style test data and validating the complete transmission and reception pipeline.

The simulator supports configurable:

- Record count
- Data type
- Timestamp
- Generation time period
- CDR receiver host and port
- NAT receiver host and port

The Receiver provides:

- TCP server for CDR records
- UDP server for NAT records
- Continuous listening
- Flat-file persistence
- Runtime reception logging
- Cumulative received-record statistics

The complete data flow is:

```text
                 Data Refinery System

       +-------------------------------+
       |      Data Refinery Simulator  |
       |                               |
       |  CDR Generator ----- TCP -----+------+
       |                               |      |
       |  NAT Generator ----- UDP -----+------|--+
       +-------------------------------+      |  |
                                              v  v
                                  +---------------------+
                                  |    Receiver Server  |
                                  |                     |
                                  | TCP Server : 5000   |
                                  | UDP Server : 5001   |
                                  +----------+----------+
                                             |
                              +--------------+--------------+
                              v                             v
                    received-cdr.csv              received-nat.csv
```

The Simulator is a **finite batch application**: it generates and transmits the configured batch and then completes.

The Receiver is a **long-running server**: it remains available for incoming records and can process multiple simulator executions without requiring a restart.

---

## 2. Project Objectives

- Generate configurable CDR and NAT test records.
- Transmit CDR records using TCP.
- Transmit NAT records using UDP.
- Receive and persist incoming records.
- Provide transmission and reception statistics.
- Containerize the applications using Docker.
- Automate builds and Docker image publishing using Jenkins.
- Store application images in Docker Hub.
- Deploy the containerized applications on AWS EC2 using Docker Compose.
- Deploy the applications using Kubernetes.
- Manage Kubernetes configuration and deployment using Helm.
- Validate the complete end-to-end data flow through controlled test scenarios.

---

## 3. Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Application development |
| Spring Boot 4.1 | Application framework |
| Maven | Build and dependency management |
| TCP | CDR transmission |
| UDP | NAT transmission |
| Docker | Application containerization |
| Docker Compose | Multi-container deployment |
| Jenkins | CI/CD automation |
| GitHub | Source-code management |
| Docker Hub | Container image registry |
| AWS EC2 | Cloud deployment environment |
| Kubernetes | Container orchestration |
| Minikube | Local Kubernetes environment |
| Helm | Kubernetes packaging and configuration |

---

## 4. Repository Structure

The project is maintained across separate application repositories and a deployment repository.

```text
Data Refinery Project
|
+-- data-refinery-simulator/
|   +-- src/
|   +-- pom.xml
|   +-- Dockerfile
|   +-- Jenkinsfile
|   +-- README.md
|
+-- receiver-server/
|   +-- src/
|   +-- pom.xml
|   +-- Dockerfile
|   +-- Jenkinsfile
|   +-- README.md
|
+-- refinery-deployment/
    +-- docker-compose.yml
    |
    +-- k8s/
    |   +-- namespace.yaml
    |   +-- configmap.yaml
    |   +-- receiver-deployment.yaml
    |   +-- receiver-service.yaml
    |   +-- simulator-deployment.yaml
    |
    +-- helm/
        +-- refinery/
            +-- Chart.yaml
            +-- values.yaml
            +-- templates/
                +-- configmap.yaml
                +-- receiver-deployment.yaml
                +-- receiver-service.yaml
                +-- simulator-deployment.yaml
```

The `refinery-deployment` repository is the central deployment repository for Docker Compose, Kubernetes, and Helm configurations.

---

## 5. System Architecture

### Application Architecture

```text
                         +------------------------+
                         | Data Refinery Simulator|
                         |                        |
                         | Record Generation      |
                         +-----------+------------+
                                     |
                         +-----------+-----------+
                         |                       |
                         v                       v
                   CDR Generator           NAT Generator
                         |                       |
                      TCP : 5000             UDP : 5001
                         |                       |
                         +-----------+-----------+
                                     v
                         +------------------------+
                         |     Receiver Server    |
                         |                        |
                         | TCP Server             |
                         | UDP Server             |
                         +-----------+------------+
                                     |
                         +-----------+-----------+
                         v                       v
                 received-cdr.csv        received-nat.csv
```

### AWS EC2 — Docker Compose

AWS EC2 was used for cloud-based container deployment and end-to-end Docker validation.

```text
GitHub
   |
   v
Jenkins
   |
   +-- Maven Build
   +-- Docker Build
   +-- Docker Push
          |
          v
      Docker Hub
          |
          v
       AWS EC2
          |
          v
    Docker Compose
          |
      +---+----+
      v        v
 Simulator  Receiver
      |        |
      +---+----+
          v
      CSV Output
```

### Local PC — Kubernetes + Helm

Kubernetes and Helm were implemented and validated locally using Minikube.

```text
Docker Hub
    |
    v
 Local PC
    |
    v
 Minikube
    |
    v
 Kubernetes
    |
    +-- Namespace: refinery
    |
    +-- Simulator Deployment
    |      |
    |      +-- ConfigMap
    |
    +-- Receiver Deployment
           |
           +-- Receiver Service
                  |
                  +-- TCP : 5000
                  +-- UDP : 5001
```

Kubernetes/Helm deployment was not performed on AWS EC2. AWS EC2 was used for the Docker Compose deployment, while Kubernetes and Helm were validated locally with Minikube.

---

## 6. Application Components

### Data Refinery Simulator

The Simulator generates configurable CDR and NAT records.

```text
Start
  |
  v
Load configuration
  |
  v
Generate records
  |
  +----------+----------+
  v                     v
 CDR                   NAT
  |                     |
 TCP                   UDP
  |                     |
  +----------+----------+
             v
Transmission statistics
             |
             v
          Complete
```

The Simulator does not need to run indefinitely. Each execution represents a finite test-data generation batch.

### Receiver Server

The Receiver is a long-running application.

```text
Start
  |
  +-- TCP Server : 5000
  |
  +-- UDP Server : 5001
        |
        v
     Wait for data
        |
        v
     Receive record
        |
        v
     Write to CSV
        |
        v
     Continue listening
        |
        +----------------> ...
```

The Receiver can accept records from multiple simulator executions without restarting.

---

## 7. Configuration

Example Simulator configuration:

```yaml
simulator:
  record-count: 5
  datatype: cdr,nat
  timestamp: 2026-07-13
  timeperiod: 1000

  cdrhost: localhost
  cdrport: 5000

  nathost: localhost
  natport: 5001
```

| Parameter | Description | Example |
|---|---|---|
| `record-count` | Number of records generated for each selected data type | `5` |
| `datatype` | Data type to generate | `cdr,nat` |
| `timestamp` | Starting date used by generated records | `2026-07-13` |
| `timeperiod` | Configured generation time period | `1000` |
| `cdrhost` | CDR receiver hostname | `localhost` |
| `cdrport` | CDR receiver TCP port | `5000` |
| `nathost` | NAT receiver hostname | `localhost` |
| `natport` | NAT receiver UDP port | `5001` |

Supported `datatype` values:

```text
cdr
nat
cdr,nat
```

---

## 8. Configuration Override Mechanism

The Simulator supports configuration overrides using:

1. Spring configuration
2. Java system properties
3. Environment variables

Supported runtime properties/environment variables include:

| Property | Environment Variable | Purpose |
|---|---|---|
| `recordcount` | `RECORDCOUNT` | Number of records |
| `datatype` | `DATATYPE` | Record type |
| `timestamp` | `TIMESTAMP` | Record timestamp |
| `timeperiod` | `TIMEPERIOD` | Generation time period |
| `cdrhost` | `CDRHOST` | CDR receiver host |
| `cdrport` | `CDRPORT` | CDR receiver port |
| `nathost` | `NATHOST` | NAT receiver host |
| `natport` | `NATPORT` | NAT receiver port |

This allows the same application image to be used in different environments without modifying the application source code.

---

## 9. Docker Deployment

Both applications are packaged as Docker images and published to Docker Hub:

```text
pkistrying/data-refinery-simulator:latest
pkistrying/receiver-server:latest
```

### Docker Compose

The Docker Compose deployment runs both services on a shared Docker network.

The Simulator communicates with the Receiver using the Docker service name rather than `localhost`.

Communication:

```text
CDR -> receiver:5000/TCP
NAT -> receiver:5001/UDP
```

Start the deployment:

```bash
docker compose up -d
```

View running containers:

```bash
docker compose ps
```

View logs:

```bash
docker compose logs
```

Stop the deployment:

```bash
docker compose down
```

---

## 10. CI/CD Pipeline

The applications are integrated with Jenkins pipelines.

```text
Developer Repository
        |
        v
      Jenkins
        |
        +-- Checkout
        +-- Maven Build
        +-- Docker Build
        +-- Docker Tag
        +-- Docker Push
                |
                v
            Docker Hub
```

The resulting Docker images are used by the deployment environments.

The pipeline publishes the `latest` image tag used by the deployment configuration.

---

## 11. AWS EC2 Deployment

The containerized system was deployed and validated on an AWS EC2 instance.

The AWS deployment uses Docker Compose.

```text
AWS EC2
   |
   +-- Docker
   |
   +-- Docker Compose
          |
          +-- Simulator
          |
          +-- Receiver
```

The AWS environment was used to validate that:

- Docker images could be pulled from Docker Hub.
- The Simulator and Receiver could communicate remotely.
- CDR records could be transmitted using TCP.
- NAT records could be transmitted using UDP.
- Records could be written to the Receiver's flat files.

The AWS environment is separate from the local Kubernetes/Helm environment.

---

## 12. Kubernetes Deployment

Kubernetes deployment was implemented and validated locally using Minikube.

### Namespace

The project uses:

```text
refinery
```

Create/use the namespace:

```bash
kubectl create namespace refinery
```

### Deployments

The project contains two Deployments:

```text
refinery-receiver
refinery-simulator
```

The Receiver Deployment maintains the long-running Receiver application.

The Simulator Deployment runs the finite simulation workload.

### Service

The Receiver is exposed internally through:

```text
refinery-receiver-service
```

with:

```text
5000/TCP
5001/UDP
```

The Simulator uses the Kubernetes Service name rather than `localhost`.

---

## 13. Kubernetes ConfigMap

The Simulator configuration is supplied through a Kubernetes ConfigMap.

Example:

```yaml
RECORDCOUNT: "5"
DATATYPE: "cdr,nat"
TIMESTAMP: "2026-07-13"
TIMEPERIOD: "1000"

CDRHOST: "refinery-receiver-service"
CDRPORT: "5000"

NATHOST: "refinery-receiver-service"
NATPORT: "5001"
```

This allows configuration to be changed without rebuilding the Docker image.

After updating configuration through Helm, the Simulator Pod is restarted so that the new environment variables are loaded.

---

## 14. Useful Kubernetes Commands

Check all project resources:

```bash
kubectl get all -n refinery
```

Check Pods:

```bash
kubectl get pods -n refinery
```

Check Services:

```bash
kubectl get services -n refinery
```

Check Deployments:

```bash
kubectl get deployments -n refinery
```

Describe a Pod:

```bash
kubectl describe pod <pod-name> -n refinery
```

View Simulator logs:

```bash
kubectl logs deployment/refinery-simulator -n refinery
```

View Receiver logs:

```bash
kubectl logs deployment/refinery-receiver -n refinery
```

Open a shell inside a Pod:

```bash
kubectl exec -it <pod-name> -n refinery -- sh
```

Restart the Simulator:

```bash
kubectl rollout restart deployment/refinery-simulator -n refinery
```

Restart the Receiver:

```bash
kubectl rollout restart deployment/refinery-receiver -n refinery
```

Check rollout status:

```bash
kubectl rollout status deployment/refinery-receiver -n refinery
kubectl rollout status deployment/refinery-simulator -n refinery
```

Scale the Receiver down:

```bash
kubectl scale deployment refinery-receiver --replicas=0 -n refinery
```

Start the Receiver again:

```bash
kubectl scale deployment refinery-receiver --replicas=1 -n refinery
```

---

## 15. Helm Deployment

The Kubernetes deployment is packaged as a Helm chart.

```text
helm/
+-- refinery/
    +-- Chart.yaml
    +-- values.yaml
    +-- templates/
        +-- configmap.yaml
        +-- receiver-deployment.yaml
        +-- receiver-service.yaml
        +-- simulator-deployment.yaml
```

### Validate the Chart

```bash
helm lint helm/refinery
```

### Render the Templates

```bash
helm template refinery helm/refinery -n refinery
```

### Install

```bash
helm install refinery helm/refinery -n refinery
```

### List Releases

```bash
helm list -n refinery
```

### Upgrade

After changing `values.yaml`:

```bash
helm upgrade refinery helm/refinery -n refinery
```

If Simulator configuration changes through the ConfigMap, restart the Simulator so the new environment variables are loaded:

```bash
kubectl rollout restart deployment/refinery-simulator -n refinery
```

### Check Release Status

```bash
helm status refinery -n refinery
```

### View Release History

```bash
helm history refinery -n refinery
```

### Uninstall

```bash
helm uninstall refinery -n refinery
```

---

## 16. Helm Configuration

The main deployment values are maintained in:

```text
helm/refinery/values.yaml
```

For example:

```yaml
simulator:
  recordCount: 10
  datatype: cdr,nat
  timestamp: 2026-07-13
  timeperiod: 1000
```

The configuration flow is:

```text
values.yaml
     |
     v
Helm Template
     |
     v
ConfigMap
     |
     v
Simulator Deployment
     |
     v
Environment Variables
     |
     v
SimulatorProperties
```

---

## 17. Receiver Output

The Receiver persists records to two CSV files.

### CDR

```text
received-cdr.csv
```

Header:

```text
IMSI,MSISDN,IMEI,APN,RATType,Action,Timestamp
```

### NAT

```text
received-nat.csv
```

Header:

```text
Private_IP,Private_Port,Public_IP,Public_Port,Destination_IP,Destination_Port,Protocol,Timestamp
```

The Receiver creates the output directory when required and appends new records rather than overwriting existing records.

---

## 18. Logging

The Receiver provides runtime logging for operational visibility.

At startup:

```text
TCP Server listening on port 5000
UDP Server listening on port 5001
```

When CDR records are received:

```text
TCP: CDR record received | Total: 1
TCP: CDR record received | Total: 2
...
```

When NAT records are received:

```text
UDP: NAT record received | Total: 1
UDP: NAT record received | Total: 2
...
```

The cumulative counters demonstrate that the Receiver can remain active while processing multiple simulator executions.

Simulator execution statistics include:

```text
Generated
Sent
Failed
```

These values are used during end-to-end testing.

---

## 19. Testing and Validation

Testing was performed by varying:

- `recordCount`
- `datatype`
- `timeperiod`

The following areas were validated:

- Record generation
- Successful transmission
- Failed transmission
- Receiver-side reception
- CDR file generation
- NAT file generation
- Different record counts
- CDR-only execution
- NAT-only execution
- Different generation periods
- Multiple simulator executions without restarting the Receiver
- Kubernetes service communication
- Helm configuration changes

A typical end-to-end validation flow is:

```text
Configure
   |
   v
Helm Upgrade
   |
   v
Simulator Pod receives configuration
   |
   v
Simulator generates records
   |
   v
CDR -- TCP --> Receiver
NAT -- UDP --> Receiver
   |
   v
Receiver writes CSV
   |
   v
Validate sent count
   |
   v
Validate received count
   |
   v
Validate CSV output
```

Detailed test results and evidence are maintained separately as part of the final project documentation.

---

## 20. Example Test Scenario

Example configuration:

```text
recordCount = 10
datatype    = cdr,nat
timeperiod  = 1000
```

Expected:

```text
CDR Generated = 10
CDR Sent      = 10
CDR Received  = 10

NAT Generated = 10
NAT Sent      = 10
NAT Received  = 10
```

For the CSV files, the expected line count is:

```text
1 header + 10 records = 11 lines
```

Testing evidence includes:

- Configuration screenshot
- Simulator logs
- Receiver logs
- CSV output verification
- Kubernetes resource status
- Relevant command output

---

## 21. Troubleshooting

### Docker image not found

If Docker reports an image/tag as not found, verify that the image and tag exist in Docker Hub and that the deployment uses the same tag.

```bash
docker pull pkistrying/data-refinery-simulator:latest
docker pull pkistrying/receiver-server:latest
```

When using Kubernetes with `latest`, verify that the Deployment uses:

```yaml
imagePullPolicy: Always
```

### Receiver Pod CrashLoopBackOff

Check:

```bash
kubectl logs deployment/refinery-receiver -n refinery
```

Verify that the output directory is created before the CSV files are opened.

### Simulator cannot send CDR records

Check:

```bash
kubectl exec -it deployment/refinery-simulator -n refinery -- sh
```

Then:

```bash
echo $CDRHOST
echo $CDRPORT
```

Expected Kubernetes values:

```text
refinery-receiver-service
5000
```

Check the Receiver Service:

```bash
kubectl get service refinery-receiver-service -n refinery
```

### Simulator cannot send NAT records

Check:

```bash
echo $NATHOST
echo $NATPORT
```

Expected:

```text
refinery-receiver-service
5001
```

Verify that the Service exposes UDP port `5001`.

### Helm configuration changed but Simulator still uses old values

A ConfigMap update does not automatically update environment variables inside an already-running container.

Run:

```bash
helm upgrade refinery helm/refinery -n refinery
```

Then restart the Simulator:

```bash
kubectl rollout restart deployment/refinery-simulator -n refinery
```

Verify:

```bash
kubectl exec deployment/refinery-simulator -n refinery -- sh -c "echo RECORDCOUNT=$RECORDCOUNT && echo DATATYPE=$DATATYPE && echo TIMEPERIOD=$TIMEPERIOD"
```

---

## 22. Deployment Comparison

| Environment | Technology | Purpose |
|---|---|---|
| Local development | Java / Spring Boot | Application development and debugging |
| AWS EC2 | Docker + Docker Compose | Cloud container deployment |
| Local Minikube | Kubernetes | Container orchestration validation |
| Local Minikube | Helm | Kubernetes packaging and configuration |

The AWS and Kubernetes environments were intentionally kept separate during validation.

---

## 23. Project Workflow

```text
                    SOURCE CODE
                        |
                        v
                     GitHub
                        |
                        v
                     Jenkins
                        |
             +----------+----------+
             |                     |
        Maven Build          Docker Build
             |                     |
             +----------+----------+
                        v
                    Docker Hub
                    /         \
                   /           \
                  v             v
             AWS EC2        Local Minikube
                  |             |
            Docker Compose      Helm
                  |             |
                  v             v
             Simulator       Simulator
                  |             |
                  v             v
              Receiver        Receiver
                  |             |
                  +------+------+ 
                         v
                     CSV Output
                         |
                         v
                  Testing & Validation
```

---

## 24. Final Project Status

The project implementation and deployment workflow have been completed and validated.

Completed:

- [x] CDR generation
- [x] NAT generation
- [x] TCP transmission
- [x] UDP transmission
- [x] Configurable record count
- [x] Configurable data type
- [x] Configurable timestamp
- [x] Configurable time period
- [x] Runtime configuration
- [x] Environment-variable configuration
- [x] Runtime transmission statistics
- [x] Long-running Receiver
- [x] Flat-file persistence
- [x] Docker containerization
- [x] Docker Compose deployment
- [x] Jenkins CI/CD
- [x] Docker Hub publishing
- [x] AWS EC2 deployment
- [x] Kubernetes deployment
- [x] Minikube validation
- [x] Helm chart
- [x] Helm install/upgrade/uninstall workflow
- [x] End-to-end testing
- [x] Testing evidence collection

---

## 25. Future Enhancements

Potential future improvements include:

- API-triggered simulation runs instead of restarting the finite Simulator workload.
- Centralized persistent storage instead of Pod-local flat files.
- Centralized log aggregation.
- Application metrics and monitoring.
- Automated integration and end-to-end tests.
- Kubernetes health probes.
- Automatic Deployment rollout when configuration ConfigMaps change.
- Horizontal scaling of the Receiver.
- Message-queue based ingestion for higher-volume workloads.
- Deployment of the Kubernetes/Helm environment to a managed cloud Kubernetes platform.

These are potential extensions and are not part of the current implementation.

---

## 26. Related Repositories

The project is divided into separate application and deployment repositories:

- **Data Refinery Simulator** — simulator application and record generation.
- https://github.com/kushpulkit95/data-refinery-simulator
- **Receiver Server** — TCP/UDP receiver and flat-file persistence.
- https://github.com/kushpulkit95/receiver-server
- **Refinery Deployment** — Docker Compose, Kubernetes, and Helm deployment configuration.
- https://github.com/kushpulkit95/refinery-deployment

---

## 27. Author

**Pulkit Kush**

B.Tech Computer Science — Data Science

Internship Project

Java • Spring Boot • TCP/UDP • Docker • Jenkins • AWS • Kubernetes • Helm • CI/CD
