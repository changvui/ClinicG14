/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author user
 */


/**
 * A simple data-holding class (DTO) to transfer all the pieces of the
 * complex Low Stock Report from the Control layer to the Boundary layer.
 */

public class DispensingReportData {
    private final String[][] tableData;
    private final String[][] topDispensedByQtyChartData;
    private final String[][] topDispensedByTypeChartData;
    private final int totalUniqueMeds;
    private final int totalQtyDispensed;
    private final String highestDemandMedication;
    private final String mostPrescribedType;

    public DispensingReportData(String[][] tableData, String[][] topDispensedByQtyChartData, String[][] topDispensedByTypeChartData, int totalUniqueMeds, int totalQtyDispensed, String highestDemandMedication, String mostPrescribedType) {
        this.tableData = tableData;
        this.topDispensedByQtyChartData = topDispensedByQtyChartData;
        this.topDispensedByTypeChartData = topDispensedByTypeChartData;
        this.totalUniqueMeds = totalUniqueMeds;
        this.totalQtyDispensed = totalQtyDispensed;
        this.highestDemandMedication = highestDemandMedication;
        this.mostPrescribedType = mostPrescribedType;
    }

    // --- Getters ---
    public String[][] getTableData() { return tableData; }
    public String[][] getTopDispensedByQtyChartData() { return topDispensedByQtyChartData; }
    public String[][] getTopDispensedByTypeChartData() { return topDispensedByTypeChartData; }
    public int getTotalUniqueMeds() { return totalUniqueMeds; }
    public int getTotalQtyDispensed() { return totalQtyDispensed; }
    public String getHighestDemandMedication() { return highestDemandMedication; }
    public String getMostPrescribedType() { return mostPrescribedType; }
}
