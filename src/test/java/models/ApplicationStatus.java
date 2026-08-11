package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatus {
    private ApplicationStatusData data;
    private String requestId;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApplicationStatusData {
        private String dateofapplication;
        private String kindofapplication;
        private String statusofapplication;
    }

    public String getStatusofapplication() {
        return data != null ? data.getStatusofapplication() : null;
    }

    public String getKindofapplication() {
        return data != null ? data.getKindofapplication() : null;
    }

    public String getDateofapplication() {
        return data != null ? data.getDateofapplication() : null;
    }
}