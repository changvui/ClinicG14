/*
 * Control Class: PharmacyControl.java
 * Author: Tiong Chang Vui
 * Manages the business logic for the pharmacy module.
 */

package control;

import adt.LinkedQueue;
import adt.QueueInterface;
import entity.*;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;


public class PharmacyControl {

   // Here is my ADT. It's a private variable, hidden from the UI.
    private final QueueInterface<Pharmacy> medicationStock = new LinkedQueue<>();
     // **NEW QUEUES FOR THE PRESCRIPTION WORKFLOW**
    private final QueueInterface<Prescription> pendingPrescriptions = new LinkedQueue<>();
    private final QueueInterface<Prescription> heldPrescriptions = new LinkedQueue<>();
    // **NEW QUEUE to store a history of completed transactions**
    private final QueueInterface<Prescription> approvedPrescriptions = new LinkedQueue<>();


     public PharmacyControl() {
       // This space is intentionally left blank.
    }

      // This method is called by the DAO initializer
    public void addMedication(Pharmacy pharmacy) {
        medicationStock.enqueue(pharmacy);
    }
    
    // This method is called by the UI. It takes raw data.
   public void addNewMedication(String id, String name, String desc, double price, int qty, String type) {
    // 1. It creates the Entity object
    Pharmacy newMed = new Pharmacy(id, name, desc, price, qty, type, new Date());
    
    // 2. It calls another method to handle the ADT interaction
    this.addMedication(newMed);
}

     public String[][] getMedicationStockForDisplay(String sortBy) {
        // 1. Sort the data using the ADT's sort method
         if ("name".equalsIgnoreCase(sortBy)) {
            // Sort by Medication Name
            medicationStock.sort(Comparator.comparing(Pharmacy::getMedicationName));
        } else {
            // **DEFAULT CASE:** For any other input (including "id" or null), sort by ID.
            medicationStock.sort(Comparator.comparing(Pharmacy::getMedicationID));
        }

        // 2. Convert the ADT to an array of Entity objects
        Pharmacy[] medsArray = new Pharmacy[medicationStock.size()];
        medsArray = medicationStock.toArray(medsArray);

        // 3. Create a simple 2D String array to hold the data for the UI
        String[][] displayData = new String[medicationStock.size()][6];

        // 4. Loop through the entity objects and extract the data into the simple array
        for (int i = 0; i < medsArray.length; i++) {
            Pharmacy med = medsArray[i]; // This is the Entity interaction
            displayData[i][0] = med.getMedicationID();
            displayData[i][1] = med.getMedicationName();
            displayData[i][2] = med.getMedicationDescription();
            displayData[i][3] = String.format("RM %.2f", med.getMedicationPrice());
            displayData[i][4] = String.valueOf(med.getMedicationQuantity());
            displayData[i][5] = med.getMedicationType();
        }

        // 5. Return the simple data structure to the UI
        return displayData;
    }
     
     //This method contains the business logic to calculate the next available Medication ID.
      public String getNextMedicationID() {
        // The next ID number will be the current size of the stock + 1.
        // If you have 100 items (M001 to M100), the size is 100, and the next number is 101.
        int nextNumber = medicationStock.size() + 1;
        
        // Format the number as a three-digit string with leading zeros (e.g., 8 -> "008", 101 -> "101").
        String formattedNumber = String.format("%03d", nextNumber);
        
        return "M" + formattedNumber;
    }
   
     /**
     * **NEW, SAFE METHOD FOR THE UI**
     * Checks if a medication with the given ID exists in the system.
     * This method is safe for the UI to call because it only returns a boolean.
     *
     * @param id The Medication ID to check.
     * @return true if the medication exists, false otherwise.
     */
    public boolean medicationIdExists(String id) {
        // It uses the private helper method to do the actual search.
        return findMedicationById(id) != null;
    }
    
    
     
    // Helper method to find a medication by its ID
     //change private to prevent the UI from accessing entities directly
    private Pharmacy findMedicationById(String id) {
        Pharmacy[] meds = new Pharmacy[medicationStock.size()];
        meds = medicationStock.toArray(meds);
        for (Pharmacy med : meds) {
            if (med.getMedicationID().equalsIgnoreCase(id)) {
                return med;
            }
        }
        return null;
    }

    // Corresponds to +editMedication()  
    public boolean editMedicationName(String id, String newName) {
        Pharmacy med = findMedicationById(id);
        if (med != null) {
            med.setMedicationName(newName);
            return true;
        }
        return false;
    }

    public boolean editMedicationDescription(String id, String newDesc) {
        Pharmacy med = findMedicationById(id);
        if (med != null) {
            med.setMedicationDescription(newDesc);
            return true;
        }
        return false;
    }

    public boolean editMedicationPrice(String id, double newPrice) {
        Pharmacy med = findMedicationById(id);
        if (med != null) {
            med.setMedicationPrice(newPrice);
            return true;
        }
        return false;
    }

    public boolean editMedicationQuantity(String id, int newQty) {
        Pharmacy med = findMedicationById(id);
        if (med != null) {
            med.setMedicationQuantity(newQty);
            return true;
        }
        return false;
    }

    public boolean editMedicationType(String id, String newType) {
        Pharmacy med = findMedicationById(id);
        if (med != null) {
            med.setMedicationType(newType);
            return true;
        }
        return false;
    }

    // This method scans the entire medication stock and returns a list of unique medication types.
     public String[] getDistinctMedicationTypes() {
        if (medicationStock.isEmpty()) {
            return new String[0]; // Return an empty array if there's no stock
        }

        // --- Logic to find unique types without using Java Collections ---
        
        // 1. Get all medications
        Pharmacy[] allMeds = new Pharmacy[medicationStock.size()];
        allMeds = medicationStock.toArray(allMeds);

        // 2. Use a temporary array to store the unique types found so far
        String[] tempUniqueTypes = new String[allMeds.length];
        int uniqueCount = 0;

        // 3. Loop through every medication
        for (Pharmacy med : allMeds) {
            String currentType = med.getMedicationType();
            boolean isFound = false;
            // Check if we have already added this type
            for (int i = 0; i < uniqueCount; i++) {
                if (tempUniqueTypes[i].equalsIgnoreCase(currentType)) {
                    isFound = true;
                    break;
                }
            }
            // If it's a new type, add it to our temporary array
            if (!isFound) {
                tempUniqueTypes[uniqueCount] = currentType;
                uniqueCount++;
            }
        }

        // 4. Create a final array of the exact size and copy the unique types into it
        String[] finalUniqueTypes = new String[uniqueCount];
        System.arraycopy(tempUniqueTypes, 0, finalUniqueTypes, 0, uniqueCount);

        return finalUniqueTypes;
    }

    // Corresponds to +deleteMedication()
    public boolean deleteMedication(String id) {
        Pharmacy medToDelete = findMedicationById(id);
        if (medToDelete != null) {
            return medicationStock.hold(medToDelete);
        }
        return false;
    }

    // Corresponds to +dispenseMedication()
    public boolean dispenseMedication(String id, int quantityToDispense) {
        Pharmacy med = findMedicationById(id);
        if (med != null && med.getMedicationQuantity() >= quantityToDispense) {
            med.setMedicationQuantity(med.getMedicationQuantity() - quantityToDispense);
            return true;
        }
        return false;
    }

    
    
   

    // Report 1: +lowStockReport()
    public LowStockReportData generateFullLowStockReport(int threshold) {
        // 1. Get the complete list of low-stock items using the private helper.
        QueueInterface<Pharmacy> lowStockQueue = this.generateLowStockReport(threshold);
        if (lowStockQueue.isEmpty()) {
            return null; // No data for this period
        }

        // 2. Convert to an array to work with.
    lowStockQueue.sort(Comparator.comparing(Pharmacy::getMedicationID));
    Pharmacy[] medsArrayForTable = new Pharmacy[lowStockQueue.size()];
    medsArrayForTable = lowStockQueue.toArray(medsArrayForTable);

        // --- 3. Prepare the Main Data Table (sorted by ID) ---
        String[][] tableData = new String[medsArrayForTable.length][6];
    for (int i = 0; i < medsArrayForTable.length; i++) {
        Pharmacy med = medsArrayForTable[i];
        tableData[i][0] = med.getMedicationID();
        tableData[i][1] = med.getMedicationName();
        tableData[i][2] = med.getMedicationDescription();
        tableData[i][3] = String.format("RM %.2f", med.getMedicationPrice());
        tableData[i][4] = String.valueOf(med.getMedicationQuantity());
        tableData[i][5] = med.getMedicationType();
        }

        // --- 4. Prepare the Chart Data (sorted by Quantity, lowest first) ---
        lowStockQueue.sort(Comparator.comparingInt(Pharmacy::getMedicationQuantity));
        Pharmacy[] medsArrayForChart = new Pharmacy[lowStockQueue.size()];
        medsArrayForChart = lowStockQueue.toArray(medsArrayForChart);
    
        int limit = Math.min(10, medsArrayForChart.length);
        String[][] chartData = new String[limit][2];
        for (int i = 0; i < limit; i++) {
        chartData[i][0] = medsArrayForChart[i].getMedicationName();
        chartData[i][1] = String.valueOf(medsArrayForChart[i].getMedicationQuantity());
    }

         // --- 5. Prepare the Insights (using the quantity-sorted array) ---
        String lowestStockItem = medsArrayForChart[0].getMedicationName() + " (Qty: " + medsArrayForChart[0].getMedicationQuantity() + ")";
        String highestStockItem = medsArrayForChart[medsArrayForChart.length - 1].getMedicationName() + " (Qty: " + medsArrayForChart[medsArrayForChart.length - 1].getMedicationQuantity() + ")";

        // --- 6. Return the complete data package ---
         return new LowStockReportData(tableData, chartData, medsArrayForChart.length, lowestStockItem, highestStockItem);
    }

    /**
     * This private helper remains. Its only job is to perform the filtering.
     */
    private QueueInterface<Pharmacy> generateLowStockReport(int threshold) {
        return medicationStock.filter(med -> med.getMedicationQuantity() < threshold);
    }
    
    
    //Prepares the low stock data specifically for visualization in a bar chart.
    public String[][] getTopNLowStockDataForChart(int threshold, int topN) {
        // 1. Get the complete list of low-stock items.
        QueueInterface<Pharmacy> lowStockQueue = this.generateLowStockReport(threshold);
        if (lowStockQueue.isEmpty()) {
            return new String[0][0]; // Return an empty array if nothing is low
        }

        //2. this sort ADT directly by quantity
        lowStockQueue.sort(Comparator.comparingInt(Pharmacy::getMedicationQuantity));
        
        // 3. Convert to an array so we can sort it.
          Pharmacy[] medsArray = new Pharmacy[lowStockQueue.size()];
          medsArray = lowStockQueue.toArray(medsArray);

       

        // 4. Determine how many items to show (e.g., if only 3 items are low, show 3, not 10).
        int limit = Math.min(topN, medsArray.length);

        // 5. Create the final data array with only the top N items.
        String[][] chartData = new String[limit][2];
        for (int i = 0; i < limit; i++) {
            Pharmacy med = medsArray[i]; // Get the item from the sorted array
            chartData[i][0] = med.getMedicationName();
            chartData[i][1] = String.valueOf(med.getMedicationQuantity());
        }
         return chartData;
    }
    
    

    // Report 2: +medicationTrentReport() -> Renamed to generateTotalStockValueReport
   public DispensingReportData generateFullDispensingReport(int month, int year) {
    // 1. Get a snapshot of all approved prescriptions.
    Prescription[] allApproved = new Prescription[approvedPrescriptions.size()];
    allApproved = approvedPrescriptions.toArray(allApproved);

    // --- Data structures for aggregation ---
    String[][] medAggData = new String[medicationStock.size()][5];
    int medCount = 0;
    String[][] typeAggData = new String[medicationStock.size()][2];
    int typeCount = 0;
    int totalQtyDispensed = 0;

        
    // --- 2. Main Aggregation Loop ---
    for (Prescription p : allApproved) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(p.getApprovalDate());
        
        if (cal.get(Calendar.MONTH) + 1 == month && cal.get(Calendar.YEAR) == year) {
            
            // =================================================================
         
            totalQtyDispensed += p.getQuantity();
            Pharmacy med = findMedicationById(p.getMedicationID());
            if (med == null) continue;

            // Aggregate by Medication
            boolean medFound = false;
            for (int i = 0; i < medCount; i++) {
                if (medAggData[i][0].equals(p.getMedicationID())) {
                    medAggData[i][2] = String.valueOf(Integer.parseInt(medAggData[i][2]) + 1);
                    medAggData[i][3] = String.valueOf(Integer.parseInt(medAggData[i][3]) + p.getQuantity());
                    medFound = true;
                    break;
                }
            }
            if (!medFound) {
                medAggData[medCount][0] = med.getMedicationID();
                medAggData[medCount][1] = med.getMedicationName();
                medAggData[medCount][2] = "1"; // Times dispensed
                medAggData[medCount][3] = String.valueOf(p.getQuantity()); // Total quantity
                medAggData[medCount][4] = String.format("RM %.2f", med.getMedicationPrice());
                medCount++; // CRITICAL: This now gets incremented
            }

            // Aggregate by Type
            boolean typeFound = false;
            for (int i = 0; i < typeCount; i++) {
                if (typeAggData[i][0].equalsIgnoreCase(med.getMedicationType())) {
                    typeAggData[i][1] = String.valueOf(Integer.parseInt(typeAggData[i][1]) + 1);
                    typeFound = true;
                    break;
                }
            }
            if (!typeFound) {
                typeAggData[typeCount][0] = med.getMedicationType();
                typeAggData[typeCount][1] = "1";
                typeCount++;
            }
            // =================================================================
        }
    }
    
    // Now that medCount is > 0, this condition will be false and the report will generate.
    if(medCount == 0) return null;

    // --- The rest of your method is correct ---
    String[][] tableData = new String[medCount][5];
    System.arraycopy(medAggData, 0, tableData, 0, medCount);

    String[][] finalTypeData = new String[typeCount][2];
    System.arraycopy(typeAggData, 0, finalTypeData, 0, typeCount);

    bubbleSortStringArray(tableData, 3, true, true);
    bubbleSortStringArray(finalTypeData, 1, true, true);

    int limitQty = Math.min(5, tableData.length);
    String[][] topDispensedByQtyChartData = new String[limitQty][2];
    for (int i = 0; i < limitQty; i++) {
        topDispensedByQtyChartData[i][0] = tableData[i][1];
        topDispensedByQtyChartData[i][1] = tableData[i][3];
    }

    int limitType = Math.min(5, finalTypeData.length);
    String[][] topDispensedByTypeChartData = new String[limitType][2];
    for (int i = 0; i < limitType; i++) {
        topDispensedByTypeChartData[i][0] = finalTypeData[i][0];
        topDispensedByTypeChartData[i][1] = finalTypeData[i][1];
    }
    
    String highestDemand = tableData[0][1] + " (" + tableData[0][3] + " units)";
    String mostPrescribedType = finalTypeData[0][0] + " (" + finalTypeData[0][1] + " times)";

    return new DispensingReportData(tableData, topDispensedByQtyChartData, topDispensedByTypeChartData, medCount, totalQtyDispensed, highestDemand, mostPrescribedType);
}
   
   
   /*This is my sorting implementation for 2D String arrays.
 *@param array The 2D array to sort.
 * @param colIndex The column index to sort by.
 * @param isNumeric True if the column contains numeric strings.
 * @param descending True for descending order, false for ascending.*/
   private void bubbleSortStringArray(String[][] array, int colIndex, boolean isNumeric, boolean descending) {
    int n = array.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            String val1 = array[j][colIndex];
            String val2 = array[j + 1][colIndex];
            boolean shouldSwap = false;

            try {
                if (isNumeric) {
                    int num1 = Integer.parseInt(val1);
                    int num2 = Integer.parseInt(val2);
                    if (descending ? (num1 < num2) : (num1 > num2)) {
                        shouldSwap = true;
                    }
                } else { // String comparison
                    if (descending ? (val1.compareTo(val2) < 0) : (val1.compareTo(val2) > 0)) {
                        shouldSwap = true;
                    }
                }
            } catch (NumberFormatException e) {
                // Fallback for safety
                if (descending ? (val1.compareTo(val2) < 0) : (val1.compareTo(val2) > 0)) {
                    shouldSwap = true;
                }
            }
            
            if (shouldSwap) {
                String[] temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }
}
   
   // Provides a simple list of all medication IDs for the initializer to use.
   public String[] getAllMedicationIDsForTesting() {
        Pharmacy[] allMeds = new Pharmacy[medicationStock.size()];
        allMeds = medicationStock.toArray(allMeds);
        String[] allIDs = new String[allMeds.length];
        for (int i = 0; i < allMeds.length; i++) {
            allIDs[i] = allMeds[i].getMedicationID();
        }
        return allIDs;
    }
    
    public boolean isStockEmpty() {
        return medicationStock.isEmpty();
    }
    
     // **NEW METHODS FOR PRESCRIPTION MANAGEMENT**
    
    //HELPER FOR INITIALIZATION ONLY Provides access to the pending queue so the initializer can manipulate dates for testing the monthly report.
     public QueueInterface<Prescription> getPendingPrescriptionsForTesting() {
        return pendingPrescriptions;
    }
     
     
     
     
     //This method gathers all prescriptions from both the 'pending' and 'held' queues and combines them into a single list for the UI to display.
    public String[][] getAllQueuedPrescriptionsForDisplay() {
        // 1. Get snapshots of both queues as arrays.
        Prescription[] pendingArray = new Prescription[pendingPrescriptions.size()];
        pendingArray = pendingPrescriptions.toArray(pendingArray);
        
        Prescription[] heldArray = new Prescription[heldPrescriptions.size()];
        heldArray = heldPrescriptions.toArray(heldArray);

        // 2. Create a final data array large enough to hold everything.
        int totalSize = pendingArray.length + heldArray.length;
        // Columns: TreatmentID, PatientID, MedID, Quantity, Status
        String[][] displayData = new String[totalSize][5];
        
        int currentIndex = 0;

        // 3. Loop through the PENDING prescriptions and add them to the list with "Pending" status.
        for (Prescription p : pendingArray) {
            displayData[currentIndex][0] = p.getTreatmentID();
            displayData[currentIndex][1] = p.getPatientID();
            displayData[currentIndex][2] = p.getMedicationID();
            displayData[currentIndex][3] = String.valueOf(p.getQuantity());
            displayData[currentIndex][4] = "Pending";
            currentIndex++;
        }

        // 4. Loop through the HELD prescriptions and add them to the list with "Held" status.
        for (Prescription p : heldArray) {
            displayData[currentIndex][0] = p.getTreatmentID();
            displayData[currentIndex][1] = p.getPatientID();
            displayData[currentIndex][2] = p.getMedicationID();
            displayData[currentIndex][3] = String.valueOf(p.getQuantity());
            displayData[currentIndex][4] = "Held";
            currentIndex++;
        }

        return displayData;
    }

    /**
     * Called by MedicalTreatmentControl to add a new prescription to the approval queue.
     */
    public void requestPrescriptionApproval(Prescription prescription) {
        pendingPrescriptions.enqueue(prescription);
    }

    /**
     * Gets the next pending prescription for the UI to display, without removing it.
     * @return A string array with prescription details, or null if the queue is empty.
     */
    public String[] getNextPrescriptionForApproval() {
        if (pendingPrescriptions.isEmpty()) {
            return null;
        }
        Prescription p = pendingPrescriptions.getFront();
        Pharmacy med = findMedicationById(p.getMedicationID());
        return new String[]{
            p.getTreatmentID(),
            p.getPatientID(),
            p.getMedicationID(),
            med != null ? med.getMedicationName() : "Unknown Medication",
            String.valueOf(p.getQuantity()),
            med != null ? String.valueOf(med.getMedicationQuantity()) : "N/A"
        };
    }

    /**
     * Approves the prescription at the front of the queue, reducing medication stock.
     * @return A status message.
     */
    // **MODIFIED METHOD: It now records the approval.**
     public String approveNextPrescription() {
        if (pendingPrescriptions.isEmpty()) {
            return "No pending prescriptions to approve.";
        }
        Prescription p = pendingPrescriptions.dequeue();
        boolean success = this.dispenseMedication(p.getMedicationID(), p.getQuantity());
        if (success) {
            // **THE FIX:**
            // If the approval date was already set by the initializer, DO NOTHING.
            // Only set the date if it's null (which it will be for a real, live prescription).
            if (p.getApprovalDate() == null) {
                p.setApprovalDate(new Date());
            }
            
            approvedPrescriptions.enqueue(p);
            return "Prescription " + p.getTreatmentID() + " approved. Stock updated.";
        } else {
            heldPrescriptions.enqueue(p);
            return "Error: Insufficient stock for " + p.getMedicationID() + ". Prescription " + p.getTreatmentID() + " has been put on hold.";
        }
    }

    /**
     * Declines the prescription at the front of the queue and moves it to the "held" queue.
     * @return A status message.
     */
    public String declineNextPrescription() {
        if (pendingPrescriptions.isEmpty()) {
            return "No pending prescriptions to decline.";
        }
        Prescription p = pendingPrescriptions.dequeue();
        heldPrescriptions.enqueue(p);
        return "Prescription " + p.getTreatmentID() + " declined and moved to the held list.";
    }
    

    // **NEW METHODS FOR MANAGING HELD PRESCRIPTIONS**

    public String[][] getHeldPrescriptionsForDisplay() {
        Prescription[] heldArray = new Prescription[heldPrescriptions.size()];
        heldArray = heldPrescriptions.toArray(heldArray);
        String[][] displayData = new String[heldArray.length][4];
        for(int i=0; i<heldArray.length; i++){
            displayData[i][0] = heldArray[i].getTreatmentID();
            displayData[i][1] = heldArray[i].getPatientID();
            displayData[i][2] = heldArray[i].getMedicationID();
            displayData[i][3] = String.valueOf(heldArray[i].getQuantity());
        }
        return displayData;
    }

    /**
     * Releases a held prescription by moving it back to the pending approval queue.
     */
    public String releaseHeldPrescription(String treatmentID) {
        Prescription toRelease = findAndRemoveHeld(treatmentID);
        if (toRelease != null) {
            pendingPrescriptions.enqueue(toRelease);
            return "Prescription " + treatmentID + " has been released back to the approval queue.";
        }
        return "Error: Held prescription with ID " + treatmentID + " not found.";
    }

    /**
     * Permanently deletes a held prescription.
     */
    public String deleteHeldPrescription(String treatmentID) {
        Prescription toDelete = findAndRemoveHeld(treatmentID);
        if (toDelete != null) {
            return "Prescription " + treatmentID + " has been permanently deleted.";
        }
        return "Error: Held prescription with ID " + treatmentID + " not found.";
    }
    
    // Private helper to find and remove from the hold queue
    private Prescription findAndRemoveHeld(String treatmentID){
        if(heldPrescriptions.isEmpty()) return null;
        
        QueueInterface<Prescription> tempQueue = new LinkedQueue<>();
        Prescription found = null;

        while(!heldPrescriptions.isEmpty()){
            Prescription current = heldPrescriptions.dequeue();
            if(current.getTreatmentID().equalsIgnoreCase(treatmentID) && found == null){
                found = current;
            } else {
                tempQueue.enqueue(current);
            }
        }
        // Restore the hold queue
        while(!tempQueue.isEmpty()){
            heldPrescriptions.enqueue(tempQueue.dequeue());
        }
        return found;
    }
    
    //This method Prepares the entire history of approved prescriptions for display by the UI
     public String[][] getApprovedHistoryForDisplay() {
        
         // 1. Sort the array by approval date, from newest to oldest.
        // This is a crucial step for a useful history view.
          approvedPrescriptions.sort((a, b) -> b.getApprovalDate().compareTo(a.getApprovalDate()));
         
        // 2. Get a snapshot of the history.
         Prescription[] historyArray = new Prescription[approvedPrescriptions.size()];
         historyArray = approvedPrescriptions.toArray(historyArray);

        // 3. Create the simple 2D String array for the UI.
        // Columns: TreatmentID, PatientID, MedID, MedName, Qty, ApprovalDate
        String[][] displayData = new String[historyArray.length][6];
        for (int i = 0; i < historyArray.length; i++) {
            Prescription p = historyArray[i];
            Pharmacy med = findMedicationById(p.getMedicationID());
            
            displayData[i][0] = p.getTreatmentID();
            displayData[i][1] = p.getPatientID();
            displayData[i][2] = p.getMedicationID();
            displayData[i][3] = (med != null) ? med.getMedicationName() : "Unknown";
            displayData[i][4] = String.valueOf(p.getQuantity());
            displayData[i][5] = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getApprovalDate());
        }

        return displayData;
    }
    
     
    
}