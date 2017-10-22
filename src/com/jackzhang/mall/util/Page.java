package com.jackzhang.mall.util;

/**
 * ��ҳ��
 * @author jackzhang
 *
 */
public class Page {

	private int totalRows; //������

	private int pageSize ; //ÿҳ��ʾ������

	private int currentPage; //��ǰҳ��

	private int totalPages; //��ҳ��

	private int startRow; //��ǰҳ�����ݿ��е���ʼ��

	public Page(int _totalRows,int _pageSize) {//����������
		totalRows = _totalRows;
		pageSize =_pageSize;
		//������ҳ��
		totalPages = totalRows / pageSize;//����������ÿҳ��ʾ������
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;//��ҳ��+1
		}

		currentPage = 1;
		startRow = 0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 *  ���ÿҳ��ʾ������
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * ��õ�ǰҳ�����ݿ��е���ʼ��
	 * @return
	 */
	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * ��һҳ
	 *
	 */
	public void first() {
		currentPage = 1;//��ǰҳ��ҳ��
		startRow = 0;
	}

	/**
	 * ǰһҳ
	 *
	 */
	public void previous() {
		if (currentPage == 1) {
			return;
		}
		currentPage--;
		startRow = (currentPage - 1) * pageSize;
	}

	/**
	 * ��һҳ
	 *
	 */
	public void next() {
		if (currentPage < totalPages) {//��ǰҳ��ҳ��С����ҳ��
			currentPage++;
		}
		startRow = (currentPage - 1) * pageSize;//��ǰҳ�����ݿ��е���ʼ��
	}

	/**
	 * ���һҳ
	 *
	 */
	public void last() {
		currentPage = totalPages;
		startRow = (currentPage - 1) * pageSize;
	}

	public void refresh(int _currentPage) {
		currentPage = _currentPage;
		if (currentPage > totalPages) {
			last();
		}
	}

}
