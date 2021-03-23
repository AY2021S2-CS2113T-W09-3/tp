package seedu.duke.model;

import java.util.ArrayList;

/**
 * Each instance of this class represent a visit record. It can contain
 * medical diagnosis, prescription, test results, etc.
 */
public class Record {
    protected ArrayList<String> symptoms, diagnoses, prescriptions;

    /**
     * Create a new visit record with details as a single string.
     */
    public Record() {
        this.symptoms = new ArrayList<>();
        this.diagnoses = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    /**
     * Obtain the consultation detail of this record.
     *
     * @return A String containing the consultation details contained in this record
     */
    public String getConsultationDetail() {
        String consultationDetail = "";
        consultationDetail += "Symptoms:\n";
        for (String symptom : symptoms) {
            consultationDetail += "\t" + symptom + "\n";
        }
        consultationDetail += "Diagnoses:\n";
        for (String diagnosis : diagnoses) {
            consultationDetail += "\t" + diagnosis + "\n";
        }
        consultationDetail += "Prescriptions:\n";
        for (String prescription : prescriptions) {
            consultationDetail += "\t" + prescription + "\n";
        }
        return consultationDetail;
    }

    @Override
    public String toString() {
        return getConsultationDetail();
    }

    public void addDetails(String symptom, String diagnosis, String prescription) {
        symptoms.add(symptom);
        diagnoses.add(diagnosis);
        prescriptions.add(prescription);
    }
}
