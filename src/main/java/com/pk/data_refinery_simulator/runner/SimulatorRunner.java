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
import com.pk.data_refinery_simulator.enums.DataType;

@Component
public class SimulatorRunner implements CommandLineRunner{
    /*main() called SpringApplication.run() which eventually called SimulatorRunner.run() */

    private final CDRGenerator cdrGenerator;
    private final SimulatorProperties simulatorProperties;
    private final NATGenerator natGenerator;
    //private & final coz we wnt SimulatorRunner only to use this and that it should not change.
    
    public SimulatorRunner(
        SimulatorProperties simulatorProperties,
        CDRGenerator cdrGenerator,
        NATGenerator natGenerator
    ){
        this.simulatorProperties = simulatorProperties;
        this.cdrGenerator = cdrGenerator;
        this.natGenerator = natGenerator;
    }
    //Constructor Dependency Injection - "To Create SimulatorRunner, I need this constructor"
    @Override
    public void run(String ...args) throws Exception {
        System.out.println("Simulator Started!");
        //System.out.println("Record Count is: "+ simulatorProperties.getRecordCount());
        //---> this was test run to see if simulator is reading the config or not

        int recordCount = simulatorProperties.getRecordCount();
        DataType datatype = simulatorProperties.getDataType();
        //We using ENUM because VSCode auto suggests CDR,NAT,BOTH and easier to DEBUG

        if(datatype==DataType.CDR || datatype==DataType.BOTH){
            //CDRGenerator gen1 = new CDRGenerator();
            //No need to use 'new' as we created bean for this
            List<CDRRecord> cdrRecords = cdrGenerator.generateRecords(recordCount);
            System.out.println("===== CDR =====");
            for(CDRRecord record : cdrRecords){
            	System.out.println(record);
            }
        }
        if(datatype==DataType.NAT || datatype==DataType.BOTH){
            //NATGenerator gen2 = new NATGenerator();
            //No need to use 'new' as we created bean for this
            List<NATRecord> natRecords = natGenerator.generateRecords(recordCount);
            System.out.println("===== NAT =====");
            for(NATRecord record : natRecords){
                System.out.println(record);
            }
        }
    }
}