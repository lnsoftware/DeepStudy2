package com.hg.mybatis.study.entity;

import java.util.Date;

public class Mail {

    /**
     * 主键id
     */
    private long id;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改时间
     */
    private Date modifyTime;
    
    /**
     * 网站id，1表示新浪，2表示QQ，3表示搜狐，4表示火狐
     */
    private int webId;
    
    /**
     * 邮箱
     */
    private String mail;
    
    /**
     * 用途
     */
    private String useFor;
    
    public Mail() {
        
    }
    
    public Mail(int webId, String mail, String useFor) {
        this.webId = webId;
        this.mail = mail;
        this.useFor = useFor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getWebId() {
        return webId;
    }

    public void setWebId(int webId) {
        this.webId = webId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    @Override
    public String toString() {
        return "MailDO [id=" + id + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", webId=" + webId + ", mail=" + mail + ", useFor=" 
                + useFor + "]";
    }
    
}