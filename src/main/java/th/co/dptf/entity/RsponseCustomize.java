package th.co.dptf.entity;

public class RsponseCustomize {
	private boolean success;
	private Object messege;
	
	public RsponseCustomize(){
		
	}

	/**
	 * @param success
	 * @param messege
	 */
	public RsponseCustomize(boolean success, Object messege) {
		
		this.success = success;
		this.messege = messege;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getMessege() {
		return messege;
	}

	public void setMessege(Object messege) {
		this.messege = messege;
	}
}
