package com.chen.trademark.entity;

import java.util.ArrayList;
import java.util.List;

public class TrademarkRecordExample {
    /**
     * orderByClause
     */
    protected String orderByClause;

    /**
     * distinct
     */
    protected boolean distinct;

    /**
     * oredCriteria
     */
    protected List<Criteria> oredCriteria;

    /**
     * TrademarkRecordExample
     */
    public TrademarkRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * setOrderByClause
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * getOrderByClause
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * setDistinct
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * isDistinct
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * getOredCriteria
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * or
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * or
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * createCriteria
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * createCriteriaInternal
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * clear
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *
     * @author  Generator
     * @date 2019/06/27
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNull() {
            addCriterion("file_id is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("file_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(Integer value) {
            addCriterion("file_id =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(Integer value) {
            addCriterion("file_id <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(Integer value) {
            addCriterion("file_id >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_id >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(Integer value) {
            addCriterion("file_id <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(Integer value) {
            addCriterion("file_id <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<Integer> values) {
            addCriterion("file_id in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<Integer> values) {
            addCriterion("file_id not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(Integer value1, Integer value2) {
            addCriterion("file_id between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(Integer value1, Integer value2) {
            addCriterion("file_id not between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameIsNull() {
            addCriterion("trademark_name is null");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameIsNotNull() {
            addCriterion("trademark_name is not null");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameEqualTo(String value) {
            addCriterion("trademark_name =", value, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameNotEqualTo(String value) {
            addCriterion("trademark_name <>", value, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameGreaterThan(String value) {
            addCriterion("trademark_name >", value, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameGreaterThanOrEqualTo(String value) {
            addCriterion("trademark_name >=", value, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameLessThan(String value) {
            addCriterion("trademark_name <", value, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameLessThanOrEqualTo(String value) {
            addCriterion("trademark_name <=", value, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameLike(String value) {
            addCriterion("trademark_name like", value, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameNotLike(String value) {
            addCriterion("trademark_name not like", value, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameIn(List<String> values) {
            addCriterion("trademark_name in", values, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameNotIn(List<String> values) {
            addCriterion("trademark_name not in", values, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameBetween(String value1, String value2) {
            addCriterion("trademark_name between", value1, value2, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andTrademarkNameNotBetween(String value1, String value2) {
            addCriterion("trademark_name not between", value1, value2, "trademarkName");
            return (Criteria) this;
        }

        public Criteria andUsptoIsNull() {
            addCriterion("USPTO is null");
            return (Criteria) this;
        }

        public Criteria andUsptoIsNotNull() {
            addCriterion("USPTO is not null");
            return (Criteria) this;
        }

        public Criteria andUsptoEqualTo(Integer value) {
            addCriterion("USPTO =", value, "uspto");
            return (Criteria) this;
        }

        public Criteria andUsptoNotEqualTo(Integer value) {
            addCriterion("USPTO <>", value, "uspto");
            return (Criteria) this;
        }

        public Criteria andUsptoGreaterThan(Integer value) {
            addCriterion("USPTO >", value, "uspto");
            return (Criteria) this;
        }

        public Criteria andUsptoGreaterThanOrEqualTo(Integer value) {
            addCriterion("USPTO >=", value, "uspto");
            return (Criteria) this;
        }

        public Criteria andUsptoLessThan(Integer value) {
            addCriterion("USPTO <", value, "uspto");
            return (Criteria) this;
        }

        public Criteria andUsptoLessThanOrEqualTo(Integer value) {
            addCriterion("USPTO <=", value, "uspto");
            return (Criteria) this;
        }

        public Criteria andUsptoIn(List<Integer> values) {
            addCriterion("USPTO in", values, "uspto");
            return (Criteria) this;
        }

        public Criteria andUsptoNotIn(List<Integer> values) {
            addCriterion("USPTO not in", values, "uspto");
            return (Criteria) this;
        }

        public Criteria andUsptoBetween(Integer value1, Integer value2) {
            addCriterion("USPTO between", value1, value2, "uspto");
            return (Criteria) this;
        }

        public Criteria andUsptoNotBetween(Integer value1, Integer value2) {
            addCriterion("USPTO not between", value1, value2, "uspto");
            return (Criteria) this;
        }

        public Criteria andUkIsNull() {
            addCriterion("UK is null");
            return (Criteria) this;
        }

        public Criteria andUkIsNotNull() {
            addCriterion("UK is not null");
            return (Criteria) this;
        }

        public Criteria andUkEqualTo(Integer value) {
            addCriterion("UK =", value, "uk");
            return (Criteria) this;
        }

        public Criteria andUkNotEqualTo(Integer value) {
            addCriterion("UK <>", value, "uk");
            return (Criteria) this;
        }

        public Criteria andUkGreaterThan(Integer value) {
            addCriterion("UK >", value, "uk");
            return (Criteria) this;
        }

        public Criteria andUkGreaterThanOrEqualTo(Integer value) {
            addCriterion("UK >=", value, "uk");
            return (Criteria) this;
        }

        public Criteria andUkLessThan(Integer value) {
            addCriterion("UK <", value, "uk");
            return (Criteria) this;
        }

        public Criteria andUkLessThanOrEqualTo(Integer value) {
            addCriterion("UK <=", value, "uk");
            return (Criteria) this;
        }

        public Criteria andUkIn(List<Integer> values) {
            addCriterion("UK in", values, "uk");
            return (Criteria) this;
        }

        public Criteria andUkNotIn(List<Integer> values) {
            addCriterion("UK not in", values, "uk");
            return (Criteria) this;
        }

        public Criteria andUkBetween(Integer value1, Integer value2) {
            addCriterion("UK between", value1, value2, "uk");
            return (Criteria) this;
        }

        public Criteria andUkNotBetween(Integer value1, Integer value2) {
            addCriterion("UK not between", value1, value2, "uk");
            return (Criteria) this;
        }

        public Criteria andEuipoIsNull() {
            addCriterion("EUIPO is null");
            return (Criteria) this;
        }

        public Criteria andEuipoIsNotNull() {
            addCriterion("EUIPO is not null");
            return (Criteria) this;
        }

        public Criteria andEuipoEqualTo(Integer value) {
            addCriterion("EUIPO =", value, "euipo");
            return (Criteria) this;
        }

        public Criteria andEuipoNotEqualTo(Integer value) {
            addCriterion("EUIPO <>", value, "euipo");
            return (Criteria) this;
        }

        public Criteria andEuipoGreaterThan(Integer value) {
            addCriterion("EUIPO >", value, "euipo");
            return (Criteria) this;
        }

        public Criteria andEuipoGreaterThanOrEqualTo(Integer value) {
            addCriterion("EUIPO >=", value, "euipo");
            return (Criteria) this;
        }

        public Criteria andEuipoLessThan(Integer value) {
            addCriterion("EUIPO <", value, "euipo");
            return (Criteria) this;
        }

        public Criteria andEuipoLessThanOrEqualTo(Integer value) {
            addCriterion("EUIPO <=", value, "euipo");
            return (Criteria) this;
        }

        public Criteria andEuipoIn(List<Integer> values) {
            addCriterion("EUIPO in", values, "euipo");
            return (Criteria) this;
        }

        public Criteria andEuipoNotIn(List<Integer> values) {
            addCriterion("EUIPO not in", values, "euipo");
            return (Criteria) this;
        }

        public Criteria andEuipoBetween(Integer value1, Integer value2) {
            addCriterion("EUIPO between", value1, value2, "euipo");
            return (Criteria) this;
        }

        public Criteria andEuipoNotBetween(Integer value1, Integer value2) {
            addCriterion("EUIPO not between", value1, value2, "euipo");
            return (Criteria) this;
        }

        public Criteria andDpmaIsNull() {
            addCriterion("DPMA is null");
            return (Criteria) this;
        }

        public Criteria andDpmaIsNotNull() {
            addCriterion("DPMA is not null");
            return (Criteria) this;
        }

        public Criteria andDpmaEqualTo(Integer value) {
            addCriterion("DPMA =", value, "dpma");
            return (Criteria) this;
        }

        public Criteria andDpmaNotEqualTo(Integer value) {
            addCriterion("DPMA <>", value, "dpma");
            return (Criteria) this;
        }

        public Criteria andDpmaGreaterThan(Integer value) {
            addCriterion("DPMA >", value, "dpma");
            return (Criteria) this;
        }

        public Criteria andDpmaGreaterThanOrEqualTo(Integer value) {
            addCriterion("DPMA >=", value, "dpma");
            return (Criteria) this;
        }

        public Criteria andDpmaLessThan(Integer value) {
            addCriterion("DPMA <", value, "dpma");
            return (Criteria) this;
        }

        public Criteria andDpmaLessThanOrEqualTo(Integer value) {
            addCriterion("DPMA <=", value, "dpma");
            return (Criteria) this;
        }

        public Criteria andDpmaIn(List<Integer> values) {
            addCriterion("DPMA in", values, "dpma");
            return (Criteria) this;
        }

        public Criteria andDpmaNotIn(List<Integer> values) {
            addCriterion("DPMA not in", values, "dpma");
            return (Criteria) this;
        }

        public Criteria andDpmaBetween(Integer value1, Integer value2) {
            addCriterion("DPMA between", value1, value2, "dpma");
            return (Criteria) this;
        }

        public Criteria andDpmaNotBetween(Integer value1, Integer value2) {
            addCriterion("DPMA not between", value1, value2, "dpma");
            return (Criteria) this;
        }

        public Criteria andInpiIsNull() {
            addCriterion("INPI is null");
            return (Criteria) this;
        }

        public Criteria andInpiIsNotNull() {
            addCriterion("INPI is not null");
            return (Criteria) this;
        }

        public Criteria andInpiEqualTo(Integer value) {
            addCriterion("INPI =", value, "inpi");
            return (Criteria) this;
        }

        public Criteria andInpiNotEqualTo(Integer value) {
            addCriterion("INPI <>", value, "inpi");
            return (Criteria) this;
        }

        public Criteria andInpiGreaterThan(Integer value) {
            addCriterion("INPI >", value, "inpi");
            return (Criteria) this;
        }

        public Criteria andInpiGreaterThanOrEqualTo(Integer value) {
            addCriterion("INPI >=", value, "inpi");
            return (Criteria) this;
        }

        public Criteria andInpiLessThan(Integer value) {
            addCriterion("INPI <", value, "inpi");
            return (Criteria) this;
        }

        public Criteria andInpiLessThanOrEqualTo(Integer value) {
            addCriterion("INPI <=", value, "inpi");
            return (Criteria) this;
        }

        public Criteria andInpiIn(List<Integer> values) {
            addCriterion("INPI in", values, "inpi");
            return (Criteria) this;
        }

        public Criteria andInpiNotIn(List<Integer> values) {
            addCriterion("INPI not in", values, "inpi");
            return (Criteria) this;
        }

        public Criteria andInpiBetween(Integer value1, Integer value2) {
            addCriterion("INPI between", value1, value2, "inpi");
            return (Criteria) this;
        }

        public Criteria andInpiNotBetween(Integer value1, Integer value2) {
            addCriterion("INPI not between", value1, value2, "inpi");
            return (Criteria) this;
        }

        public Criteria andOepmIsNull() {
            addCriterion("OEPM is null");
            return (Criteria) this;
        }

        public Criteria andOepmIsNotNull() {
            addCriterion("OEPM is not null");
            return (Criteria) this;
        }

        public Criteria andOepmEqualTo(Integer value) {
            addCriterion("OEPM =", value, "oepm");
            return (Criteria) this;
        }

        public Criteria andOepmNotEqualTo(Integer value) {
            addCriterion("OEPM <>", value, "oepm");
            return (Criteria) this;
        }

        public Criteria andOepmGreaterThan(Integer value) {
            addCriterion("OEPM >", value, "oepm");
            return (Criteria) this;
        }

        public Criteria andOepmGreaterThanOrEqualTo(Integer value) {
            addCriterion("OEPM >=", value, "oepm");
            return (Criteria) this;
        }

        public Criteria andOepmLessThan(Integer value) {
            addCriterion("OEPM <", value, "oepm");
            return (Criteria) this;
        }

        public Criteria andOepmLessThanOrEqualTo(Integer value) {
            addCriterion("OEPM <=", value, "oepm");
            return (Criteria) this;
        }

        public Criteria andOepmIn(List<Integer> values) {
            addCriterion("OEPM in", values, "oepm");
            return (Criteria) this;
        }

        public Criteria andOepmNotIn(List<Integer> values) {
            addCriterion("OEPM not in", values, "oepm");
            return (Criteria) this;
        }

        public Criteria andOepmBetween(Integer value1, Integer value2) {
            addCriterion("OEPM between", value1, value2, "oepm");
            return (Criteria) this;
        }

        public Criteria andOepmNotBetween(Integer value1, Integer value2) {
            addCriterion("OEPM not between", value1, value2, "oepm");
            return (Criteria) this;
        }

        public Criteria andJpIsNull() {
            addCriterion("JP is null");
            return (Criteria) this;
        }

        public Criteria andJpIsNotNull() {
            addCriterion("JP is not null");
            return (Criteria) this;
        }

        public Criteria andJpEqualTo(Integer value) {
            addCriterion("JP =", value, "jp");
            return (Criteria) this;
        }

        public Criteria andJpNotEqualTo(Integer value) {
            addCriterion("JP <>", value, "jp");
            return (Criteria) this;
        }

        public Criteria andJpGreaterThan(Integer value) {
            addCriterion("JP >", value, "jp");
            return (Criteria) this;
        }

        public Criteria andJpGreaterThanOrEqualTo(Integer value) {
            addCriterion("JP >=", value, "jp");
            return (Criteria) this;
        }

        public Criteria andJpLessThan(Integer value) {
            addCriterion("JP <", value, "jp");
            return (Criteria) this;
        }

        public Criteria andJpLessThanOrEqualTo(Integer value) {
            addCriterion("JP <=", value, "jp");
            return (Criteria) this;
        }

        public Criteria andJpIn(List<Integer> values) {
            addCriterion("JP in", values, "jp");
            return (Criteria) this;
        }

        public Criteria andJpNotIn(List<Integer> values) {
            addCriterion("JP not in", values, "jp");
            return (Criteria) this;
        }

        public Criteria andJpBetween(Integer value1, Integer value2) {
            addCriterion("JP between", value1, value2, "jp");
            return (Criteria) this;
        }

        public Criteria andJpNotBetween(Integer value1, Integer value2) {
            addCriterion("JP not between", value1, value2, "jp");
            return (Criteria) this;
        }

        public Criteria andWipoIsNull() {
            addCriterion("WIPO is null");
            return (Criteria) this;
        }

        public Criteria andWipoIsNotNull() {
            addCriterion("WIPO is not null");
            return (Criteria) this;
        }

        public Criteria andWipoEqualTo(Integer value) {
            addCriterion("WIPO =", value, "wipo");
            return (Criteria) this;
        }

        public Criteria andWipoNotEqualTo(Integer value) {
            addCriterion("WIPO <>", value, "wipo");
            return (Criteria) this;
        }

        public Criteria andWipoGreaterThan(Integer value) {
            addCriterion("WIPO >", value, "wipo");
            return (Criteria) this;
        }

        public Criteria andWipoGreaterThanOrEqualTo(Integer value) {
            addCriterion("WIPO >=", value, "wipo");
            return (Criteria) this;
        }

        public Criteria andWipoLessThan(Integer value) {
            addCriterion("WIPO <", value, "wipo");
            return (Criteria) this;
        }

        public Criteria andWipoLessThanOrEqualTo(Integer value) {
            addCriterion("WIPO <=", value, "wipo");
            return (Criteria) this;
        }

        public Criteria andWipoIn(List<Integer> values) {
            addCriterion("WIPO in", values, "wipo");
            return (Criteria) this;
        }

        public Criteria andWipoNotIn(List<Integer> values) {
            addCriterion("WIPO not in", values, "wipo");
            return (Criteria) this;
        }

        public Criteria andWipoBetween(Integer value1, Integer value2) {
            addCriterion("WIPO between", value1, value2, "wipo");
            return (Criteria) this;
        }

        public Criteria andWipoNotBetween(Integer value1, Integer value2) {
            addCriterion("WIPO not between", value1, value2, "wipo");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table trademark_record
     *
     * @mbg.generated do_not_delete_during_merge Thu Jun 27 10:50:15 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     *
     * @author  Generator
     * @date 2019/06/27
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