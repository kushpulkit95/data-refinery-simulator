package com.pk.data_refinery_simulator;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pk.data_refinery_simulator.generator.CDRGenerator;
import com.pk.data_refinery_simulator.generator.NATGenerator;
import com.pk.data_refinery_simulator.model.CDRRecord;
import com.pk.data_refinery_simulator.model.NATRecord;

@SpringBootApplication
public class DataRefinerySimulatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataRefinerySimulatorApplication.class, args);
		//to launch a Spring Application from a standard java main method

		// CDRGenerator gen1 = new CDRGenerator();
		// List<CDRRecord> cdrRecords = gen1.generateRecords(recordCount);
		// System.out.println("===== CDR =====");
		// for(CDRRecord record : cdrRecords){
		// 	System.out.println(record);
		// }

		// NATGenerator gen2 = new NATGenerator();
		// List<NATRecord> natRecords = gen2.generateRecords(recordCount);
		// System.out.println("===== NAT =====");
		// for(NATRecord record : natRecords){
		// 	System.out.println(record);
		// }
	}
}