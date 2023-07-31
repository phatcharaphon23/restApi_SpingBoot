package th.co.dptf.entity;

public class PaginationCustmize {

	/**
	 * @param pages
	 * @param page
	 * @param info
	 */
	public PaginationCustmize(int pages, int page, Object info) {
		super();
		this.pages = pages;
		this.page = page;
		this.info = info;
	}

	private int pages;
	private int page;
	private Object info;
	
	public PaginationCustmize (){
		
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}
	
	
}
