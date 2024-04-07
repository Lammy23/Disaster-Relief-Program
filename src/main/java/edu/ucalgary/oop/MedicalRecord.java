package edu.ucalgary.oop;

/*
- location: Location
- treatmentDetails: String
- dateOfTreatment: String

+ MedicalRecord(location: Location, treatmentDetails:
string, dateOfTreatment: String)

+ parseDate(date: String, for: String): String

+ getLocation(): Location
+ getTreatmentDetails(): String
+ getDateOfTreatment(): String

+ setTreatmentDetails(treatmentDetails: String): void
+ setLocation(location: Location): void
+ setDateOfTreatment(dateOfTreatment: String): void
*/

public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private String dateOfTreatment;

    public MedicalRecord(Location location, String treatmentDetails, String dateOfTreatment) {
        this.location = location;
        this.treatmentDetails = treatmentDetails;
        this.dateOfTreatment = dateOfTreatment;
    }

    //TODO: Figure out how to connect date parser


    public Location getLocation() {
        return this.location;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public String getDateOfTreatment() {
        return dateOfTreatment;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = treatmentDetails;
    }

    public void setDateOfTreatment(String dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }
}
