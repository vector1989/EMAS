package com.evmtv.epg.entity;

import java.util.ArrayList;
import java.util.List;

public class TContractAdvResourceExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    public TContractAdvResourceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
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
     * This method corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
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

        public Criteria andFresourceidIsNull() {
            addCriterion("FResourceId is null");
            return (Criteria) this;
        }

        public Criteria andFresourceidIsNotNull() {
            addCriterion("FResourceId is not null");
            return (Criteria) this;
        }

        public Criteria andFresourceidEqualTo(Long value) {
            addCriterion("FResourceId =", value, "fresourceid");
            return (Criteria) this;
        }

        public Criteria andFresourceidNotEqualTo(Long value) {
            addCriterion("FResourceId <>", value, "fresourceid");
            return (Criteria) this;
        }

        public Criteria andFresourceidGreaterThan(Long value) {
            addCriterion("FResourceId >", value, "fresourceid");
            return (Criteria) this;
        }

        public Criteria andFresourceidGreaterThanOrEqualTo(Long value) {
            addCriterion("FResourceId >=", value, "fresourceid");
            return (Criteria) this;
        }

        public Criteria andFresourceidLessThan(Long value) {
            addCriterion("FResourceId <", value, "fresourceid");
            return (Criteria) this;
        }

        public Criteria andFresourceidLessThanOrEqualTo(Long value) {
            addCriterion("FResourceId <=", value, "fresourceid");
            return (Criteria) this;
        }

        public Criteria andFresourceidIn(List<Long> values) {
            addCriterion("FResourceId in", values, "fresourceid");
            return (Criteria) this;
        }

        public Criteria andFresourceidNotIn(List<Long> values) {
            addCriterion("FResourceId not in", values, "fresourceid");
            return (Criteria) this;
        }

        public Criteria andFresourceidBetween(Long value1, Long value2) {
            addCriterion("FResourceId between", value1, value2, "fresourceid");
            return (Criteria) this;
        }

        public Criteria andFresourceidNotBetween(Long value1, Long value2) {
            addCriterion("FResourceId not between", value1, value2, "fresourceid");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidIsNull() {
            addCriterion("FContractAdvId is null");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidIsNotNull() {
            addCriterion("FContractAdvId is not null");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidEqualTo(Long value) {
            addCriterion("FContractAdvId =", value, "fcontractadvid");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidNotEqualTo(Long value) {
            addCriterion("FContractAdvId <>", value, "fcontractadvid");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidGreaterThan(Long value) {
            addCriterion("FContractAdvId >", value, "fcontractadvid");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidGreaterThanOrEqualTo(Long value) {
            addCriterion("FContractAdvId >=", value, "fcontractadvid");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidLessThan(Long value) {
            addCriterion("FContractAdvId <", value, "fcontractadvid");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidLessThanOrEqualTo(Long value) {
            addCriterion("FContractAdvId <=", value, "fcontractadvid");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidIn(List<Long> values) {
            addCriterion("FContractAdvId in", values, "fcontractadvid");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidNotIn(List<Long> values) {
            addCriterion("FContractAdvId not in", values, "fcontractadvid");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidBetween(Long value1, Long value2) {
            addCriterion("FContractAdvId between", value1, value2, "fcontractadvid");
            return (Criteria) this;
        }

        public Criteria andFcontractadvidNotBetween(Long value1, Long value2) {
            addCriterion("FContractAdvId not between", value1, value2, "fcontractadvid");
            return (Criteria) this;
        }

        public Criteria andFeditedIsNull() {
            addCriterion("FEdited is null");
            return (Criteria) this;
        }

        public Criteria andFeditedIsNotNull() {
            addCriterion("FEdited is not null");
            return (Criteria) this;
        }

        public Criteria andFeditedEqualTo(String value) {
            addCriterion("FEdited =", value, "fedited");
            return (Criteria) this;
        }

        public Criteria andFeditedNotEqualTo(String value) {
            addCriterion("FEdited <>", value, "fedited");
            return (Criteria) this;
        }

        public Criteria andFeditedGreaterThan(String value) {
            addCriterion("FEdited >", value, "fedited");
            return (Criteria) this;
        }

        public Criteria andFeditedGreaterThanOrEqualTo(String value) {
            addCriterion("FEdited >=", value, "fedited");
            return (Criteria) this;
        }

        public Criteria andFeditedLessThan(String value) {
            addCriterion("FEdited <", value, "fedited");
            return (Criteria) this;
        }

        public Criteria andFeditedLessThanOrEqualTo(String value) {
            addCriterion("FEdited <=", value, "fedited");
            return (Criteria) this;
        }

        public Criteria andFeditedLike(String value) {
            addCriterion("FEdited like", value, "fedited");
            return (Criteria) this;
        }

        public Criteria andFeditedNotLike(String value) {
            addCriterion("FEdited not like", value, "fedited");
            return (Criteria) this;
        }

        public Criteria andFeditedIn(List<String> values) {
            addCriterion("FEdited in", values, "fedited");
            return (Criteria) this;
        }

        public Criteria andFeditedNotIn(List<String> values) {
            addCriterion("FEdited not in", values, "fedited");
            return (Criteria) this;
        }

        public Criteria andFeditedBetween(String value1, String value2) {
            addCriterion("FEdited between", value1, value2, "fedited");
            return (Criteria) this;
        }

        public Criteria andFeditedNotBetween(String value1, String value2) {
            addCriterion("FEdited not between", value1, value2, "fedited");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidIsNull() {
            addCriterion("FOriginalResourceId is null");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidIsNotNull() {
            addCriterion("FOriginalResourceId is not null");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidEqualTo(Long value) {
            addCriterion("FOriginalResourceId =", value, "foriginalresourceid");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidNotEqualTo(Long value) {
            addCriterion("FOriginalResourceId <>", value, "foriginalresourceid");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidGreaterThan(Long value) {
            addCriterion("FOriginalResourceId >", value, "foriginalresourceid");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidGreaterThanOrEqualTo(Long value) {
            addCriterion("FOriginalResourceId >=", value, "foriginalresourceid");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidLessThan(Long value) {
            addCriterion("FOriginalResourceId <", value, "foriginalresourceid");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidLessThanOrEqualTo(Long value) {
            addCriterion("FOriginalResourceId <=", value, "foriginalresourceid");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidIn(List<Long> values) {
            addCriterion("FOriginalResourceId in", values, "foriginalresourceid");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidNotIn(List<Long> values) {
            addCriterion("FOriginalResourceId not in", values, "foriginalresourceid");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidBetween(Long value1, Long value2) {
            addCriterion("FOriginalResourceId between", value1, value2, "foriginalresourceid");
            return (Criteria) this;
        }

        public Criteria andForiginalresourceidNotBetween(Long value1, Long value2) {
            addCriterion("FOriginalResourceId not between", value1, value2, "foriginalresourceid");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceIsNull() {
            addCriterion("FIsOriginalResource is null");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceIsNotNull() {
            addCriterion("FIsOriginalResource is not null");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceEqualTo(String value) {
            addCriterion("FIsOriginalResource =", value, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceNotEqualTo(String value) {
            addCriterion("FIsOriginalResource <>", value, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceGreaterThan(String value) {
            addCriterion("FIsOriginalResource >", value, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceGreaterThanOrEqualTo(String value) {
            addCriterion("FIsOriginalResource >=", value, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceLessThan(String value) {
            addCriterion("FIsOriginalResource <", value, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceLessThanOrEqualTo(String value) {
            addCriterion("FIsOriginalResource <=", value, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceLike(String value) {
            addCriterion("FIsOriginalResource like", value, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceNotLike(String value) {
            addCriterion("FIsOriginalResource not like", value, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceIn(List<String> values) {
            addCriterion("FIsOriginalResource in", values, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceNotIn(List<String> values) {
            addCriterion("FIsOriginalResource not in", values, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceBetween(String value1, String value2) {
            addCriterion("FIsOriginalResource between", value1, value2, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFisoriginalresourceNotBetween(String value1, String value2) {
            addCriterion("FIsOriginalResource not between", value1, value2, "fisoriginalresource");
            return (Criteria) this;
        }

        public Criteria andFsourceidIsNull() {
            addCriterion("FSourceId is null");
            return (Criteria) this;
        }

        public Criteria andFsourceidIsNotNull() {
            addCriterion("FSourceId is not null");
            return (Criteria) this;
        }

        public Criteria andFsourceidEqualTo(Long value) {
            addCriterion("FSourceId =", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidNotEqualTo(Long value) {
            addCriterion("FSourceId <>", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidGreaterThan(Long value) {
            addCriterion("FSourceId >", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidGreaterThanOrEqualTo(Long value) {
            addCriterion("FSourceId >=", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidLessThan(Long value) {
            addCriterion("FSourceId <", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidLessThanOrEqualTo(Long value) {
            addCriterion("FSourceId <=", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidIn(List<Long> values) {
            addCriterion("FSourceId in", values, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidNotIn(List<Long> values) {
            addCriterion("FSourceId not in", values, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidBetween(Long value1, Long value2) {
            addCriterion("FSourceId between", value1, value2, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidNotBetween(Long value1, Long value2) {
            addCriterion("FSourceId not between", value1, value2, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFisusingIsNull() {
            addCriterion("FIsUsing is null");
            return (Criteria) this;
        }

        public Criteria andFisusingIsNotNull() {
            addCriterion("FIsUsing is not null");
            return (Criteria) this;
        }

        public Criteria andFisusingEqualTo(String value) {
            addCriterion("FIsUsing =", value, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFisusingNotEqualTo(String value) {
            addCriterion("FIsUsing <>", value, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFisusingGreaterThan(String value) {
            addCriterion("FIsUsing >", value, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFisusingGreaterThanOrEqualTo(String value) {
            addCriterion("FIsUsing >=", value, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFisusingLessThan(String value) {
            addCriterion("FIsUsing <", value, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFisusingLessThanOrEqualTo(String value) {
            addCriterion("FIsUsing <=", value, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFisusingLike(String value) {
            addCriterion("FIsUsing like", value, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFisusingNotLike(String value) {
            addCriterion("FIsUsing not like", value, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFisusingIn(List<String> values) {
            addCriterion("FIsUsing in", values, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFisusingNotIn(List<String> values) {
            addCriterion("FIsUsing not in", values, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFisusingBetween(String value1, String value2) {
            addCriterion("FIsUsing between", value1, value2, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFisusingNotBetween(String value1, String value2) {
            addCriterion("FIsUsing not between", value1, value2, "fisusing");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeIsNull() {
            addCriterion("FUseStartTime is null");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeIsNotNull() {
            addCriterion("FUseStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeEqualTo(String value) {
            addCriterion("FUseStartTime =", value, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeNotEqualTo(String value) {
            addCriterion("FUseStartTime <>", value, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeGreaterThan(String value) {
            addCriterion("FUseStartTime >", value, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeGreaterThanOrEqualTo(String value) {
            addCriterion("FUseStartTime >=", value, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeLessThan(String value) {
            addCriterion("FUseStartTime <", value, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeLessThanOrEqualTo(String value) {
            addCriterion("FUseStartTime <=", value, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeLike(String value) {
            addCriterion("FUseStartTime like", value, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeNotLike(String value) {
            addCriterion("FUseStartTime not like", value, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeIn(List<String> values) {
            addCriterion("FUseStartTime in", values, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeNotIn(List<String> values) {
            addCriterion("FUseStartTime not in", values, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeBetween(String value1, String value2) {
            addCriterion("FUseStartTime between", value1, value2, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFusestarttimeNotBetween(String value1, String value2) {
            addCriterion("FUseStartTime not between", value1, value2, "fusestarttime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeIsNull() {
            addCriterion("FUseEndTime is null");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeIsNotNull() {
            addCriterion("FUseEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeEqualTo(String value) {
            addCriterion("FUseEndTime =", value, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeNotEqualTo(String value) {
            addCriterion("FUseEndTime <>", value, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeGreaterThan(String value) {
            addCriterion("FUseEndTime >", value, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeGreaterThanOrEqualTo(String value) {
            addCriterion("FUseEndTime >=", value, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeLessThan(String value) {
            addCriterion("FUseEndTime <", value, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeLessThanOrEqualTo(String value) {
            addCriterion("FUseEndTime <=", value, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeLike(String value) {
            addCriterion("FUseEndTime like", value, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeNotLike(String value) {
            addCriterion("FUseEndTime not like", value, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeIn(List<String> values) {
            addCriterion("FUseEndTime in", values, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeNotIn(List<String> values) {
            addCriterion("FUseEndTime not in", values, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeBetween(String value1, String value2) {
            addCriterion("FUseEndTime between", value1, value2, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFuseendtimeNotBetween(String value1, String value2) {
            addCriterion("FUseEndTime not between", value1, value2, "fuseendtime");
            return (Criteria) this;
        }

        public Criteria andFnodeidIsNull() {
            addCriterion("FNodeId is null");
            return (Criteria) this;
        }

        public Criteria andFnodeidIsNotNull() {
            addCriterion("FNodeId is not null");
            return (Criteria) this;
        }

        public Criteria andFnodeidEqualTo(Long value) {
            addCriterion("FNodeId =", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidNotEqualTo(Long value) {
            addCriterion("FNodeId <>", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidGreaterThan(Long value) {
            addCriterion("FNodeId >", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidGreaterThanOrEqualTo(Long value) {
            addCriterion("FNodeId >=", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidLessThan(Long value) {
            addCriterion("FNodeId <", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidLessThanOrEqualTo(Long value) {
            addCriterion("FNodeId <=", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidIn(List<Long> values) {
            addCriterion("FNodeId in", values, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidNotIn(List<Long> values) {
            addCriterion("FNodeId not in", values, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidBetween(Long value1, Long value2) {
            addCriterion("FNodeId between", value1, value2, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidNotBetween(Long value1, Long value2) {
            addCriterion("FNodeId not between", value1, value2, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andForderIsNull() {
            addCriterion("FOrder is null");
            return (Criteria) this;
        }

        public Criteria andForderIsNotNull() {
            addCriterion("FOrder is not null");
            return (Criteria) this;
        }

        public Criteria andForderEqualTo(Integer value) {
            addCriterion("FOrder =", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderNotEqualTo(Integer value) {
            addCriterion("FOrder <>", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderGreaterThan(Integer value) {
            addCriterion("FOrder >", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderGreaterThanOrEqualTo(Integer value) {
            addCriterion("FOrder >=", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderLessThan(Integer value) {
            addCriterion("FOrder <", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderLessThanOrEqualTo(Integer value) {
            addCriterion("FOrder <=", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderIn(List<Integer> values) {
            addCriterion("FOrder in", values, "forder");
            return (Criteria) this;
        }

        public Criteria andForderNotIn(List<Integer> values) {
            addCriterion("FOrder not in", values, "forder");
            return (Criteria) this;
        }

        public Criteria andForderBetween(Integer value1, Integer value2) {
            addCriterion("FOrder between", value1, value2, "forder");
            return (Criteria) this;
        }

        public Criteria andForderNotBetween(Integer value1, Integer value2) {
            addCriterion("FOrder not between", value1, value2, "forder");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated do_not_delete_during_merge Mon Dec 02 13:40:29 CST 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_contract_adv_resource
     *
     * @mbggenerated Mon Dec 02 13:40:29 CST 2013
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