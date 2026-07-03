/*
 * -----------------------------------------------------------------------------
 * SimulatorRunner
 * -----------------------------------------------------------------------------
 *
 * Purpose:
 * This class contains the simulator workflow that should execute after the
 * Spring Boot application has completely started.
 *
 * Why not write this logic inside the main() method?
 *
 * The responsibility of main() is only to start the Spring Boot application.
 * After calling SpringApplication.run(...), Spring Boot takes control of the
 * application lifecycle by creating beans, loading configuration files,
 * performing dependency injection, and preparing the application.
 *
 * Business logic such as:
 *  - reading configuration,
 *  - generating CDR/NAT records,
 *  - writing CSV files,
 *  - sending records over TCP,
 *  - logging statistics,
 * should not be mixed with application startup.
 *
 * Instead, this class implements CommandLineRunner. Spring Boot automatically
 * calls its run() method once the application is fully initialized.
 *
 * This design follows the Single Responsibility Principle (SRP):
 *
 *   DataRefinerySimulatorApplication
 *       -> Starts Spring Boot.
 *
 *   SimulatorRunner
 *       -> Coordinates and executes the simulator workflow.
 *
 * Keeping these responsibilities separate makes the project easier to
 * understand, maintain, test, and extend as new features are added.
 * -----------------------------------------------------------------------------
 */
package com.pk.data_refinery_simulator.runner;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pk.data_refinery_simulator.config.SimulatorProperties;
import com.pk.data_refinery_simulator.generator.CDRGenerator;
import com.pk.data_refinery_simulator.generator.NATGenerator;
import com.pk.data_refinery_simulator.model.CDRRecord;
import com.pk.data_refinery_simulator.model.NATRecord;

@Component
public class SimulatorRunner implements CommandLineRunner{
    /*main() called SpringApplication.run() which eventually called SimulatorRunner.run() */

    private final SimulatorProperties simulatorProperties;
    //private & final coz we wnt SimulatorRunner only to use this and that it should not change.
    
    public SimulatorRunner(SimulatorProperties simulatorProperties){
        this.simulatorProperties = simulatorProperties;
    }
    //Constructor Dependency Injection
    @Override
    public void run(String ...args) throws Exception {
        System.out.println("Simulator Started!");
        //System.out.println("Record Count is: "+ simulatorProperties.getRecordCount());
        //---> this was test run to see if simulator is reading the config or not

        int recordCount = simulatorProperties.getRecordCount();
        String datatype = simulatorProperties.getDataType();

        if(datatype.equals("CDR") || datatype.equals("BOTH")){
            CDRGenerator gen1 = new CDRGenerator();
            List<CDRRecord> cdrRecords = gen1.generateRecords(recordCount);
            System.out.println("===== CDR =====");
            for(CDRRecord record : cdrRecords){
            	System.out.println(record);
            }
        }
        if(datatype.equals("NAT") || datatype.equals("BOTH")){
            NATGenerator gen2 = new NATGenerator();
            List<NATRecord> natRecords = gen2.generateRecords(recordCount);
            System.out.println("===== NAT =====");
            for(NATRecord record : natRecords){
                System.out.println(record);
            }
        }
    }
}