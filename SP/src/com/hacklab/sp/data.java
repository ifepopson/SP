package com.hacklab.sp;

public class data {
	
	static String columnhead = "\"Location\",\"User_Location\",\"Interview_ER\",\"Interview_Hour\",\"Interview_Minute\",\"Info-date\",\"Month\",\"Gender\",\"Q1\",\"Q2-1\",\"Q2-2\",\"Q2-3\",\"Q2-4\",\"Q3-Hour\",\"Q3-Minute\",\"Q4-Danfo-Hour\",\"Q4-Danfo-Minute\",\"Q4-BRT-Hour\",\"Q4-BRT-Minute\",\"Q4-BigBus-Hour\",\"Q4-BigBus-Minute\",\"Q4-Waiting-Hour\",\"Q4-Waiting-Minute\",\"Q4-Walking-Hour\",\"Q4-Walking-Minute\",\"Q4-other-Hour\",\"Q4-Other-Minute\",\"Q5-Origin\",\"Q6-Destination\",\"Q7-FareforFuel\",\"Q7-FareforParking\",\"Q8Set\",\"SP1\",\"SP2\",\"SP3\",\"SP4\",\"SP5\",\"SP6\",\"SP7\",\"SP8\",\"Q9-Income\",\"Occupation\",\"NumberOfAdults\",\"NumberOfChildren\"\n";
	static String columnheadcar = "\"Location\",\"User_Location\",\"Interview_ER\",\"Interview_Hour\",\"Interview_Minute\",\"Info-date\",\"Month\",\"Gender\",\"Q1\",\"Q2-1\",\"Q2-2\",\"Q2-3\",\"Q2-4\",\"Q3-Hour\",\"Q3-Minute\",\"Q4-Car-Hour\",\"Q4-Car-Minute\",\"Q4-taxi-Hour\",\"Q4-taxi-Minute\",\"Q4-Brt-Hour\",\"Q4-Brt-Minute\",\"Q4-kekenapep-Hour\",\"Q4-kekenapep-Minute\",\"Q4-Walking-Hour\",\"Q4-Walking-Minute\",\"Q4-other-Hour\",\"Q4-Other-Minute\",\"Q5-Origin\",\"Q6-Destination\",\"Q7-FareforFuel\",\"Q7-FareforParking\",\"Q8Set\",\"SP1\",\"SP2\",\"SP3\",\"SP4\",\"SP5\",\"SP6\",\"SP7\",\"SP8\",\"Q9-Income\"\n";
	
	
	static String currentrow = "";
	static String vehicle = "";
	static String userlocation = "";
	//if 0 dn its danfo/bus else its car studf
	static int deter = 0;
	
	//fdanfo 
	static String location = "";
	static String hour = "";
	static String minute = "";
	static String date = "";
	static String gender = "";
	static String q1 = "";
	static String q2_1 = "";
	static String q2_2 = "";
	static String q2_3 = "";
	static String q2_4 = "";
	static String q3hour = "";
	static String q3minute = "";
	static String q4_Danfo_Hour = "";
	static String q4_Danfo_minute = "";
	static String q4_brt_Hour = "";
	static String q4_brt_minute = "";
	static String q4_bigbus_Hour = "";
	static String q4_bigbus_minute = "";
	static String q4_waiting_Hour = "";
	static String q4_waiting_minute = "";
	static String q4_walking_Hour = "";
	static String q4_walking_minute = "";
	static String q4_other_hour = "";
	static String q4_other_minute = "";
	static String q5_origin = "";
	static String q6_destination = "";
	static String q7_fareforfuel = "";
	static String q7_fareforparking = "";
	static String sp1 = "";
	static String sp2 = "";
	static String sp3 = "";
	static String sp4 = "";
	static String sp5 = "";
	static String sp6= "";
	static String sp7 = "";
	static String sp8 = "";
	static String q9_income = "";
	
	
	//car
	static String carlocation = "";
	static String cargender = "";
	static String carq1 = "";
	static String carq2_1 = "";
	static String carq2_2 = "";
	static String carq2_3 = "";
	static String carq2_4 = "";
	static String carq3_hour= "";
	static String carq3_minute= "";
	static String carq4car_hour = "";
	static String carq4car_minute = "";
	static String carq4_taxi_Hour = "";
	static String carq4_taxi_minute = "";
	static String carq4_napep_Hour = "";
	static String carq4_napep_minute = "";
	static String carq4_bigbus_Hour = "";
	static String carq4_bigbus_minute = "";
	static String carq4_walking_Hour = "";
	static String carq4_walking_minute = "";
	static String carq4_other_hour = "";
	static String carq4_other_minute = "";
	static String carq5_origin = "";
	static String carq6_destination = "";
	static String carq7_fareforfuel = "";
	static String carq7_fareforparking = "";
	static String carsp1 = "";
	static String carsp2 = "";
	static String carsp3 = "";
	static String carsp4 = "";
	static String carsp5 = "";
	static String carsp6= "";
	static String carsp7 = "";
	static String carsp8 = "";
	static String carq9_income = "";
	
	static String adult = "0";
	static String child = "0";
	static String occupation="";

}
