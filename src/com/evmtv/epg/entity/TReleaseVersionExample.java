package com.evmtv.epg.entity;

import java.util.ArrayList;
import java.util.List;

import com.evmtv.epg.request.BaseRequest;

public class TReleaseVersionExample extends BaseRequest{
    /**
	 * @field serialVersionUID
	 * @field_type long
	 */
	private static final long serialVersionUID = -4653670155254245781L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    public TReleaseVersionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFversionIsNull() {
            addCriterion("FVersion is null");
            return (Criteria) this;
        }

        public Criteria andFversionIsNotNull() {
            addCriterion("FVersion is not null");
            return (Criteria) this;
        }

        public Criteria andFversionEqualTo(String value) {
            addCriterion("FVersion =", value, "fversion");
            return (Criteria) this;
        }

        public Criteria andFversionNotEqualTo(String value) {
            addCriterion("FVersion <>", value, "fversion");
            return (Criteria) this;
        }

        public Criteria andFversionGreaterThan(String value) {
            addCriterion("FVersion >", value, "fversion");
            return (Criteria) this;
        }

        public Criteria andFversionGreaterThanOrEqualTo(String value) {
            addCriterion("FVersion >=", value, "fversion");
            return (Criteria) this;
        }

        public Criteria andFversionLessThan(String value) {
            addCriterion("FVersion <", value, "fversion");
            return (Criteria) this;
        }

        public Criteria andFversionLessThanOrEqualTo(String value) {
            addCriterion("FVersion <=", value, "fversion");
            return (Criteria) this;
        }

        public Criteria andFversionLike(String value) {
            addCriterion("FVersion like", value, "fversion");
            return (Criteria) this;
        }

        public Criteria andFversionNotLike(String value) {
            addCriterion("FVersion not like", value, "fversion");
            return (Criteria) this;
        }

        public Criteria andFversionIn(List<String> values) {
            addCriterion("FVersion in", values, "fversion");
            return (Criteria) this;
        }

        public Criteria andFversionNotIn(List<String> values) {
            addCriterion("FVersion not in", values, "fversion");
            return (Criteria) this;
        }

        public Criteria andFversionBetween(String value1, String value2) {
            addCriterion("FVersion between", value1, value2, "fversion");
            return (Criteria) this;
        }

        public Criteria andFversionNotBetween(String value1, String value2) {
            addCriterion("FVersion not between", value1, value2, "fversion");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeIsNull() {
            addCriterion("FCreateTime is null");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeIsNotNull() {
            addCriterion("FCreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeEqualTo(String value) {
            addCriterion("FCreateTime =", value, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeNotEqualTo(String value) {
            addCriterion("FCreateTime <>", value, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeGreaterThan(String value) {
            addCriterion("FCreateTime >", value, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("FCreateTime >=", value, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeLessThan(String value) {
            addCriterion("FCreateTime <", value, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeLessThanOrEqualTo(String value) {
            addCriterion("FCreateTime <=", value, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeLike(String value) {
            addCriterion("FCreateTime like", value, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeNotLike(String value) {
            addCriterion("FCreateTime not like", value, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeIn(List<String> values) {
            addCriterion("FCreateTime in", values, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeNotIn(List<String> values) {
            addCriterion("FCreateTime not in", values, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeBetween(String value1, String value2) {
            addCriterion("FCreateTime between", value1, value2, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFcreatetimeNotBetween(String value1, String value2) {
            addCriterion("FCreateTime not between", value1, value2, "fcreatetime");
            return (Criteria) this;
        }

        public Criteria andFbranchidIsNull() {
            addCriterion("FBranchId is null");
            return (Criteria) this;
        }

        public Criteria andFbranchidIsNotNull() {
            addCriterion("FBranchId is not null");
            return (Criteria) this;
        }

        public Criteria andFbranchidEqualTo(Long value) {
            addCriterion("FBranchId =", value, "fbranchid");
            return (Criteria) this;
        }

        public Criteria andFbranchidNotEqualTo(Long value) {
            addCriterion("FBranchId <>", value, "fbranchid");
            return (Criteria) this;
        }

        public Criteria andFbranchidGreaterThan(Long value) {
            addCriterion("FBranchId >", value, "fbranchid");
            return (Criteria) this;
        }

        public Criteria andFbranchidGreaterThanOrEqualTo(Long value) {
            addCriterion("FBranchId >=", value, "fbranchid");
            return (Criteria) this;
        }

        public Criteria andFbranchidLessThan(Long value) {
            addCriterion("FBranchId <", value, "fbranchid");
            return (Criteria) this;
        }

        public Criteria andFbranchidLessThanOrEqualTo(Long value) {
            addCriterion("FBranchId <=", value, "fbranchid");
            return (Criteria) this;
        }

        public Criteria andFbranchidIn(List<Long> values) {
            addCriterion("FBranchId in", values, "fbranchid");
            return (Criteria) this;
        }

        public Criteria andFbranchidNotIn(List<Long> values) {
            addCriterion("FBranchId not in", values, "fbranchid");
            return (Criteria) this;
        }

        public Criteria andFbranchidBetween(Long value1, Long value2) {
            addCriterion("FBranchId between", value1, value2, "fbranchid");
            return (Criteria) this;
        }

        public Criteria andFbranchidNotBetween(Long value1, Long value2) {
            addCriterion("FBranchId not between", value1, value2, "fbranchid");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridIsNull() {
            addCriterion("FCreateUserId is null");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridIsNotNull() {
            addCriterion("FCreateUserId is not null");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridEqualTo(Long value) {
            addCriterion("FCreateUserId =", value, "fcreateuserid");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridNotEqualTo(Long value) {
            addCriterion("FCreateUserId <>", value, "fcreateuserid");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridGreaterThan(Long value) {
            addCriterion("FCreateUserId >", value, "fcreateuserid");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridGreaterThanOrEqualTo(Long value) {
            addCriterion("FCreateUserId >=", value, "fcreateuserid");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridLessThan(Long value) {
            addCriterion("FCreateUserId <", value, "fcreateuserid");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridLessThanOrEqualTo(Long value) {
            addCriterion("FCreateUserId <=", value, "fcreateuserid");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridIn(List<Long> values) {
            addCriterion("FCreateUserId in", values, "fcreateuserid");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridNotIn(List<Long> values) {
            addCriterion("FCreateUserId not in", values, "fcreateuserid");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridBetween(Long value1, Long value2) {
            addCriterion("FCreateUserId between", value1, value2, "fcreateuserid");
            return (Criteria) this;
        }

        public Criteria andFcreateuseridNotBetween(Long value1, Long value2) {
            addCriterion("FCreateUserId not between", value1, value2, "fcreateuserid");
            return (Criteria) this;
        }

        public Criteria andFdescIsNull() {
            addCriterion("FDesc is null");
            return (Criteria) this;
        }

        public Criteria andFdescIsNotNull() {
            addCriterion("FDesc is not null");
            return (Criteria) this;
        }

        public Criteria andFdescEqualTo(String value) {
            addCriterion("FDesc =", value, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdescNotEqualTo(String value) {
            addCriterion("FDesc <>", value, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdescGreaterThan(String value) {
            addCriterion("FDesc >", value, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdescGreaterThanOrEqualTo(String value) {
            addCriterion("FDesc >=", value, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdescLessThan(String value) {
            addCriterion("FDesc <", value, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdescLessThanOrEqualTo(String value) {
            addCriterion("FDesc <=", value, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdescLike(String value) {
            addCriterion("FDesc like", value, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdescNotLike(String value) {
            addCriterion("FDesc not like", value, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdescIn(List<String> values) {
            addCriterion("FDesc in", values, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdescNotIn(List<String> values) {
            addCriterion("FDesc not in", values, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdescBetween(String value1, String value2) {
            addCriterion("FDesc between", value1, value2, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdescNotBetween(String value1, String value2) {
            addCriterion("FDesc not between", value1, value2, "fdesc");
            return (Criteria) this;
        }

        public Criteria andFdayversionIsNull() {
            addCriterion("FDayVersion is null");
            return (Criteria) this;
        }

        public Criteria andFdayversionIsNotNull() {
            addCriterion("FDayVersion is not null");
            return (Criteria) this;
        }

        public Criteria andFdayversionEqualTo(Integer value) {
            addCriterion("FDayVersion =", value, "fdayversion");
            return (Criteria) this;
        }

        public Criteria andFdayversionNotEqualTo(Integer value) {
            addCriterion("FDayVersion <>", value, "fdayversion");
            return (Criteria) this;
        }

        public Criteria andFdayversionGreaterThan(Integer value) {
            addCriterion("FDayVersion >", value, "fdayversion");
            return (Criteria) this;
        }

        public Criteria andFdayversionGreaterThanOrEqualTo(Integer value) {
            addCriterion("FDayVersion >=", value, "fdayversion");
            return (Criteria) this;
        }

        public Criteria andFdayversionLessThan(Integer value) {
            addCriterion("FDayVersion <", value, "fdayversion");
            return (Criteria) this;
        }

        public Criteria andFdayversionLessThanOrEqualTo(Integer value) {
            addCriterion("FDayVersion <=", value, "fdayversion");
            return (Criteria) this;
        }

        public Criteria andFdayversionIn(List<Integer> values) {
            addCriterion("FDayVersion in", values, "fdayversion");
            return (Criteria) this;
        }

        public Criteria andFdayversionNotIn(List<Integer> values) {
            addCriterion("FDayVersion not in", values, "fdayversion");
            return (Criteria) this;
        }

        public Criteria andFdayversionBetween(Integer value1, Integer value2) {
            addCriterion("FDayVersion between", value1, value2, "fdayversion");
            return (Criteria) this;
        }

        public Criteria andFdayversionNotBetween(Integer value1, Integer value2) {
            addCriterion("FDayVersion not between", value1, value2, "fdayversion");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeIsNull() {
            addCriterion("FUpdateTime is null");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeIsNotNull() {
            addCriterion("FUpdateTime is not null");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeEqualTo(String value) {
            addCriterion("FUpdateTime =", value, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeNotEqualTo(String value) {
            addCriterion("FUpdateTime <>", value, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeGreaterThan(String value) {
            addCriterion("FUpdateTime >", value, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("FUpdateTime >=", value, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeLessThan(String value) {
            addCriterion("FUpdateTime <", value, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeLessThanOrEqualTo(String value) {
            addCriterion("FUpdateTime <=", value, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeLike(String value) {
            addCriterion("FUpdateTime like", value, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeNotLike(String value) {
            addCriterion("FUpdateTime not like", value, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeIn(List<String> values) {
            addCriterion("FUpdateTime in", values, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeNotIn(List<String> values) {
            addCriterion("FUpdateTime not in", values, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeBetween(String value1, String value2) {
            addCriterion("FUpdateTime between", value1, value2, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFupdatetimeNotBetween(String value1, String value2) {
            addCriterion("FUpdateTime not between", value1, value2, "fupdatetime");
            return (Criteria) this;
        }

        public Criteria andFdefinitionIsNull() {
            addCriterion("FDefinition is null");
            return (Criteria) this;
        }

        public Criteria andFdefinitionIsNotNull() {
            addCriterion("FDefinition is not null");
            return (Criteria) this;
        }

        public Criteria andFdefinitionEqualTo(String value) {
            addCriterion("FDefinition =", value, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFdefinitionNotEqualTo(String value) {
            addCriterion("FDefinition <>", value, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFdefinitionGreaterThan(String value) {
            addCriterion("FDefinition >", value, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFdefinitionGreaterThanOrEqualTo(String value) {
            addCriterion("FDefinition >=", value, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFdefinitionLessThan(String value) {
            addCriterion("FDefinition <", value, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFdefinitionLessThanOrEqualTo(String value) {
            addCriterion("FDefinition <=", value, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFdefinitionLike(String value) {
            addCriterion("FDefinition like", value, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFdefinitionNotLike(String value) {
            addCriterion("FDefinition not like", value, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFdefinitionIn(List<String> values) {
            addCriterion("FDefinition in", values, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFdefinitionNotIn(List<String> values) {
            addCriterion("FDefinition not in", values, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFdefinitionBetween(String value1, String value2) {
            addCriterion("FDefinition between", value1, value2, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFdefinitionNotBetween(String value1, String value2) {
            addCriterion("FDefinition not between", value1, value2, "fdefinition");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidIsNull() {
            addCriterion("FProvReleaseVersionId is null");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidIsNotNull() {
            addCriterion("FProvReleaseVersionId is not null");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidEqualTo(Long value) {
            addCriterion("FProvReleaseVersionId =", value, "fprovreleaseversionid");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidNotEqualTo(Long value) {
            addCriterion("FProvReleaseVersionId <>", value, "fprovreleaseversionid");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidGreaterThan(Long value) {
            addCriterion("FProvReleaseVersionId >", value, "fprovreleaseversionid");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidGreaterThanOrEqualTo(Long value) {
            addCriterion("FProvReleaseVersionId >=", value, "fprovreleaseversionid");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidLessThan(Long value) {
            addCriterion("FProvReleaseVersionId <", value, "fprovreleaseversionid");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidLessThanOrEqualTo(Long value) {
            addCriterion("FProvReleaseVersionId <=", value, "fprovreleaseversionid");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidIn(List<Long> values) {
            addCriterion("FProvReleaseVersionId in", values, "fprovreleaseversionid");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidNotIn(List<Long> values) {
            addCriterion("FProvReleaseVersionId not in", values, "fprovreleaseversionid");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidBetween(Long value1, Long value2) {
            addCriterion("FProvReleaseVersionId between", value1, value2, "fprovreleaseversionid");
            return (Criteria) this;
        }

        public Criteria andFprovreleaseversionidNotBetween(Long value1, Long value2) {
            addCriterion("FProvReleaseVersionId not between", value1, value2, "fprovreleaseversionid");
            return (Criteria) this;
        }

        public Criteria andFstatusIsNull() {
            addCriterion("FStatus is null");
            return (Criteria) this;
        }

        public Criteria andFstatusIsNotNull() {
            addCriterion("FStatus is not null");
            return (Criteria) this;
        }

        public Criteria andFstatusEqualTo(Integer value) {
            addCriterion("FStatus =", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusNotEqualTo(Integer value) {
            addCriterion("FStatus <>", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusGreaterThan(Integer value) {
            addCriterion("FStatus >", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("FStatus >=", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusLessThan(Integer value) {
            addCriterion("FStatus <", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusLessThanOrEqualTo(Integer value) {
            addCriterion("FStatus <=", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusIn(List<Integer> values) {
            addCriterion("FStatus in", values, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusNotIn(List<Integer> values) {
            addCriterion("FStatus not in", values, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusBetween(Integer value1, Integer value2) {
            addCriterion("FStatus between", value1, value2, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("FStatus not between", value1, value2, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitIsNull() {
            addCriterion("FIsFinishedEdit is null");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitIsNotNull() {
            addCriterion("FIsFinishedEdit is not null");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitEqualTo(Integer value) {
            addCriterion("FIsFinishedEdit =", value, "fisfinishededit");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitNotEqualTo(Integer value) {
            addCriterion("FIsFinishedEdit <>", value, "fisfinishededit");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitGreaterThan(Integer value) {
            addCriterion("FIsFinishedEdit >", value, "fisfinishededit");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitGreaterThanOrEqualTo(Integer value) {
            addCriterion("FIsFinishedEdit >=", value, "fisfinishededit");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitLessThan(Integer value) {
            addCriterion("FIsFinishedEdit <", value, "fisfinishededit");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitLessThanOrEqualTo(Integer value) {
            addCriterion("FIsFinishedEdit <=", value, "fisfinishededit");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitIn(List<Integer> values) {
            addCriterion("FIsFinishedEdit in", values, "fisfinishededit");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitNotIn(List<Integer> values) {
            addCriterion("FIsFinishedEdit not in", values, "fisfinishededit");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitBetween(Integer value1, Integer value2) {
            addCriterion("FIsFinishedEdit between", value1, value2, "fisfinishededit");
            return (Criteria) this;
        }

        public Criteria andFisfinishededitNotBetween(Integer value1, Integer value2) {
            addCriterion("FIsFinishedEdit not between", value1, value2, "fisfinishededit");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidIsNull() {
            addCriterion("FPreviousVersionId is null");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidIsNotNull() {
            addCriterion("FPreviousVersionId is not null");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidEqualTo(Long value) {
            addCriterion("FPreviousVersionId =", value, "fpreviousversionid");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidNotEqualTo(Long value) {
            addCriterion("FPreviousVersionId <>", value, "fpreviousversionid");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidGreaterThan(Long value) {
            addCriterion("FPreviousVersionId >", value, "fpreviousversionid");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidGreaterThanOrEqualTo(Long value) {
            addCriterion("FPreviousVersionId >=", value, "fpreviousversionid");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidLessThan(Long value) {
            addCriterion("FPreviousVersionId <", value, "fpreviousversionid");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidLessThanOrEqualTo(Long value) {
            addCriterion("FPreviousVersionId <=", value, "fpreviousversionid");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidIn(List<Long> values) {
            addCriterion("FPreviousVersionId in", values, "fpreviousversionid");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidNotIn(List<Long> values) {
            addCriterion("FPreviousVersionId not in", values, "fpreviousversionid");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidBetween(Long value1, Long value2) {
            addCriterion("FPreviousVersionId between", value1, value2, "fpreviousversionid");
            return (Criteria) this;
        }

        public Criteria andFpreviousversionidNotBetween(Long value1, Long value2) {
            addCriterion("FPreviousVersionId not between", value1, value2, "fpreviousversionid");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_release_version
     *
     * @mbggenerated do_not_delete_during_merge Fri Dec 06 16:38:39 CST 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_release_version
     *
     * @mbggenerated Fri Dec 06 16:38:39 CST 2013
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}