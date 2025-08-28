package control;

import adt.QueueInterface;
import adt.LinkedQueue;
import entity.MedicalTreatment;
import entity.Prescription;
import entity.DiagnosisTemplate; // Correctly changed from entity.*
import java.util.Date;

public class MedicalTreatmentControl {

    // Declare Collection ADT objects like the sample
    private final QueueInterface<MedicalTreatment> treatmentHistory = new LinkedQueue<>();
    private final QueueInterface<DiagnosisTemplate> diagnosisTemplates = new LinkedQueue<>();
    private final PharmacyControl pharmacyControl;

    public MedicalTreatmentControl(PharmacyControl pharmacyControl) {
        this.pharmacyControl = pharmacyControl;
    }
    
    // --- Template Management Methods (Correct) ---
    public void addDiagnosisTemplate(String id, String name, String desc) {
        diagnosisTemplates.enqueue(new DiagnosisTemplate(id, name, desc));
    }

    public String[][] getDiagnosisTemplatesForDisplay() {
        DiagnosisTemplate[] templates = new DiagnosisTemplate[diagnosisTemplates.size()];
        templates = diagnosisTemplates.toArray(templates);
        String[][] displayData = new String[templates.length][3];
        for (int i = 0; i < templates.length; i++) {
            displayData[i][0] = String.valueOf(i + 1);
            displayData[i][1] = templates[i].getTemplateId();
            displayData[i][2] = templates[i].getDiagnosisName();
        }
        return displayData;
    }

    private DiagnosisTemplate findTemplateById(String templateId) {
        DiagnosisTemplate[] templates = new DiagnosisTemplate[diagnosisTemplates.size()];
        templates = diagnosisTemplates.toArray(templates);
        for (DiagnosisTemplate template : templates) {
            if (template.getTemplateId().equalsIgnoreCase(templateId)) {
                return template;
            }
        }
        return null;
    }

    // --- CRUD Operations ---

    // CREATE (This is correct from the last version)
   public String createTreatment(String patientID, String doctorID, String patientSicknessDesc, String sickType,
                                  String chosenTemplateID, String medicationID, int quantity) {

        // **STEP 1: INTERACTION WITH PHARMACY MODULE**
        // Ask the PharmacyControl if the requested medication ID is valid by checking
        // against the data loaded by the ClinicInitializer.
        // **FIX: Corrected the typo from "medicationIdIdExists" to "medicationIdExists"**
        if (!pharmacyControl.medicationIdExists(medicationID)) {
            return "Error: Medication ID '" + medicationID + "' does not exist in the pharmacy stock.";
        }
        
        // Find the chosen diagnosis template
        DiagnosisTemplate template = findTemplateById(chosenTemplateID);
        if (template == null) {
            return "Error: Invalid Diagnosis Template ID selected.";
        }
        
        // Generate a unique ID for this treatment record
        String newTreatmentID = "T" + String.format("%03d", treatmentHistory.size() + 1);

        // Create the full medical treatment record
        MedicalTreatment newTreatment = new MedicalTreatment(
            newTreatmentID, template.getTemplateId(), patientID, doctorID, medicationID,
            patientSicknessDesc, sickType, template.getDefaultDescription(), new Date()
        );
        newTreatment.setDispensedQuantity(quantity);
        treatmentHistory.enqueue(newTreatment);

        // **STEP 2: INTERACTION WITH PHARMACY MODULE**
        // Create a prescription object to send to the pharmacy's approval queue.
        Prescription newPrescription = new Prescription(newTreatmentID, patientID, medicationID, quantity);
        
        // Send the request to the PharmacyControl.
        pharmacyControl.requestPrescriptionApproval(newPrescription);

        return "Medical Treatment " + newTreatmentID + " created successfully. Prescription sent to pharmacy for approval.";
    }

    // READ (This is now the only "get history" method)
    public String[][] getTreatmentHistoryForDisplay() {
        MedicalTreatment[] history = new MedicalTreatment[treatmentHistory.size()];
        history = treatmentHistory.toArray(history);
        String[][] displayData = new String[history.length][8];
        for (int i = 0; i < history.length; i++) {
            MedicalTreatment t = history[i];
            displayData[i][0] = t.getTreatmentID();
            displayData[i][1] = t.getPatientID();
            displayData[i][2] = t.getDoctorID();
            displayData[i][3] = t.getPatientSicknessDescription();
            displayData[i][4] = t.getDiagnosisDescription();
            displayData[i][5] = t.getMedicationID();
            displayData[i][6] = String.valueOf(t.getDispensedQuantity());
            displayData[i][7] = t.getCreatedDate().toString();
        }
        return displayData;
    }

    // UPDATE (Now uses treatmentID as the key)
    public boolean updateTreatment(String treatmentID, String newSicknessDesc, String newDiagnosisDesc) {
        MedicalTreatment treatmentToUpdate = findTreatmentById(treatmentID);
        if (treatmentToUpdate != null) {
            treatmentToUpdate.setPatientSicknessDescription(newSicknessDesc);
            treatmentToUpdate.setDiagnosisDescription(newDiagnosisDesc);
            return true;
        }
        return false;
    }

    // DELETE (Now uses treatmentID as the key)
    public boolean deleteTreatment(String treatmentID) {
        boolean found = false;
        QueueInterface<MedicalTreatment> tempQueue = new LinkedQueue<>();

        while (!treatmentHistory.isEmpty()) {
            MedicalTreatment current = treatmentHistory.dequeue();
            if (!current.getTreatmentID().equalsIgnoreCase(treatmentID)) {
                tempQueue.enqueue(current);
            } else {
                found = true;
            }
        }

        // refill the original queue instead of reassigning
        while (!tempQueue.isEmpty()) {
            treatmentHistory.enqueue(tempQueue.dequeue());
        }

        return found;
    }

    // --- Helper Methods ---

    // Safe check for the UI, now using treatmentID
    public boolean treatmentIdExists(String treatmentID) {
        return findTreatmentById(treatmentID) != null;
    }

    // Private helper, now finds by treatmentID
    private MedicalTreatment findTreatmentById(String treatmentID) {
        if (treatmentHistory.isEmpty()) return null;
        MedicalTreatment[] historyArray = new MedicalTreatment[treatmentHistory.size()];
        historyArray = treatmentHistory.toArray(historyArray);
        for (MedicalTreatment treat : historyArray) {
            if (treat.getTreatmentID().equalsIgnoreCase(treatmentID)) {
                return treat;
            }
        }
        return null;
    }
    
    // --- SEARCH BY PATIENT ID ---
    public QueueInterface<MedicalTreatment> searchTreatmentByPatientId(String patientId) {
        QueueInterface<MedicalTreatment> result = new LinkedQueue<>();

        // Convert queue to array for iteration
        MedicalTreatment[] historyArray = new MedicalTreatment[treatmentHistory.size()];
        historyArray = treatmentHistory.toArray(historyArray);

        for (MedicalTreatment t : historyArray) {
            if (t.getPatientID().equalsIgnoreCase(patientId)) {
                result.enqueue(t);   // add to custom queue instead of ArrayList
            }
        }
        return result;
    }
    
    public String generatePatientHistoryReport(String patientID) {
    QueueInterface<MedicalTreatment> treatments = searchTreatmentByPatientId(patientID);
    
    if (treatments.isEmpty()) {
        return "No treatment history found for patient ID: " + patientID;
    }

    StringBuilder report = new StringBuilder();
    MedicalTreatment[] treatmentArray = treatments.toArray(new MedicalTreatment[0]);

    // Professional Header - FIXED WIDTH
    report.append("===================================================================\n");
    report.append("|                     PATIENT TREATMENT HISTORY                    |\n");
    report.append("|==================================================================|\n");
    report.append("| Patient ID: ").append(String.format("%-53s", patientID)).append("|\n");
    report.append("| Generated: ").append(String.format("%-54s", new Date())).append("|\n");
    report.append("===================================================================\n\n");

    // Treatment Summary
    report.append("TREATMENT SUMMARY:\n");
    report.append("===================================================================\n");
    report.append("Total Treatments: ").append(treatmentArray.length).append("\n\n");

    // Treatment Details Table
    report.append("DETAILED TREATMENT HISTORY:\n");
    report.append("===================================================================\n");
    report.append(String.format("%-12s %-12s %-13s %-12s\n", 
        "TreatmentID", "Date", "Doctor ID", "Medication"));
    report.append("===================================================================\n");

    for (MedicalTreatment t : treatmentArray) {
        String shortDate = t.getCreatedDate().toString().substring(0, 10);
        report.append(String.format("%-12s %-12s %-13s  %-12s\n",
            t.getTreatmentID(),
            shortDate,
            t.getDoctorID(),
            t.getMedicationID()));
    }

    // Statistics Section
    report.append("\nSTATISTICS:\n");
    report.append("===================================================================\n");
    
    // Count by sickness type 
    int acute = 0, chronic = 0, followup = 0;
    for (MedicalTreatment t : treatmentArray) {
        String sickType = t.getSickType().toLowerCase().trim();
        
        if (sickType.contains("acute")) {
            acute++;
        } 
        else if (sickType.contains("chronic")) {
            chronic++;
        }
        else if (sickType.contains("follow")) {
            followup++;
        }
        else {
            // If none match, assume it's follow-up
            followup++;
        }
    }
    
    report.append("Acute Treatments:    ").append(acute).append("\n");
    report.append("Chronic Treatments:  ").append(chronic).append("\n");
    report.append("Follow-up Visits:    ").append(followup).append("\n\n");

    // SICKNESS TYPE HISTOGRAM
    report.append("SICKNESS TYPE DISTRIBUTION:\n");
    report.append("===================================================================\n");
    report.append(generateCompactHistogram(new int[]{acute, chronic, followup}, 
                                         new String[]{"Acute", "Chronic", "Follow-up"}));
    report.append("\n");

    // Footer
    report.append("===================================================================\n");
    report.append("END OF REPORT - ").append(new Date()).append("\n");
    report.append("===================================================================\n");

    return report.toString();
}

// Compact histogram generator
private String generateCompactHistogram(int[] values, String[] labels) {
    StringBuilder histogram = new StringBuilder();
    
    if (values.length == 0 || values.length != labels.length) {
        return "No data available for histogram";
    }
    
    // Find maximum value for scaling
    int maxValue = 0;
    for (int value : values) {
        if (value > maxValue) maxValue = value;
    }
    
    if (maxValue == 0) {
        return "No data available for histogram";
    }
    
    // Scale to fit within the report width
    final int MAX_WIDTH = 30;
    
    for (int i = 0; i < values.length; i++) {
        int barLength = maxValue > 0 ? (int) ((double) values[i] / maxValue * MAX_WIDTH) : 0;
        String bar = new String(new char[barLength]).replace('\0', '*');
        
        // Adjusted formatting to match header width
        histogram.append(String.format("%-10s | %-30s | %3d\n", 
            labels[i], bar, values[i]));
    }
    
    return histogram.toString();
}

// Helper method for text truncation
private String truncateText(String text, int maxLength) {
    if (text == null) return "";
    if (text.length() <= maxLength) return text;
    return text.substring(0, maxLength - 3) + "...";
}
     public String generateClinicSummaryReport() {
    StringBuilder report = new StringBuilder();
    report.append("====================================================\n");
    report.append("           MEDICAL CLINIC SUMMARY REPORT            \n");
    report.append("====================================================\n");
    report.append(String.format("Report Generated: %s\n\n", new Date()));

    report.append("----------------------------------------------------\n");
    report.append("                Overall Statistics                  \n");
    report.append("----------------------------------------------------\n");
    report.append(String.format("Total Treatments: %d\n", treatmentHistory.size()));

    // Use temporary arrays for counting and sorting
    MedicalTreatment[] historyArray = new MedicalTreatment[treatmentHistory.size()];
    historyArray = treatmentHistory.toArray(historyArray);

    String[] diagnosisIDs = new String[historyArray.length];
    int[] diagnosisCounts = new int[historyArray.length];
    int uniqueDiagnosisCount = 0;

    for (MedicalTreatment t : historyArray) {
        // Count diagnosis occurrences
        String diagnosisId = t.getDiagnosisID();
        boolean foundDiagnosis = false;
        for (int i = 0; i < uniqueDiagnosisCount; i++) {
            if (diagnosisIDs[i].equals(diagnosisId)) {
                diagnosisCounts[i]++;
                foundDiagnosis = true;
                break;
            }
        }
        if (!foundDiagnosis) {
            diagnosisIDs[uniqueDiagnosisCount] = diagnosisId;
            diagnosisCounts[uniqueDiagnosisCount] = 1;
            uniqueDiagnosisCount++;
        }
    }
    
    // Sort and report Top 5 Most Common Diagnoses
    report.append("\n----------------------------------------------------\n");
    report.append("             Top 5 Most Common Diagnoses            \n");
    report.append("----------------------------------------------------\n");
    
    // Manual Bubble Sort for Diagnoses
    for (int i = 0; i < uniqueDiagnosisCount - 1; i++) {
        for (int j = 0; j < uniqueDiagnosisCount - i - 1; j++) {
            if (diagnosisCounts[j] < diagnosisCounts[j + 1]) {
                int tempCount = diagnosisCounts[j];
                diagnosisCounts[j] = diagnosisCounts[j + 1];
                diagnosisCounts[j + 1] = tempCount;

                String tempId = diagnosisIDs[j];
                diagnosisIDs[j] = diagnosisIDs[j + 1];
                diagnosisIDs[j + 1] = tempId;
            }
        }
    }

    int diagnosisLimit = Math.min(5, uniqueDiagnosisCount);
    for (int i = 0; i < diagnosisLimit; i++) {
        report.append(String.format("  - %s: %d treatments\n", diagnosisIDs[i], diagnosisCounts[i]));
    }
    if (uniqueDiagnosisCount == 0) {
        report.append("  (No data available)\n");
    }

    // DIAGNOSIS HISTOGRAM
    if (diagnosisLimit > 0) {
        report.append("\nDIAGNOSIS DISTRIBUTION:\n");
        report.append("----------------------------------------------------\n");
        int[] topCounts = new int[diagnosisLimit];
        String[] topLabels = new String[diagnosisLimit];
        for (int i = 0; i < diagnosisLimit; i++) {
            topCounts[i] = diagnosisCounts[i];
            topLabels[i] = diagnosisIDs[i];
        }
        report.append(generateHistogram(topCounts, topLabels));
    }

    report.append("\n====================================================\n");
    report.append("                 END OF REPORT                  \n");
    report.append("====================================================\n");

    return report.toString();
}

// Keep your existing histogram generator
private String generateHistogram(int[] values, String[] labels) {
    StringBuilder histogram = new StringBuilder();
    
    if (values.length == 0 || values.length != labels.length) {
        return "No data available for histogram\n";
    }
    
    // Find maximum value for scaling
    int maxValue = 0;
    for (int value : values) {
        if (value > maxValue) maxValue = value;
    }
    
    if (maxValue == 0) {
        return "No data available for histogram\n";
    }
    
    // Scale to fit in 30 characters width
    final int MAX_WIDTH = 30;
    
    for (int i = 0; i < values.length; i++) {
        int barLength = maxValue > 0 ? (int) ((double) values[i] / maxValue * MAX_WIDTH) : 0;
        String bar = new String(new char[barLength]).replace('\0', '*');
        
        // Format label (truncate if too long)
        String label = labels[i];
        if (label.length() > 12) {
            label = label.substring(0, 9) + "...";
        }
        
        histogram.append(String.format("%-12s | %-30s | %3d\n", 
            label, bar, values[i]));
    }
    
    return histogram.toString();
}
    
}