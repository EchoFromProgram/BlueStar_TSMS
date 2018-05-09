package entity;
/**
 * 详细通知内容，对应notice_detail表
 * @author happyChicken
 *
 */
public class NoticeDetail {
	
	// 通知详情id
	private Integer noticeDetailId;
	
	// 详细内容
	private String content;
	
	private String title;

	public int getNoticeDetailId() {
		return noticeDetailId;
	}

	public void setNoticeDetailId(Integer noticeDetailId) {
		this.noticeDetailId = noticeDetailId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "NoticeDetail [noticeDetailId=" + noticeDetailId + ", content=" + content + ", title=" + title + "]";
	}

	

}
