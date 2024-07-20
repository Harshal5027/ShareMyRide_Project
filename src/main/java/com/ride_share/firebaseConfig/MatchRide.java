package com.ride_share.firebaseConfig;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.ride_share.dashboards.HomePage;
import com.ride_share.dashboards.ViewPage;

public class MatchRide {
    private static String selectedRide;
    private static String endRide;

    public static String nameUser;
    public static String emailUser;
    public static String phoneNoUser;
    public static String rideAcceptFlag;
    public static String nameDriver;
    public static String vehicleNoDriver;
    public static String phoneNoDriver;
    // public static String emailD;

    // Match Ride of user and driver
    // to see rides of that rout to user

    // Here email,startDestinstion and endDestination directly come through fire
    // store from driverRide
    public static void matchRide(String emailD, String selectedValue, String endValue)
            throws InterruptedException, ExecutionException {

        DocumentSnapshot document = DataService.db.collection("driverRide").document(emailD).get().get();
        if (document.exists()) {
            selectedRide = document.getString("startDestinationDriver");
            endRide = document.getString("endDestinationDriver");
            ViewPage obj = new ViewPage();
            // Compare Firestore data with current data
            try {
                if (selectedRide.equals(selectedValue) || endRide.equals(selectedValue)) {
                    System.out.println("Data from Firestore is equal to current data.");
                    // emailD=document.getId();
                    obj.readRecDriverForMatchRide(emailD);
                } else {
                    System.out.println("Data from Firestore is not equal to current data.");
                }
            } catch (Exception e) {
                System.out.println("No Ride available");
            }
        } else {
            System.out.println("No such document ");
        }

        /*
         * if (document.exists()) {
         * Object obj1 = document.getString("startDestinationDriver");
         * selectedRide=obj1.toString();
         * System.out.println(selectedRide);
         * System.out.println(selectedValue);
         * 
         * Object ob = document.getString("endDestinationDriver");
         * endRide=ob.toString();
         * System.out.println(endRide);
         * System.out.println(endValue);
         * 
         * if(selectedRide ==selectedValue || endRide==endValue){
         * System.out.println("Start Destination");
         * 
         * }
         * else{
         * System.out.println("No Destination Found");
         * 
         * }
         * 
         * }else{
         * System.out.println("No Document Found");
         * }
         */
    }

    // Code for Retrive data of Passenger/User from fire store in Response of Driver

    public void readRecUserInDriverResponse(String email) throws InterruptedException, ExecutionException {

        DocumentReference docRef = DataService.db.collection("users").document(email);

        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot docSnap = snapShot.get();

        if (docSnap.exists()) {
            // Firebase varun yenara data type Object aahe , so convert it into string
            Object obj = (docSnap.get("name"));
            nameUser = obj.toString();
            Object obj2 = docSnap.get("email");
            emailUser = obj2.toString();
            Object obj3 = docSnap.get("phoneNo");
            phoneNoUser = obj3.toString();
            System.out.println(nameUser);

        } else {
            System.out.println("Document Not Found");
        }
    }

    // Retrive data from fire store DriverRide for accept ride to show User ticket
    // Call method in Home page in response button
    public void readRecDriverToGenerateTicket(String email) throws InterruptedException, ExecutionException {

        DocumentReference docRef = DataService.db.collection("driverRideFlag").document(email);

        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot docSnap = snapShot.get();

        if (docSnap.exists()) {
            // Firebase varun yenara data type Object aahe , so convert it into string
            Object obj = (docSnap.get("rideAcceptFlag"));
            rideAcceptFlag = obj.toString();

        } else {
            System.out.println("Document Not Found");
        }
    }

    // Retrive data from fire store Drivere to show Driver data on ticket
    // Call method in Home page in response button
    public void readRecDriverDataOnUserTicket(String email) throws InterruptedException, ExecutionException {

        DocumentReference docRef = DataService.db.collection("drivers").document(email);

        ApiFuture<DocumentSnapshot> snapShot = docRef.get();
        DocumentSnapshot docSnap = snapShot.get();

        if (docSnap.exists()) {
            // Firebase varun yenara data type Object aahe , so convert it into string
            Object obj = (docSnap.get("name"));
            nameDriver = obj.toString();
            Object obj2 = docSnap.get("vehicleNo");
            vehicleNoDriver = obj2.toString();
            Object obj3 = docSnap.get("phoneNo");
            phoneNoDriver = obj3.toString();
            System.out.println(nameUser);

        } else {
            System.out.println("Document Not Found");
        }
    }

}
