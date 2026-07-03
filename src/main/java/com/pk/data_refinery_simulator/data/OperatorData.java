/*This class's function is to have fixed operator data stored, because fields like mnc, mcc and ndc cannot be random
and must not vary field to field. For Example, mcc in imsi must be the same in apn, that is why this class is necessary */
package com.pk.data_refinery_simulator.data;

import com.pk.data_refinery_simulator.model.Operator;

import java.util.List;

public final class OperatorData {

    public static final List<Operator> OPERATORS = List.of(
        // --- INDIA ---
        new Operator(
                "Airtel",
                "India",
                "91",
                "404",
                "45",
                "98"
        ),

        new Operator(
                "Jio",
                "India",
                "91",
                "405",
                "53",
                "70"
        ),

        new Operator(
                "Vodafone Idea",
                "India",
                "91",
                "404",
                "10",
                "99"
        ),

        new Operator(
                "BSNL",
                "India",
                "91",
                "404",
                "38",
                "94"
        ),

        // --- UNITED STATES ---
        new Operator(
                "AT&T",
                "United States",
                "1",
                "310",
                "410",
                "415"
        ),

        new Operator(
                "T-Mobile",
                "United States",
                "1",
                "310",
                "260",
                "206"
        ),

        new Operator(
                "Verizon",
                "United States",
                "1",
                "311",
                "480",
                "917"
        ),

        // --- UNITED KINGDOM ---
        new Operator(
                "EE",
                "United Kingdom",
                "44",
                "234",
                "30",
                "791"
        ),

        new Operator(
                "Vodafone",
                "United Kingdom",
                "44",
                "234",
                "15",
                "777"
        ),

        new Operator(
                "O2",
                "United Kingdom",
                "44",
                "234",
                "10",
                "786"
        ),

        // --- GERMANY ---
        new Operator(
                "Deutsche Telekom",
                "Germany",
                "49",
                "262",
                "01",
                "171"
        ),

        new Operator(
                "Vodafone",
                "Germany",
                "49",
                "262",
                "02",
                "172"
        ),

        // --- AUSTRALIA ---
        new Operator(
                "Telstra",
                "Australia",
                "61",
                "505",
                "01",
                "417"
        ),

        new Operator(
                "Optus",
                "Australia",
                "61",
                "505",
                "02",
                "412"
        ),

        // --- BRAZIL ---
        new Operator(
                "Vivo",
                "Brazil",
                "55",
                "724",
                "10",
                "11"
        ),

        new Operator(
                "Claro",
                "Brazil",
                "55",
                "724",
                "05",
                "21"
        ),

        // --- JAPAN ---
        new Operator(
                "NTT Docomo",
                "Japan",
                "81",
                "440",
                "10",
                "90"
        ),

        new Operator(
                "SoftBank",
                "Japan",
                "81",
                "440",
                "20",
                "80"
        ),

        // --- SOUTH AFRICA ---
        new Operator(
                "Vodacom",
                "South Africa",
                "27",
                "655",
                "01",
                "82"
        ),

        new Operator(
                "MTN",
                "South Africa",
                "27",
                "655",
                "10",
                "83"
        ),

        // --- UNITED ARAB EMIRATES ---
        new Operator(
                "Etisalat",
                "United Arab Emirates",
                "971",
                "424",
                "02",
                "50"
        ),

        new Operator(
                "du",
                "United Arab Emirates",
                "971",
                "424",
                "03",
                "55"
        )
    );
}