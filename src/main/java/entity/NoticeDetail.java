package entity;
/**
 * 详细通知内容，对应notice_detail表
 * @author happyChicken
 *
 */
public class NoticeDetail {
	
	// 通知详情id
	private int noticeDetail;
	
	// 详细内容
	private String content;

	public int getNoticeDetail() {
		return noticeDetail;
	}

	public void setNoticeDetail(int noticeDetail) {
		this.noticeDetail = noticeDetail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "NoticeDetail [noticeDetail=" + noticeDetail + ", content=" + content + "]";
	}
}
