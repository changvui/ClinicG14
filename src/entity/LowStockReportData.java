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
public class LowStockReportData {
    private final String[][] tableData;
    private final String[][] chartData;
    private final int totalItemsLow;
    private final String lowestStockItem;
    private final String highestStockItemInReport;

    public LowStockReportData(String[][] tableData, String[][] chartData, int totalItemsLow, String lowestStockItem, String highestStockItemInReport) {
        this.tableData = tableData;
        this.chartData = chartData;
        this.totalItemsLow = totalItemsLow;
        this.lowestStockItem = lowestStockItem;
        this.highestStockItemInReport = highestStockItemInReport;
    }

    // --- Getters ---
    public String[][] getTableData() { return tableData; }
    public String[][] getChartData() { return chartData; }
    public int getTotalItemsLow() { return totalItemsLow; }
    public String getLowestStockItem() { return lowestStockItem; }
    public String getHighestStockItemInReport() { return highestStockItemInReport; }
}