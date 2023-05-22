package service;

import com.alibaba.fastjson.JSON;
import com.demo.boot.api.enums.LimitTypeEnum;
import com.demo.boot.api.exception.ApiCommonException;
import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.api.vo.response.QueryLogResponse;
import com.demo.boot.core.BaseTest;
import com.demo.boot.core.client.MethodExecClient;
import com.demo.boot.core.client.RequestCounter;
import com.demo.boot.core.dao.source1.entity.TblPeReportManageLog;
import com.demo.boot.core.dao.source1.mapper.TblPeReportManageLogMapper;
import com.demo.boot.core.service.TestService;
import com.demo.boot.core.util.SpringContextUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ServiceTest extends BaseTest {

    @Resource
    private TestService testService;
    @Resource
    private TblPeReportManageLogMapper manageLogMapper;

    @Resource
    private SpringContextUtil applicationContext;

    @Resource
    private List<RequestCounter> requestCounters;

    @Test
    public void queryLog(){
        PageInfo<TblPeReportManageLog> pageInfo = testService.queryLog(new QueryLogRequest());
        System.out.println(pageInfo);
    }

    @Test
    public void testMapper(){
        List<QueryLogResponse> responses = testService.queryCusReportLog(new QueryLogRequest()
                .setBusId(545L)
                .setStatus("11,16"));
        System.out.println(responses);
    }

    @Test
    public void testPa(){
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", "sob5187");
        map.put("pe_report_manage_status", "6");
        List<TblPeReportManageLog> list = manageLogMapper.selectByMap(map);
        System.out.println(list);
    }

    @Test
    public void testAbstract() throws ApiCommonException {
        MethodExecClient execClient = (MethodExecClient) applicationContext.getBean("adaMethodExecClient");
        Map<String,Object> targetService = new HashMap<>();
        targetService.put("a","aService");
        Map<String,Object> method = new HashMap<>();
        method.put("aM","method");
        Map<String,Object> methodExec = new HashMap<>();
        methodExec.put("aME","methodExec");
        Map<String, Object> response = execClient.exec(targetService, method, methodExec);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void testADBAbstract() throws ApiCommonException {
        MethodExecClient execClient = (MethodExecClient) applicationContext.getBean("adbMethodExecClient",MethodExecClient.class);
        Map<String,Object> targetService = new HashMap<>();
        targetService.put("b","bService");
        targetService.put("type","bat");
        Map<String,Object> method = new HashMap<>();
        method.put("bM","method");
        Map<String,Object> methodExec = new HashMap<>();
        methodExec.put("bME","methodExec");
        Map<String, Object> response = execClient.exec(targetService, method, methodExec);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void testRequestCounter(){
//        System.out.println(requestCounterManager.getRequestCounter(LimitTypeEnum.DAY.getDesc()).count());
        requestCounters.stream().filter(o -> o.count() == LimitTypeEnum.DAY.getCode()).findFirst().ifPresent(i -> System.out.println(i.buildParam()));
    }
}
