package com.anup.webapp;

public class ReleaseArtifactInfo {
	
   public ReleaseArtifactInfo(String release, String component, String certified_artifactVersion, String current_artifactVersion)
   {    	
       this.release = release;
       this.component = component;
       this.certified_artifactVersion = certified_artifactVersion;
       this.current_artifactVersion = current_artifactVersion;

   }
    
    private String release;
    private String component;	
    private String certified_artifactVersion;
    private String current_artifactVersion;
    private String qa_artifactVersion;
    private String stage_artifactVersion;
    private String prod_artifactVersion;
	
	public ReleaseArtifactInfo() {
	}
	
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getCertifiedArtifactVersion() {
		return certified_artifactVersion;
	}
	public void setCertifiedArtifactVersion(String certified_artifactVersion) {
		this.certified_artifactVersion = certified_artifactVersion;
	}
	public String getCurrentArtifactVersion() {
		return current_artifactVersion;
	}
	public void setCurrentArtifactVersion(String current_artifactVersion) {
		this.current_artifactVersion = current_artifactVersion;
	}
	public String getQAversion() {
		return qa_artifactVersion;
	}
	public void setQAVersion(String qa_artifactVersion) {
		this.qa_artifactVersion = qa_artifactVersion;
	}
	public String getStageversion() {
		return stage_artifactVersion;
	}
	public void setStageVersion(String stage_artifactVersion) {
		this.stage_artifactVersion = stage_artifactVersion;
	}
	public String getProdversion() {
		return prod_artifactVersion;
	}
	public void setProdVersion(String prod_artifactVersion) {
		this.prod_artifactVersion = prod_artifactVersion;
	}

	

}
