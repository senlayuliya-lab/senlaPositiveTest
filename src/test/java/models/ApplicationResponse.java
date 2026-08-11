package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse {
    private ResponseData data;  // ← поле называется data
    private String requestId;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseData {
        private int applicantid;
        private int citizenid;
        private int applicationid;
        private int merrigecertificateid;
        private int deathcertificateid;
        private int birthcertificateid;
    }

    public ResponseData getResponseData() {
        return data;
    }

}