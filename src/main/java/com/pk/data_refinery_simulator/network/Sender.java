/*
 * -----------------------------------------------------------------------------
 * Sender Interface
 * -----------------------------------------------------------------------------
 *
 * Purpose:
 * Defines a common contract for every class capable of sending records.
 *
 * Why use an interface?
 *
 * The simulator supports multiple communication protocols such as:
 *
 *      - TCP
 *      - UDP
 *      - (Future protocols like HTTP, Kafka, etc.)
 *
 * Every protocol sends data differently, but from the simulator's perspective,
 * the only operation it cares about is:
 *
 *      send(String message)
 *
 * Instead of making SimulatorRunner depend directly on TcpSender or UdpSender,
 * it depends on this interface. This allows the communication protocol to be
 * selected at runtime (using application.yml) without changing the simulator's
 * business logic.
 *
 * Before introducing this interface, switching protocols required modifying
 * SimulatorRunner and replacing sender objects manually.
 *
 * With this interface:
 *
 *      protocol = tcp
 *          -> TcpSender
 *
 *      protocol = udp
 *          -> UdpSender
 *
 * SimulatorRunner simply works with a Sender reference and calls:
 *
 *      sender.send(message);
 *
 * It does not know (or need to know) whether the implementation uses TCP,
 * UDP, or any future protocol.
 *
 * This follows the Dependency Inversion Principle (DIP):
 *
 *      High-level module (SimulatorRunner)
 *              ↓
 *      depends on
 *              ↓
 *      Sender (abstraction)
 *              ↑
 *      implemented by
 *      TcpSender / UdpSender
 *
 * Benefits:
 * - Removes duplicate code.
 * - Makes protocol switching configurable.
 * - Improves maintainability and extensibility.
 * - Keeps SimulatorRunner focused on simulation rather than networking details.
 * -----------------------------------------------------------------------------
 */
package com.pk.data_refinery_simulator.network;

public interface Sender {
    boolean send(String message);
}