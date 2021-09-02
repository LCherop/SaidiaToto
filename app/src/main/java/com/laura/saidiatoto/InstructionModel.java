package com.laura.saidiatoto;

public class InstructionModel {
    private String title;
    private String common_causes;
    private String symptoms;
    private String moreInfo;
    private String steps;
    //private String k ="";

    public InstructionModel(){

    }

    public InstructionModel( String common_causes,String title,String moreInfo, String steps, String symptoms) {
        this.title = title;
        this.common_causes = common_causes;
        this.symptoms = symptoms;
        this.steps = steps;
        this.moreInfo = moreInfo;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommon_causes() {
        return common_causes;
    }

    public void setCommon_causes(String common_causes) {
        this.common_causes = common_causes;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }



}
