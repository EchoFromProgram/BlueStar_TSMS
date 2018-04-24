package entity;
/**
 * 详细通知内容，对应notice_detail表
 * @author happyChicken
 *
 */
public class NoticeDetail {
	
	// 通知详情id
	private int noticeDetailId;
	
	// 详细内容
	private String content;

	public int getNoticeDetailId() {
		return noticeDetailId;
	}

	public void setNoticeDetailId(int noticeDetailId) {
		this.noticeDetailId = noticeDetailId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "NoticeDetail [noticeDetailId=" + noticeDetailId + ", content=" + content + "]";
	}


}
