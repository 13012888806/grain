package com.xinlin.app.entity.pojo;

import java.io.Serializable;

/**
 * 文件上传信息，有关实体
 * 
 * @author jxq
 * @date	2013-10-21
 */
public class FileLoad implements Serializable{

	private static final long serialVersionUID = 121232132133L;
	
    private String fileid;

    private String filename;

    private String filemark;

    private byte[] blobfile;

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilemark() {
        return filemark;
    }

    public void setFilemark(String filemark) {
        this.filemark = filemark;
    }

    public byte[] getBlobfile() {
        return blobfile;
    }

    public void setBlobfile(byte[] blobfile) {
        this.blobfile = blobfile;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileid == null) ? 0 : fileid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileLoad other = (FileLoad) obj;
		if (fileid == null) {
			if (other.fileid != null)
				return false;
		} else if (!fileid.equals(other.fileid))
			return false;
		return true;
	}
    
    
    
}