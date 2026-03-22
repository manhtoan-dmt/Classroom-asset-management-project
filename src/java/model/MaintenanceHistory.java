package model;
import java.sql.Timestamp;
public class MaintenanceHistory {

    private Timestamp date;
    private String problem;
    private String status;
    private String reportedBy;
    private String technician;
    private String completedAt;

    public MaintenanceHistory() {
    }

    public MaintenanceHistory(Timestamp date, String problem, String status, String reportedBy, String technician, String completedAt) {
        this.date = date;
        this.problem = problem;
        this.status = status;
        this.reportedBy = reportedBy;
        this.technician = technician;
        this.completedAt = completedAt;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    
}
