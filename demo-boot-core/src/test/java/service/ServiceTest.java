package service;

import com.alibaba.fastjson.JSON;
import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.api.vo.response.QueryLogResponse;
import com.demo.boot.core.BaseTest;
import com.demo.boot.core.client.MethodExecClient;
import com.demo.boot.core.dao.entity.TblPeReportManageLog;
import com.demo.boot.core.dao.mapper.TblPeReportManageLogMapper;
import com.demo.boot.core.service.TestService;
import com.demo.boot.core.util.SpringContextUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.entity.Example;

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

    @Test
    public void queryLog(){
        PageInfo<TblPeReportManageLog> pageInfo = testService.queryLog(new QueryLogRequest());
        System.out.println(pageInfo);
    }

    @Test
    public void testMapper(){
        List<QueryLogResponse> responses = testService.queryCustReportLog(new QueryLogRequest()
                .setBusId(545L)
                .setStatus("11,16"));
        System.out.println(responses);
    }

    @Test
    public void testPa(){
        Example example = new Example(TblPeReportManageLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", "sob5187");
        criteria.andEqualTo("peReportManageStatus", "6");
        List<TblPeReportManageLog> list = manageLogMapper.selectByCondition(example);
        System.out.println(list);
    }

    @Test
    public void testAbstract() throws Exception {
        MethodExecClient execClient = (MethodExecClient) applicationContext.getBen("aMethodExecClient",MethodExecClient.class);
        Map<String,Object> targetService = new HashMap<>();
        targetService.put("a","aService");
        Map<String,Object> method = new HashMap<>();
        method.put("a","method");
        Map<String,Object> methodExec = new HashMap<>();
        methodExec.put("a","methodExec");
        Map<String, Object> response = execClient.exec(targetService, method, methodExec);
        System.out.println(JSON.toJSONString(response));
    }
}
