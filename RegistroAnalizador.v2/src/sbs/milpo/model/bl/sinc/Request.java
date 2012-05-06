package sbs.milpo.model.bl.sinc;

public class Request {


	private String httpMethod = "";
    private String codeStatus = "";
    private String userMessage = "";
    private String userMessageTech = "";
    private String ResultInfoValue = "";
    private String InfoMessage = "";
    
	/**
	 * @return the httpMethod
	 */
	public String getHttpMethod() {
		return httpMethod;
	}
	/**
	 * @param httpMethod the httpMethod to set
	 */
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	/**
	 * @return the codeStatus
	 */
	public String getCodeStatus() {
		return codeStatus;
	}
	/**
	 * @param codeStatus the codeStatus to set
	 */
	public void setCodeStatus(String codeStatus) {
		this.codeStatus = codeStatus;
	}
	/**
	 * @return the userMessage
	 */
	public String getUserMessage() {
		return userMessage;
	}
	/**
	 * @param userMessage the userMessage to set
	 */
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	/**
	 * @return the userMessageTech
	 */
	public String getUserMessageTech() {
		return userMessageTech;
	}
	/**
	 * @param userMessageTech the userMessageTech to set
	 */
	public void setUserMessageTech(String userMessageTech) {
		this.userMessageTech = userMessageTech;
	}
	/**
	 * @return the resultInfoValue
	 */
	public String getResultInfoValue() {
		return ResultInfoValue;
	}
	/**
	 * @param resultInfoValue the resultInfoValue to set
	 */
	public void setResultInfoValue(String resultInfoValue) {
		ResultInfoValue = resultInfoValue;
	}
	/**
	 * @return the infoMessage
	 */
	public String getInfoMessage() {
		return InfoMessage;
	}
	/**
	 * @param infoMessage the infoMessage to set
	 */
	public void setInfoMessage(String infoMessage) {
		InfoMessage = infoMessage;
	}
    
	

    

}
