package boundary;

import adt.QueueInterface;
import control.MedicalTreatmentControl;
import entity.MedicalTreatment;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MedicalTreatmentUI {

    private final MedicalTreatmentControl medicalTreatmentControl;
    private final Scanner scanner;

    // Constructor receives the existing scanner from MainUI
    public MedicalTreatmentUI(MedicalTreatmentControl medicalTreatmentControl, Scanner scanner) {
        this.medicalTreatmentControl = medicalTreatmentControl;
        this.scanner = scanner;
    }

    // Main loop
    public void runModule() {
        int choice;
        do {
            System.out.println("\n|-------------------------------------------------|");
            System.out.println("|       MEDICAL TREATMENT & DIAGNOSIS MODULE      |");
            System.out.println("|-------------------------------------------------|");
            System.out.println("| 1. Create New Medical Treatment                 |");
            System.out.println("| 2. View All Treatment History                   |");
            System.out.println("| 3. Update a Treatment Record                    |");
            System.out.println("| 4. Delete a Treatment Record                    |");
            System.out.println("| 5. Search Treatments by Patient ID              |");
            System.out.println("| 6. Generate Reports Menu                        |");
            System.out.println("| 0. Return to Main Menu                          |");
            System.out.println("|-------------------------------------------------|");
            System.out.print("Enter your choice: ");

            try {
                // Use nextLine() for all input to be safe
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> doCreateTreatment();
                    case 2 -> doViewHistory();
                    case 3 -> doUpdateTreatment();
                    case 4 -> doDeleteTreatment();
                    // **NEW CASES ADDED**
                    case 5 -> searchTreatmentByPatientId();
                    case 6 -> generateReportsMenu();
                    case 0 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1; // Set to a non-zero value to continue the loop
            }
        } while (choice != 0);
    }

    // --- CREATE ---
    private void doCreateTreatment() {
        System.out.println("\n--- Create New Medical Treatment ---");

        System.out.print("Enter Patient ID: ");
        String patientID = scanner.nextLine().toUpperCase();
        System.out.print("Enter Doctor ID: ");
        String doctorID = scanner.nextLine().toUpperCase();

        System.out.print("Enter Patient's Sickness Description: ");
        String patientSicknessDesc = scanner.nextLine();

        String sickType = selectSickType();
        if (sickType == null) return;

        String chosenTemplateID = selectDiagnosisTemplate();
        if (chosenTemplateID == null) return;

        System.out.print("Enter Medication ID to Prescribe(e.g., M001): ");
        String medID = scanner.nextLine().toUpperCase();
        System.out.print("Enter Quantity to Dispense: ");
        int quantity = -1;
        while (quantity < 0) {
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) System.out.println("Quantity cannot be negative.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        String result = medicalTreatmentControl.createTreatment(patientID, doctorID, patientSicknessDesc,
                sickType, chosenTemplateID, medID, quantity);
        System.out.println("\n" + result);
    }

    // --- SELECTION HELPERS ---
    private String selectSickType() {
        int typeChoice;
        do {
            System.out.println("\nPlease select the Sickness Type:");
            System.out.println("1. Acute");
            System.out.println("2. Chronic");
            System.out.println("3. Follow-up");
            System.out.println("0. Cancel");
            System.out.print("Enter your choice: ");
            try {
                typeChoice = Integer.parseInt(scanner.nextLine());
                switch (typeChoice) {
                    case 1: return "Acute";
                    case 2: return "Chronic";
                    case 3: return "Follow-up";
                    case 0: System.out.println("Operation cancelled."); return null;
                    default: System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (true);
    }

    private String selectDiagnosisTemplate() {
        String[][] templates = medicalTreatmentControl.getDiagnosisTemplatesForDisplay();
        if (templates.length == 0) {
            System.out.println("Error: No diagnosis templates configured.");
            return null;
        }

        int diagChoice;
        do {
            System.out.println("\nPlease select a formal Diagnosis:");
            System.out.printf("%-5s | %-10s | %s\n", "No.", "ID", "Diagnosis Name");
            System.out.println(new String(new char[50]).replace('\0', '-'));
            for (String[] row : templates) {
                System.out.printf("%-5s | %-10s | %s\n", row[0], row[1], row[2]);
            }
            System.out.println("0. Cancel");
            System.out.print("Enter your choice: ");

            try {
                diagChoice = Integer.parseInt(scanner.nextLine());
                if (diagChoice == 0) {
                    System.out.println("Operation cancelled.");
                    return null;
                }
                if (diagChoice > 0 && diagChoice <= templates.length) {
                    return templates[diagChoice - 1][1];
                } else {
                    System.out.println("Invalid choice. Please select a number from the list.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (true);
    }

    // --- VIEW HISTORY ---
    private void doViewHistory() {
        System.out.println("\n--- Full Medical Treatment History ---");
        String[][] historyData = medicalTreatmentControl.getTreatmentHistoryForDisplay();

        if (historyData.length == 0) {
            System.out.println("No treatment history found.");
            return;
        }

        // Table header
        System.out.printf("%-12s | %-10s | %-10s | %-20s | %-42s | %-12s | %-5s | %-50s\n",
                "DiagnosisID", "PatientID", "DoctorID", "Sickness", "Diagnosis", "Medication", "Qty", "Date");
        System.out.println(new String(new char[170]).replace('\0', '-'));

        // Table rows with wrapped text
        for (String[] row : historyData) {
            String[] wrappedSickness = wrapText(row[3], 20);
            String[] wrappedDiagnosis = wrapText(row[4], 40);
            int maxLines = Math.max(wrappedSickness.length, wrappedDiagnosis.length);

            for (int i = 0; i < maxLines; i++) {
                System.out.printf("%-12s | %-10s | %-10s | %-20s | %-42s | %-12s | %-5s | %-50s\n",
                        i == 0 ? row[0] : "",
                        i == 0 ? row[1] : "",
                        i == 0 ? row[2] : "",
                        i < wrappedSickness.length ? wrappedSickness[i] : "",
                        i < wrappedDiagnosis.length ? wrappedDiagnosis[i] : "",
                        i == 0 ? row[5] : "",
                        i == 0 ? row[6] : "",
                        i == 0 ? row[7] : "");
            }
            System.out.println(new String(new char[170]).replace('\0', '-'));
        }
    }

    // --- UPDATE ---
    private void doUpdateTreatment() {
        System.out.println("\n--- Update a Treatment Record ---");
        doViewHistory();

        System.out.print("\nEnter Diagnosis ID to update (or 0 to cancel): ");
        String id = scanner.nextLine().toUpperCase();
        if (id.equals("0")) return;

        if (!medicalTreatmentControl.treatmentIdExists(id)) {
            System.out.println("Error: Diagnosis ID not found.");
            return;
        }

        System.out.print("Enter NEW Sickness Description: ");
        String newSickness = capitalizeFirstLetter(scanner.nextLine());
        System.out.print("Enter NEW Diagnosis Description: ");
        String newDiagnosis = capitalizeFirstLetter(scanner.nextLine());

        if (medicalTreatmentControl.updateTreatment(id, newSickness, newDiagnosis)) {
            System.out.println("Record updated successfully.");
        } else {
            System.out.println("Error: Could not update the record.");
        }
    }

    // --- DELETE ---
    private void doDeleteTreatment() {
        System.out.println("\n--- Delete a Treatment Record ---");
        doViewHistory();

        System.out.print("\nEnter Diagnosis ID to DELETE (or 0 to cancel): ");
        String id = scanner.nextLine().toUpperCase();
        if (id.equals("0")) return;

        if (!medicalTreatmentControl.treatmentIdExists(id)) {
            System.out.println("Error: Diagnosis ID not found.");
            return;
        }

        System.out.print("Are you sure you want to permanently delete record " + id + "? (Y/N): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            if (medicalTreatmentControl.deleteTreatment(id)) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Error: Could not delete the record.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    // --- HELPER: Capitalize first letter ---
    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    // --- Word-based wrap text helper ---
private String[] wrapText(String text, int width) {
    if (text == null || text.isEmpty()) return new String[]{""};

    text = text.trim();
    String[] words = text.split(" ");
    StringBuilder line = new StringBuilder();
    java.util.List<String> lines = new java.util.ArrayList<>();

    for (String word : words) {
        if (line.length() + word.length() + 1 > width) {
            // Save current line and start a new one
            lines.add(line.toString());
            line = new StringBuilder(word);
        } else {
            if (line.length() > 0) line.append(" ");
            line.append(word);
        }
    }

    if (line.length() > 0) lines.add(line.toString());

    return lines.toArray(new String[0]);
}

// NEW: Reports Menu
    private void generateReportsMenu() {
        int choice;
        do {
            System.out.println("\n|-------------------------------------------------|");
            System.out.println("|              GENERATE REPORTS MENU              |");
            System.out.println("|-------------------------------------------------|");
            System.out.println("| 1. Generate Patient Treatment History Report    |");
            System.out.println("| 2. Generate Medical Clinic Summary Report       |");
            System.out.println("| 0. Return to Treatment Menu                     |");
            System.out.println("|-------------------------------------------------|");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> generatePatientHistoryReport();
                    case 2 -> generateClinicSummaryReport();
                    case 0 -> System.out.println("Returning to previous menu...");
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
            }
        } while (choice != 0);
    }

    private void generatePatientHistoryReport() {
        System.out.println("\n--- Generate Patient History Report ---");
        System.out.print("Enter Patient ID for the report: ");
        String patientID = scanner.nextLine().toUpperCase();
        String report = medicalTreatmentControl.generatePatientHistoryReport(patientID);
        System.out.println(report);
    }

    private void generateClinicSummaryReport() {
        System.out.println("\n--- Generating Clinic Summary Report ---");
        String report = medicalTreatmentControl.generateClinicSummaryReport();
        System.out.println(report);
    }
    
    private void searchTreatmentByPatientId() {
        System.out.print("Enter Patient ID to search: ");
        String patientId = scanner.nextLine().toUpperCase();
        QueueInterface<MedicalTreatment> found = medicalTreatmentControl.searchTreatmentByPatientId(patientId);
        if (found.isEmpty()) {
            System.out.println("No treatments found for Patient ID: " + patientId);
            return;
        }
        int count = found.size();
        MedicalTreatment[] results = new MedicalTreatment[count];
        for (int i = 0; i < count && !found.isEmpty(); i++) {
            results[i] = found.dequeue();
        }
        System.out.println("\n--- Search Results for Patient ID: " + patientId + " ---");
        System.out.printf("%-12s | %-10s | %-10s | %-20s | %-42s | %-12s | %-5s | %-50s\n",
                "TreatmentID", "PatientID", "DoctorID", "Sickness", "Diagnosis", "Medication", "Qty", "Date");
        System.out.println(new String(new char[170]).replace('\0', '-'));
        for (MedicalTreatment t : results) {
            String[] wrappedSickness = wrapText(t.getPatientSicknessDescription(), 20);
            String[] wrappedDiagnosis = wrapText(t.getDiagnosisDescription(), 40);
            int maxLines = Math.max(wrappedSickness.length, wrappedDiagnosis.length);
            for (int i = 0; i < maxLines; i++) {
                System.out.printf("%-12s | %-10s | %-10s | %-20s | %-42s | %-12s | %-5s | %-50s\n",
                        i == 0 ? t.getTreatmentID() : "",
                        i == 0 ? t.getPatientID() : "",
                        i == 0 ? t.getDoctorID() : "",
                        i < wrappedSickness.length ? wrappedSickness[i] : "",
                        i < wrappedDiagnosis.length ? wrappedDiagnosis[i] : "",
                        i == 0 ? t.getMedicationID() : "",
                        i == 0 ? String.valueOf(t.getDispensedQuantity()) : "",
                        i == 0 ? t.getCreatedDate().toString() : "");
            }
            System.out.println(new String(new char[170]).replace('\0', '-'));
        }
    }

}
