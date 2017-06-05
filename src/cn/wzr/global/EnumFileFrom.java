package cn.wzr.global;

public final class EnumFileFrom {
	
	public enum FileFrom{
		
		DISK_FILE("本地文件"),
		UPLOAD_FILE("上传文件"),
		WEB_URL("网络文件");
		
		private String description;
		
		private FileFrom(String description) {
			this.description=description;
		}
		
		@Override
		public String toString(){
			return this.description;
		}
	}
}
