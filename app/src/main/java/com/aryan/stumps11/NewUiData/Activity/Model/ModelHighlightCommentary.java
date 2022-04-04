package com.aryan.stumps11.NewUiData.Activity.Model;

public class ModelHighlightCommentary {
    String overNo,runType,commentary;

    public ModelHighlightCommentary(String overNo, String runType, String commentary) {
        this.overNo = overNo;
        this.runType = runType;
        this.commentary = commentary;
    }

    public String getOverNo() {
        return overNo;
    }

    public void setOverNo(String overNo) {
        this.overNo = overNo;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
