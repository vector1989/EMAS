package com.evmtv.epg.entity;

import com.evmtv.epg.request.BaseRequest;

public class TDbConfig  extends BaseRequest{
    /**
	 * @field serialVersionUID
	 * @field_type long
	 */
	private static final long serialVersionUID = 4993094829685865559L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_db_config.ID
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_db_config.FIp
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    private String fip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_db_config.FUser
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    private String fuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_db_config.FPasswd
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    private String fpasswd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_db_config.FBranchId
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    private Long fbranchid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_db_config.FPlayStatus
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    private Integer fplaystatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_db_config.ID
     *
     * @return the value of t_db_config.ID
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_db_config.ID
     *
     * @param id the value for t_db_config.ID
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_db_config.FIp
     *
     * @return the value of t_db_config.FIp
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public String getFip() {
        return fip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_db_config.FIp
     *
     * @param fip the value for t_db_config.FIp
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public void setFip(String fip) {
        this.fip = fip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_db_config.FUser
     *
     * @return the value of t_db_config.FUser
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public String getFuser() {
        return fuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_db_config.FUser
     *
     * @param fuser the value for t_db_config.FUser
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public void setFuser(String fuser) {
        this.fuser = fuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_db_config.FPasswd
     *
     * @return the value of t_db_config.FPasswd
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public String getFpasswd() {
        return fpasswd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_db_config.FPasswd
     *
     * @param fpasswd the value for t_db_config.FPasswd
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public void setFpasswd(String fpasswd) {
        this.fpasswd = fpasswd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_db_config.FBranchId
     *
     * @return the value of t_db_config.FBranchId
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public Long getFbranchid() {
        return fbranchid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_db_config.FBranchId
     *
     * @param fbranchid the value for t_db_config.FBranchId
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public void setFbranchid(Long fbranchid) {
        this.fbranchid = fbranchid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_db_config.FPlayStatus
     *
     * @return the value of t_db_config.FPlayStatus
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public Integer getFplaystatus() {
        return fplaystatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_db_config.FPlayStatus
     *
     * @param fplaystatus the value for t_db_config.FPlayStatus
     *
     * @mbggenerated Mon Dec 09 16:06:58 CST 2013
     */
    public void setFplaystatus(Integer fplaystatus) {
        this.fplaystatus = fplaystatus;
    }
}