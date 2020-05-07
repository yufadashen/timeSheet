package com.intehel.common.util;

/**
 * 错误码枚举类
 * @author 余发
 */
public enum ResultCode {

    /**
     * 公共
     */


    SCUUESS(0, "操作成功"),
    ERROR(-1, "操作失败"),
    OPIDERROR(-2, "opid为空"),
    NO_BIND_PATIENT_CARD(-3,"用户未绑定就诊卡"),
    USER_INFO_ERROT(-4,"验证用户信息异常"),
    USER_INFO_PAST_DUE(-5,"用户信息过期，请重新绑定"),

    /**
     * 预约挂号
     */
    DOCTOR_ID_IS_NULL(10001, "参数医生id为空"),
    FEE_IS_NULL(10002, "参数挂号金额为空"),
    ORDER_TIME_IS_NULL(10003, "参数挂号时间为空"),
    ASROWID_IS_NULL(10004, "参数医生号码为空"),
    VISITID_IS_NULL(10005, "参数医生出诊记录id为空"),
    REGISTERID_IS_NULL(10006, "参数挂号记录id为空"),
    PAYTYPE_IS_NULL(10007, "参数支付状态为空"),
    REGISTER_STATIC_IS_NULL(10008, "参数号码状态为空"),
    DOCTROERROR(50001,"获取医生信息失败"),

    UPDATE_APPOINTMENTERROR(10010, "修改预约记录状态失败"),
    TO_APPOINTMENTERROR(10006, "跳转到预约挂号信息页面失败"),
    PAY_SUCCESS_ERROR(10007, "支付完成后台请求异常"),

    /**
     * 预约挂号
     */
    QUERY_PAY_STATUS_EXCEPTION(40001, "查询订单支付状态异常"),
    GET_APPOINTMENT_EXCEPTION(40002, "获取预约挂号信息异常"),
    APPOINTMENT_EXCEPTION(40003, "提交预约挂号异常"),
    GET_NO_START_REG_EXCEPTION(40004, "查询已缴费医师未接诊挂号接口异常"),
    GET_OUT_NUM_EXCEPTION(40009, "挂号锁号解锁接口异常"),
    GET_REG_SUBMIT_EXCEPTION(40010, "提交挂号接口异常"),
    GET_PAY_SUM_EXCEPTION(40010, "获取挂号金额接口异常"),
    GET_LOCK_NUM_EXCEPTION(40011, "挂号锁号接口异常"),
    GET_REG_TIME_VALUE_EXCEPTION(40012, "查询挂号安排时段接口异常"),
    GET_DOCTOR_EXCEPTION(40013, "查询挂号医生接口异常"),
    GET_DEPARTMENT_EXCEPTION(40014, "查询门诊科室信息接口异常"),
    GET_DOCTOR_PHOTO_EXCEPTION(40015, "查询医生头像接口异常"),
    GET_NO_WITHDRAW_NUM(40016, "取消已缴费自助挂号接口异常"),
    PATIENT_REGISTERED_REPEAD(40017, "当前时间段已存在挂号记录，禁止再此挂号"),
    ERROR_DOCTOR_TIME(40018, "该医生暂无排班信息"),

    PAY_SUCCESS_HIS_SUCCESS_GH(40005, "挂号成功"),
    PAY_SUCCESS_HIS_ERROR_REFUND_SUCCESS_GH(40006, "挂号失败，已退款"),
    PAY_SUCCESS_HIS_ERROR_REFUND_ERROR_GH(40007, "挂号失败，请联系工作人员进行退费，联系电话：1234567"),
    PAY_ERROR(40008, "支付失败"),

    WAP_UNIFIEDORDER_PAY_EXCEPTION(50001, "支付平台网页支付下单接口异常"),
    QUERY_PAY_EXCEPTION(50002, "支付平台查询订单状态接口异常"),
    REFUND_PAY_EXCEPTION(50003, "支付平台退费接口异常"),


    /**
     * 文章
     */
    ARTICLE_ID_NULL(60001,"文章ID为空"),
    GET_ARTICLE_DETIAL_FALSE(60002,"获取文章内容失败"),

    /**
     * 智能导诊
     */
    BODYPARTS_IS_NULL(70001,"获取身体部位信息为空"),
    SYMPTOM_IS_NULL(70002,"获取身体部位信息为空"),
    SYMPTOM_ID_IS_NULL(70003,"查询其他伴随症状症状id为空"),
    GET_OTHER_SYMPTOM_ERROR(70004,"查询其他伴随症状异常"),
    GET_BODYPARTS_ERROR(70005,"查询身体部位信息及疾病异常"),
    GET_SYMPTOM_ERROR(70006,"查询症状信息异常"),
    GET_DISEASE_DETAILS_ERROR(70007,"查询疾病详情异常"),
    DISEASE_ID_IS_NULL(70008, "查询疾病详情id为空"),

    /**
     * 网络异常
     */

    /**
     * 错误信息
     *
     * "密码修改失败",80007
     */

    SYS_USER_NAME_NULL(80001,"用户不存在"),
    SYS_USER_PASSWORD_ERROR(80002,"输入密码错误"),
    SYS_USER_LOGIN_OVERTTIME(80006,"登录超时"),
    SYS_USER_UPDATE_PASSWORD_ERROR(80007,"密码修改失败"),

    /**
     * 系统用户信息管理
     */
    SYS_USER_NAME_EXITENCE(90001,"用户名已存在"),
    SYS_USER_NAME_INSERT_FALSE(90002,"用户信息添加失败"),
    SYS_USER_DELETE_FALSE(90003,"用户信息删除失败"),
    SYS_USER_UPDATE_FALSE(90004,"用户信息修改失败"),

    /**
     * 科室
     */
    GET_DEPARTMENT_FALSE(11001,"获取科室信息失败"),
    DEPARTMENT_MSG_FALSE(11002,"科室信息填写错误"),
    DEPARTMENT_IS_EXIST(11003,"科室已存在"),
    /**
     * 上传图片
     */
    UPLOAD_PICTURE_ERROR(12001, "上传图片异常"),
    DEPARTMENT_ID_IS_NULL(12002, "参数科室id不能为空"),

    /**
     * 门诊缴费查询
     */
    OUT_PATIENT_ERROR(13001, "门诊缴费查询异常"),
    OUT_HOSPITAL_ERROR(13002, "住院缴费查询异常"),
    OUT_HOSPITAL_PARAMETER_ERROR(13003, "查询起止时间不能为空"),
    /**
     * 住院信息查询
     */
    HOSPITAL_ERROR(14001, "住院信息查询异常"),
    HOSPITAL_PARAMETER_ERROR(14002, "病人住院ID不能为空"),
    /**
     * 病人报告查询
     */
    PATIENT_REPORT_ERROR(15001, "病人报告查询异常"),
    PATIENT_REPORT_PARAMETER_ERROR(15002, "病人报告查询异常"),
    /**
     * 模板消息
     */
    OPENID_NULL(20001, "openId错误"),
    USERBEAN_NULL(20003,"userBean错误"),
    PUSH_ERROR(20002, "模板消息推送是失败"),

    /**
     * 退号
     */
    GO_WITHDRAW_NUM_ERROR(11001, "退号接口异常"),
    HIS_SUCCESS_REFUND_SUCCESS_TH(40005, "退号成功，已退款"),
    HIS_SUCCESS_REFUND_ERROR_TH(40007, "退号成功，1-3个工作日进行退款"),


    PAY_SUCCESS_HIS_SUCCESS(12001,"支付成功，通知his成功"),
    PAY_SUCCESS_HIS_ERROR_REFUND_SUCCESS(12002,"支付成功,通知his失败，退费成功"),
    PAY_SUCCESS_HIS_ERROR_REFUND_ERROR(12003,"支付成功,通知his失败，退费失败"),
    NO_PAYMENT_REWORD(13001,"暂无已支付记录")
    ;

    private int code;

    private String desc;

    public int code() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String desc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     *
     * @param code
     * @param desc
     */
    private ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
