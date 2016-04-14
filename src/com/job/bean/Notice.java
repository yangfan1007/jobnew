package com.job.bean;

import java.io.InputStream;
import java.io.Serializable;



/**
 * ֪ͨ��Ϣʵ����
 * @version 1.0
 * @created 2012-3-21
 */
public class Notice implements Serializable {
	
	public final static String UTF8 = "UTF-8";
	public final static String NODE_ROOT = "LISTVIEW";
	
	public final static int	TYPE_ATME = 1;
	public final static int	TYPE_MESSAGE = 2;
	public final static int	TYPE_COMMENT = 3;
	public final static int	TYPE_NEWFAN = 4;

	private int atmeCount;
	private int msgCount;
	private int reviewCount;
	private int newFansCount;
	
	public int getAtmeCount() {
		return atmeCount;
	}
	public void setAtmeCount(int atmeCount) {
		this.atmeCount = atmeCount;
	}
	public int getMsgCount() {
		return msgCount;
	}
	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getNewFansCount() {
		return newFansCount;
	}
	public void setNewFansCount(int newFansCount) {
		this.newFansCount = newFansCount;
	}	
	
	public static Notice parse(InputStream inputStream)   {
		Notice notice = null;
        return notice;       
	}
}
