package com.anup.webapp;

public class CurrentArtifactInfo {
	
	   public CurrentArtifactInfo(String current_release, String component, String current_artifactVersion)
	   {    	
	       this.current_release = current_release;
	       this.component = component;
	       this.current_artifactVersion = current_artifactVersion;
	   }
	   
	    private String current_release;
	    private String component;	
	    private String current_artifactVersion;
		
		public CurrentArtifactInfo() {
		}
		
		public String getcurrent_release() {
			return current_release;
		}
		public void setcurrent_release(String current_release) {
			this.current_release = current_release;
		}
		
		public String getcomponent() {
			return component;
		}
		public void setcomponent(String component) {
			this.component = component;
		}
		public String getCurrentArtifactVersion() {
			return current_artifactVersion;
		}
		public void setCurrentArtifactVersion(String current_artifactVersion) {
			this.current_artifactVersion = current_artifactVersion;
		}

}
