package com.ray.power.util;

/**
 * 常量
 * @author hss
 *
 */
public class ApplicationConstants {

	//请假流程
	public static String 	ACTIVITI_TYPE_KEY_LEAVE = "leave";
	public static final int 	ACTIVITI_TYPE_KEY_LEAVE_INT = 1;
	public static String 	ACTIVITI_TYPE_KEY_LEAVE_CHINESS = "请假";
	
	//进班流程
	public static String 	ACTIVITI_TYPE_KEY_ENTERCLASS = "enterclass";
	public static final int 	ACTIVITI_TYPE_KEY_ENTERCLASS_INT = 2;
	public static String 	ACTIVITI_TYPE_KEY_ENTERCLASS_CHINESS = "进班";
	
	// 前期优惠流程
	public static String 	ACTIVITI_TYPE_KEY_EARLYFREE = "earlyfree";
	public static final int 	ACTIVITI_TYPE_KEY_EARLYFREE_INT = 3;
	public static String 	ACTIVITI_TYPE_KEY_EARLYFREE_CHINESS = "前期优惠";
	
	// 后期优惠流程
	public static String	ACTIVITI_TYPE_KEY_LATERFREE = "laterfree";
	public static final int	ACTIVITI_TYPE_KEY_LATERFREE_INT = 5;
	public static String 	ACTIVITI_TYPE_KEY_LATERFREE_CHINESS = "后期优惠";
	
	// 缴费流程
	public static String 	ACTIVITI_TYPE_KEY_PAYMENT = "paymentRecord";
	public static final int 	ACTIVITI_TYPE_KEY_PAYMENT_INT = 4;
	public static String 	ACTIVITI_TYPE_KEY_PAYMENT_CHINESS = "缴费记录";
										
	// 转班流程
	public static String 	ACTIVITI_TYPE_KEY_TRANSFERCLASS = "transferclass";
	public static final int 	ACTIVITI_TYPE_KEY_TRANSFERCLASS_INT = 6;
	public static String 	ACTIVITI_TYPE_KEY_TRANSFERCLASS_CHINESS = "转班";
	

	//休学流程
	public static String 	ACTIVITI_TYPE_KEY_SUSPEND = "suspend";
	public static final int 	ACTIVITI_TYPE_KEY_SUSPEND_INT = 7;
	public static String 	ACTIVITI_TYPE_KEY_SUSPEND_CHINESS = "休学";
	
	// 退学退费申请流程
	public static String 	ACTIVITI_TYPE_KEY_LEAVESCHOOL = "leaveschool";
	public static final int 	ACTIVITI_TYPE_KEY_LEAVESCHOOL_INT = 8;
	public static String 	ACTIVITI_TYPE_KEY_LEAVESCHOOL_CHINESS = "退学";
	
	// 复学申请流程
	public static String 	ACTIVITI_TYPE_KEY_BACKSCHOOL = "backschool";
	public static final int 	ACTIVITI_TYPE_KEY_BACKSCHOOL_INT = 9;
	public static String 	ACTIVITI_TYPE_KEY_BACKSCHOOL_CHINESS = "复学";
	
	// 缴费单流程
	public static String 	ACTIVITI_TYPE_KEY_PAYMENTBILL = "paymentBill";
	public static final int 	ACTIVITI_TYPE_KEY_PAYMENTBILL_INT = 10;
	public static String 	ACTIVITI_TYPE_KEY_PAYMENTBILL_CHINESS = "缴费单";
	
	// 合同申请流程
	public static String 	ACTIVITI_TYPE_KEY_AGREEMENT = "agreement";
	public static final int 	ACTIVITI_TYPE_KEY_AGREEMENT_INT = 11;
	public static String 	ACTIVITI_TYPE_KEY_AGREEMENT_CHINESS = "合同申请";
	
	// 合同变更流程
	public static String	ACTIVITI_TYPE_KEY_AGREEMENTCHANGE = "agreementChange";
	public static final int 	ACTIVITI_TYPE_KEY_AGREEMENTCHANGE_INT = 12;
	public static String 	ACTIVITI_TYPE_KEY_AGREEMENTCHANGE_CHINESS = "合同变更";
	
	// 其他缴费单流程
	public static String 	ACTIVITI_TYPE_KEY_PAYMENTOTHERBILL = "paymentOtherBill";
	public static final int 	ACTIVITI_TYPE_KEY_PAYMENTOTHERBILL_INT = 13;
	public static String 	ACTIVITI_TYPE_KEY_PAYMENTOTHERBILL_CHINESS = "其他缴费单";
	
	// 费用调整流程
	public static String 	ACTIVITI_TYPE_KEY_COSTADJUSTMENT = "costAdjustment";
	public static final int 	ACTIVITI_TYPE_KEY_COSTADJUSTMENT_INT = 14;
	public static String 	ACTIVITI_TYPE_KEY_COSTADJUSTMENT_CHINESS = "费用调整";
	
	// 收入调整流程
	public static String 	ACTIVITI_TYPE_KEY_INCOMEADJUSTMENT = "incomeAdjustment";
	public static final int 	ACTIVITI_TYPE_KEY_INCOMEADJUSTMENT_INT = 15;
	public static String 	ACTIVITI_TYPE_KEY_INCOMEADJUSTMENT_CHINESS = "收入调整";
	
	// 留级流程
	public static String 	ACTIVITI_TYPE_KEY_DOWNCLASS = "downclass";
	public static final int 	ACTIVITI_TYPE_KEY_DOWNCLASS_INT = 16;
	public static String 	ACTIVITI_TYPE_KEY_DOWNCLASS_CHINESS = "留级";
	
	public static String    ACTIVITI_TYPE_KEY = "";
	
	//流程进行状态			
							/**发起**/
	public static final int ACTIVITI_VERIFY_STATE_START = 111;
							/**驳回**/
	public static final int	ACTIVITI_VERIFY_STATE_REBUT = 112;
						  	/**通过**/
	public static final int	ACTIVITI_VERIFY_STATE_OK = 113;
	
	
							/**发起**/
	public static String  	ACTIVITI_VERIFY_STATE_START_STR = "发起";
							/**驳回**/
	public static String	ACTIVITI_VERIFY_STATE_REBUT_STR = "未通过";
						  	/**通过**/
	public static String	ACTIVITI_VERIFY_STATE_OK_STR = "通过";
	
	
	// 流程审批状态			

	/**未提交**/
	public static final int	WORKFLOW_STATE_UNSTART = 101;
	/**提交审批**/
	//public static final int	WORKFLOW_STATE_STARTED = 102;
	/**审批进行中**/
	public static final int	WORKFLOW_STATE_AUDITING = 103;
	/**未通过审批**/
	public static final int	WORKFLOW_STATE_UNPASS = 104;
	/**通过审批**/
	public static final int WORKFLOW_STATE_PASS = 105;
	/** 废弃**/
	public static final int	WORKFLOW_STATE_DISCARD = 106;
	
	
	//是否老学员推荐
	public static final int ISOLDSTUDENTRECOMMEND_NO = 0,//否
							ISOLDSTUDENTRECOMMEND_YES = 1;//是
	
	//进班装填
	public static final int STUDENT_STATE_23 = 23,  //缴费合规
							STUDENT_STATE_24 = 24,  //财务合规
							STUDENT_STATE_25 = 25,  //教学合规
							STUDENT_STATE_31 = 31,  //已留级
							STUDENT_STATE_32 = 32,  //已转班
							STUDENT_STATE_33 = 33,  //已休学
							STUDENT_STATE_34 = 34,  //已复学原班
							STUDENT_STATE_35 = 35,  //已复学新班
							STUDENT_STATE_36 = 36,  //已退班
							STUDENT_STATE_41 = 41,  //留级插班
							STUDENT_STATE_42 = 42,  //转班插班
							STUDENT_STATE_43 = 43,  //复学插班
							STUDENT_STATE_51 = 51,  //毕业未就业
							STUDENT_STATE_52 = 52;  //毕业已就业
}
