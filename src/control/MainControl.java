/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.MainUI;
import control.*;
import dao.ClinicInitializer;

public class MainControl {

    /**
     * The main entry point for the entire Clinic Management System.
     */
    public static void main(String[] args) {
        System.out.println("System starting up...");

        // 1. Create all control-layer managers
        DoctorManager doctorManager = new DoctorManager();
        PharmacyControl pharmacyControl = new PharmacyControl();
        MedicalTreatmentControl medicalTreatmentControl = new MedicalTreatmentControl(pharmacyControl);
        PatientManager patientManager = new PatientManager();
        ConsultationManager consultationManager = new ConsultationManager();
        // As you add more modules, you would create their control objects here.

        // 2. Initialize all system data using the DAO Initializer
        System.out.println("Initializing sample data...");
        ClinicInitializer.initializeSampleDoctors(doctorManager);
        ClinicInitializer.initializeSamplePharmacyStock(pharmacyControl);
        ClinicInitializer.initializeSampleDiagnoses(medicalTreatmentControl);
        ClinicInitializer.initializeSamplePatients(patientManager);
        ClinicInitializer.initializeSampleConsultations(consultationManager,doctorManager,patientManager);
        ClinicInitializer.initializeSamplePrescriptionHistory(medicalTreatmentControl, pharmacyControl);
        System.out.println("All sample data loaded successfully!");
        
        // 3. Create the main UI, passing the control objects to it
        MainUI mainUI = new MainUI(doctorManager, pharmacyControl, medicalTreatmentControl, patientManager, consultationManager);

        // 4. Launch the main user interface loop
        mainUI.launch();
    }
}