/*
 * Boundary Class: PharmacyUI.java
 * Interacts with the user for all pharmacy-related functions.
 */
package boundary;


import control.*;
import entity.DispensingReportData;
import entity.LowStockReportData;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PharmacyUI {

    // --- ANSI Color Codes for a prettier chart ---
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    
    private final PharmacyControl pharmacyControl;
    private final Scanner scanner;

     public PharmacyUI(PharmacyControl pharmacyControl) {
        this.pharmacyControl = pharmacyControl;
        this.scanner = new Scanner(System.in);
    }

    public void runPharmacyModule() {
        int choice;
        do {
            displayMenu(); // This method now ONLY displays the menu.
            
            System.out.print("Enter your choice: "); // The prompt is here, and only here.
            
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> displayAllMedication();
                    case 2 -> doAddNewMedication();
                    case 3 -> doEditMedication();
                    case 4 -> doDeleteMedication();
                    case 5 -> displayLowStockReport();
                    case 6 -> displayDispensingReport();
                    case 7 -> displayAllQueuedPrescriptions();
                    case 8 -> doReviewPendingPrescriptions();
                    case 9 -> doManageHeldPrescriptions();
                    case 10 -> displayApprovedHistory();
                    case 0 -> System.out.println("\nReturning to Main Menu...");
                    default -> System.out.println("\nInvalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a whole number.");
                scanner.nextLine(); // Clear the bad input
                choice = -1; // Set choice to a non-zero value to continue the loop
            }
        } while (choice != 0);
    }
    
    private void displayMenu() {
        // Define the width of the menu's content area. You can adjust this value.
        final int MENU_WIDTH = 45;
        
        // Create the border and separator lines based on the defined width
        String border = "+" + "-".repeat(MENU_WIDTH + 2) + "+";
        String separator = "|" + "-".repeat(MENU_WIDTH + 2) + "|";

        // Create a reusable format string for all menu items to ensure perfect alignment
        // The format is: "|  1. Text goes here                |"
        String itemFormat = "|  %-2d. %-" + (MENU_WIDTH - 5) + "s |";
        
        // A format for the section headers
        String headerFormat = "| --- %-" + (MENU_WIDTH - 4) + "s |";

        System.out.println(); // Add a blank line for spacing
        System.out.println(border);
        // Use the helper method to print a centered title
        System.out.println(centerTextForDisplayMenu("Pharmacy Management Module", MENU_WIDTH));
        System.out.println(separator);
        
        // Medication Stock section
        System.out.printf(headerFormat, "Medication Stock");
        System.out.println();
        System.out.printf(itemFormat, 1, "List All Medication Stock");
        System.out.println();
        System.out.printf(itemFormat, 2, "Add New Medication");
        System.out.println();
        System.out.printf(itemFormat, 3, "Edit Medication Details");
        System.out.println();
        System.out.printf(itemFormat, 4, "Delete Medication");
        System.out.println();
        System.out.println(separator);

        // Reports section
        System.out.printf(headerFormat, "Reports");
        System.out.println();
        System.out.printf(itemFormat, 5, "Generate Low Stock Report");
        System.out.println();
        System.out.printf(itemFormat, 6, "Monthly Dispensing Summary");
        System.out.println();
        System.out.println(separator);

        // Prescription Workflow section
        System.out.printf(headerFormat, "Prescription Workflow");
        System.out.println();
        System.out.printf(itemFormat, 7, "List All Queued Prescriptions");
        System.out.println();
        System.out.printf(itemFormat, 8, "Review Pending Prescriptions");
        System.out.println();
        System.out.printf(itemFormat, 9, "Manage Held Prescriptions");
        System.out.println();
        System.out.printf(itemFormat, 10, "View Approved Prescription History");
        System.out.println();
        System.out.println(separator);

        // Exit Option
        System.out.printf(itemFormat, 0, "Return to Main Menu");
        System.out.println();
        System.out.println(border);
    }
    
     private String centerTextForDisplayMenu(String text, int width) {
        if (text.length() >= width) {
            return "| " + text.substring(0, width) + " |";
        }
        int padding = width - text.length();
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        return "| " + " ".repeat(leftPadding) + text + " ".repeat(rightPadding) + " |";
    }

    private void displayAllMedication() {
        System.out.println("\n--- Full Medication Stock List ---");

        System.out.println("How would you like to sort the list?");
        System.out.println("  1. By Medication ID (Default)");
        System.out.println("  2. By Medication Name");
        System.out.print("Enter your choice (1-2): ");
        
        // Set the default sortBy variable to "id".
        String sortBy = "id"; 
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Only change the sortBy variable if the user explicitly selects option 2.
            if (choice == 2) {
                sortBy = "name";
            }
        } catch (Exception e) {
            // If user enters non-numeric input, just proceed with the default "id" sort.
            scanner.nextLine(); // Clear the invalid input
        }
        
        // Step 1: Call the control layer to get a simple, displayable 2D array.
        // The UI has no idea how this data was retrieved or processed.
        String[][] medsData = pharmacyControl.getMedicationStockForDisplay(sortBy);

        
    
        
        // Step 2: Check if the returned data is empty.
        if (medsData.length == 0) {
            System.out.println("No medication in stock.");
            return;
        }
        
         // Step 3: Display the sorted data.
        System.out.println("\nDisplaying list sorted by: " + capitalizeFirstLetter(sortBy));
        printMedicationTable(medsData);
    }

       
   
    
       private void doAddNewMedication() {
        System.out.println("\n--- Add New Medication ---");

        // Step 1: Get the next available ID from the control layer BEFORE asking for input.
        String newId = pharmacyControl.getNextMedicationID();

        // Step 2: Inform the user what the new ID will be.
        System.out.println("A new Medication ID will be automatically assigned: " + newId);

        // Step 3: Gather all OTHER details from the user.
        System.out.print("Enter Medication Name: ");
        String name = capitalizeFirstLetter(scanner.nextLine());

        System.out.print("Enter Description: ");
        String desc = capitalizeFirstLetter(scanner.nextLine());

        System.out.print("Enter Price: ");
        double price = -1;
        while(price < 0) {
            try {
                price = scanner.nextDouble();
                if(price < 0) System.out.println("Price cannot be negative.");
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear bad input
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }
        
        System.out.print("Enter Quantity: ");
        int qty = -1;
        while(qty < 0) {
            try {
                qty = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if(qty < 0) System.out.println("Quantity cannot be negative.");
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear bad input
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
        
         String type = selectMedicationType();
        if (type == null) {
            System.out.println("Add new medication cancelled.");
            return; // Exit if the user cancels the selection
        }
        
        // Step 4: Call the control layer, passing the auto-generated ID along with the user's input.
        pharmacyControl.addNewMedication(newId, name, desc, price, qty, type);
        
        System.out.println("\nMedication '" + newId + " - " + name + "' was added successfully!");
    }
       
       /**
     * **NEW HELPER METHOD**
     * Displays a dynamic menu of medication types and returns the user's selection.
     * @return The selected medication type as a String, or null if the user cancels.
     */
    private String selectMedicationType() {
        // 1. Get the list of unique types from the control layer.
        String[] types = pharmacyControl.getDistinctMedicationTypes();

        // Fallback for an empty system, though unlikely with 100 sample items.
        if (types.length == 0) {
            System.out.println("No pre-existing types found.");
            System.out.print("Please enter the type manually: ");
            return capitalizeFirstLetter(scanner.nextLine());
        }

        int choice = -1;
        do {
            System.out.println("\nPlease select a Medication Type:");
            // 2. Display the options in a numbered list.
            for (int i = 0; i < types.length; i++) {
                System.out.printf("  %d. %s\n", (i + 1), types[i]);
            }
            System.out.println("  0. Cancel");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 0) {
                    return null; // User chose to cancel
                }
                if (choice > 0 && choice <= types.length) {
                    // 3. Return the selected type string from the array.
                    return types[choice - 1];
                } else {
                    System.out.println("Invalid choice. Please select a number from the list.");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear bad input
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (true); // Loop until a valid choice is made or cancelled
    }
    
     
     //This now uses the safe query method to validate the ID first.
      private void doEditMedication() {
        System.out.println("\n--- Edit Medication ---");

        //  First, check if there is anything to edit at all.
        if (pharmacyControl.isStockEmpty()) {
            System.out.println("There is no medication in stock to edit.");
            return;
        }

        //  Show the user the list of medications so they can see the IDs.
        // We can simply reuse the method we already built.
        displayAllMedication();

        // Now that the user has seen the list, prompt them for an ID.
        System.out.print("\nEnter the ID of the medication you wish to edit from the list above (or type '0' to cancel): ");
        String id = scanner.nextLine().toUpperCase();

        // Provide an easy way for the user to back out.
        if (id.equals("0")) {
            System.out.println("Edit operation cancelled.");
            return;
        }

        // Validate the ID they entered.
        if (!pharmacyControl.medicationIdExists(id)) {
            System.out.println("\nError: The ID '" + id + "' was not found in the list.");
            return;
        }
        
           // --- Start of the new Edit Sub-Menu Loop ---
        int editChoice;
        do {
            // Display the sub-menu
            System.out.println("\nWhat would you like to edit for Medication '" + id + "'?");
            System.out.println("  1. Name");
            System.out.println("  2. Description");
            System.out.println("  3. Price");
            System.out.println("  4. Quantity");
            System.out.println("  5. Type");
            System.out.println("  0. Finish Editing and Save");
            System.out.print("Enter your choice: ");

            try {
                editChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (editChoice) {
                    case 1:
                        System.out.print("Enter NEW Name: ");
                        String newName = capitalizeFirstLetter(scanner.nextLine());
                        pharmacyControl.editMedicationName(id, newName);
                        System.out.println("Name updated.");
                        break;
                    case 2:
                        System.out.print("Enter NEW Description: ");
                        String newDesc = capitalizeFirstLetter(scanner.nextLine());
                        pharmacyControl.editMedicationDescription(id, newDesc);
                        System.out.println("Description updated.");
                        break;
                    case 3:
                        System.out.print("Enter NEW Price: ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        pharmacyControl.editMedicationPrice(id, newPrice);
                        System.out.println("Price updated.");
                        break;
                    case 4:
                        System.out.print("Enter NEW Quantity: ");
                        int newQty = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        pharmacyControl.editMedicationQuantity(id, newQty);
                        System.out.println("Quantity updated.");
                        break;
                    case 5:
                        String newType = selectMedicationType();
                        if (newType != null) { // Check for cancellation
                            pharmacyControl.editMedicationType(id, newType);
                            System.out.println("Type updated.");
                        } else {
                            System.out.println("Update for Type cancelled.");
                        }
                        break;
                    case 0:
                        System.out.println("\nAll changes for '" + id + "' have been saved.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select from the menu.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the bad input
                editChoice = -1; // Set choice to a non-zero value to continue the loop
            }

        } while (editChoice != 0);
    }
        
        
    
    
   

    private void doDeleteMedication() {
        System.out.println("\n--- Delete Medication ---");
        System.out.print("Enter Medication ID to delete: ");
        String id = scanner.nextLine().toUpperCase();

        if (pharmacyControl.deleteMedication(id)) {
            System.out.println("Medication deleted successfully.");
        } else {
            System.out.println("Error: Deletion failed. Check Medication ID.");
        }
    }

     private void displayLowStockReport() {
        System.out.println("\n--- Low Stock Report ---");
        System.out.print("Enter stock threshold (e.g., 50): ");
        int threshold = 50;
        try {
            threshold = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input. Using default threshold of 50.");
        }

        // 1. Get the complete, processed data package from the control layer.
        LowStockReportData reportData = pharmacyControl.generateFullLowStockReport(threshold);
        if (reportData == null) {
            System.out.println("\nNo medications are below the threshold of " + threshold + ".");
            return;
        }

        // --- 2. Print the Formatted Report Header ---
        System.out.println("\n========================================================================================");
        System.out.println("                      TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.println("                                   PHARMACY MODULE SUBSYSTEM");
        System.out.println("\nGenerated at: " + new java.util.Date());
        System.out.println("\n----------------------------------------------------------------------------------------");
        System.out.println("                       SUMMARY OF LOW STOCK REPORT (Threshold: " + threshold + ")");
        System.out.println("****************************************************************************************");
        
        // --- 3. Print the Main Data Table ---
        System.out.println("\nMedication Details (All items with stock < " + threshold + "):");
        printMedicationTable(reportData.getTableData()); // Reuse the existing table printer

        // --- 4. Print the Summary Statistics ---
        System.out.println("\nTotal Number of Low Stock Items: " + reportData.getTotalItemsLow());

        // --- 5. Print the Graphical Representation ---
        System.out.println("\n----------------------------------------------------------------------------------------");
        System.out.println("                             GRAPHICAL REPRESENTATION OF LOWEST STOCK");
        System.out.println("----------------------------------------------------------------------------------------");
        
        drawBarChart("Top " + reportData.getChartData().length + " Medications with Lowest Stock", reportData.getChartData(), "Medications", threshold);
        
        // --- 6. Print the Insights ---
        System.out.println("\n----------------------------------------------------------------------------------------");
        System.out.println("                                  INSIGHTS AND SUMMARY");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Most Critical Item (Lowest Stock): \n< " + reportData.getLowestStockItem() + " >");
        System.out.println("\nHighest Stock Item in this Report: \n< " + reportData.getHighestStockItemInReport() + " >");
        
        // --- 7. Print the Footer ---
        System.out.println("\n****************************************************************************************");
        System.out.println("                                     END OF THE REPORT");
        System.out.println("========================================================================================");
    }
      
     
    
   
    
    
     
     /**
     * This helper method is used by both displayAllMedication and displayLowStockReport.
     */
     private void printMedicationTable(String[][] tableData) {
        // --- Define the column widths ---
        // These can be adjusted to fit your data.
        int idWidth = 5;
        int nameWidth = 25;
        int descWidth = 35;
        int priceWidth = 10;
        int qtyWidth = 5;
        int typeWidth = 15;

        // --- Create the format string for both the header and the data rows ---
        // The `%-Ns` format means: Left-align (-) a string (s) in a space of N characters.
        String formatString = "| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + descWidth + "s | %-" + priceWidth + "s | %-" + qtyWidth + "s | %-" + typeWidth + "s |";
        
        // --- Create a separator line of the correct total length ---
        // The total length is the sum of widths + spaces and bars.
        int totalWidth = idWidth + nameWidth + descWidth + priceWidth + qtyWidth + typeWidth + 19; // 19 is for bars and spaces
        String separator = "-".repeat(totalWidth);

        // --- Print the Header ---
        System.out.println(separator);
        System.out.printf(formatString, "ID", "Name", "Description", "Price", "Qty", "Type");
        System.out.println();
        System.out.println(separator);

        // --- Print each row of data using the same format string ---
        for (String[] medRow : tableData) {
            System.out.printf(formatString, medRow[0], medRow[1], medRow[2], medRow[3], medRow[4], medRow[5]);
            System.out.println();
        }

        // --- Print the Footer ---
        System.out.println(separator);
    }
     
//Monthly Dispensing Summary
    private void displayDispensingReport() {
        System.out.println("\n--- Monthly Medication Dispensing Summary ---");
        System.out.print("Enter month to generate report for (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter year (e.g., 2025): ");
        int year = scanner.nextInt();
        scanner.nextLine();

        if (month < 1 || month > 12) {
            System.out.println("Invalid month.");
            return;
        }

        // 1. Get the complete, processed data package from the control layer.
        DispensingReportData reportData = pharmacyControl.generateFullDispensingReport(month, year);
        if (reportData == null) {
            System.out.println("\nNo dispensing records found for the selected period.");
            return;
        }

        // --- 2. Print the Formatted Report Header ---
        System.out.println("\n========================================================================================");
        System.out.println("                      TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.println("                                   PHARMACY MODULE SUBSYSTEM");
        System.out.println("\nGenerated at: " + new java.util.Date());
        System.out.println("\n----------------------------------------------------------------------------------------");
        System.out.println("                       SUMMARY OF DISPENSING REPORT (" + month + "/" + year + ")");
        System.out.println("****************************************************************************************");

        // --- 3. Print the Main Data Table ---
        System.out.println("\nMedication Details:");
        System.out.printf("%-12s | %-25s | %-12s | %-15s | %s\n", "Med ID", "Name", "Unit Price", "Times Dispensed", "Total Qty Dispensed");
        System.out.println("-".repeat(95));
        // Use the getter to retrieve the table data
        for (String[] row : reportData.getTableData()) {
            System.out.printf("| %-10s | %-25s | %-12s | %-15s | %s\n", row[0], row[1], row[4], row[2], row[3]);
        }
        System.out.println("-".repeat(95));
        
        // --- 4. Print the Summary Statistics ---
        System.out.println("\nTotal Unique Medications Dispensed: " + reportData.getTotalUniqueMeds());
        System.out.println("Total Quantity of All Items Dispensed: " + reportData.getTotalQtyDispensed());

        // --- 5. Print the Graphical Representation ---
        System.out.println("\n----------------------------------------------------------------------------------------");
        System.out.println("                             GRAPHICAL REPRESENTATION OF DISPENSING");
        System.out.println("----------------------------------------------------------------------------------------");
        
        // Use the getter to retrieve the data for the first chart
        drawBarChart("Top 5 Most Dispensed (by Quantity)", reportData.getTopDispensedByQtyChartData(), "Medications", 0);
        
        // Use the getter to retrieve the data for the second chart
        drawBarChart("Top 5 Most Frequent Medication Types", reportData.getTopDispensedByTypeChartData(), "Types", 0);
        
        // --- 6. Print the Insights ---
        System.out.println("\n----------------------------------------------------------------------------------------");
        System.out.println("                                  INSIGHTS AND SUMMARY");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Medication with the HIGHEST demand: \n< " + reportData.getHighestDemandMedication() + " >");
        System.out.println("\nMost prescribed Medication Type: \n< " + reportData.getMostPrescribedType() + " >");
        
        // --- 7. Print the Footer ---
        System.out.println("\n****************************************************************************************");
        System.out.println("                                     END OF THE REPORT");
        System.out.println("========================================================================================");
    }
    
    // The drawBarChart method needs the dynamic Y-axis calculation
     private void drawBarChart(String title, String[][] data, String xAxisLabel, int yAxisHeight) {
        if (data.length == 0) return;

        // --- Configuration ---
        final String BAR_CHAR = "*";
        
        System.out.println("\n" + ANSI_YELLOW + title + ANSI_RESET);

        // --- Step 1: Determine Y-axis height if it's not provided ---
        if (yAxisHeight <= 0) {
            int maxValue = 0;
            for (String[] row : data) {
                try {
                    int value = Integer.parseInt(row[1]);
                    if (value > maxValue) maxValue = value;
                } catch (NumberFormatException e) {}
            }
            yAxisHeight = (int) (Math.ceil(maxValue / 5.0) * 5);
            if (yAxisHeight == 0) yAxisHeight = 5;
        }

        // --- Step 2: Determine dynamic column width ---
        int columnWidth = String.valueOf(data.length).length() + 2;
        columnWidth = Math.max(7, columnWidth); // Min width for aesthetics

        // --- Step 3: Draw the chart body ---
        System.out.println("   ^");
        for (int y = yAxisHeight; y >= 1; y--) {
             if (yAxisHeight > 20 && y % 2 != 0 && y != 1) continue;
            System.out.printf("%3d |", y);
            for (String[] row : data) {
                int value = Integer.parseInt(row[1]);
                String barSegment = (value >= y) ? BAR_CHAR.repeat(columnWidth - 2) : "";
                System.out.print(" " + ANSI_GREEN + centerText(barSegment, columnWidth - 2) + ANSI_RESET + " ");
            }
            System.out.println();
        }

        // --- Step 4: Draw the X-axis line ---
        System.out.print("---+" + "-".repeat(columnWidth * data.length));
        System.out.println("-> " + xAxisLabel);

        // --- Step 5: Draw the numbered legend labels ---
        System.out.print("    ");
        for (int i = 0; i < data.length; i++) {
            System.out.print(centerText(String.valueOf(i + 1), columnWidth));
        }
        System.out.println("\n");

        // --- Step 6: Print the legend ---
        System.out.println("Legend:");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("  %d. %s (Value: %s)\n", (i + 1), data[i][0], data[i][1]);
        }
    }
     
     private String centerText(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = width - text.length();
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }
    
    
    
    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str; // Return as-is if null or empty
        }
        // Return the first character as uppercase + the rest of the string as lowercase
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    
    //UI Display consolidated list of all prescriptions they are pending or on hold, along with their current status.
    private void displayAllQueuedPrescriptions() {
        System.out.println("\n--- All Queued Prescriptions (Pending & Held) ---");
        
        // 1. Get the combined data from the control layer.
        String[][] queuedData = pharmacyControl.getAllQueuedPrescriptionsForDisplay();

        if (queuedData.length == 0) {
            System.out.println("There are currently no prescriptions in the queue.");
            return;
        }

        // 2. Print a formatted table.
        String formatString = "| %-12s | %-12s | %-15s | %-8s | %-10s |";
        int totalWidth = 12 + 12 + 15 + 8 + 10 + 16;
        String separator = "-".repeat(totalWidth);
        
        System.out.println(separator);
        System.out.printf(formatString, "Treatment ID", "Patient ID", "Medication ID", "Quantity", "Status");
        System.out.println();
        System.out.println(separator);
        
        for (String[] row : queuedData) {
            System.out.printf(formatString, row[0], row[1], row[2], row[3], row[4]);
            System.out.println();
        }
        System.out.println(separator);
    }
    
    
    //  UI METHOD FOR APPROVALS**
    private void doReviewPendingPrescriptions() {
        System.out.println("\n--- Review Pending Prescriptions ---");
        while(true) {
            String[] p = pharmacyControl.getNextPrescriptionForApproval();
            if (p == null) {
                System.out.println("There are no more pending prescriptions to review.");
                break;
            }

            System.out.println("\nNext Prescription for Approval:");
            System.out.println("  Treatment ID: " + p[0]);
            System.out.println("  Patient ID:   " + p[1]);
            System.out.println("  Medication:   " + p[2] + " (" + p[3] + ")");
            System.out.println("  Qty Required: " + p[4]);
            System.out.println("  Stock on Hand:" + p[5]);
            System.out.print("\nChoose an action: (1-Approve, 2-Decline, 0-Stop Reviewing): ");
             int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear bad input
                System.out.println("Invalid input. Please enter a number.");
                continue; // Skip to the next loop iteration
            }

            String result = "";
            if (choice == 1) {
                result = pharmacyControl.approveNextPrescription();
            } else if (choice == 2) {
                result = pharmacyControl.declineNextPrescription();
            } else {
                System.out.println("Stopping review process.");
                break;
            }
            System.out.println("Result: " + result);
        }
    }

    // **NEW UI METHOD FOR MANAGING HELD ITEMS**
    private void doManageHeldPrescriptions() {
        System.out.println("\n--- Manage Held Prescriptions ---");
        String[][] heldData = pharmacyControl.getHeldPrescriptionsForDisplay();

        if (heldData.length == 0) {
            System.out.println("There are no held prescriptions.");
            return;
        }

        System.out.printf("%-12s | %-12s | %-15s | %s\n", "Treatment ID", "Patient ID", "Medication ID", "Qty");
        System.out.println(new String(new char[55]).replace('\0', '-'));
        for(String[] row : heldData){
            System.out.printf("%-12s | %-12s | %-15s | %s\n", row[0], row[1], row[2], row[3]);
        }
        
        System.out.print("\nEnter Treatment ID to action (or 0 to cancel): ");
        String id = scanner.nextLine().toUpperCase();
        if(id.equals("0")) return;

        System.out.print("Choose action (1-Release to queue, 2-Delete permanently): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String result = "";
        if (choice == 1) {
            result = pharmacyControl.releaseHeldPrescription(id);
        } else if (choice == 2) {
            result = pharmacyControl.deleteHeldPrescription(id);
        } else {
            result = "Invalid action choice.";
        }
        System.out.println("Result: " + result);
    }
    
    //Displays a clean, chronological log of all approved prescriptions.
    private void displayApprovedHistory() {
        System.out.println("\n--- Approved Prescription History (Most Recent First) ---");
        
        // 1. Get the processed data from the control layer.
        String[][] historyData = pharmacyControl.getApprovedHistoryForDisplay();

        if (historyData.length == 0) {
            System.out.println("There is no approved prescription history.");
            return;
        }

        // 2. Print a formatted table.
        String formatString = "| %-12s | %-10s | %-10s | %-25s | %-8s | %-20s |";
        int totalWidth = 12 + 10 + 10 + 25 + 8 + 20 + 19;
        String separator = "-".repeat(totalWidth);
        
        System.out.println(separator);
        System.out.printf(formatString, "Treatment ID", "Patient ID", "Med ID", "Medication Name", "Quantity", "Approval Time");
        System.out.println();
        System.out.println(separator);
        
        for (String[] row : historyData) {
            System.out.printf(formatString, row[0], row[1], row[2], row[3], row[4], row[5]);
            System.out.println();
        }
        System.out.println(separator);
    }
    
}