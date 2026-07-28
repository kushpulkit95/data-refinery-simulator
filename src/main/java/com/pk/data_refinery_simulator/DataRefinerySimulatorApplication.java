package com.pk.data_refinery_simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataRefinerySimulatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataRefinerySimulatorApplication.class, args);
		//to launch a Spring Application from a standard java main method

		// ========== ALL THIS IS NOT NEEDED NOW!!! WE MADE BEANS!!! ==========
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
		// =====================================================================
	}
}