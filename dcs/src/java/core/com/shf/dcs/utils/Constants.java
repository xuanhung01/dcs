package com.shf.dcs.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static String SUB_PAGE = "subpage";
	public static String LIST_ROLE = "listRole";
	public static String LIST_PARTNER = "listPartner";
	public static String LIST_TEAM = "listTeam";
	public static String LIST_PRIVILEGE = "listPrivilege";
	public static String FORM_MODE = "formMode";
	public static String FORM_MODEL = "formModel";
	public static String ACTION_VIEW = "view";
	public static String ACTION_LIST = "list";
	public static String ACTION_EDIT = "edit";
	public static String ACTION_CREATE = "create";
	public static String ACTION = "act";
	public static String LIST_ERRORS = "listErrors";
	public static String MESSAGE = "message";
	public static String MESSAGE_ADD_USER_SUCCESS = "AddUserSuccess";
	public static String MESSAGE_EDIT_USER_SUCCESS = "EditUserSuccess";
	public static String MESSAGE_REMOVE_USER_SUCCESS = "RemoveSuccess";
	public static String URL_DASH_BOARD = "/dashboard";
	public static String URL_MODAL_WINDOWS = "/modalWindows";
	// ROLE
	public static String[] PRIVILEGE_ID_CALLER = {"1043","1044","1045","1046","1049","1050","1051"};
	public static String[] PRIVILEGE_ID_HARD = {"1047","1048"};
	// public static String[] PRIVILEGE_ID_CALLER_AUTO = {"1041"};
	public static String PRIVILEGE_ID_CALL_TEAMLEADER = "1042";
	public static String PRIVILEGE_ID_HARD_TEAMLEADER = "1041";
	public static String PRIVILEGE_ID_ADHOC = "1151";
	public static String PRIVILEGE_ADHOC_ALL = "PRIVILEGE_ADHOC_COLLECTOR";
	// Excel upload user
	public static int MAX_NUMBER_UPLOAD_USER = 150;
	public static int MAX_NUMBER_UPLOAD = 10000;
	public static String ENABLE_ACTIVE = "1";
	public static String SEX_MALE = "MALE";
	public static BigDecimal ID_CONTACT_METHOD = new BigDecimal("1");
	public static BigDecimal ID_CONTACT_PLACE = new BigDecimal("2");
	public static BigDecimal ID_CONTACT_PERSON = new BigDecimal("3");
	public static BigDecimal ID_CODE_TYPE = new BigDecimal("4");
	public static String CODE_TYPE_PTP = "PTP";
	public static String CODE_TYPE_TER = "TER";
	public static String CODE_TYPE_COVID = "COVID-CT";
	public static String NGHI_NGO_GIAN_LAN_N = "N";
	
	//public static String PRIVILEGE_HEAD_ID = "1041";
	public static String PRIVILEGE_CALLER_ID = "1043";
	public static Long COLLECTION_DIRECTOR_ID = new Long(1052);
	public static Long ANALYST_SPECIAL_ID = new Long(1099);
	@SuppressWarnings("serial")
	public static List<Long> ROLE_FULL_UP_RECASE = new ArrayList<Long>()
	{ 
        {
            add(new Long(1099)); 
            add(new Long(1380));
            add(new Long(1481)); 
        } 
    }; 
	public static List<Long> ROLE_FULL_UP_TRAIL = new ArrayList<Long>()
	{ 
        {
            add(new Long(1382));  
        } 
    }; 
	public static String COMMA_ADDRESS = ", ";
	public static String COMMA = ",";
	public static String HEADER_NUMBER_CALL_OUT = "9";
	//
	// public static String USERVIEWDETAILCONTRACT = "UserViewDetailContract";
	public static String STATUS_ACTIVE = "A";
	public static String STATUS_REJECT = "REVERT";
	public static long TOTAL_MAX_TABLE = 500;
	//
	public static String ENV_LIVE = "LIVE";
	public static String GROUP_CODE_FIELD = "FIELD";
	
	public static BigDecimal ID_PRIVILAGE_CALLER = new BigDecimal("1043");
	public static String ACTION_TYPE_VIEW = "V";
	public static String ACTION_TYPE_UPDATE = "U";
	public static String USER_ADMIN = "ADMIN";
	public static String USER_SYSTEM = "SYSTEM";
	public static String ACTION_SMS = "SMS";
	public static String ACTION_LET = "LET";
	public static String ESB_ERROR_CODE_000 = "000";
	
	// status upload file
	public static String ERROR_CODE_00 = "00";
	public static String ERROR_CODE_10 = "10";
	public static String ERROR_CODE_20 = "20";
	public static String ERROR_CODE_30 = "30";
	
	public static final String SITE_KEY ="6Lc3VScTAAAAAMU6X_asVS3wrsAXFYS2MzIACeA2";
	    
	public static final String SECRET_KEY ="6Lc3VScTAAAAAAytxBTsB5TrC96q0mhraZXS-SgU";
	
	public static String PROJECT_CODE = "DCS";
	public static String DOCUMENT_CODE = "DOCUMENT_CODE_LEGAL";
	public static String DOCUMENT_NAME = "Thông tin hồ sơ Legal thu hồi nợ";
	// Enum class
	public static String MAP_EXCEL_FIELD_CUS_LD = "MapExcelFieldCusLd";
	public static String MAP_EXCEL_FIELD_DU_THU = "MapExcelFieldDuThu";
	public static String MAP_EXCEL_FIELD_SO_THU = "MapExcelFieldSoThu";
}
