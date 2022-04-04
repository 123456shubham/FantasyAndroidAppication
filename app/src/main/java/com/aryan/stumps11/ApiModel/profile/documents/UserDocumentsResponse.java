package com.aryan.stumps11.ApiModel.profile.documents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDocumentsResponse {


    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("document_status")
    @Expose
    private Boolean documentStatus;

    @SerializedName("data")
    @Expose
    private DocumentsData documentsData;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(Boolean documentStatus) {
        this.documentStatus = documentStatus;
    }


    public DocumentsData getDocumentsData() {
        return documentsData;
    }

    public void setDocumentsData(DocumentsData documentsData) {
        this.documentsData = documentsData;
    }
}
