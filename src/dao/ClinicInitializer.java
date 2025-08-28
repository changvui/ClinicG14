package dao;

import adt.*;
import control.*;
import entity.*;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author user
 */
public class ClinicInitializer {
    
    
     public static void initializeSampleDoctors(DoctorManager manager) {
        try {
            // Cardiology  
            addSampleDoctor(manager, "D101", "Dr. Ahmad", "Cardiology", "+60123456789", 12, 150.00, new String[]{"2023-12-25", "2024-01-01"}, "9am-5pm");
            addSampleDoctor(manager, "D102", "Dr. Siti", "Cardiology", "+60198765432", 8, 120.00, new String[]{"2023-12-31"}, "10am-6pm");
            addSampleDoctor(manager, "D103", "Dr. Lim", "Cardiology", "+60111223344", 15, 180.00, new String[]{}, "8am-4pm");
            addSampleDoctor(manager, "D104", "Dr. Fatimah", "Cardiology", "+60122334455", 10, 160.00, new String[]{"2024-02-14"}, "11am-7pm");

            // Pediatrics  
            addSampleDoctor(manager, "D201", "Dr. Mei Ling", "Pediatrics", "+60345678901", 15, 100.00, new String[]{}, "8am-4pm");
            addSampleDoctor(manager, "D202", "Dr. Kumar", "Pediatrics", "+60456789012", 5, 80.00, new String[]{"2024-02-01"}, "2pm-10pm");
            addSampleDoctor(manager, "D203", "Dr. Ali", "Pediatrics", "+60333444555", 7, 90.00, new String[]{"2024-01-15"}, "9am-5pm");
            addSampleDoctor(manager, "D204", "Dr. Aina", "Pediatrics", "+60444555666", 12, 110.00, new String[]{}, "10am-6pm");

            // Orthopedics  
            addSampleDoctor(manager, "D301", "Dr. Rajesh", "Orthopedics", "+60567890123", 20, 200.00, new String[]{"2023-12-20", "2023-12-21"}, "9am-5pm");
            addSampleDoctor(manager, "D302", "Dr. Fatimah", "Orthopedics", "+60678901234", 7, 130.00, new String[]{}, "11am-7pm");
            addSampleDoctor(manager, "D303", "Dr. Wong", "Orthopedics", "+60555666777", 18, 220.00, new String[]{"2024-01-10"}, "8am-4pm");
            addSampleDoctor(manager, "D304", "Dr. Sofia", "Orthopedics", "+60666777888", 9, 170.00, new String[]{}, "2pm-10pm");

            // Neurology  
            addSampleDoctor(manager, "D401", "Dr. Lim", "Neurology", "+60789012345", 18, 250.00, new String[]{"2024-01-15"}, "8am-4pm");
            addSampleDoctor(manager, "D402", "Dr. Ali", "Neurology", "+60890123456", 10, 180.00, new String[]{}, "10am-6pm");
            addSampleDoctor(manager, "D403", "Dr. Chen", "Neurology", "+60777888999", 22, 280.00, new String[]{"2024-02-05"}, "9am-5pm");
            addSampleDoctor(manager, "D404", "Dr. Priya", "Neurology", "+60888999000", 14, 210.00, new String[]{}, "11am-7pm");

            // Oncology 
            addSampleDoctor(manager, "D501", "Dr. Chen", "Oncology", "+60901234567", 22, 300.00, new String[]{"2023-12-28"}, "9am-5pm");
            addSampleDoctor(manager, "D502", "Dr. Devi", "Oncology", "+60111234567", 6, 160.00, new String[]{}, "2pm-10pm");
            addSampleDoctor(manager, "D503", "Dr. Ahmad", "Oncology", "+60999887766", 25, 320.00, new String[]{"2024-01-20"}, "8am-4pm");
            addSampleDoctor(manager, "D504", "Dr. Anis", "Oncology", "+60188776655", 8, 190.00, new String[]{}, "10am-6pm");

            // General Surgery 
            addSampleDoctor(manager, "D601", "Dr. Wong", "General Surgery", "+60122345678", 14, 170.00, new String[]{"2024-01-10"}, "8am-4pm");
            addSampleDoctor(manager, "D602", "Dr. Anwar", "General Surgery", "+60133456789", 9, 140.00, new String[]{}, "10am-6pm");
            addSampleDoctor(manager, "D603", "Dr. Kamal", "General Surgery", "+60144556677", 19, 200.00, new String[]{"2024-02-15"}, "9am-5pm");
            addSampleDoctor(manager, "D604", "Dr. Nina", "General Surgery", "+60155667788", 11, 160.00, new String[]{}, "11am-7pm");

            // Dermatology 
            addSampleDoctor(manager, "D701", "Dr. Tan", "Dermatology", "+60144567890", 11, 110.00, new String[]{"2023-12-15"}, "9am-5pm");
            addSampleDoctor(manager, "D702", "Dr. Kamal", "Dermatology", "+60155678901", 4, 90.00, new String[]{}, "11am-7pm");
            addSampleDoctor(manager, "D703", "Dr. Lee", "Dermatology", "+60166778899", 16, 130.00, new String[]{"2024-01-25"}, "8am-4pm");
            addSampleDoctor(manager, "D704", "Dr. Sara", "Dermatology", "+60177889900", 7, 100.00, new String[]{}, "10am-6pm");

            // Ophthalmology 
            addSampleDoctor(manager, "D801", "Dr. Lee", "Ophthalmology", "+60166789012", 17, 190.00, new String[]{"2024-01-05"}, "8am-4pm");
            addSampleDoctor(manager, "D802", "Dr. Saras", "Ophthalmology", "+60177890123", 8, 125.00, new String[]{}, "10am-6pm");
            addSampleDoctor(manager, "D803", "Dr. Raj", "Ophthalmology", "+60188992233", 20, 210.00, new String[]{"2024-02-20"}, "9am-5pm");
            addSampleDoctor(manager, "D804", "Dr. Maya", "Ophthalmology", "+60199003344", 12, 170.00, new String[]{}, "11am-7pm");

            // Emergency Medicine 
            addSampleDoctor(manager, "D901", "Dr. John", "Emergency Medicine", "+60188901234", 13, 160.00, new String[]{}, "24-hour shifts");
            addSampleDoctor(manager, "D902", "Dr. Aisha", "Emergency Medicine", "+60199012345", 5, 110.00, new String[]{"2023-12-24"}, "24-hour shifts");
            addSampleDoctor(manager, "D903", "Dr. Sam", "Emergency Medicine", "+60100112233", 18, 190.00, new String[]{"2024-01-31"}, "24-hour shifts");
            addSampleDoctor(manager, "D904", "Dr. Lina", "Emergency Medicine", "+60111223344", 9, 140.00, new String[]{}, "24-hour shifts");

            // Psychiatry 
            addSampleDoctor(manager, "DC01", "Dr. Richard", "Psychiatry", "+60109123456", 19, 220.00, new String[]{"2024-01-20"}, "9am-5pm");
            addSampleDoctor(manager, "DC02", "Dr. Nor", "Psychiatry", "+60119234567", 7, 150.00, new String[]{}, "2pm-10pm");
            addSampleDoctor(manager, "DC03", "Dr. David", "Psychiatry", "+60109334455", 22, 240.00, new String[]{"2024-02-10"}, "8am-4pm");
            addSampleDoctor(manager, "DC04", "Dr. Zara", "Psychiatry", "+60119445566", 10, 180.00, new String[]{}, "10am-6pm");    
            
            
        } catch (Exception e) {
            System.out.println("Error loading sample data: " + e.getMessage());
        }
    }

  
     
    private static void addSampleDoctor(DoctorManager manager, String id, String name, 
            String specialization, String contact, int experience, double fee, 
            String[] leaveDates, String hours) {
        
        Doctor doctor = new Doctor(id, name, specialization);
        doctor.setContactNumber(contact);
        doctor.setYearsOfExperience(experience);
        doctor.setConsultationFee(fee);
        doctor.setLeaveDates(leaveDates);
        doctor.setWorkingHours(hours);
        doctor.setAvailable(leaveDates.length == 0);
        doctor.setOnLeave(leaveDates.length > 0);
        
        manager.addDoctor(doctor);
    }
    
    
    public static void initializeSamplePharmacyStock(PharmacyControl pharmacyControl) {
        try {
             // --- Pain Relievers & Anti-Inflammatories ---
            addSampleMedication(pharmacyControl, "M001", "Paracetamol 500mg", "Pain and fever relief", 10.50, 250, "Tablet");
            addSampleMedication(pharmacyControl, "M002", "Ibuprofen 200mg", "Anti-inflammatory", 15.00, 180, "Tablet");
            addSampleMedication(pharmacyControl, "M003", "Aspirin 100mg", "Blood thinner, pain relief", 8.00, 90, "Tablet");
            addSampleMedication(pharmacyControl, "M004", "Naproxen 220mg", "Long-lasting pain relief", 22.75, 60, "Tablet");
            addSampleMedication(pharmacyControl, "M005", "Diclofenac Gel", "Topical pain relief", 35.50, 45, "Cream");
            addSampleMedication(pharmacyControl, "M006", "Celecoxib 200mg", "Prescription anti-inflammatory", 55.00, 25, "Capsule");
            addSampleMedication(pharmacyControl, "M007", "Tramadol 50mg", "Strong pain relief", 48.20, 30, "Capsule");
            addSampleMedication(pharmacyControl, "M008", "Mefenamic Acid", "Menstrual pain relief", 19.80, 75, "Tablet");
            
            // --- Antibiotics ---
            addSampleMedication(pharmacyControl, "M009", "Amoxicillin 500mg", "Broad-spectrum antibiotic", 35.20, 120, "Capsule");
            addSampleMedication(pharmacyControl, "M010", "Doxycycline 100mg", "Tetracycline antibiotic", 42.00, 80, "Capsule");
            addSampleMedication(pharmacyControl, "M011", "Azithromycin 250mg", "Macrolide antibiotic", 65.00, 50, "Tablet");
            addSampleMedication(pharmacyControl, "M012", "Ciprofloxacin 500mg", "Fluoroquinolone antibiotic", 58.50, 40, "Tablet");
            addSampleMedication(pharmacyControl, "M013", "Cephalexin 250mg", "Cephalosporin antibiotic", 40.10, 65, "Capsule");
            addSampleMedication(pharmacyControl, "M014", "Metronidazole 400mg", "Anaerobic bacteria antibiotic", 33.00, 55, "Tablet");

            // --- Allergy, Cold & Flu ---
            addSampleMedication(pharmacyControl, "M015", "Loratadine 10mg", "Non-drowsy antihistamine", 18.50, 150, "Tablet");
            addSampleMedication(pharmacyControl, "M016", "Cetirizine 10mg", "Antihistamine for allergies", 15.00, 200, "Tablet");
            addSampleMedication(pharmacyControl, "M017", "Guaifenesin Syrup", "Expectorant for cough", 25.00, 80, "Liquid");
            addSampleMedication(pharmacyControl, "M018", "Dextromethorphan", "Cough suppressant", 28.90, 70, "Liquid");
            addSampleMedication(pharmacyControl, "M019", "Pseudoephedrine", "Nasal decongestant", 21.40, 110, "Tablet");
            addSampleMedication(pharmacyControl, "M020", "Fluticasone Spray", "Nasal steroid for allergies", 45.00, 35, "Spray");
            addSampleMedication(pharmacyControl, "M021", "Chlorpheniramine", "Antihistamine for cold", 9.50, 300, "Tablet");
            addSampleMedication(pharmacyControl, "M022", "Oseltamivir 75mg", "Antiviral for influenza", 110.00, 15, "Capsule");
            
            // --- Stomach & Digestive Health ---
            addSampleMedication(pharmacyControl, "M023", "Antacid Liquid", "Relieves heartburn", 12.75, 120, "Liquid");
            addSampleMedication(pharmacyControl, "M024", "Omeprazole 20mg", "Proton-pump inhibitor", 38.00, 90, "Capsule");
            addSampleMedication(pharmacyControl, "M025", "Loperamide 2mg", "Anti-diarrheal", 14.20, 130, "Tablet");
            addSampleMedication(pharmacyControl, "M026", "Bismuth Subsalicylate", "Upset stomach relief", 24.50, 60, "Liquid");
            addSampleMedication(pharmacyControl, "M027", "Simethicone 125mg", "Anti-gas relief", 17.80, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M028", "Hyoscine Butylbromide", "Stomach cramp relief", 29.00, 50, "Tablet");
            addSampleMedication(pharmacyControl, "M029", "Domperidone 10mg", "Anti-nausea", 31.50, 40, "Tablet");
            addSampleMedication(pharmacyControl, "M030", "Ranitidine 150mg", "Acid reducer", 25.50, 20, "Tablet"); // Low stock example

            // --- Vitamins & Supplements ---
            addSampleMedication(pharmacyControl, "M031", "Vitamin C 1000mg", "Immune support", 18.00, 220, "Tablet");
            addSampleMedication(pharmacyControl, "M032", "Vitamin D3 1000IU", "Bone health", 25.00, 150, "Tablet");
            addSampleMedication(pharmacyControl, "M033", "Multivitamin", "General wellness", 45.00, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M034", "Iron Supplement", "For anemia", 22.30, 80, "Tablet");
            addSampleMedication(pharmacyControl, "M035", "Calcium + Magnesium", "Bone and muscle support", 33.80, 95, "Tablet");
            addSampleMedication(pharmacyControl, "M036", "Fish Oil Omega-3", "Heart and brain health", 55.00, 60, "Capsule");
            addSampleMedication(pharmacyControl, "M037", "Folic Acid 400mcg", "Prenatal supplement", 19.90, 110, "Tablet");
            addSampleMedication(pharmacyControl, "M038", "Probiotic Capsules", "Digestive health", 68.00, 40, "Capsule");
            
            // --- Topical (Skin) ---
            addSampleMedication(pharmacyControl, "M039", "Hydrocortisone Cream", "Anti-itch cream", 16.50, 85, "Cream");
            addSampleMedication(pharmacyControl, "M040", "Clotrimazole Cream", "Antifungal cream", 21.00, 70, "Cream");
            addSampleMedication(pharmacyControl, "M041", "Mupirocin Ointment", "Topical antibiotic", 39.50, 30, "Ointment");
            addSampleMedication(pharmacyControl, "M042", "Bacitracin Ointment", "First aid antibiotic", 14.00, 140, "Ointment");
            addSampleMedication(pharmacyControl, "M043", "Calamine Lotion", "Skin soothing lotion", 11.20, 90, "Lotion");
            addSampleMedication(pharmacyControl, "M044", "Salicylic Acid Wash", "Acne treatment", 28.00, 50, "Liquid");
            
            // --- Cardiovascular ---
            addSampleMedication(pharmacyControl, "M045", "Amlodipine 5mg", "Blood pressure control", 24.00, 180, "Tablet");
            addSampleMedication(pharmacyControl, "M046", "Atorvastatin 20mg", "Cholesterol control", 75.00, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M047", "Metoprolol 25mg", "Beta-blocker", 32.50, 120, "Tablet");
            addSampleMedication(pharmacyControl, "M048", "Lisinopril 10mg", "ACE inhibitor", 28.00, 150, "Tablet");
            addSampleMedication(pharmacyControl, "M049", "Furosemide 40mg", "Diuretic (water pill)", 15.60, 90, "Tablet");
            addSampleMedication(pharmacyControl, "M050", "Warfarin 5mg", "Anticoagulant", 41.00, 40, "Tablet");

            // --- Diabetes ---
            addSampleMedication(pharmacyControl, "M051", "Metformin 500mg", "Type 2 diabetes", 19.00, 300, "Tablet");
            addSampleMedication(pharmacyControl, "M052", "Gliclazide 80mg", "Type 2 diabetes", 27.50, 120, "Tablet");
            addSampleMedication(pharmacyControl, "M053", "Sitagliptin 100mg", "DPP-4 inhibitor", 150.00, 30, "Tablet");
            addSampleMedication(pharmacyControl, "M054", "Insulin Glargine", "Long-acting insulin", 120.00, 20, "Injection");
            addSampleMedication(pharmacyControl, "M055", "Insulin Aspart", "Rapid-acting insulin", 95.00, 25, "Injection");

            // --- Mental Health ---
            addSampleMedication(pharmacyControl, "M056", "Sertraline 50mg", "Antidepressant (SSRI)", 60.00, 70, "Tablet");
            addSampleMedication(pharmacyControl, "M057", "Escitalopram 10mg", "Antidepressant (SSRI)", 72.00, 60, "Tablet");
            addSampleMedication(pharmacyControl, "M058", "Alprazolam 0.5mg", "Anti-anxiety", 45.00, 45, "Tablet");
            addSampleMedication(pharmacyControl, "M059", "Lorazepam 1mg", "Anti-anxiety", 38.50, 55, "Tablet");
            
            // --- Miscellaneous ---
            addSampleMedication(pharmacyControl, "M060", "Levothyroxine 50mcg", "Thyroid hormone", 22.00, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M061", "Salbutamol Inhaler", "Asthma relief", 34.00, 80, "Inhaler");
            addSampleMedication(pharmacyControl, "M062", "Prednisolone 5mg", "Corticosteroid", 26.80, 90, "Tablet");
            addSampleMedication(pharmacyControl, "M063", "Allopurinol 100mg", "Gout treatment", 18.90, 75, "Tablet");
            addSampleMedication(pharmacyControl, "M064", "Tamsulosin 0.4mg", "Prostate medication", 58.00, 40, "Capsule");
            addSampleMedication(pharmacyControl, "M065", "Artificial Tears", "Dry eye relief", 19.50, 120, "Eye Drop");
            addSampleMedication(pharmacyControl, "M066", "Ketotifen Eye Drops", "Allergy eye relief", 29.00, 50, "Eye Drop");
            addSampleMedication(pharmacyControl, "M067", "Oral Rehydration Salt", "Dehydration treatment", 5.50, 400, "Powder");
            addSampleMedication(pharmacyControl, "M068", "Smokers Lozenge", "Nicotine replacement", 42.00, 30, "Lozenge");
            
            // --- Filling up to 100 ---
            addSampleMedication(pharmacyControl, "M069", "Bisacodyl 5mg", "Laxative", 12.00, 150, "Tablet");
            addSampleMedication(pharmacyControl, "M070", "Dimenhydrinate 50mg", "Motion sickness", 16.00, 90, "Tablet");
            addSampleMedication(pharmacyControl, "M071", "Zinc Oxide Ointment", "Diaper rash, skin protectant", 20.00, 60, "Ointment");
            addSampleMedication(pharmacyControl, "M072", "Glucosamine Sulfate", "Joint health supplement", 75.00, 50, "Tablet");
            addSampleMedication(pharmacyControl, "M073", "Melatonin 3mg", "Sleep aid", 35.00, 80, "Tablet");
            addSampleMedication(pharmacyControl, "M074", "Permethrin Cream", "Scabies treatment", 48.00, 20, "Cream");
            addSampleMedication(pharmacyControl, "M075", "Clindamycin 150mg", "Lincosamide antibiotic", 52.00, 50, "Capsule");
            addSampleMedication(pharmacyControl, "M076", "Erythromycin 250mg", "Macrolide antibiotic", 44.00, 60, "Tablet");
            addSampleMedication(pharmacyControl, "M077", "Famotidine 20mg", "Acid reducer (H2 blocker)", 23.00, 110, "Tablet");
            addSampleMedication(pharmacyControl, "M078", "Lactulose Solution", "Constipation relief", 31.00, 40, "Liquid");
            addSampleMedication(pharmacyControl, "M079", "Paracetamol 120mg/5ml", "Children's fever relief", 15.50, 100, "Liquid");
            addSampleMedication(pharmacyControl, "M080", "Ibuprofen 100mg/5ml", "Children's pain relief", 19.50, 90, "Liquid");
            addSampleMedication(pharmacyControl, "M081", "Betamethasone Cream", "Topical steroid", 33.00, 45, "Cream");
            addSampleMedication(pharmacyControl, "M082", "Terbinafine Cream", "Athlete's foot treatment", 27.00, 55, "Cream");
            addSampleMedication(pharmacyControl, "M083", "Clobetasol Ointment", "Potent topical steroid", 50.00, 25, "Ointment");
            addSampleMedication(pharmacyControl, "M084", "Diazepam 5mg", "Anxiety, muscle relaxant", 39.00, 30, "Tablet");
            addSampleMedication(pharmacyControl, "M085", "Citalopram 20mg", "Antidepressant (SSRI)", 55.00, 65, "Tablet");
            addSampleMedication(pharmacyControl, "M086", "Olanzapine 5mg", "Antipsychotic", 125.00, 20, "Tablet");
            addSampleMedication(pharmacyControl, "M087", "Rosuvastatin 10mg", "Cholesterol control", 85.00, 90, "Tablet");
            addSampleMedication(pharmacyControl, "M088", "Telmisartan 40mg", "Blood pressure (ARB)", 65.00, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M089", "Hydrochlorothiazide", "Diuretic", 12.50, 130, "Tablet");
            addSampleMedication(pharmacyControl, "M090", "Spironolactone 25mg", "Diuretic", 21.50, 70, "Tablet");
            addSampleMedication(pharmacyControl, "M091", "Gabapentin 300mg", "Nerve pain, anticonvulsant", 78.00, 50, "Capsule");
            addSampleMedication(pharmacyControl, "M092", "Pregabalin 75mg", "Nerve pain", 95.00, 40, "Capsule");
            addSampleMedication(pharmacyControl, "M093", "Montelukast 10mg", "Asthma, allergies", 68.00, 60, "Tablet");
            addSampleMedication(pharmacyControl, "M094", "Budesonide Inhaler", "Asthma preventer", 88.00, 30, "Inhaler");
            addSampleMedication(pharmacyControl, "M095", "Finasteride 5mg", "Prostate medication", 72.00, 25, "Tablet");
            addSampleMedication(pharmacyControl, "M096", "Sildenafil 50mg", "Erectile dysfunction", 99.00, 15, "Tablet");
            addSampleMedication(pharmacyControl, "M097", "Esomeprazole 40mg", "Proton-pump inhibitor", 82.00, 50, "Capsule");
            addSampleMedication(pharmacyControl, "M098", "Pantoprazole 40mg", "Proton-pump inhibitor", 77.00, 55, "Tablet");
            addSampleMedication(pharmacyControl, "M099", "Ondansetron 4mg", "Anti-nausea", 51.00, 35, "Tablet");
            addSampleMedication(pharmacyControl, "M100", "Methylphenidate 10mg", "ADHD treatment", 130.00, 10, "Tablet");
            
        } catch (Exception e) {
            System.out.println("Error loading sample pharmacy data: " + e.getMessage());
        }
    }
    
    private static void addSampleMedication(PharmacyControl pharmacyControl, String id, String name, 
            String description, double price, int quantity, String type) {

        // Here we create the entity object within the DAO layer
        Pharmacy medication = new Pharmacy(id, name, description, price, quantity, type, new Date());
        
        // And pass the complete object to the control layer's new method
        pharmacyControl.addMedication(medication);
    }
    
     public static void initializeSamplePrescriptionHistory(MedicalTreatmentControl treatmentControl, PharmacyControl pharmacyControl) {
        System.out.println("Simulating a large, hardcoded prescription history for Jan-Aug 2025...");
        try {
            // --- Data pools for creating realistic, random history ---
            String[] patientIDs = {
                "P001", "P002", "P003", "P004", "P005", "P006", "P007", "P008", "P009", "P010",
                "P011", "P012", "P013", "P014", "P015", "P016", "P017", "P018", "P019", "P020",
                "P021", "P022", "P023", "P024", "P025", "P026", "P027", "P028", "P029", "P030",
                "P031", "P032", "P033", "P034", "P035", "P036", "P037", "P038", "P039", "P040",
                "P041", "P042", "P043", "P044", "P045", "P046", "P047", "P048", "P049", "P050"
            };
            String[] doctorIDs = {"D101", "D201", "D301", "D401", "D501", "D102", "D202", "D302"};
            String[] diagnosisTemplateIDs = {"DIAG01", "DIAG02", "DIAG03", "DIAG04", "DIAG05", "DIAG06"};
            String[] sickTypes = {"Acute", "Chronic", "Follow-up"};
            String[] medicationIDs = pharmacyControl.getAllMedicationIDsForTesting();
            Random rand = new Random();
            int year = 2025;

            // --- Hardcode 25 approved prescriptions for JANUARY 2025 ---
            for (int i = 0; i < 40; i++) {
                createAndApproveRandomTreatment(treatmentControl, pharmacyControl, year, Calendar.JANUARY, rand, patientIDs, doctorIDs, diagnosisTemplateIDs, sickTypes, medicationIDs);
            }

            // --- Hardcode 20 approved prescriptions for FEBRUARY 2025 ---
            for (int i = 0; i < 45; i++) {
                createAndApproveRandomTreatment(treatmentControl, pharmacyControl, year, Calendar.FEBRUARY, rand, patientIDs, doctorIDs, diagnosisTemplateIDs, sickTypes, medicationIDs);
            }
            
            // --- Hardcode 28 approved prescriptions for MARCH 2025 ---
            for (int i = 0; i < 44; i++) {
                createAndApproveRandomTreatment(treatmentControl, pharmacyControl, year, Calendar.MARCH, rand, patientIDs, doctorIDs, diagnosisTemplateIDs, sickTypes, medicationIDs);
            }

            // --- Hardcode 23 approved prescriptions for APRIL 2025 ---
            for (int i = 0; i < 42; i++) {
                createAndApproveRandomTreatment(treatmentControl, pharmacyControl, year, Calendar.APRIL, rand, patientIDs, doctorIDs, diagnosisTemplateIDs, sickTypes, medicationIDs);
            }
            
            // --- Hardcode 26 approved prescriptions for MAY 2025 ---
            for (int i = 0; i < 46; i++) {
                createAndApproveRandomTreatment(treatmentControl, pharmacyControl, year, Calendar.MAY, rand, patientIDs, doctorIDs, diagnosisTemplateIDs, sickTypes, medicationIDs);
            }

            // --- Hardcode 21 approved prescriptions for JUNE 2025 ---
            for (int i = 0; i < 31; i++) {
                createAndApproveRandomTreatment(treatmentControl, pharmacyControl, year, Calendar.JUNE, rand, patientIDs, doctorIDs, diagnosisTemplateIDs, sickTypes, medicationIDs);
            }
            
            // --- Hardcode 24 approved prescriptions for JULY 2025 ---
            for (int i = 0; i < 24; i++) {
                createAndApproveRandomTreatment(treatmentControl, pharmacyControl, year, Calendar.JULY, rand, patientIDs, doctorIDs, diagnosisTemplateIDs, sickTypes, medicationIDs);
            }
            
           

            // --- Add one case that gets put on hold for testing ---
            treatmentControl.createTreatment("P005", "D401", "Recurring severe headaches.", "Chronic", "DIAG05", "M007", 500);
            pharmacyControl.approveNextPrescription(); // This will fail due to low stock and be held

            System.out.println("Prescription history simulation complete. Over 180 historical records created.");

        } catch (Exception e) {
            System.err.println("An error occurred during history simulation: " + e.getMessage());
            e.printStackTrace();
        }
    }

     private static void createAndApproveRandomTreatment(MedicalTreatmentControl treatmentControl, PharmacyControl pharmacyControl,
                                                         int year, int month, Random rand, String[] patientIDs,
                                                         String[] doctorIDs, String[] diagnosisTemplateIDs,
                                                         String[] sickTypes, String[] medicationIDs) {
        // --- Create random data ---
        String patientID = patientIDs[rand.nextInt(patientIDs.length)];
        String doctorID = doctorIDs[rand.nextInt(doctorIDs.length)];
        String templateID = diagnosisTemplateIDs[rand.nextInt(diagnosisTemplateIDs.length)];
        String sickType = sickTypes[rand.nextInt(sickTypes.length)];
        String medID = medicationIDs[rand.nextInt(medicationIDs.length)];
        int quantity = rand.nextInt(20) + 5;

        // --- Create a random date within the specified month ---
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int randomDay = rand.nextInt(maxDay) + 1;
        cal.set(Calendar.DAY_OF_MONTH, randomDay);
        Date specificDate = cal.getTime();

        // --- Execute the creation and approval ---
        treatmentControl.createTreatment(patientID, doctorID, "Patient symptoms recorded.", sickType, templateID, medID, quantity);
        approveWithSpecificDate(pharmacyControl, specificDate);
    }
   
    
     
    
     private static void approveWithSpecificDate(PharmacyControl pharmacyControl, Date specificDate) {
        Prescription p = pharmacyControl.getPendingPrescriptionsForTesting().getFront();
        if (p == null) return;

        p.setApprovalDate(specificDate);
        pharmacyControl.approveNextPrescription();
    }
    
    public static void initializeSampleDiagnoses(MedicalTreatmentControl treatmentControl) {
        try {
                        // --- Respiratory & ENT (Ear, Nose, Throat) ---
            treatmentControl.addDiagnosisTemplate("DIAG01", "Common Flu", "Viral infection of the upper respiratory tract. Recommend rest and hydration."); // Respiratory
            treatmentControl.addDiagnosisTemplate("DIAG02", "Strep Throat", "Bacterial infection causing a severe sore throat. Requires antibiotics."); // Respiratory
            treatmentControl.addDiagnosisTemplate("DIAG03", "Asthma Attack", "Narrowing of airways causing wheezing and shortness of breath. Requires inhaler use."); // Respiratory
            treatmentControl.addDiagnosisTemplate("DIAG04", "Bronchitis", "Inflammation of the bronchial tubes, leading to cough and mucus. Suggest fluids and rest."); // Respiratory
            treatmentControl.addDiagnosisTemplate("DIAG05", "Pneumonia", "Lung infection causing cough, fever, and breathing difficulty. Antibiotics may be needed."); // Respiratory
            treatmentControl.addDiagnosisTemplate("DIAG06", "Measles", "Viral infection with fever, cough, and rash. Highly contagious, requires isolation."); // Infectious/Respiratory
            treatmentControl.addDiagnosisTemplate("DIAG07", "Whooping Cough", "Severe coughing fits followed by a ‘whoop’ sound. Requires antibiotics."); // Infectious/Respiratory
            treatmentControl.addDiagnosisTemplate("DIAG08", "Tuberculosis", "Bacterial lung infection causing cough and weight loss. Needs long-term antibiotics."); // Infectious/Respiratory
            treatmentControl.addDiagnosisTemplate("DIAG09", "COVID-19", "Respiratory infection with cough, fever, and fatigue. May need isolation and antiviral therapy."); // Infectious/Respiratory
            treatmentControl.addDiagnosisTemplate("DIAG10", "Sinusitis", "Inflammation of sinuses causing facial pain and congestion. Suggest decongestants."); // ENT
            treatmentControl.addDiagnosisTemplate("DIAG11", "Otitis Media", "Middle ear infection causing pain and fever. May require antibiotics."); // ENT

            // --- Gastrointestinal & Digestive ---
            treatmentControl.addDiagnosisTemplate("DIAG12", "Indigestion", "Discomfort in the upper abdomen, often after eating. Recommend antacids."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG13", "Gastritis", "Stomach lining inflammation causing pain and nausea. Avoid spicy food and use antacids."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG14", "Food Poisoning", "Nausea, vomiting, and diarrhea caused by contaminated food. Recommend fluids and rest."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG15", "Constipation", "Difficulty passing stools. Suggest high fiber diet and hydration."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG16", "Diarrhea", "Frequent loose stools. Risk of dehydration. Recommend oral rehydration salts."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG17", "Acid Reflux", "Burning sensation in the chest after eating. Suggest antacids and lifestyle changes."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG18", "Appendicitis", "Severe abdominal pain needing surgical removal of appendix."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG19", "Gallstones", "Hardened deposits in gallbladder causing pain. May require surgery."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG20", "Pancreatitis", "Inflammation of pancreas causing abdominal pain. Needs hospitalization."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG21", "Irritable Bowel Syndrome", "Chronic digestive disorder with cramps and bloating. Lifestyle management needed."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG22", "Crohn Disease", "Inflammatory bowel disease affecting intestines. Requires medication."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG23", "Ulcerative Colitis", "Inflammatory bowel disease causing bloody diarrhea. Needs treatment."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG24", "Peptic Ulcer", "Sores in stomach lining causing pain. Antacids and antibiotics recommended."); // Gastrointestinal
            treatmentControl.addDiagnosisTemplate("DIAG25", "Hemorrhoids", "Swollen veins in rectum causing pain and bleeding. Suggest high fiber diet."); // Gastrointestinal

            // --- Cardiovascular & Circulatory ---
            treatmentControl.addDiagnosisTemplate("DIAG26", "Hypertension", "High blood pressure often without symptoms. Recommend monitoring and lifestyle change."); // Cardiovascular
            treatmentControl.addDiagnosisTemplate("DIAG27", "Hypotension", "Low blood pressure causing dizziness and fainting. Suggest hydration and rest."); // Cardiovascular
            treatmentControl.addDiagnosisTemplate("DIAG28", "Stroke", "Brain damage due to interrupted blood flow. Emergency treatment required."); // Cardiovascular/Neurological
            treatmentControl.addDiagnosisTemplate("DIAG29", "Heart Attack", "Blocked blood flow to the heart muscle. Emergency treatment required."); // Cardiovascular
            treatmentControl.addDiagnosisTemplate("DIAG30", "Arrhythmia", "Irregular heartbeat. May need medication or pacemaker."); // Cardiovascular
            treatmentControl.addDiagnosisTemplate("DIAG31", "Heart Failure", "Weak heart unable to pump effectively. Lifestyle changes and medication needed."); // Cardiovascular
            treatmentControl.addDiagnosisTemplate("DIAG32", "Anemia", "Low red blood cell count causing fatigue. Iron supplements may be required."); // Hematological

            // --- Neurological & Brain ---
            treatmentControl.addDiagnosisTemplate("DIAG33", "Parkinson Disease", "Nervous system disorder causing tremors and stiffness. Requires medication."); // Neurological
            treatmentControl.addDiagnosisTemplate("DIAG34", "Epilepsy", "Seizure disorder. Requires anticonvulsant medication."); // Neurological
            treatmentControl.addDiagnosisTemplate("DIAG35", "Sciatica", "Nerve pain radiating down the leg. Rest and physiotherapy recommended."); // Neurological
            treatmentControl.addDiagnosisTemplate("DIAG36", "Herniated Disc", "Spinal disc displacement causing back pain. May need surgery."); // Neurological

            // --- Mental & Behavioral Health ---
            treatmentControl.addDiagnosisTemplate("DIAG37", "Depression", "Persistent sadness and lack of interest. Suggest counseling and possible medication."); // Mental Health
            treatmentControl.addDiagnosisTemplate("DIAG38", "Anxiety Disorder", "Excessive worry and restlessness. Therapy or medication may be needed."); // Mental Health
            treatmentControl.addDiagnosisTemplate("DIAG39", "Panic Attack", "Sudden intense fear with rapid heartbeat. Encourage relaxation techniques."); // Mental Health
            treatmentControl.addDiagnosisTemplate("DIAG40", "Insomnia", "Difficulty falling or staying asleep. Suggest sleep hygiene and relaxation."); // Mental Health
            treatmentControl.addDiagnosisTemplate("DIAG41", "Bipolar Disorder", "Mood swings between highs and lows. Requires medical treatment."); // Mental Health
            treatmentControl.addDiagnosisTemplate("DIAG42", "Schizophrenia", "Mental disorder with distorted thinking and hallucinations. Needs antipsychotics."); // Mental Health
            treatmentControl.addDiagnosisTemplate("DIAG43", "PTSD", "Anxiety after traumatic events. Therapy and medication may be required."); // Mental Health
            treatmentControl.addDiagnosisTemplate("DIAG44", "Dementia", "Decline in memory and thinking skills. Supportive care and medication available."); // Mental Health
            treatmentControl.addDiagnosisTemplate("DIAG45", "Alzheimer Disease", "Progressive brain disorder causing memory loss. Supportive treatment only."); // Mental Health

            // --- Endocrine, Metabolic & Nutritional ---
            treatmentControl.addDiagnosisTemplate("DIAG46", "Diabetes (Type 2)", "High blood sugar levels. Recommend lifestyle changes and possible medication."); // Endocrine
            treatmentControl.addDiagnosisTemplate("DIAG47", "Diabetes (Type 1)", "Insufficient insulin production. Requires insulin therapy."); // Endocrine
            treatmentControl.addDiagnosisTemplate("DIAG48", "Obesity", "Excess body fat increasing health risks. Recommend diet and exercise."); // Metabolic
            treatmentControl.addDiagnosisTemplate("DIAG49", "Hyperthyroidism", "Overactive thyroid causing weight loss and anxiety. Requires medication."); // Endocrine
            treatmentControl.addDiagnosisTemplate("DIAG50", "Hypothyroidism", "Underactive thyroid causing fatigue and weight gain. Needs hormone therapy."); // Endocrine
            treatmentControl.addDiagnosisTemplate("DIAG51", "Vitamin D Deficiency", "Lack of vitamin D causing bone pain. Recommend supplements and sunlight."); // Nutritional
            treatmentControl.addDiagnosisTemplate("DIAG52", "Vitamin B12 Deficiency", "Causes anemia and neurological issues. Requires supplements."); // Nutritional
            treatmentControl.addDiagnosisTemplate("DIAG53", "Scurvy", "Vitamin C deficiency causing gum bleeding. Recommend citrus fruits."); // Nutritional
            treatmentControl.addDiagnosisTemplate("DIAG54", "Rickets", "Bone softening in children due to vitamin D deficiency. Needs supplements."); // Nutritional
            treatmentControl.addDiagnosisTemplate("DIAG55", "Gout", "Sudden joint pain due to uric acid buildup. Suggest dietary changes."); // Metabolic

            // --- Musculoskeletal & Orthopedic ---
            treatmentControl.addDiagnosisTemplate("DIAG56", "Minor Sprain", "Stretching or tearing of ligaments. Apply RICE method (Rest, Ice, Compression, Elevation)."); // Musculoskeletal
            treatmentControl.addDiagnosisTemplate("DIAG57", "Arthritis", "Joint inflammation causing pain and stiffness. Pain relievers may help."); // Musculoskeletal
            treatmentControl.addDiagnosisTemplate("DIAG58", "Carpal Tunnel Syndrome", "Nerve compression in the wrist causing pain and numbness. May need splint."); // Musculoskeletal
            treatmentControl.addDiagnosisTemplate("DIAG59", "Tendonitis", "Inflammation of tendons causing pain. Rest and anti-inflammatory drugs help."); // Musculoskeletal
            treatmentControl.addDiagnosisTemplate("DIAG60", "Fracture", "Broken bone requiring immobilization or surgery."); // Musculoskeletal
            treatmentControl.addDiagnosisTemplate("DIAG61", "Dislocation", "Bone out of joint position. Needs reduction by medical staff."); // Musculoskeletal
            treatmentControl.addDiagnosisTemplate("DIAG62", "Whiplash", "Neck injury due to sudden jerk. Rest and pain relievers recommended."); // Musculoskeletal
            treatmentControl.addDiagnosisTemplate("DIAG63", "Osteoporosis", "Weak and brittle bones. Recommend calcium and exercise."); // Musculoskeletal

            // --- Dermatological (Skin) ---
            treatmentControl.addDiagnosisTemplate("DIAG64", "Chickenpox", "Viral infection with itchy blisters. Supportive care and avoid scratching."); // Dermatological/Infectious
            treatmentControl.addDiagnosisTemplate("DIAG65", "Eczema", "Chronic skin condition with dry, itchy patches. Use moisturizers and avoid triggers."); // Dermatological
            treatmentControl.addDiagnosisTemplate("DIAG66", "Psoriasis", "Skin condition with red, scaly patches. May need topical or systemic treatment."); // Dermatological
            treatmentControl.addDiagnosisTemplate("DIAG67", "Acne", "Clogged pores leading to pimples. Use topical treatments or antibiotics."); // Dermatological
            treatmentControl.addDiagnosisTemplate("DIAG68", "Sunburn", "Skin damage due to UV rays. Apply soothing lotions and avoid sun exposure."); // Dermatological
            treatmentControl.addDiagnosisTemplate("DIAG69", "Fungal Infection", "Itchy rash caused by fungus (athlete’s foot, ringworm). Use antifungal cream."); // Dermatological
            treatmentControl.addDiagnosisTemplate("DIAG70", "Scabies", "Skin infestation by mites causing intense itching. Requires medicated cream."); // Dermatological

            // --- Urological & Renal (Urinary & Kidney) ---
            treatmentControl.addDiagnosisTemplate("DIAG71", "Urinary Tract Infection", "Burning sensation during urination, frequent urge to urinate. Needs antibiotics."); // Urological
            treatmentControl.addDiagnosisTemplate("DIAG72", "Kidney Stones", "Severe pain in lower back or side. May require pain relief or medical removal."); // Renal

            // --- Hepatic (Liver) ---
            treatmentControl.addDiagnosisTemplate("DIAG73", "Hepatitis A", "Viral liver infection spread by food. Usually self-limiting."); // Hepatic
            treatmentControl.addDiagnosisTemplate("DIAG74", "Hepatitis B", "Viral liver infection spread by blood. May become chronic."); // Hepatic
            treatmentControl.addDiagnosisTemplate("DIAG75", "Hepatitis C", "Viral liver infection causing chronic damage. Needs antiviral drugs."); // Hepatic
            treatmentControl.addDiagnosisTemplate("DIAG76", "Cirrhosis", "Chronic liver damage due to alcohol or hepatitis. Supportive care required."); // Hepatic
            treatmentControl.addDiagnosisTemplate("DIAG77", "Jaundice", "Yellowing of skin due to liver issues. Treat underlying cause."); // Hepatic
            treatmentControl.addDiagnosisTemplate("DIAG78", "Cholecystitis", "Gallbladder inflammation. May need surgery."); // Hepatic

            // --- Allergic & Immunological ---
            treatmentControl.addDiagnosisTemplate("DIAG79", "Allergic Rhinitis", "Runny nose, sneezing, and congestion caused by allergens. Suggest antihistamines."); // Allergic
            treatmentControl.addDiagnosisTemplate("DIAG80", "Conjunctivitis", "Red, itchy eyes caused by infection or allergy. Use eye drops or antihistamines."); // Allergic/Infectious
            treatmentControl.addDiagnosisTemplate("DIAG81", "Anaphylaxis", "Severe allergic reaction. Emergency epinephrine required."); // Allergic

            // --- Headaches & Pain ---
            treatmentControl.addDiagnosisTemplate("DIAG82", "Tension Headache", "Mild to moderate pain in the head, often described as a tight band. Suggest pain relievers."); // Headache
            treatmentControl.addDiagnosisTemplate("DIAG83", "Migraine", "Severe, recurring headache, often accompanied by nausea and light sensitivity."); // Headache

            // --- Environmental & Toxin-Related ---
            treatmentControl.addDiagnosisTemplate("DIAG84", "Dehydration", "Loss of body fluids leading to weakness and dizziness. Recommend oral rehydration."); // Environmental
            treatmentControl.addDiagnosisTemplate("DIAG85", "Heat Exhaustion", "Weakness, heavy sweating, and fainting due to high temperatures. Cool down immediately."); // Environmental
            treatmentControl.addDiagnosisTemplate("DIAG86", "Heat Stroke", "Severe overheating causing confusion and collapse. Requires emergency care."); // Environmental
            treatmentControl.addDiagnosisTemplate("DIAG87", "Frostbite", "Skin and tissue freezing due to cold exposure. Warm gradually."); // Environmental
            treatmentControl.addDiagnosisTemplate("DIAG88", "Hypothermia", "Dangerously low body temperature. Emergency warming required."); // Environmental
            treatmentControl.addDiagnosisTemplate("DIAG89", "Snake Bite", "Venom injection causing pain and swelling. Emergency antivenom required."); // Toxin
            treatmentControl.addDiagnosisTemplate("DIAG90", "Dog Bite", "Puncture wounds risk infection. May need rabies vaccination."); // Toxin/Injury
            treatmentControl.addDiagnosisTemplate("DIAG91", "Bee Sting", "Localized pain and swelling. Severe allergy may require epinephrine."); // Toxin/Allergic
            treatmentControl.addDiagnosisTemplate("DIAG92", "Poisoning", "Ingestion of toxic substance. Emergency care required."); // Toxin
            treatmentControl.addDiagnosisTemplate("DIAG93", "Lead Poisoning", "Chronic exposure causes developmental issues. Requires chelation therapy."); // Toxin
            treatmentControl.addDiagnosisTemplate("DIAG94", "Carbon Monoxide Poisoning", "Headache, dizziness, and confusion. Requires oxygen therapy."); // Toxin
            treatmentControl.addDiagnosisTemplate("DIAG95", "Alcohol Intoxication", "Impaired judgment and coordination. Rest and hydration required."); // Toxin
            treatmentControl.addDiagnosisTemplate("DIAG96", "Drug Overdose", "Toxic reaction to excessive drug use. Emergency care required."); // Toxin

            // --- Injuries & Burns ---
            treatmentControl.addDiagnosisTemplate("DIAG97", "Burn (First Degree)", "Red skin without blisters. Cool water treatment."); // Injury
            treatmentControl.addDiagnosisTemplate("DIAG98", "Burn (Second Degree)", "Blistered, painful skin. Medical care required."); // Injury
            treatmentControl.addDiagnosisTemplate("DIAG99", "Burn (Third Degree)", "Severe skin damage affecting deeper tissues. Emergency care needed."); // Injury

            // --- Infectious Diseases (General) ---
            treatmentControl.addDiagnosisTemplate("DIAG100", "Mumps", "Swelling of salivary glands. Rest, fluids, and pain relievers recommended."); // Infectious
        } catch (Exception e) {
            System.out.println("Error loading sample diagnoses: " + e.getMessage());
        }
    }
    
    public static void initializeSamplePatients(PatientManager patientManager) {
        try {
            // Patients in queue
            addSamplePatient(patientManager, "Ali Bin Abu", "990101014321", "0123456789", "01/01/1999", 
                "M", "A+", "Peanuts", 70.5, 170.0, true, "15/03/2024");
            addSamplePatient(patientManager, "Siti Aminah", "000202023456", "0139876543", "02/02/2000", 
                "F", "O-", "", 55.0, 160.0, true, "18/03/2024");
            addSamplePatient(patientManager, "John Tan", "981010104321", "0112233445", "10/10/1998", 
                "M", "B+", "Seafood", 68.0, 175.0, true, "20/03/2024");
            addSamplePatient(patientManager, "Nur Izzati", "010303035432", "0199988776", "03/03/2001", 
                "F", "AB-", "", 60.0, 162.0, true, "22/03/2024");
            addSamplePatient(patientManager, "Raj Kumar", "970707074321", "0105678901", "07/07/1997", 
                "M", "O+", "Dust", 72.3, 180.0, true, "25/03/2024");
            
            // Patients not in queue
            addSamplePatient(patientManager, "Lim Mei Ling", "960606062222", "0143322110", "06/06/1996", 
                "F", "A-", "Lactose", 50.0, 155.0, false, "05/01/2024");
            addSamplePatient(patientManager, "Ahmad Fauzi", "950505051234", "0176543210", "05/05/1995", 
                "M", "B-", "Penicillin", 80.0, 178.0, false, "10/01/2024");
            addSamplePatient(patientManager, "Sarah Chong", "940404043210", "0167890123", "04/04/1994", 
                "F", "AB+", "", 58.0, 165.0, false, "15/01/2024");
            addSamplePatient(patientManager, "Mohd Razak", "930303032345", "0156789012", "03/03/1993", 
                "M", "A+", "Shellfish", 75.0, 172.0, false, "20/01/2024");
            addSamplePatient(patientManager, "Tan Wei Ling", "920202021234", "0198765432", "02/02/1992", 
                "F", "O+", "Nuts", 52.0, 158.0, false, "25/01/2024");
            
            // More patients
            addSamplePatient(patientManager, "Kumar Selvam", "910101013456", "0134567890", "01/01/1991", 
                "M", "B+", "", 82.0, 182.0, false, "01/02/2024");
            addSamplePatient(patientManager, "Fatimah Binti Abdullah", "891212123456", "0123456780", "12/12/1989", 
                "F", "O-", "Eggs", 65.0, 168.0, false, "05/02/2024");
            addSamplePatient(patientManager, "Lee Chong Wei", "881010102345", "0112345678", "10/10/1988", 
                "M", "A-", "", 70.0, 175.0, true, "10/02/2024");
            addSamplePatient(patientManager, "Nurul Syafiqah", "870707073210", "0190123456", "07/07/1987", 
                "F", "AB-", "Soy", 54.0, 160.0, false, "15/02/2024");
            addSamplePatient(patientManager, "Ramesh Naidu", "860606062345", "0178901234", "06/06/1986", 
                "M", "O+", "", 78.0, 180.0, true, "20/02/2024");
            
            // Additional patients
            addSamplePatient(patientManager, "Wong Mei Chen", "850505051234", "0167890123", "05/05/1985", 
                "F", "B+", "Dairy", 56.0, 163.0, false, "01/03/2024");
            addSamplePatient(patientManager, "Ahmad Hakimi", "840404043456", "0156789012", "04/04/1984", 
                "M", "A+", "", 85.0, 185.0, false, "05/03/2024");
            addSamplePatient(patientManager, "Norhayati Binti Osman", "830303032345", "0145678901", "03/03/1983", 
                "F", "O-", "Wheat", 62.0, 170.0, true, "10/03/2024");
            addSamplePatient(patientManager, "Chan Kok Leong", "820202021234", "0134567890", "02/02/1982", 
                "M", "AB+", "", 76.0, 178.0, false, "15/03/2024");
            addSamplePatient(patientManager, "Siti Nurhaliza", "810101013456", "0123456789", "01/01/1981", 
                "F", "B-", "Peanuts", 60.0, 165.0, false, "20/03/2024");
            
            // More diverse patients
            addSamplePatient(patientManager, "Muthu Samy", "791212123456", "0198765432", "12/12/1979", 
                "M", "O+", "", 90.0, 182.0, true, "01/04/2024");
            addSamplePatient(patientManager, "Lim Siew Ling", "781010102345", "0187654321", "10/10/1978", 
                "F", "A+", "Shellfish", 58.0, 162.0, false, "05/04/2024");
            addSamplePatient(patientManager, "Abdul Rahman", "770707073210", "0176543210", "07/07/1977", 
                "M", "B+", "", 82.0, 178.0, false, "10/04/2024");
            addSamplePatient(patientManager, "Tan Bee Lian", "760606062345", "0165432109", "06/06/1976", 
                "F", "AB-", "Latex", 63.0, 168.0, true, "15/04/2024");
            addSamplePatient(patientManager, "Krishnan A/L Muthu", "750505051234", "0154321098", "05/05/1975", 
                "M", "O-", "Eggs", 88.0, 183.0, false, "20/04/2024");
            
            // Older patients
            addSamplePatient(patientManager, "Ong Swee Lin", "740404043456", "0143210987", "04/04/1974", 
                "F", "A-", "", 65.0, 170.0, false, "01/05/2024");
            addSamplePatient(patientManager, "Mohd Faisal", "730303032345", "0132109876", "03/03/1973", 
                "M", "B-", "Penicillin", 92.0, 185.0, true, "05/05/2024");
            addSamplePatient(patientManager, "Yusuf Bin Ismail", "720202021234", "0121098765", "02/02/1972", 
                "M", "AB+", "", 84.0, 180.0, false, "10/05/2024");
            addSamplePatient(patientManager, "Noraini Binti Ali", "710101013456", "0110987654", "01/01/1971", 
                "F", "O+", "Nuts", 70.0, 175.0, false, "15/05/2024");
            addSamplePatient(patientManager, "Robert Chan", "691212123456", "0198765432", "12/12/1969", 
                "M", "A+", "", 95.0, 188.0, true, "20/05/2024");
            
            // Senior patients
            addSamplePatient(patientManager, "Maimunah Binti Ahmad", "681010102345", "0187654321", "10/10/1968", 
                "F", "B+", "Dairy", 68.0, 172.0, false, "01/06/2024");
            addSamplePatient(patientManager, "Ravi Shankar", "670707073210", "0176543210", "07/07/1967", 
                "M", "O-", "", 87.0, 182.0, false, "05/06/2024");
            addSamplePatient(patientManager, "Lily Wong", "660606062345", "0165432109", "06/06/1966", 
                "F", "AB+", "Wheat", 72.0, 178.0, true, "10/06/2024");
            addSamplePatient(patientManager, "Hassan Bin Omar", "650505051234", "0154321098", "05/05/1965", 
                "M", "A-", "", 90.0, 185.0, false, "15/06/2024");
            addSamplePatient(patientManager, "Susila Devi", "640404043456", "0143210987", "04/04/1964", 
                "F", "B-", "Peanuts", 75.0, 180.0, false, "20/06/2024");
            
            // More senior patients
            addSamplePatient(patientManager, "Goh Peng Lim", "630303032345", "0132109876", "03/03/1963", 
                "M", "O+", "Shellfish", 92.0, 188.0, true, "01/07/2024");
            addSamplePatient(patientManager, "Zainab Binti Yusof", "620202021234", "0121098765", "02/02/1962", 
                "F", "AB-", "", 78.0, 175.0, false, "05/07/2024");
            addSamplePatient(patientManager, "Arunachalam Muthu", "610101013456", "0110987654", "01/01/1961", 
                "M", "A+", "Latex", 85.0, 182.0, false, "10/07/2024");
            addSamplePatient(patientManager, "Lim Siew Hong", "591212123456", "0198765432", "12/12/1959", 
                "F", "B+", "Eggs", 80.0, 178.0, true, "15/07/2024");
            addSamplePatient(patientManager, "Ismail Bin Ahmad", "581010102345", "0187654321", "10/10/1958", 
                "M", "O-", "", 95.0, 185.0, false, "20/07/2024");
            
            // Elderly patients
            addSamplePatient(patientManager, "Chong Mei Fong", "570707073210", "0176543210", "07/07/1957", 
                "F", "AB+", "", 82.0, 180.0, false, "01/08/2024");
            addSamplePatient(patientManager, "Muthu Palanisamy", "560606062345", "0165432109", "06/06/1956", 
                "M", "A-", "Penicillin", 98.0, 188.0, true, "05/08/2024");
            addSamplePatient(patientManager, "Aishah Binti Mohd", "550505051234", "0154321098", "05/05/1955", 
                "F", "B-", "Nuts", 85.0, 175.0, false, "10/08/2024");
            addSamplePatient(patientManager, "Tan Kok Wai", "540404043456", "0143210987", "04/04/1954", 
                "M", "O+", "", 100.0, 185.0, false, "15/08/2024");
            addSamplePatient(patientManager, "Norhayati Binti Ali", "530303032345", "0132109876", "03/03/1953", 
                "F", "AB-", "Dairy", 88.0, 178.0, true, "20/08/2024");
            
            // Final set of patients
            addSamplePatient(patientManager, "Raja Kumar", "520202021234", "0121098765", "02/02/1952", 
                "M", "A+", "", 92.0, 182.0, false, "01/09/2024");
            addSamplePatient(patientManager, "Lim Siew Chin", "510101013456", "0110987654", "01/01/1951", 
                "F", "B+", "Wheat", 78.0, 175.0, false, "05/09/2024");
            addSamplePatient(patientManager, "Ahmad Bin Hassan", "491212123456", "0198765432", "12/12/1949", 
                "M", "O-", "Peanuts", 102.0, 188.0, true, "10/09/2024");
            addSamplePatient(patientManager, "Wong Mei Yee", "481010102345", "0187654321", "10/10/1948", 
                "F", "AB+", "", 85.0, 180.0, false, "15/09/2024");
            addSamplePatient(patientManager, "Muthu Samynathan", "470707073210", "0176543210", "07/07/1947", 
                "M", "A-", "Shellfish", 95.0, 185.0, false, "20/09/2024");
            
            // Additional patients - Group 1
            addSamplePatient(patientManager, "Chen Wei Liang", "961111115678", "0144556677", "11/11/1996", 
            "M", "B+", "Pollen", 72.0, 176.0, true, "22/03/2024");
            addSamplePatient(patientManager, "Nur Syakirah", "970909094321", "0133445566", "09/09/1997", 
            "F", "A-", "Shellfish", 58.5, 163.0, false, "12/02/2024");
            addSamplePatient(patientManager, "David Ng", "950808083456", "0122334455", "08/08/1995", 
            "M", "O+", "", 81.0, 179.0, true, "18/04/2024");
            addSamplePatient(patientManager, "Aisyah Binti Mohd", "980707072345", "0199887766", "07/07/1998", 
            "F", "AB+", "Dust mites", 62.0, 167.0, false, "05/03/2024");
            addSamplePatient(patientManager, "Karthik Murugan", "940606061234", "0188776655", "06/06/1994", 
            "M", "B-", "Penicillin", 76.0, 177.0, true, "28/04/2024");

// Additional patients - Group 2
            addSamplePatient(patientManager, "Lim Jia Hui", "990505054321", "0177665544", "05/05/1999", 
            "F", "O-", "Nuts", 54.0, 161.0, false, "15/01/2024");
            addSamplePatient(patientManager, "Amirul Hafiz", "930404043210", "0166554433", "04/04/1993", 
            "M", "A+", "", 83.5, 181.0, true, "02/05/2024");
            addSamplePatient(patientManager, "Priya Devi", "910303032345", "0155443322", "03/03/1991", 
            "F", "B+", "Latex", 59.0, 164.0, false, "22/02/2024");
            addSamplePatient(patientManager, "Tan Kok Ming", "900202021456", "0144332211", "02/02/1990", 
            "M", "AB-", "Eggs", 77.0, 178.0, true, "08/05/2024");
            addSamplePatient(patientManager, "Siti Aishah", "880101013456", "0133221100", "01/01/1988", 
            "F", "O+", "Soy", 63.5, 169.0, false, "18/01/2024");

// Additional patients - Group 3
            addSamplePatient(patientManager, "Rahman Bin Ali", "871212123210", "0122110099", "12/12/1987", 
            "M", "A-", "", 85.0, 182.0, true, "12/05/2024");
            addSamplePatient(patientManager, "Chong Mei Ling", "861010102345", "0111009988", "10/10/1986", 
            "F", "B-", "Dairy", 60.0, 165.0, false, "25/02/2024");
            addSamplePatient(patientManager, "Manoj Kumar", "850909093456", "0199887766", "09/09/1985", 
            "M", "AB+", "Peanuts", 79.0, 180.0, true, "15/05/2024");
            addSamplePatient(patientManager, "Nor Azlina", "840808082345", "0188776655", "08/08/1984", 
            "F", "O-", "", 64.0, 168.0, false, "08/03/2024");
            addSamplePatient(patientManager, "Goh Seng Choon", "830707071234", "0177665544", "07/07/1983", 
            "M", "A+", "Wheat", 87.0, 183.0, true, "18/05/2024");

// Additional patients - Group 4
            addSamplePatient(patientManager, "Yap Mei Fong", "820606062345", "0166554433", "06/06/1982", 
            "F", "B+", "Shellfish", 61.5, 166.0, false, "15/03/2024");
            addSamplePatient(patientManager, "Hafiz Bin Osman", "810505051456", "0155443322", "05/05/1981", 
            "M", "AB-", "", 90.0, 184.0, true, "22/05/2024");
            addSamplePatient(patientManager, "Lai Yee Ling", "800404043210", "0144332211", "04/04/1980", 
            "F", "O+", "Pollen", 66.0, 170.0, false, "22/03/2024");
            addSamplePatient(patientManager, "Rajendran A/L Muthu", "790303032345", "0133221100", "03/03/1979", 
            "M", "A-", "Penicillin", 92.5, 185.0, true, "25/05/2024");
            addSamplePatient(patientManager, "Wong Siew Peng", "780202021234", "0122110099", "02/02/1978", 
            "F", "B-", "", 68.0, 172.0, false, "28/03/2024");

// Additional patients - Group 5
            addSamplePatient(patientManager, "Ahmad Firdaus", "771111113456", "0111009988", "11/11/1977", 
            "M", "AB+", "Dust", 94.0, 186.0, true, "28/05/2024");
            addSamplePatient(patientManager, "Norlela Binti Samad", "761010102345", "0199887766", "10/10/1976", 
            "F", "O-", "Latex", 70.5, 173.0, false, "05/04/2024");
            addSamplePatient(patientManager, "Lim Chee Beng", "750909091234", "0188776655", "09/09/1975", 
            "M", "A+", "", 96.0, 187.0, true, "01/06/2024");
            addSamplePatient(patientManager, "Saraswathy Devi", "740808082345", "0177665544", "08/08/1974", 
            "F", "B+", "Eggs", 72.0, 174.0, false, "12/04/2024");
            addSamplePatient(patientManager, "Omar Bin Hussain", "730707073210", "0166554433", "07/07/1973", 
            "M", "AB-", "Soy", 98.0, 188.0, true, "05/06/2024");

// Additional patients - Group 6
            addSamplePatient(patientManager, "Tan Bee Choo", "720606061234", "0155443322", "06/06/1972", 
            "F", "O+", "", 74.0, 175.0, false, "18/04/2024");
            addSamplePatient(patientManager, "Krishnan A/L Palani", "710505053456", "0144332211", "05/05/1971", 
            "M", "A-", "Nuts", 100.0, 189.0, true, "08/06/2024");
           addSamplePatient(patientManager, "Chan Mei Yee", "700404042345", "0133221100", "04/04/1970", 
           "F", "B-", "Dairy", 76.0, 176.0, false, "25/04/2024");
           addSamplePatient(patientManager, "Ismail Bin Ibrahim", "691212123210", "0122110099", "12/12/1969", 
           "M", "AB+", "", 102.0, 190.0, true, "12/06/2024");
           addSamplePatient(patientManager, "Lim Siew Mei", "681010101234", "0111009988", "10/10/1968", 
           "F", "O-", "Wheat", 78.0, 177.0, false, "02/05/2024");

        } catch (Exception e) {
            System.out.println("Error loading sample patients: " + e.getMessage());
        }
    }
    
    private static void addSamplePatient(PatientManager patientManager, String name, String ic, 
    String contact, String dob, String gender, String bloodType, String allergies, 
    double weight, double height, boolean inQueue, String registrationDateStr) {
    
    try {
        String patientID = patientManager.generatePatientID();
        String queueID = inQueue ? patientManager.generateQueueID() : null;
        
        Patient patient = new Patient(
            patientID,
            name, 
            ic, 
            contact, 
            dob, 
            gender, 
            bloodType, 
            allergies, 
            weight, 
            height, 
            queueID,
            registrationDateStr
        );
        
        patientManager.addSamplePatient(patient, inQueue);
    } catch (IllegalArgumentException e) {
        System.err.println("Failed to add patient " + name + ": " + e.getMessage());
        // Don't increment counters for failed additions
        patientManager.rollbackCounters();
    }
}
    // Main sample consultation initializer
    public static void initializeSampleConsultations(
    ConsultationManager consultationManager,
    DoctorManager doctorManager,
    PatientManager patientManager) {

    try {
        // ===== 1. HARDCODE REQUIRED PATIENTS (P001-P008) =====
        // (If they don't exist, this creates them on the spot)
        addSamplePatient(patientManager, "Ali Bin Abu", "990101014321", "0123456789",
            "01/01/1999", "M", "A+", "Peanuts", 70.5, 170.0, true, "01/01/2023");

        addSamplePatient(patientManager, "Siti Aminah", "000202023456", "0139876543",
            "02/02/2000", "F", "O-", "", 55.0, 160.0, true, "02/01/2023");

        addSamplePatient(patientManager, "John Tan", "981010104321", "0112233445",
            "10/10/1998", "M", "B+", "Seafood", 68.0, 175.0, true, "03/01/2023");

        addSamplePatient(patientManager, "Nur Izzati", "010303035432", "0199988776",
            "03/03/2001", "F", "AB-", "", 60.0, 162.0, true, "04/01/2023");

        addSamplePatient(patientManager, "Raj Kumar", "970707074321", "0105678901",
            "07/07/1997", "M", "O+", "Dust", 72.3, 180.0, true, "05/01/2023");

        addSamplePatient(patientManager, "Lim Mei Ling", "960606062222", "0143322110",
            "06/06/1996", "F", "A-", "Lactose", 50.0, 155.0, true, "06/01/2023");

        addSamplePatient(patientManager, "Ahmad Fauzi", "950505051234", "0176543210",
            "05/05/1995", "M", "B-", "Penicillin", 80.0, 178.0, true, "07/01/2023");

        addSamplePatient(patientManager, "Sarah Chong", "940404043210", "0167890123",
            "04/04/1994", "F", "AB+", "", 58.0, 165.0, true, "08/01/2023");

        addSamplePatient(patientManager, "Hafiz Rahman", "930303033333", "0121112233",
            "03/03/1993", "M", "O+", "Shellfish", 74.0, 176.0, true, "09/01/2023");

        addSamplePatient(patientManager, "Chong Wei", "920202022222", "0185556677",
            "02/02/1992", "M", "A+", "", 65.0, 170.0, true, "10/01/2023");

        addSamplePatient(patientManager, "Amira Yusuf", "910101011111", "0178889990",
            "01/01/1991", "F", "B-", "Gluten", 59.0, 162.0, true, "11/01/2023");

        addSamplePatient(patientManager, "Michael Lee", "890909099876", "0134445566",
            "09/09/1989", "M", "AB+", "", 82.0, 185.0, true, "12/01/2023");

        addSamplePatient(patientManager, "Farah Zain", "880808088765", "0192233445",
            "08/08/1988", "F", "O-", "Nuts", 52.0, 158.0, true, "13/01/2023");

        addSamplePatient(patientManager, "Daniel Wong", "870707077654", "0123344556",
            "07/07/1987", "M", "A-", "", 75.0, 172.0, true, "14/01/2023");

        addSamplePatient(patientManager, "Aisyah Karim", "860606066543", "0166677788",
            "06/06/1986", "F", "B+", "Eggs", 54.0, 160.0, true, "15/01/2023");

        // ===== 2. ADD CONSULTATIONS (FORCE ALL 8) =====
        // Cardiology
        consultationManager.addConsultation(new Consultation(
            "C001", "D101", "P001", LocalDate.of(2025, 3, 15), LocalTime.of(10, 0),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C002", "D102", "P002", LocalDate.of(2025, 3, 18), LocalTime.of(14, 0),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C003", "D103", "P003", LocalDate.of(2025, 3, 20), LocalTime.of(11, 30),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C004", "D104", "P004", LocalDate.of(2025, 3, 22), LocalTime.of(15, 0),
            "Scheduled", "General", false, ""
        ));

        // Pediatrics
        consultationManager.addConsultation(new Consultation(
            "C005", "D201", "P005", LocalDate.of(2025, 3, 25), LocalTime.of(9, 30),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C006", "D202", "P006", LocalDate.of(2025, 3, 26), LocalTime.of(13, 0),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C007", "D203", "P007", LocalDate.of(2025, 3, 27), LocalTime.of(10, 30),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C008", "D204", "P008", LocalDate.of(2025, 3, 28), LocalTime.of(14, 0),
            "Scheduled", "General", false, ""
        ));
                
        consultationManager.addConsultation(new Consultation(
                "C009", "D301", "P009", LocalDate.of(2025, 3, 29), LocalTime.of(11, 0),
                "Scheduled", "Skin Check", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
                "C010", "D302", "P010", LocalDate.of(2025, 3, 30), LocalTime.of(15, 30),
                "Scheduled", "Skin Check", false, ""
        ));

        consultationManager.addConsultation(new Consultation(
                "C011", "D401", "P011", LocalDate.of(2025, 4, 1), LocalTime.of(9, 0),
                "Scheduled", "Bone/Joint", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
                "C012", "D402", "P012", LocalDate.of(2025, 4, 2), LocalTime.of(14, 30),
                "Scheduled", "Bone/Joint", false, ""
        ));

        consultationManager.addConsultation(new Consultation(
                "C013", "D501", "P013", LocalDate.of(2025, 4, 3), LocalTime.of(10, 15),
                "Scheduled", "Neuro", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
                "C014", "D502", "P014", LocalDate.of(2025, 4, 4), LocalTime.of(13, 45),
                "Scheduled", "Neuro", false, ""
        ));

        consultationManager.addConsultation(new Consultation(
                "C015", "D103", "P015", LocalDate.of(2025, 4, 5), LocalTime.of(11, 30),
                "Scheduled", "General Follow-up", true, "Follow-up for routine check" // General follow-up
        ));

       
    } catch (Exception e) {
        System.out.println("CRITICAL ERROR: " + e.getMessage());
     }
    }
}