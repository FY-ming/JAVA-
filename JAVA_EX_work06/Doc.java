package JAVA_EX_work06;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.TimeZone;

public class Doc{
	private String ID;
	private String creator;
	private Timestamp timestamp;
	private String description;
	private String filename;
	
	public Doc(String ID, String creator, Timestamp timestamp, String description, String filename) {
		super();
		this.ID = ID;// 档案名
		this.creator = creator;// 文件上传人
		this.timestamp = timestamp;// 时间戳：记录文件上传时间
		this.description = description;// 文件描述
		this.filename=filename;// 文件名
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public static String TimestampToStr(Timestamp timestamp) {
		//Timestamp 转换为 String
		if (Objects.isNull(timestamp)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		if (isThirteenDigitTimestamp(timestamp.getTime())) {
			return sdf.format(new Date(timestamp.getTime()));
		}
		return sdf.format(new Date(timestamp.getTime() * 1000L));
	}
	public static boolean isThirteenDigitTimestamp(long timestamp) {
		//辅助函数，帮助实现TimestampToStr，判断timestamp是否为13位数字
		return String.valueOf(timestamp).length() == 13;
	}

}