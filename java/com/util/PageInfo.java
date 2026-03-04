package com.util;

public class PageInfo {

	private int currentPage;
	private int listCount;
	private int pageLimit;
	private int boardLimit;

	private int maxPage;
	private int startPage;
	private int endPage;
	private int startRow;
	private int endRow;

	public PageInfo() {
	}

	public PageInfo(int currentPage, int listCount, int pageLimit, int boardLimit) {
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;

		this.maxPage = (int) Math.ceil((double) listCount / boardLimit);
		if (maxPage == 0) {
			maxPage = 1;
		}

		this.startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		this.endPage = Math.min(startPage + pageLimit - 1, maxPage);

		this.startRow = (currentPage - 1) * boardLimit;
		this.endRow = boardLimit;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getListCount() {
		return listCount;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}
}