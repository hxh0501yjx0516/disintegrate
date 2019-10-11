package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 手续管理
 *
 * @author ren lei
 * @date 2019-09-23 09:45:57
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarProcessing implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;
	/**
	 * 解体厂id
	 */
	private Long disintegratePlantId;
	/**
	 * ts_car_info的主键id
	 */
	private Long carInfoId;
	/**
	 * 登记时间
	 */
	private Date registerTime;
	/**
	 * 登记人（用户表主键id）
	 */
	private Long registerUserId;
	/**
	 * 1:未登记;2:已登记
	 */
	private Integer isRegister;
	/**
	 * 查询结果
	 */
	private String queryResult;
	/**
	 * 查询时间
	 */
	private Date queryTime;
	/**
	 * 查询人(用户表主键id）
	 */
	private Long queryUserId;
	/**
	 * 1:未查询;2:已查询;3:查询不通过
	 */
	private Integer isQuery;
	/**
	 * 核档结果
	 */
	private String verifyResult;
	/**
	 * 核档时间
	 */
	private Date verifyTime;
	/**
	 * 核档人(用户表主键id)
	 */
	private Long verifyUserId;
	/**
	 * 核档号
	 */
	private String recordNumber;
	/**
	 * 回收证明编号
	 */
	private String recycleNumber;
	/**
	 * 1:未核档；2：已核档；3:核档不通过
	 */
	private Integer isVerify;
	/**
	 * 1:未打印核档单；2：已打印核档单
	 */
	private Integer isPrintVerifyBill;
	/**
	 * 打印核档单人id
	 */
	private Long printVerifyBillUserId;
	/**
	 * 打印核档单时间
	 */
	private Date printVerifyBillTime;
	/**
	 * 上传商委时间
	 */
	private Date dataUploadTime;
	/**
	 * 上传商委人（用户主键id）
	 */
	private Long dataUploadUserId;
	/**
	 * 1:未上传商委;2:已上传商委
	 */
	private Integer isDataUpload;
	/**
	 * 拆解时间
	 */
	private Date dismantleTime;
	/**
	 * 拆解人（用户主键id)
	 */
	private Long dismantleUserId;
	/**
	 * 1：未拆解；2：已拆解；
	 */
	private Integer isDismantle;
	/**
	 * 上传商委图片时间
	 */
	private Date picUploadTime;
	/**
	 * 上传商委图片的人(用户主键id)
	 */
	private Long picUploadUserId;
	/**
	 * 1：未上传商委图片；2：已上传商委图片；
	 */
	private Integer isPicUpload;
	/**
	 * 打印回收证明时间
	 */
	private Date printRecycleTime;
	/**
	 * 打印回收证明的人（用户主键id)
	 */
	private Long printRecycleUserId;
	/**
	 * 1：未打印回收证明；2：已打印回收证明；
	 */
	private Integer isPrintRecycle;
	/**
	 * 是否录入注销(1:未录入;2:已录入)
	 */
	private Integer isLogout;
	/**
	 * 注销时间
	 */
	private Date logoutResult;
	/**
	 * 录入的注销时间
	 */
	private Date logoutTime;
	/**
	 * 录入注销的人（用户主键id)
	 */
	private Long logoutUserId;
	/**
	 * 完成商委注销时间
	 */
	private Date isAppointTime;
	/**
	 * 完成商委注销人(用户主键id)
	 */
	private Long isAppointUserid;
	/**
	 * 1：未上传完成商委注销时间；2：已完成商委注销时间上传；
	 */
	private Integer isAppointLogoutTime;
	/**
	 * 手续发放时间
	 */
	private Date procedureIssueTime;
	/**
	 * 手续发放处理人（用户主键id)
	 */
	private Long procedureIssueUserId;
	/**
	 * 登记地址
	 */
	private String registerAddress;
	/**
	 * 1：未发放手续；2：已发放手续；
	 */
	private Integer isProcedureIssue;
	/**
	 * 发放收车凭证的人（用户主键id)
	 */
	private Long grantCarReceiptUserId;
	/**
	 * 发放收车凭证的时间
	 */
	private Date grantCarReceiptTime;
	/**
	 * 1：不发放收车凭据；2：发放收车凭据
	 */
	private Integer isGrantCarReceipt;
	/**
	 * 打印车辆台账人员（用户主键id)
	 */
	private Long printCarLedgerUserId;
	/**
	 * 打印车辆台账的时间
	 */
	private Date printCarLedgerTime;
	/**
	 * 1：不打印车辆台账；2：打印车辆台账
	 */
	private Integer isPrintCarLedger;
	/**
	 * 转档打印的人
	 */
	private Long printOutputUserId;
	/**
	 * 转档打印的时间
	 */
	private Date printOutputTime;
	/**
	 * 1：转档打印；2：不转档打印
	 */
	private Integer isPrintoutPut;
	/**
	 * 打印注销证明人员(用户主键id)
	 */
	private Long printCancelProveUserId;
	/**
	 * 打印注销证明的时间
	 */
	private Date printCancelProveTime;
	/**
	 * 打印次数
	 */
	private Integer printCount;
	/**
	 * 批量打印标识
	 */
	private String printLedgerBatch;
	/**
	 * 1：打印注销证明；2：不打印注销证明
	 */
	private Integer isPrintCancelProve;
	/**
	 * 1:监销;2:不监销
	 */
	private Integer isSuperviseSale;
	/**
	 * 监销人
	 */
	private String superviseSaleUser;
	/**
	 * 监销确认时间
	 */
	private Date saleOfAffirmTime;
	/**
	 * 1：未拆解;2:已拆解
	 */
	private Integer isDismantleAffirm;
	/**
	 * 拆解确认时间
	 */
	private Date dismantleAffirmTime;
	/**
	 * 拆解确认人
	 */
	private Long dismantleAffirmUserId;
	/**
	 * 是否删除。1.删除，2,正常
	 */
	private Integer isDelete;
	// 报废的状态：1.待报废，2.报废完成
	private Integer isPremiumCompletion;
	// 是否毁型。1.待毁型，2.毁型成功
	private Integer isDestructive;
	// 毁型时间
	private Date destructiveTime;
	// 毁型操作人
	private Long destructiveUserId;
}
