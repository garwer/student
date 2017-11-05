package course.dao.test;

import java.util.HashMap;
import java.util.Map;

public class testbbb {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", null);
		System.out.println(map.get("a"));
		String strSQL = "";
		String neMsgType = "";
		String alarmId = "";
		//String alarmId = "";
	    strSQL = "select a.* "
	            + " from KPI_CODE_LIST a,NE_ALARM_MSG b,CI_BASE_ELEMENT c"
	            + " where a.NE_MSG_TYPE='" + neMsgType + "' and b.ne_alarm_msg_id='" 
	            + "' and b.NE_ID=c.instance_id and a.NE_TYPE_ID=c.class_id ";
		System.out.println(strSQL);
		
	    strSQL = "select a.ne_alarm_msg_id, a.kpi_id, a.ne_id, a.region_id, a.ne_perf_msg_id,"
	        	+ " a.config_ne_id, a.config_ne_name, a.kpi_value,a.alarm_type, a.alarm_origin,"
	        	+ " a.alarm_level, a.alarm_times, a.msg_remark, a.merge_alarm_msg_id,"
	        	+ " a.analyze_task_batch_id,a.merge_reason,a.state,"
	        	+ " to_char(a.generate_date, 'yyyy-mm-dd hh24:mi:ss') generate_date,"
	        	+ " to_char(a.receive_date, 'yyyy-mm-dd hh24:mi:ss') receive_date,"
	        	+ " to_char(a.dispose_date, 'yyyy-mm-dd hh24:mi:ss') dispose_date,"
	        	+ " to_char(a.state_date, 'yyyy-mm-dd hh24:mi:ss') state_date,"
	        	+ " a.alarm_region_origin, a.alarm_class, b.short_description,"
	        	+ " (select list_label from TP_DOMAIN_LISTVALUES"
	        	+ " where domain_code = 'DOMAIN_NE_ALARM_ORIGIN'"
	        	+ " and list_value = a.alarm_Origin) alarmOrigin,c.KPI_DESCRIPTION,a.DR_ID "
	        	+ " from NE_ALARM_MSG a, ci_base_element b,kpi_code_list c "
	        	+ " where a.ne_id=b.instance_id(+) and a.kpi_id=c.kpi_id(+) and a.NE_ALARM_MSG_ID='" + alarmId + "'";
		System.out.println(strSQL);

		StringBuffer sql = new StringBuffer();
		sql
		.append(" select a.alarm_title, /*�澯����*/ ")
		.append("        a.ne_alarm_list_id, /*�澯ID*/ ")
		.append("        b1.region_name alarm_region_origin, /*������Դ*/ ")
		.append("        b2.region_name, /*����*/ ")
		.append("        h.mean alarm_class, /*�澯����*/ ")
		.append("        c.kpi_name, /*KPI����*/ ")
        .append("        c.kpi_description, /*KPI����*/ ")
		.append("        a.kpi_id, /*KPI ID*/ ")
		.append("        a.kpi_value, /*KPIֵ*/ ")
		.append("        d.short_description, /*��Ԫ����*/ ")
		.append("        g.class_name, /*��Ԫ����*/ ")
		.append("        a.config_ne_name, /*����������*/ ")
		.append("        e1.list_label alarm_origin, /*�澯��Դ*/ ")
		.append("        a.analyze_task_batch_id, /*�߼������������κ�*/ ")
		.append("        e2.list_label alarm_type, /*�澯����*/ ")
		.append("        e3.list_label alarm_level, /*�澯����*/ ")
		.append("        e4.list_label ori_alarm_type, /*ԭʼ�澯����*/ ")
		.append("        e5.list_label ori_alarm_level, /*ԭʼ�澯����*/ ")
		.append("        a.alarm_times, /*�澯����*/ ")
		.append("        e6.list_label oprt_state, /*�״̬*/ ")
		.append("        to_char(a.create_time, 'yyyy-mm-dd hh24:mi:ss') create_time, /*����ʱ��*/ ")
		.append("        to_char(a.last_send_time, 'yyyy-mm-dd hh24:mi:ss') last_send_time, /*���һ�ν���ʱ��*/ ")
		.append("        to_char(a.generate_time, 'yyyy-mm-dd hh24:mi:ss') generate_time, /*����ʱ��*/ ")
		.append("        to_char(a.last_generate_time, 'yyyy-mm-dd hh24:mi:ss') last_generate_time, /*���һ�θ澯����ʱ��*/ ")
		.append("        to_char(a.confirm_time, 'yyyy-mm-dd hh24:mi:ss') confirm_time, /*ȷ��ʱ��*/ ")
		.append("        to_char(a.clear_time, 'yyyy-mm-dd hh24:mi:ss') clear_time, /*���ʱ��*/ ")
		.append("        to_char(a.complete_time, 'yyyy-mm-dd hh24:mi:ss') complete_time, /*����ʱ��*/ ")
		.append("        f1.staff_name confirm_operator, /*ȷ�ϲ���Ա*/ ")
		.append("        f2.staff_name clear_operator, /*�������Ա*/ ")
		.append("        a.flow_id, /*����ID*/ ")
		.append("        f3.staff_name flow_operator, /*���ϵ�������(���̵�ǰ������)*/ ")
		.append("        a.merge_alarm_msg_id, /*�ϲ��澯��ϢID*/ ")
		.append("        a.perf_msg_id, /*������ϢID*/ ")
		.append("        a.merge_reason, /*�ϲ�ԭ��*/ ")
		.append("        a.msg_remark, /*��Ϣ��ע*/ ")
		.append("        a.oprt_result, /*����������*/ ")
		.append("        a.ne_id, /*��ԪID*/ ")
		.append("        a.ne_type_id, /*��Ԫ����ID*/ ")
		.append("        a.config_ne_id, /*��Ԫ������ID*/ ")
		.append("        f4.staff_name complete_operator, /*������*/ ")//
		.append("        a.busi_class_name /*ҵ�����*/ ")
		.append("   from ne_alarm_list a, ")
		.append("        MANAGE_REGION b1, ")
		.append("        MANAGE_REGION b2, ")
		.append("        kpi_code_list c, ")
		//.append("        net_element d, ")
		.append("        ci_base_element d, ")
		.append("        (select * ")
		.append("           from tp_domain_listvalues ")
		.append("          where domain_code = 'DOMAIN_NE_ALARM_ORIGIN') e1, ")
		.append("        (select * ")
		.append("           from tp_domain_listvalues ")
		.append("          where domain_code = 'DOMAIN_NE_ALARM_TYPE') e2, ")
		.append("        (select * ")
		.append("           from tp_domain_listvalues ")
		.append("          where domain_code = 'DOMAIN_NE_ALARM_LEVEL') e3, ")
		.append("        (select * ")
		.append("           from tp_domain_listvalues ")
		.append("          where domain_code = 'DOMAIN_NE_ALARM_TYPE') e4, ")
		.append("        (select * ")
		.append("           from tp_domain_listvalues ")
		.append("          where domain_code = 'DOMAIN_NE_ALARM_LEVEL') e5, ")
		.append("        (select * ")
		.append("           from tp_domain_listvalues ")
		.append("          where domain_code = 'DOMAIN_ALARM_STATE') e6, ")
		.append("        staff f1, ")
		.append("        staff f2, ")
		.append("        staff f3, ")
		.append("        staff f4, ")
		//.append("        ne_type g, ")
		.append("        ci_class_tree g, ")
		.append("        (select * from codelist where code_type = 'ALARM_CLASS') h, ")
		//.append("        kpi_mapping_cfg i, ")
		//.append("        all_busi_class_tab j ")
		.append("  where a.alarm_region_origin = b1.region_id(+) ")
		.append("    and a.region_id = b2.region_id(+) ")
		.append("    and a.kpi_id = c.kpi_id(+) ")
		.append("    and a.ne_id = d.instance_id(+) ")
		.append("    and a.alarm_origin = e1.list_value(+) ")
		.append("    and a.alarm_type = e2.list_value(+) ")
		.append("    and a.alarm_level = e3.list_value(+) ")
		.append("    and a.ori_alarm_type = e4.list_value(+) ")
		.append("    and a.ori_alarm_level = e5.list_value(+) ")
		.append("    and a.oprt_state = e6.list_value(+) ")
		.append("    and a.confirm_operator = f1.staff_id(+) ")
		.append("    and a.clear_operator = f2.staff_id(+) ")
		.append("    and a.flow_operator = f3.staff_id(+) ")
		.append("    and a.complete_operator = f4.staff_id(+) ")
		.append("    and a.ne_type_id = g.class_id(+) ")
		.append("    and a.alarm_class = h.code(+) ")
		//.append("    and a.kpi_id = i.kpi_id(+) ")
		//.append("    and i.busi_class = j.busi_class(+) ")
		.append("   and a.ne_alarm_list_id = "+alarmId);
		System.out.println(sql);
		
	    StringBuffer sql1 = new StringBuffer();
	    StringBuffer countsql = new StringBuffer();
	    StringBuffer tables = new StringBuffer();
	    StringBuffer fields = new StringBuffer();
	    StringBuffer conditions = new StringBuffer();
	      tables.append("KPI_MAPPING_CFG a,KPI_CODE_LIST b");
	      fields.append(" count(a.KPI_ID) kpicount ");
	      conditions.append("a.KPI_ID=b.KPI_ID and b.NE_MSG_TYPE=20");

	      sql1.append(" select ").append(fields.toString()).append(" from ").append(tables).append(" where ").append(conditions.toString());
	      countsql.append(" select count(*) from ").append(tables).append(" where ").append(conditions.toString());
	      System.out.println(countsql);
	      
	      System.out.println(sql1);
	      
	      String sql2 = "select a.GROUP_OBJECT_ID key1,a.NE_ID key2,a.KPI_ID key3,decode(a.NE_ID,'0',b.NE_TYPE_NAME,d.NE_NAME) NE_NAME," +
	              "b.ne_type_id ne_type_id,(b.kpi_name || '[' || b.kpi_id || ']') kpi_name,a.MATCH_KPI_VALUE,decode(a.NE_ID,'0','1',d.NE_FLAG) NE_FLAG from GROUP_OBJECT a," +
	              " (select a.kpi_id,a.kpi_name,b.ne_type_id,b.ne_type_name from KPI_CODE_LIST a,NE_TYPE b where a.ne_type_id = b.ne_type_id(+)) b" +
	              ",NET_ELEMENT d where a.NE_ID=d.NE_ID(+) and a.kpi_id=b.kpi_id(+)";
	    System.out.println(sql2);
	    
	    String a = "select count(*) "+
	            "from NE_FILTER_KPI_CONFIG a,NE_TYPE b, NET_ELEMENT c,KPI_CODE_LIST d"+
	              " where a.NE_TYPE_ID=b.NE_TYPE_ID(+) and a.KPI_ID=d.KPI_ID(+) and a.NE_ID=c.NE_ID(+)";
	    
	    System.out.println(a);
	    String neTypeId = "";
	    strSQL = "select * "
	            + " from KPI_CODE_LIST"
	            + " where NE_MSG_TYPE='" + neMsgType + "' and NE_TYPE_ID='" + neTypeId +
	            "'";
	   System.out.println(strSQL);
	 String SQL_KPI_GROUP_LIST = "SELECT a.GROUP_ID, a.GROUP_NAME, (SELECT wmsys.wm_concat(k.KPI_NAME) FROM ALARM_FORM_KPI_GROUP_RELA r, KPI_CODE_LIST k WHERE r.KPI_ID = k.KPI_ID AND r.GROUP_ID = a.GROUP_ID) KPI_NAMES, a.REMARK, t.LIST_LABEL STATE, to_char(a.LAST_UPDATE_TIME, 'yyyy-MM-dd hh24:mi:ss') LAST_UPDATE_TIME "              
						   + "FROM ALARM_FORM_KPI_GROUP a, TP_DOMAIN_LISTVALUES t "
						   + "WHERE a.STATE=t.LIST_VALUE(+) AND t.DOMAIN_CODE = 'DOMAIN_MAIN_STATE' ";
	 System.out.println(SQL_KPI_GROUP_LIST);
	 
 	String b = " select NVL((WITH CUR_NE AS(select M.EXPERT_ADVICE,M.NE_ID,M.CONFIG_NE_ID,M.KPI_ID,M.NE_ALARM_LIST_ID FROM NE_ALARM_LIST M " +
			"WHERE M.NE_ALARM_LIST_ID = "+alarmId+")," +
			" HIS_NE AS (SELECT N.EXPERT_ADVICE,N.NE_ID,N.CONFIG_NE_ID,N.KPI_ID,N.NE_ALARM_LIST_ID,N.CREATE_TIME,decode(N.OPRT_STATE,'20', to_char(N.CONFIRM_TIME, 'yyyy-mm-dd hh24:mi:ss'), '25', to_char(N.SUSPEND_TIME, 'yyyy-mm-dd hh24:mi:ss'), '30', to_char(N.CLEAR_TIME, 'yyyy-mm-dd hh24:mi:ss'),'40',to_char(N.DELETE_TIME, 'yyyy-mm-dd hh24:mi:ss')) OPER_TIME FROM NE_ALARM_LIST N WHERE N.NE_ALARM_LIST_ID <> "+alarmId+") " +
			" SELECT NVL2(CUR_NE.EXPERT_ADVICE,CUR_NE.EXPERT_ADVICE,(SELECT AA.EXPERT_ADVICE FROM(SELECT HIS_NE.EXPERT_ADVICE FROM HIS_NE,CUR_NE  " +
			" WHERE HIS_NE.NE_ID = CUR_NE.NE_ID AND HIS_NE.KPI_ID = CUR_NE.KPI_ID AND HIS_NE.CONFIG_NE_ID = CUR_NE.CONFIG_NE_ID AND HIS_NE.EXPERT_ADVICE IS NOT NULL ORDER BY HIS_NE.OPER_TIME DESC,HIS_NE.CREATE_TIME DESC)AA WHERE ROWNUM = 1)) EXPERT_ADVICE FROM CUR_NE),C.EXPERT_ADVICE) EXPERT_ADVICE, " +
			" OPRT_RESULT,PRO_PHENO,PRO_REASON,MEASURES,AFTERMATH FROM NE_ALARM_LIST N, KPI_CODE_LIST C" +
			" WHERE N.KPI_ID = C.KPI_ID(+) AND N.NE_ALARM_LIST_ID = "+ alarmId;
	    
	    
	System.out.println(b);
	    
	    
    StringBuffer aa = new StringBuffer();
    aa
    .append(" select '<font style=background-color:'||")
    .append("        decode(e3.list_value,'1','red','2','orange','3','yellow','green')")
    .append("        ||'>'||a.alarm_title||'</font>' alarm_title, /*�澯����*/ ")
    .append("        a.alarm_title alarm_title_no_color, /*�澯����*/ ")
    .append("        a.ne_alarm_list_id, /*�澯ID*/ ")
    .append("        b1.region_name alarm_region_origin, /*������Դ*/ ")
    .append("        b2.region_name, /*����*/ ")
    .append("        h.mean alarm_class, /*�澯����*/ ")
    .append("        c.kpi_name, /*KPI����*/ ")
    .append("        c.kpi_description, /*KPI����*/ ")
    .append("        a.kpi_id, /*KPI ID*/ ")
    .append("        a.kpi_value, /*KPIֵ*/ ")
    .append("        a.ne_name, /*��Ԫ����*/ ")
    .append("        a.NE_TYPE_NAME, /*��Ԫ����*/ ")
    .append("        a.config_ne_name, /*����������*/ ")
    .append("        e1.list_label alarm_origin, /*�澯��Դ*/ ")
    .append("        a.analyze_task_batch_id, /*�߼������������κ�*/ ")
    .append("        e2.list_label alarm_type, /*�澯����*/ ")
    .append("        e3.list_label alarm_level, /*�澯����*/ ")
    .append("        '<font style=background-color:'||decode(e3.list_value,'1','red','2','orange','3','yellow','green')")
    .append("        ||'>'||e3.list_label||'</font>' alarm_level_color, /*�澯����*/ ")
    .append("        e4.list_label ori_alarm_type, /*ԭʼ�澯����*/ ")
    .append("        e5.list_label ori_alarm_level, /*ԭʼ�澯����*/ ")
    .append("        a.alarm_times, /*�澯����*/ ")
    .append("        e6.list_label alarm_state, /*�澯״̬*/ ")
    .append("        e8.list_label oprt_state, /*�״̬*/ ")
    .append("        e7.list_label dr_name, /*˫����*/ ")
    .append("        to_char(a.create_time, 'yyyy-mm-dd hh24:mi:ss') create_time, /*����ʱ��*/ ")
    .append("        to_char(a.last_send_time, 'yyyy-mm-dd hh24:mi:ss') last_send_time, /*���һ�ν���ʱ��*/ ")
    .append("        to_char(a.generate_time, 'yyyy-mm-dd hh24:mi:ss') generate_time, /*����ʱ��*/ ")
    .append("        to_char(a.last_generate_time, 'yyyy-mm-dd hh24:mi:ss') last_generate_time, /*���һ�θ澯����ʱ��*/ ")
    .append("        to_char(a.confirm_time, 'yyyy-mm-dd hh24:mi:ss') confirm_time, /*ȷ��ʱ��*/ ")
    .append("        to_char(a.clear_time, 'yyyy-mm-dd hh24:mi:ss') clear_time, /*���ʱ��*/ ")
    //.append("        to_char(a.complete_time, 'yyyy-mm-dd hh24:mi:ss') complete_time, /*����ʱ��*/ ")
    .append("        to_char(a.delete_time, 'yyyy-mm-dd hh24:mi:ss') delete_time, /*ɾ��ʱ��*/ ")
    .append("        f1.staff_name confirm_operator, /*ȷ�ϲ���Ա*/ ")
    .append("        f2.staff_name clear_operator, /*�������Ա*/ ")
    .append("        a.flow_id, /*����ID*/ ")
    //.append("      PKP_COMMON.getNamesById('select staff_name from staff where staff_id in('||nvl(a.flow_operator,'')||')',',') flow_operator,/*���ϵ�������(���̵�ǰ������)*/")
    // �����̿��ȡ��ǰ������ by chenxunxin 2008/2/1
    .append("        pkp_flow_function.getFlowCurStaffName(a.flow_id) flow_operator, /*���ϵ�������(���̵�ǰ������)*/") 
    .append("        a.merge_alarm_msg_id, /*�ϲ��澯��ϢID*/ ")
    //.append("        a.perf_msg_id, /*������ϢID*/ ")
    .append("        a.merge_reason, /*�ϲ�ԭ��*/ ")
    .append("        a.msg_remark, /*��Ϣ��ע*/ ")
    .append("        a.oprt_result, /*����������*/ ")
    .append("        a.ne_id, /*��ԪID*/ ")
    .append("        a.ne_type_id, /*��Ԫ����ID*/ ")
    .append("        a.config_ne_id, /*��Ԫ������ID*/ ")
    .append("        f4.staff_name delete_operator, /*ɾ����*/ ")//
    //.append("        nvl(a.expert_advice, c.expert_advice) expert_advice, /*ר�ҽ���*/ ")
    .append("        NVL((WITH CUR_NE AS(select M.EXPERT_ADVICE,M.NE_ID,M.CONFIG_NE_ID,M.KPI_ID,M.NE_ALARM_LIST_ID FROM NE_ALARM_LIST M  WHERE M.NE_ALARM_LIST_ID =  "+alarmId)
    .append("        ),HIS_NE AS ( SELECT N.EXPERT_ADVICE,N.NE_ID,N.CONFIG_NE_ID, N.KPI_ID,N.NE_ALARM_LIST_ID,N.CREATE_TIME, decode(N.OPRT_STATE, '20', to_char(N.CONFIRM_TIME, 'yyyy-mm-dd hh24:mi:ss'), '25', to_char(N.SUSPEND_TIME, 'yyyy-mm-dd hh24:mi:ss'),'30',to_char(N.CLEAR_TIME, 'yyyy-mm-dd hh24:mi:ss'), '40', to_char(N.DELETE_TIME, 'yyyy-mm-dd hh24:mi:ss')) OPER_TIME ")
    .append("         FROM NE_ALARM_LIST N  WHERE N.NE_ALARM_LIST_ID <> "+alarmId+")SELECT NVL2(CUR_NE.EXPERT_ADVICE,CUR_NE.EXPERT_ADVICE, (SELECT AA.EXPERT_ADVICE FROM( SELECT HIS_NE.EXPERT_ADVICE FROM HIS_NE,CUR_NE  WHERE HIS_NE.NE_ID = CUR_NE.NE_ID  AND HIS_NE.KPI_ID = CUR_NE.KPI_ID  AND HIS_NE.CONFIG_NE_ID = CUR_NE.CONFIG_NE_ID  AND HIS_NE.EXPERT_ADVICE IS NOT NULL  ORDER BY OPER_TIME DESC,HIS_NE.CREATE_TIME DESC ")
    .append("         )AA  WHERE ROWNUM = 1)) EXPERT_ADVICE FROM CUR_NE),C.EXPERT_ADVICE) expert_advice,/*ר�ҽ���*/")
    .append("        a.BUSI_CLASS_NAME, /*ҵ�����*/ ")
    .append("        a.BUSI_MODULE_NAME module_code, /*ģ��*/ ")
    .append("        a.BUSI_CLASS_ID, ") //��ne_alarm_list�������ֶ�
    .append("        a.CONFIG_NE_ID,")//ne_alarm_list�е�CONFIG_NE_ID�ֶΣ�����������ALL_MODULE_TAB���ж�Ӧ
    .append("        a.PRO_PHENO /*��������*/,a.PRO_REASON /*����ԭ��*/,a.MEASURES,  /*��ȡ��ʩ*/ ")
    .append("        a.alarm_level alarm_level_id /*�澯����*/,a.oprt_state oprt_state_id, /*����״̬*/ a.alarm_state alarm_state_id ,/*�澯״̬*/")
    .append("        a.MATCH_RULES, /*��������*/ ")
    .append("        a.alarm_class alarm_class_id, /*�澯���*/ ")
    .append("		 a.BUSI_MODULE_NAME ne_tch_name, /*��������*/")
    .append("		 a.AFTERMATH, /*��ɽ��*/")
    .append("        (select staff_name from staff where staff_id=a.suspend_operator) suspend_operator, /*������*/")
    .append("        to_char(a.suspend_time,'yyyy-mm-dd hh24:mi:ss') suspend_time,/*����ʱ��*/")
    .append("        (SELECT DECODE(SUBSTR(a.NE_TYPE_ID, 0, 2),'30',(SELECT TDL.LIST_LABEL FROM TP_DOMAIN_LISTVALUES TDL " +
    		"		 WHERE TDL.LIST_VALUE = a.ALARM_TYPE and TDL.domain_code = 'DOMAIN_NE_ALARM_TYPE' and rownum=1)," +
    		"		(SELECT PKP_NE_INTERFACE.GETSINNEOPERNAMEBYNEID(a.NE_ID) FROM DUAL)) BUSS_NAME FROM dual) BUSS_NAME /*����ϵͳ*/ ")
    .append("        ,DECODE(a.ALARM_VALIDITY,'0SA','��Ч','��Ч') ALARM_VALIDITY,/*�澯�Ƿ���Ч*/(SELECT MEAN FROM CODELIST WHERE CODE_TYPE = 'ALAER_INVALID_REASON' AND CODE = A.INVALID_REASON_CODE) INVALID_REASON_CODE /*��Чԭ��*/")
    .append("   from ne_alarm_list a, ")
    .append("        MANAGE_REGION b1, ")
    .append("        MANAGE_REGION b2, ")
    .append("        kpi_code_list c, ")
    //.append("        net_element d, ")
    //.append("        ci_base_element d,")
    .append("        (select * ")
    .append("           from tp_domain_listvalues ")
    .append("          where domain_code = 'DOMAIN_NE_ALARM_ORIGIN') e1, ")
    .append("        (select * ")
    .append("           from tp_domain_listvalues ")
    .append("          where domain_code = 'DOMAIN_NE_ALARM_TYPE') e2, ")
    .append("        (select * ")
    .append("           from tp_domain_listvalues ")
    .append("          where domain_code = 'DOMAIN_NE_ALARM_LEVEL') e3, ")
    .append("        (select * ")
    .append("           from tp_domain_listvalues ")
    .append("          where domain_code = 'DOMAIN_NE_ALARM_TYPE') e4, ")
    .append("        (select * ")
    .append("           from tp_domain_listvalues ")
    .append("          where domain_code = 'DOMAIN_NE_ALARM_LEVEL') e5, ")
    .append("        (select * ")
    .append("           from tp_domain_listvalues ")
    .append("          where domain_code = 'DOMAIN_ALARM_STATE') e6, ")
    .append("        (select * ")
    .append("           from tp_domain_listvalues ")
    .append("          where domain_code = 'DOMAIN_DR_ID_FLAG') e7, ")
    .append("        (select * ")
    .append("           from tp_domain_listvalues ")
    .append("          where domain_code = 'DOMAIN_ALARM_OPRT_STATE') e8, ")
    .append("        staff f1, ")
    .append("        staff f2, ")
    .append("        staff f4, ")
    //.append("        ne_type g, ")
    //.append("        ci_class_tree g,")
    .append("        (select * from codelist where code_type = 'ALARM_CLASS') h ")
    //.append("        kpi_mapping_cfg i " )
    .append("  where a.alarm_region_origin = b1.region_id(+) ")
    .append("    and a.region_id = b2.region_id(+) ")
    .append("    and a.kpi_id = c.kpi_id(+) ")
    //.append("    and a.ne_id = d.instance_id(+) ")
    .append("    and a.alarm_origin = e1.list_value(+) ")
    .append("    and a.alarm_type = e2.list_value(+) ")
    .append("    and a.alarm_level = e3.list_value(+) ")
    .append("    and a.ori_alarm_type = e4.list_value(+) ")
    .append("    and a.ori_alarm_level = e5.list_value(+) ")
    .append("    and a.oprt_state = e8.list_value(+) ")
    .append("    and a.dr_id||'' = e7.list_value(+) ")
    .append("    and a.ALARM_STATE = e6.list_value(+) ")
    .append("    and a.confirm_operator = f1.staff_id(+) ")
    .append("    and a.clear_operator = f2.staff_id(+) ")
    .append("    and a.delete_operator = f4.staff_id(+) ")
    //.append("    and a.ne_type_id = g.class_id(+) ")
    .append("    and a.alarm_class = h.code(+) ")
    //.append("    and a.kpi_id = i.kpi_id(+) ")
    .append("   and a.ne_alarm_list_id = "+alarmId);
	    
    System.out.println(aa);
	    
    
    StringBuffer fieldss = new StringBuffer();
    fieldss.append("select a.ne_alarm_msg_id id, ")
    .append("        to_char(a.receive_date, 'yyyy-mm-dd hh24:mi:ss') receive_date, /*����ʱ��*/ ")     
    .append("        b.list_label alarm_type, /*�澯����*/ ")
    .append("        PKP_CI_VIEW.getCIName(a.ne_id) ne_name, /*��Ԫ*/ ")
    .append("        d.kpi_name, /*KPI*/ ")
    .append("        a.kpi_value, /*KPIֵ*/ ")
    .append("        '<span style=\"color:black;background:' || ")
    .append("        decode(a.alarm_Level, ")
    .append("               '1', ")
    .append("               'red', ")
    .append("               '2', ")
    .append("               'orange', ")
    .append("               '3', ")
    .append("               'yellow', ")
    .append("               'green') || '\">' || ")
    .append("        decode(a.alarm_Level, '1', '����', '2', '��Ҫ', '3', 'һ��', 'δ֪') || ")
    .append("        '</span>' alarm_Level, ")
    .append("        to_char(a.generate_date, 'yyyy-mm-dd hh24:mi:ss') generate_date, /*����ʱ��*/ ")
    .append("        a.alarm_times /*����*/ ,")
    .append("        '<div title=\"'||PKP_STRING_UTIL.replaceDoubleToSingle(a.MSG_REMARK)||'\">'||PKP_STRING_UTIL.cutOutString(a.MSG_REMARK,200)||'</div>' as MSG_REMARK /*��Ϣ��ע*/ ");
    //��ѯ�ı�Ͳ�ѯ����
    StringBuffer fromWhere =  new StringBuffer();
    fromWhere.append("   from ne_alarm_msg a, ")
    .append("        (select * ")
    .append("           from TP_DOMAIN_LISTVALUES ")
    .append("          where domain_code = 'DOMAIN_NE_ALARM_TYPE') b, ")
    //.append("        net_element c, ")
    //.append("        ci_base_element c, ")
    .append("        kpi_code_list d ")
    .append("  where a.alarm_type = b.list_value ")
    //.append("    and a.ne_id = c.instance_id ")
    .append("    and a.kpi_id = d.kpi_id ")
    .append("    and a.ALARM_CLASS not in (3,4,5)")//�ų���ԭʼ�澯��Ϣ
    .append("    and a.merge_alarm_msg_id in ("+alarmId+")");
    
    System.out.println(fieldss);
    
    StringBuffer fromWhere3 =  new StringBuffer();
    fromWhere3.append("   from ne_alarm_msg a, ")
    .append("        (select * ")
    .append("           from TP_DOMAIN_LISTVALUES ")
    .append("          where domain_code = 'DOMAIN_NE_ALARM_TYPE') b, ")
    //.append("        net_element c, ")
    //.append("        ci_base_element c, ")
    .append("        kpi_code_list d ")
    .append("  where a.alarm_type = b.list_value ")
    //.append("    and a.ne_id = c.instance_id ")
    .append("    and a.kpi_id = d.kpi_id ")
    .append("    and a.ALARM_CLASS not in (3,4,5)")//�ų���ԭʼ�澯��Ϣ
    .append("    and a.merge_alarm_msg_id in ("+alarmId+")");
    
    //���ղ�ѯSQL
    StringBuffer sql3 = new StringBuffer();
    sql3.append(fieldss).append(fromWhere);
    System.out.println(sql3);
    
    StringBuffer sql4 = new StringBuffer();
    sql4
    .append(" select a.ne_alarm_msg_id, /*�澯��ϢID*/ ")
    .append("        c.kpi_name, /*KPI����*/ ")
    .append("        PKP_CI_VIEW.getCIName(a.ne_id) ne_name, /*��Ԫ����*/ ")
    .append("        a.config_ne_id, /*��Ԫ������ID*/ ")
    .append("        a.config_ne_name, /*��Ԫ����������*/ ")
    .append("        e2.list_label alarm_type, /*�澯����*/ ")
    .append("        b.region_name, /*����*/ ")
    .append("        a.alarm_times, /*�澯����*/ ")
    .append("        e1.list_label ALARM_ORIGIN, /*�澯��Դ*/ ")
    .append("        decode(a.state, '0SA', '��Ч', '0SX', '��Ч', a.state) STATE, /*״̬*/ ")
    .append("        e3.list_label ALARM_LEVEL, /*�澯����*/ ")
    .append("        to_char(a.generate_date, 'yyyy-mm-dd hh24:mi:ss') generate_date, /*����ʱ��*/ ")
    .append("        to_char(a.receive_date, 'yyyy-mm-dd hh24:mi:ss') receive_date, /*����ʱ��*/ ")
    .append("        to_char(a.dispose_date, 'yyyy-mm-dd hh24:mi:ss') dispose_date, /*����ʱ��*/ ")
    .append("        a.msg_remark, /*��Ϣ��ע*/ ")
    .append("        a.merge_reason /*�ϲ�ԭ��*/ ")
    .append("   from ne_alarm_msg a, ")
    .append("        MANAGE_REGION b, ")
    .append("        kpi_code_list c, ")
    //.append("        net_element d, ")
    //.append("        ci_base_element d, ")
    .append("        (select * ")
    .append("           from tp_domain_listvalues ")
    .append("          where domain_code = 'DOMAIN_NE_ALARM_TYPE') e2, ")
    .append("        (select * ")
    .append("           from tp_domain_listvalues ")
    .append("          where domain_code = 'DOMAIN_NE_ALARM_LEVEL') e3, ")
    .append("        (select * ")
    .append("           from tp_domain_listvalues ")
    .append("          where domain_code = 'DOMAIN_NE_ALARM_ORIGIN') e1 ")
    .append("  where a.region_id = b.region_id(+) ")
    .append("    and a.kpi_id = c.kpi_id(+) ")
    //.append("    and a.ne_id = d.instance_id(+) ")
    .append("    and a.alarm_type = e2.list_value(+) ")
    .append("    and a.alarm_level = e3.list_value(+) ")
    .append("    and a.alarm_origin = e1.list_value(+) ")
    .append("    and a.region_id = b.region_id(+) ")
    .append("    and a.kpi_id = c.kpi_id(+) ")
    .append("    and a.ne_alarm_msg_id in ()");
    System.out.println(sql4);
    
    String sql5 = "SELECT A.NE_ALARM_LIST_ID ID,A.FLOW_ID,/*����ID*/ A.ALARM_STATE ALARMSTATE,/*�澯״̬*/" +
    		"A.OPRT_STATE OPRTSTATE,/*����״̬*/ A.ALARM_LEVEL ALARMLEVEL,/*�澯����*/ A.PERF_MSG_ID,/*������ϢID*/ " +
    		"A.ALARM_CLASS ALARMCLASS,/*�澯���*/ B1.LIST_LABEL ALARM_TYPE,/*�澯����*/ PKP_CI_VIEW.getCIName(a.ne_id) ne_name,/*��Ԫ*/ " +
    		//"D.KPI_NAME,/*KPI*/ A.KPI_VALUE,/*KPIֵ*/ DECODE(C1.NE_FLAG,6,E.REGION_NAME,C2.NE_NAME) DATASOURCE,/*������/������Դ?*/ " +
    		"D.KPI_NAME,/*KPI*/ A.KPI_VALUE,/*KPIֵ*/ DECODE(PKP_CI_VIEW.isBusiCI(a.ne_id),1,E.REGION_NAME,PKP_CI_VIEW.getCIName(a.ne_id)) DATASOURCE,/*������/������Դ?*/ " +
    		"'<span style=\"color:black;background:' || DECODE(A.ALARM_LEVEL,'1','red','2','orange','3','yellow','green') || '\">' || " +
    		"DECODE(A.ALARM_LEVEL,'1','����','2','��Ҫ','3','һ��','δ֪') || '</span>' ALARM_LEVEL,TO_CHAR(A.GENERATE_TIME,'yyyy-mm-dd hh24:mi:ss') GENERATE_TIME,/*����ʱ��*/ " +
    		"TO_CHAR(A.LAST_GENERATE_TIME,'yyyy-mm-dd hh24:mi:ss') LAST_GENERATE_TIME,/*���ʱ��*/ A.ALARM_TIMES,/*����*/ DECODE(A.OPRT_STATE,'20'," +
    		"TO_CHAR(A.CONFIRM_TIME,'yyyy-mm-dd hh24:mi:ss'),'30',TO_CHAR(A.CLEAR_TIME,'yyyy-mm-dd hh24:mi:ss'),'40',TO_CHAR(A.DELETE_TIME,'yyyy-mm-dd hh24:mi:ss'))" +
    		" EXECUTETIME,/*����ʱ��*/ B2.LIST_LABEL ALARM_STATE /*�澯״̬*/,B3.LIST_LABEL OPRT_STATE /*�澯״̬*/ FROM NE_ALARM_LIST A," +
    		"(SELECT * FROM TP_DOMAIN_LISTVALUES WHERE DOMAIN_CODE = 'DOMAIN_NE_ALARM_TYPE') B1,(SELECT * FROM TP_DOMAIN_LISTVALUES " +
    		"WHERE DOMAIN_CODE = 'DOMAIN_ALARM_STATE') B2,(SELECT * FROM TP_DOMAIN_LISTVALUES WHERE DOMAIN_CODE = 'DOMAIN_ALARM_OPRT_STATE') " +
    		"B3,KPI_CODE_LIST D,MANAGE_REGION E,ALARM_AUDIO_KPI_CFG F WHERE B1.LIST_VALUE = A.ALARM_TYPE AND " +
    		"A.KPI_ID = F.KPI_ID AND A.NE_ID = C1.instance_id AND B2.LIST_VALUE = A.ALARM_STATE AND B3.LIST_VALUE=A.OPRT_STATE " +
    		"AND A.KPI_ID = D.KPI_ID AND NVL(A.ALARM_REGION_ORIGIN,'-1') = TO_CHAR(E.REGION_ID(+)) AND A.ALARM_STATE=0 AND A.OPRT_STATE=10";
    System.out.println(sql5);
    
    String sql6 = " select t.ne_task_config_id,e.script_type,n.ne_id "+
			" from ne_alarm_list n, kpi_code_list k, exec_script e, ne_task_config t "+
			"where n.kpi_id = k.kpi_id "+
			"and k.exec_script_id = e.exec_script_id "+
			"and t.exec_script_id = e.exec_script_id "+
			"and (t.ne_id = n.ne_id or t.agent_id = n.ne_id) "+
			"and t.state = '0SA' and n.ne_alarm_list_id = ?";
    
    System.out.println(sql6);
    
	String sql7 = "select t.ne_alarm_list_id," 
		     + " t.ALARM_TITLE TITLE," 
		     + " k.Kpi_Name KpiName, " 
		     + " t.kpi_value kpi_value, "  
			 + " t.EXPERT_ADVICE ADVICE, "	// --ר�ҽ���
		     + " t.PRO_PHENO,  "         	//--��������
		     + " t.PRO_REASON, "   		  	//--����ԭ��
		     + " t.AFTERMATH, "    		 	//--��ɺ��
		     + " t.MEASURES,  "   		  	//--��ȡ��ʩ
		     + " t.OPRT_RESULT "     		//--�����
		     + " from ne_alarm_list t, ne_kpi k "
		     + "where t.kpi_id = k.kpi_id and t.ne_alarm_list_id = ?";
	System.out.println(sql7);
	
	String sql8 = "(SELECT " +
			"'NO_BUSI' BUSI_TYPE,"+
			"T.TACHE_ID || '' TACHE_ID," +
			"T.NE_ID || '' NE_ID," +
			"(SELECT short_description NE_NAME FROM ci_base_element WHERE NE_ID = T.NE_ID) NE_NAME," +
			"T.NE_MSG_TYPE," +
			"T.KPI_ID || '' KPI_ID," +
			"(SELECT KPI_NAME FROM KPI_CODE_LIST WHERE KPI_ID = T.KPI_ID) KPI_NAME " +
			"FROM CUST_GD_ALARM_ITOP_CFG T WHERE T.P_REGION_ID = (SELECT REGION_DESC FROM MANAGE_REGION WHERE REGION_ID = ?))";		
	sql8 += " UNION ";
	//ҵ��ϵͳ�ĸ澯
	sql8 += "(SELECT DISTINCT " +
		   "'BUSI' BUSI_TYPE,"+
		   "IR.TACHE_ID || '' TACHE_ID,"+
		   "LAR.NE_ID || '' NE_ID, " +
	       "(SELECT short_description NE_NAME FROM ci_base_element WHERE NE_ID = LAR.NE_ID) NE_NAME," +
	       "'20' NE_MSG_TYPE," +
		   "AF.KPI_ID || '' KPI_ID,"+
		   "(SELECT KPI_NAME FROM KPI_CODE_LIST WHERE KPI_ID = AF.KPI_ID) KPI_NAME " +
	       " FROM ITOP_RELATIONSHIP IR,"+
	       "       LOGIC_ANALYZE_RULE LAR,"+
	       "       AUDIT_FORMULA AF"+
	       " WHERE LAR.LOGIC_ANALYZE_RULE_ID = AF.LOGIC_ANALYZE_RULE_ID"+
	       "   AND IR.LOGIC_ANALYZE_RULE_ID = LAR.LOGIC_ANALYZE_RULE_ID"+
	       "   AND IR.REGION_ID = ?"+
	       "   AND IR.TACHE_ID = ?)";
	System.out.println(sql8);
	
    StringBuffer almSql = new StringBuffer(
            "select * from (select nalid.*,ROWNUM R from (");
    almSql.append(" select a.ne_alarm_list_id sid,");
    almSql.append("a.kpi_id,");
    almSql.append("a.flow_id,");
    almSql.append("c1.INSTANCE_ID,");
    almSql.append("mar.region_name,");
    almSql.append("a.oprt_state oprtState,");
    almSql.append("a.alarm_state alarmState,");
    almSql.append("a.alarm_Level alarmLevel,");
    almSql.append("a.perf_msg_id,");
    almSql.append("a.alarm_class alarmClass,");
    almSql
            .append("to_char(a.generate_time, 'yyyy-mm-dd') generatedate, ");
    almSql
            .append("to_char(a.last_generate_time, 'yyyy-mm-dd') lastdate, ");
    almSql
            .append("decode(b3.list_value, '0', '', b3.list_label) dr_name,");
    almSql.append("b1.list_label alarm_type,");
    almSql.append("c1.SHORT_DESCRIPTION,");
    //almSql.append("decode(c1.ne_flag, 6, e.region_name, c2.ne_name) e.region_name,");
    almSql.append("e.region_name e.region_name,");
    almSql.append("a.kpi_value,");
    //almSql.append("PKP_COMMON.getMouleCode(f.ne_id, null, f.module_code) module_code,");
    almSql.append("a.CONFIG_NE_ID module_code,");
    almSql.append("d.kpi_name,");
    almSql
            .append("to_char(a.generate_time, 'yyyy-mm-dd hh24:mi:ss') generate_time,");
    almSql
            .append("to_char(a.last_generate_time, 'yyyy-mm-dd hh24:mi:ss') last_generate_time,");
    almSql.append("a.alarm_times,");
    almSql.append("b4.list_label oprt_state,");
    almSql.append("b2.list_label alarm_state,");
    almSql
            .append("pkp_flow_function.getFlowCurStaff(a.flow_id) flow_operator,");
    almSql
            .append(" decode(a.OPRT_STATE,'20',to_char(a.CONFIRM_TIME, 'yyyy-mm-dd hh24:mi:ss'),");
    almSql
            .append(" '25', to_char(a.SUSPEND_TIME, 'yyyy-mm-dd hh24:mi:ss'), '30', to_char(a.CLEAR_TIME, 'yyyy-mm-dd hh24:mi:ss'),");
    almSql
            .append(" '40',to_char(a.DELETE_TIME, 'yyyy-mm-dd hh24:mi:ss')) executeTim ");
    almSql.append(" from ne_alarm_list a,");
    almSql
            .append("(select * from TP_DOMAIN_LISTVALUES where domain_code = 'DOMAIN_NE_ALARM_TYPE') b1,");
    almSql
            .append("(select * from tp_domain_listvalues where domain_code = 'DOMAIN_ALARM_STATE') b2,");
    almSql
            .append("(select * from tp_domain_listvalues where domain_code = 'DOMAIN_DR_ID_FLAG') b3,");
    almSql
            .append("(select * from tp_domain_listvalues where domain_code = 'DOMAIN_ALARM_OPRT_STATE') b4,");
    almSql.append("CI_BASE_ELEMENT c1,");
    //almSql.append("CI_BASE_ELEMENT c2,");
    almSql.append("kpi_code_list d,");
    almSql.append("manage_region e,");
    //almSql.append("kpi_mapping_cfg f,");
    //almSql.append("ne_trans_alarm nta,");
    //almSql.append("PERF_ALARM_KPI pak,");
    almSql.append("manage_region  mar");
    almSql.append(" where b1.list_value = a.alarm_Type");
    almSql.append(" and a.ne_id = c1.INSTANCE_ID");
    almSql.append(" and b2.list_value = a.alarm_state");
    almSql.append(" and b3.list_value = a.dr_id || ''");
    almSql.append(" and b4.list_value = a.oprt_state");
    //almSql.append(" and a.config_ne_id = c2.INSTANCE_ID(+)");
    almSql.append(" and a.kpi_id = d.kpi_id");
    //almSql.append(" and a.kpi_id = f.kpi_id(+)");
    //almSql.append(" and pak.alarm_kpi_id=a.kpi_id ");
    almSql.append(" and a.alarm_region_origin=mar.region_id(+)");
    almSql.append(" and nvl(a.ALARM_REGION_ORIGIN, '-1') =");
    almSql.append(" to_char(e.region_id(+))");
    //almSql.append(" and PKP_TREE_PRIVILEGE.HASPRIVILEGE('NET_ELEMENT',NVL(a.CONFIG_NE_ID, a.NE_ID), 1, 'READ') = 1");
    almSql.append(" and PKP_TREE_PRIVILEGE.HASPRIVILEGE('NET_ELEMENT',a.NE_ID, 1, 'READ') = 1");
    //almSql.append(" and a.ne_alarm_list_id = nta.ne_alarm_list_id(+)");
    almSql.append(" and c1.markasdeleted='0'");
    //almSql.append(" and pak.alarm_kpi_id=to_number(?)");
    almSql.append(" and a.ne_id=to_number(?)");
    almSql.append(" order by b2.sort_id,");
    almSql.append(" b4.sort_id,");
    almSql.append(" a.alarm_type,");
    almSql.append(" a.ne_id,");
    almSql.append(" nvl(a.last_send_time, a.create_Time) desc");
    almSql.append("  )  nalid  WHERE ROWNUM < ?) E WHERE R >=? ");
    // almSql.append(" inner join PERF_ALARM_KPI pak on pak.alarm_kpi_id=nalid.kpi_id ");

    StringBuffer countnSql = new StringBuffer(
            "select count(a.ne_alarm_list_id) ");
    countnSql.append(" from ne_alarm_list a,");
    countnSql
            .append("(select * from TP_DOMAIN_LISTVALUES where domain_code = 'DOMAIN_NE_ALARM_TYPE') b1,");
    countnSql
            .append("(select * from tp_domain_listvalues where domain_code = 'DOMAIN_ALARM_STATE') b2,");
    countnSql
            .append("(select * from tp_domain_listvalues where domain_code = 'DOMAIN_DR_ID_FLAG') b3,");
    countnSql
            .append("(select * from tp_domain_listvalues where domain_code = 'DOMAIN_ALARM_OPRT_STATE') b4,");
    countnSql.append("CI_BASE_ELEMENT c1,");
    //countnSql.append("net_element c2,");
    countnSql.append("kpi_code_list d,");
    countnSql.append("manage_region e,");
    //countnSql.append("kpi_mapping_cfg f,");
    //countnSql.append("ne_trans_alarm nta,");
    //countnSql.append("PERF_ALARM_KPI pak");
    countnSql.append(" where b1.list_value = a.alarm_Type");
    countnSql.append(" and a.ne_id = c1.INSTANCE_ID");
    countnSql.append(" and b2.list_value = a.alarm_state");
    countnSql.append(" and b3.list_value = a.dr_id || ''");
    countnSql.append(" and b4.list_value = a.oprt_state");
    //countnSql.append(" and a.config_ne_id = c2.ne_id(+)");
    countnSql.append(" and a.kpi_id = d.kpi_id");
    //countnSql.append(" and a.kpi_id = f.kpi_id(+)");
    //countnSql.append(" and pak.alarm_kpi_id=a.kpi_id ");
    countnSql.append(" and nvl(a.ALARM_REGION_ORIGIN, '-1') =");
    countnSql.append(" to_char(e.region_id(+))");
    countnSql
            .append(" and PKP_TREE_PRIVILEGE.HASPRIVILEGE('NET_ELEMENT',a.NE_ID, 1, 'READ') = 1");
    //countnSql.append(" and a.ne_alarm_list_id = nta.ne_alarm_list_id(+)");
    //countnSql.append(" and c2.state='0SA'");
    almSql.append(" and c1.markasdeleted='0'");
    //countnSql.append(" and pak.alarm_kpi_id=to_number(?)");
    countnSql.append(" and a.ne_id=to_number(?)");
    String ab = "a,c,dfd,gggtg,dw,e";
    String[] str=ab.split(","); 
    if(str.length > 1) {
    	System.out.println(1);
    }
    System.out.println(str[0]);
	}
}
