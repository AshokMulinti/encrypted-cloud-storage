package com.Veri.bean;

public class UploadBean {
	private String filename, type, content, path, size, encrypt, decrypt;
	private String dataowner;
	
	private String key;
	
	
	
	
	public String getDataowner() {
		return dataowner;
	}
	public void setDataowner(String dataowner) {
		this.dataowner = dataowner;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilename(){
		return filename;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	
	public void setPath(String path){
		this.path = path;  
	}
	public String getPath() {
		return path;
	}
	
	public void setSize(String size){
		this.size = size;
	}
	public String getSize() {
		return size;
	}
	
	
	public String getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	public String getDecrypt() {
		return decrypt;
	}
	public void setDecrypt(String decrypt) {
		this.decrypt = decrypt;
	}
	
	

}
